import pyarrow.parquet as pq
import pyarrow as pa
from config import read_config
from dao.hdfs_opt import HDFSOpt
from tools.hx_logger import Logger
logger = Logger(__name__).get_log
from dao import mysql_opt


def save(allchunk, file):
    """
    :param file_id:
    :param savedir: 文件路径
    :param allchunk: 待保存数据
    :return:
    todo 保存到hdfs多出一列 __index_level_0__ 但是没有影响最终查询，因为这一列在parquet最后一列
    """
    try:
        print("进入保存阶段")
        if read_config.output_fs_type == "hdfs":
            conn = HDFSOpt()
            fs = conn.get_fs()
            allchunk = pa.Table.from_pandas(allchunk)
            size = len(allchunk)
            savedir = read_config.hdfs_output_dir
            # 清除待保存数据
            print(f"保存数据的列有{allchunk.column_names}")
            pq.write_to_dataset(allchunk, root_path=savedir,
                                partition_cols=["unit", "ana_obj", "rulegroup", "project"],
                                filesystem=fs)
            print(f'{file["file_path"]} 输出 {size}条数据>>hdfs_parquet: {savedir}')
            fs.close()
            mysql_opt.UpdateFileProcess(file['file_id'], output_data=size)  # 更新文件处理

    except Exception as e1:
        logger.warning("写入异常" + str(e1))
        return False
    return True


def output(save_pool, save_pool_list, filename, allchunk):
    """
    多线程输出数据
    :param filename: 文件名
    :param allchunk: 待保存数据
    """
    logger.warning("开始保存文件" + filename)
    # 异步保存数据
    trd = save_pool.submit(save, filename, allchunk)
    save_pool_list.append(trd)
