import multiprocessing
import os
import time
import requests
from re_engine.main_engine import ReEngine
import processes.doWork as doWork
import pandas as pd
from plugins.plugins_api import IpCity
from dao import mysql_opt
import dao.impala_opt as impala_opt
from tools.hx_logger import Logger
from tools import genflag, public_func
from config import read_config
from dao import impala_opt

logger = Logger(__name__).get_log
global refresh


# 进程管理器
class ProcessManager():
    main_pool = None
    ctx = None
    close_status = None
    done_status = None
    job_queue = None
    reduce_list = None
    reduce_lock = None
    file_index = 0

    def init_pool(self):
        """
        初始化进程池
        """
        self.ctx = multiprocessing.get_context(read_config.process_mode)
        self.main_pool = self.ctx.Pool(read_config.work_pool)

    def init_shard_data(self):
        """
        初始化共享对象
        """
        # pass
        self.reduce_list = self.ctx.Manager().list()
        self.reduce_list_tmp = self.ctx.Manager().list()
        self.reduce_lock = self.ctx.Manager().Lock()

    def reset_pool(self):
        self.main_pool.close()
        self.main_pool.join()
        self.init_pool()

    def reset_shard_data(self):
        """
        重置共享对象
        """
        self.close_status.value = 0
        self.done_status.value = 0

    def start(self):
        # 初始化线程池
        self.init_pool()
        # 初始化共享数据
        self.init_shard_data()

    def stop(self):
        self.main_pool.close()
        self.main_pool.join()

    def runRule(self, ipc, rulemapper, import_tree):
        # print(rulemapper.import_tree)
        # pro_id = rulemapper.import_tree['pro_id']
        # pro_name = rulemapper.import_tree['pro_name']
        # 接下来对文件处理

        # 分区为单位-分析对象-规则组-任务

        # todo 根据分析对象判断是否建表，如果没有则重新建表
        # todo 根据任务id查询是否建立好分区，如果没有建立则重新建立
        # 接下来遍历文件夹对每个文件夹处理
        for folder in import_tree['folders']:
            # 接下来对文件进行处理
            if mysql_opt.query_project_status(import_tree['pro_id']) == -10:
                if mysql_opt.query_project_status(import_tree["pro_id"]) in (-10):
                    print("任务已暂停，停止处理文件夹")
                    break
            print(f"处理文件夹--{folder['folderpath']}")
            for file in folder["files"]:
                # todo 处理文件前一定要先查询文件或任务状态
                if mysql_opt.query_project_status(import_tree["pro_id"]) == -10:
                    print("任务已暂停，停止处理文件")
                    break
                # 每每到一定文件数量就重启
                self.file_index += 1
                if self.file_index > 20:
                    self.file_index = 0
                    self.stop()
                    self.start()
                    self.file_index = 0
                # 每个文件运行结束后都要 genflag.write_flag()
                self.main_pool.apply_async(doWork.transAll,
                                           args=[ipc, rulemapper, file])

            self.stop()
            self.start()
            self.file_index = 0
        # 更新任务
        # mysql_opt.UpdateStatus('project', import_tree['pro_id'], 100)
        print(f"任务{import_tree['pro_name']}处理完毕")

    def main(self):
        """
        主执行函数
        """
        print("开始加载基础数据")
        print("正在检查基础表信息")
        impala_opt.create_table()
        mainEngine = ReEngine()
        ipc = IpCity()
        ip_citys = pd.read_parquet(read_config.project_path + "/data/ip_city_results.parquet")
        geo_list = ip_citys.to_dict(orient="records")
        print("加载ip归属地列表完成")
        ipc.set_basedata(geo_list)
        mainEngine.build()
        rulemapper = mainEngine.rulemapper
        print("加载分析规则完成")
        while True:
            import_tree = mysql_opt.get_import_tree()
            if not import_tree:
                genflag.write_flag()
                print("未发现任务，10秒钟后查询")
                time.sleep(10)
                continue
            print(f'发现新导入任务 {import_tree["pro_name"]} {import_tree["pro_id"]}')
            # 更改任务状态为正在运行
            self.init_pool()
            self.init_shard_data()
            print("开始处理任务")
            self.runRule(ipc, rulemapper, import_tree)
            self.stop()
            print("处理完毕")
            # 更改状态为结束
            impala_opt.create_partitions(import_tree["pro_id"])
            logger.warning(f'{import_tree["pro_name"]} 运行完毕')
            mysql_opt.UpdateStatus('project', import_tree["pro_id"], 100)
            # mysql_opt.update_project_status(project_id=mainEngine.import_tree['pro_id'], status=100)
            genflag.write_flag()
            # logger.warning("刷新缓存")
            # country = f'http://localhost:8070/server/logana/forceRefreshCache?refresh=True'
            # logger.warning(requests.get(country).text)
            mainEngine.error_list = []
            logger.warning("执行完毕")
