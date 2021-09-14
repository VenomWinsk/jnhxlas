import os
from config import read_config
from tools.hx_logger import Logger

logger = Logger(__name__).get_log


def create_partitions(tablename, unit, ana_obj, rule_group, project):
    tablename = tablename
    cmdstr = f"/usr/bin/bash {read_config.impala_create_partition_shell}  {tablename} {unit}  {ana_obj} {rule_group} {project} "
    logger.warning(cmdstr)
    s = os.popen(cmdstr).read()
    logger.warning(s)


def create_table(tablename, fields):
    strs = []
    for one in fields:
        strs.append(f"{one['ename']} {one['ftype']}")
    fieldstr = ",".join(strs)
    cmdstr = f'/usr/bin/bash {read_config.impala_create_table_shell} {tablename} "{fieldstr}" '
    logger.warning(cmdstr)
    s = os.popen(cmdstr).read()
    logger.warning(s)

# create_table("t_test_aa", [{"ename": "req_ip", "ftype": "string"}, {"ename": "username", "ftype": "string"}])

# create_partitions("t_test_aa","wd","coremail","imap")

# create_partitions('t_eyou_source','wang','eyou','eyou_auth','eyou_auth')