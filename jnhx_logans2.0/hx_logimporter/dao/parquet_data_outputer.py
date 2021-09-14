import pyarrow.parquet as pq
import pyarrow as pa
from config import read_config
from dao.hdfs_opt import HDFSOpt
from tools.hx_logger import Logger

logger = Logger(__name__).get_log
from dao import mysql_opt


def save(filepath, allchunk, file, chunk_count):
    """
    :param file_id:
    :param chunk_count:
    :param filepath: 文件路径
    :param allchunk: 待保存数据
    :return: 
    """
    try:
        logger.warning("进入保存阶段")
        if read_config.output_fs_type == "hdfs":
            conn = HDFSOpt()
            fs = conn.get_fs()
            adf = pa.Table.from_pandas(allchunk)
            size = len(allchunk)

            # 清除待保存数据
            del allchunk
            pq.write_to_dataset(adf, root_path=filepath,
                                partition_cols=["unit",  "ana_obj", "rule_group", "project"],
                                filesystem=fs)

            mysql_opt.update_file_by_id(ftype="data_length",
                                        param={"data_length": size, "file_id": file['id'], "chunk_count": chunk_count})
            logger.warning(f'{file["filename"]} 输出 {size}条数据>>hdfs_parquet: {filepath}')
            fs.close()

        else:
            adf = pa.Table.from_pandas(allchunk)
            size = len(allchunk)
            # 清除待保存数据
            del allchunk
            pq.write_to_dataset(adf, root_path=filepath,
                                partition_cols=["unit", "project", "ana_obj", "rule_group", 'ip_part',
                                                'username_part'],
                                filesystem=None)
            logger.warning("输出" + str(size) + "条数据>>fs_parquet:" + filepath)
        return True

    except Exception as e1:
        logger.warning("写入异常" + str(e1))
        return False


def output(save_pool, chdfs, save_pool_list, filename, allchunk):
    """
    多线程输出数据
    :param filename: 文件名
    :param allchunk: 待保存数据
    """
    logger.warning("开始保存文件" + filename)
    # 异步保存数据
    trd = save_pool.submit(save, chdfs, filename, allchunk)
    save_pool_list.append(trd)
