import sys
from dao.hdfs_opt import HDFSOpt
import pyarrow.parquet as pq
from config import read_config
import datetime
from tools.hx_logger import Logger

hdfs_conn = HDFSOpt()
chdfs = hdfs_conn.get_fs()
logger = Logger(__name__).get_log
logger.warning("parquet文件检测程序已开启...")

unit = "test_unit"
project = "ca132dbf-905a-4c53-b16d-b41c1cad0e25"
ana_object = "iis"
rule_group = "iis_csv_rg"
rule = "iis_csv_rule"
hdfs_path = f"/home/hxht_data/mlogs/unit={unit}/project={project}/ana_obj={ana_object}/rule_group={rule_group}/rule={rule}"
parquet_list = chdfs.ls(hdfs_path)
for i, one in enumerate(parquet_list):
    try:
        with chdfs.open(one) as content:
            print(f"正在打开第{i}个")
            pf = pq.ParquetFile(content)
            print(chdfs.info(one))
            logger.warning("检测" + one + "文件，未发现异常")
    except Exception as e:
        logger.warning(e)
        nfn = one.replace(one, "/home/rawdata_err_file/")
        logger.warning("发现异常文件" + one + "->" + nfn)
        # chdfs.mv(one, nfn)
        # logger.warning("移动完毕，移动后路径" + nfn)


chdfs.close()
