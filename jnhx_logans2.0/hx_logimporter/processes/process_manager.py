import multiprocessing
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

    def runRule(self, mainEngine, wherecond, ipc, isfirst):
        # 执行一个规则
        for ana_object in mainEngine.import_tree['ana_objects']:
            logger.warning(f'运行分析对象{ana_object["object_name"]} {ana_object["porject_analysis_id"]}')
            if not mysql_opt.query_status_by_id(qtype="object", id=ana_object["porject_analysis_id"]):
                logger.warning(mysql_opt.query_status_by_id(qtype="object", id=ana_object["porject_analysis_id"]))
                logger.warning(f'分析对象{ana_object["object_name"]}运行完毕 {ana_object["porject_analysis_id"]}')
                continue
            # 更新分析对象状态
            mysql_opt.update_status_by_id(utype="object",
                                          param={"status": 10, "id": ana_object["porject_analysis_id"]})
            fields = mysql_opt.query_fields_by_obj(ana_object["object_id"])
            tablename = f't_{ana_object["object_name"]}_source'
            logger.warning(f"创建表{tablename}")
            logger.warning(f"字段有{fields}")
            impala_opt.create_table(tablename, fields)
            # logger.warning(f"开始执行规则组{ana_object['rule_groups']['rule_group_name']}")
            params = {
                # "esIndex": esIndex,
                "filename_mdate": "",
                "whereCond": wherecond,
                "orders": public_func.genlistby(fields, "ename")
            }
            for rule_group in ana_object['rule_groups']:
                logger.warning(f"执行规则{rule_group['rule']['rule_name']}")
                # 当前一个规则对应一个规则组，所以查询规则和规则组是一样的，但是状态只更新规则
                if not mysql_opt.query_status_by_id(qtype="rule", id=rule_group['rule']['rule_id']):
                    logger.warning(f'规则{rule_group["rule"]["rule_name"]}运行完毕 {rule_group["rule"]["rule_id"]}')
                    continue
                logger.warning(f'正在运行规则{rule_group["rule"]["rule_name"]} {rule_group["rule"]["rule_id"]}')
                mysql_opt.update_status_by_id(utype="rule", param={"status": 10, "id": rule_group['rule']['rule_id']})
                partitions = {
                    # 这个信息在创建分区的时候用到，在补充dataframe的时候用到
                    # 'table_name': tablename,
                    'unit': mainEngine.import_tree["unit_name"],
                    'project': mainEngine.import_tree['pro_id'],
                    'ana_obj': ana_object['object_name'],
                    'rule_group': rule_group['rule_group_name'],
                    'rule': rule_group['rule']['rule_name'],
                    'ip_part': 0,
                    'username_part': 0
                }
                # 创建分区
                logger.warning("create partitions")

                impala_opt.create_partitions(tablename, mainEngine.import_tree["unit_name"],
                                             ana_object['object_name'], rule_group['rule_group_name'],
                                             mainEngine.import_tree['pro_id'])
                files = []
                for file_dir in rule_group['file_dirs']:
                    for file in file_dir['files']:
                        if not mysql_opt.query_status_by_id(qtype="file", id=file['id']):
                            logger.warning(f'文件{file["filename"]}已处理完毕，{file["id"]}')
                            continue
                        file["dir_id"] = file_dir["id"]
                        files.append(file)
                for file in files:
                    self.file_index += 1
                    if self.file_index > 20:
                        self.file_index = 0
                        self.stop()
                        self.start()
                    genflag.write_flag()
                    # if not mysql_opt.query_status_by_id(qtype="file",id=file["file_id"])
                    self.main_pool.apply_async(doWork.call_transall,
                                               args=[self.reduce_list, self.reduce_lock, rule_group['rule'], file,
                                                     file['dir_id'],
                                                     partitions, ipc, isfirst, params])
                self.stop()
                self.start()
                self.file_index = 0
                # 更新规则
                mysql_opt.update_status_by_id(utype="rule", param={"status": 100, "id": rule_group['rule']['rule_id']})
            mysql_opt.update_status_by_id(utype="object",
                                          param={"status": 100, "id": ana_object["porject_analysis_id"]})
        time.sleep(10)

    def main(self):
        """
        主执行函数
        """
        global refresh
        refresh = True
        logger.warning("开始加载基础数据")
        ipc = IpCity()
        ip_citys = pd.read_parquet(read_config.project_path + "/data/ip_city_results.parquet")
        # ip_citys = pd.read_csv(read_config.dir_path + "/ip_city_results.csv", header=None, sep=",",
        #                        names=["num", "start_ip", "end_ip", "qcc", "contry"], low_memory=False)
        geo_list = ip_citys.to_dict(orient="records")
        logger.warning("加载ip归属地列表完成")
        ipc.set_basedata(geo_list)
        del ip_citys
        while True:
            mainEngine = ReEngine("")
            # 从数据库刷新导入规则，测试阶段通过配置文件模拟,
            # 如果没有发现新的规则，则睡眠一会等待新任务
            has_result = mainEngine.refreshRoles()
            if not has_result:
                genflag.write_flag()
                time.sleep(10)
                continue
            logger.warning(f'发现新导入任务 {mainEngine.import_tree["pro_name"]} {mainEngine.import_tree["pro_id"]}')
            # 更改任务状态为正在运行
            mysql_opt.update_project_status(project_id=mainEngine.import_tree['pro_id'], status=1)
            self.init_pool()
            self.init_shard_data()
            # 编译规则
            mainEngine.build()
            # 获取筛选条件
            wherecond = mainEngine.getCond()
            logger.warning(f"开始执行优先条件 :")
            logger.warning(wherecond)
            # 如果有优先处理条件时，先处理一遍优先处理条件，然后再执行非优先处理条件的
            # 先进行优先处理，如果条件为空则优先处理即为全部处理，如果条件不为空则还需要进行后续处理
            self.runRule(mainEngine, wherecond, ipc, False)
            # self.runRule(mainEngine, wherecond, ipc, True)
            # if wherecond:
            #     logger.warning("优先处理完毕，开始处理剩下的")
            #     self.runRule(mainEngine, wherecond, ipc, False)
            self.stop()
            # 更改状态为结束
            logger.warning(f'{mainEngine.import_tree["pro_name"]} 运行完毕')
            mysql_opt.update_project_status(project_id=mainEngine.import_tree['pro_id'], status=100)
            genflag.write_flag()
            logger.warning("刷新缓存")
            country = f'http://localhost:8070/server/logana/forceRefreshCache?refresh=True'
            logger.warning(requests.get(country).text)
            mainEngine.error_list = []
            logger.warning("执行完毕")
