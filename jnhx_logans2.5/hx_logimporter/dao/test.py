from dao.hdfs_opt import HDFSOpt
from impala.dbapi import connect


# conn = HDFSOpt()
# fs = conn.get_fs()
#
# path = "/home/hxht_data/mlogs/unit=sss/ana_obj=teyou/rulegroup=eyou_auth/project=pro_1"
#
# a= fs.exists(path)
# print(a)

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


sql = "show partitions t_eyou_source;"
r = queryimpala(sql)
partitions = []
for tmp in r:
    x = f"unit={tmp[0]}ana_obj={tmp[1]}rulegroup={tmp[2]}project={3}"
    print(x)