import os
from config import read_config
from tools.hx_logger import Logger
from impala.dbapi import connect
from dao.hdfs_opt import HDFSOpt

from dao import mysql_opt

logger = Logger(__name__).get_log


def queryimpala(sql):
    """
    执行impala sql语句
    :param sql:
    :return:
    """
    conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
    cursor = conn.cursor()
    cursor.execute(sql)
    res = cursor.fetchall()
    cursor.close()
    conn.close()
    return res


def create_partitions(project_id):
    """
    创建分区 目前分区结构为 unit=unit_name/ana_obj=ana_obj_name/rulegroup=rulegroup_name/project=project_name
    :param project_id:
    :return:
    """
    # 首先该任务下理论上的所有分区
    partitions = mysql_opt.GetPartitionsFromPRM(project_id)
    # conn = HDFSOpt()
    # fs = conn.get_fs()
    sql = f"show partitions t_{partitions[0]['ana_obj']}_source;"
    partitions_existed = []  # 保存实际已有的分区
    res = queryimpala(sql)
    for tmp in res:
        if not tmp[1]:
            continue
        x = f"unit={tmp[0]}ana_obj={tmp[1]}rulegroup={tmp[2]}project={3}"
        partitions_existed.append(x)
    for part in partitions:
        tablename = f"t_{part['ana_obj']}_source"
        path = f"unit={part['unit']}ana_obj={part['ana_obj']}rulegroup={part['rulegroup']}project={part['project']}"
        if not path in partitions_existed:
            cmdstr = f"/usr/bin/bash {read_config.impala_create_partition_shell}  {tablename} {part['unit']}  {part['ana_obj']} {part['rulegroup']} {part['project']}"
            print(cmdstr)
            os.system(cmdstr)

def create_table():
    """
    创建表
    :param ana_name:
    :param fields:
    :return:
    """
    # 查看是否存在表
    # 如果存在则放弃
    ana_fields = mysql_opt.GetAnaFields()
    for res in ana_fields:
        ana_name = list(res.keys())[0]
        fields = res[ana_name]
        tablename = f't_{ana_name}_source'
        table_sql = "show tables"
        res = queryimpala(table_sql)
        tables = []
        for r in res:
            tables.append(r[0])
        if not tablename in tables:
            print(f"创建表{ana_name}")
            strs = []
            for one in fields:
                strs.append(f"{one} string")
            fieldstr = ",".join(strs)
            cmdstr = f'/usr/bin/bash {read_config.impala_create_table_shell} {tablename} "{fieldstr}" '
            logger.warning(cmdstr)
            s = os.popen(cmdstr).read()
            logger.warning(s)

# create_table()
# create_partitions(11)
