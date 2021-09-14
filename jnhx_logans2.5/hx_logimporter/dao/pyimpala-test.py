import pandas as pd
from django.http import JsonResponse
from impala.dbapi import connect
import os


def query(table, selectfields, partitions, condations, offset, qtype="comment"):
    """
    查询普通字段
    :return: 查询条件和结果
    """
    mapper = {
        "req_ipcount": "req_ip",
        "unamecount": "username",
        "countrycount": "country",
        "citycount": "city"
    }
    if len(condations) == 0:
        condations.append("1=1")
    if qtype in ["req_ipcount", "unamecount", "countrycount", "citycount"]:
        selectfields = [mapper[qtype]]
        sql1 = f"select {mapper[qtype]} from {table} where {' and '.join(partitions)} and {' and '.join(condations)} group by {mapper[qtype]} order by '{table}' desc limit 10 offset {offset}"
        sql2 = f"select count(distinct {mapper[qtype]}) from {table} where {' and '.join(partitions)} and {' and '.join(condations)} group by {mapper[qtype]}"
        # sql2 = f"select order by '{table}' desc limit 10 offset {offset}"
    else:
        # sql1 = f"select {','.join(selectfields)} from {table} where {' and '.join(partitions)} and {' and '.join(condations)} order by '{table}' desc limit 10 offset {offset}"
        sql1 = f"select {','.join(selectfields)} from {table} where {' and '.join(partitions)} and {' and '.join(condations)} order by '{table}' desc limit 10 offset {offset}"
        sql2 = f"select count(*) from {table} where {' and '.join(partitions)} and {' and '.join(condations)}"
    print(sql1)

    conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
    cursor = conn.cursor()
    cursor.execute(sql1)
    results = cursor.fetchall()
    cursor.execute(sql2)
    count = cursor.fetchone()[0]
    cursor.close()
    conn.close()
    data = []
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": count, "data": data}
    return fin


def gen_cache(table, cache_tabel, selectfields, partitions, condations, qtyoe="comment"):
    """
    传入原始表名称，传入缓存表名称，传入所有字段，传入条件
    :param table:
    :param cache_tabel:
    :param selectfields:
    :param partitions:
    :param condations:
    :return:
    """
    sql = f"create table {cache_tabel} STORED as parquet as select {','.join(selectfields)} from {table} where {' and '.join(partitions)} and {' and '.join(condations)}"
    gencache_sh_path = f"{os.getcwd()}/queryset/utils/gencache.sh"
    cmd = f'/usr/bin/bash {gencache_sh_path} "{sql};"'
    print(cmd)
    os.system(cmd)
    # os.system()


# : tmp_F638CAE51A5D42A5E30826075534A6B6 STORED as parquet as select * from hxht_maillog_db.t_iis_source where 1=1 and req_ip in ('60.25.59.246') and unit = 'test'  and rulegroup = 'iis_txt_rg'

# sql = "select username from t_test_table where country!='中国' group by username order by 't_test_table' desc limit 2 offset 1"
# sql = "select country from t_test_table where country in (select country from t_test_table where req_ip is not null)"
# sql = "select username,req_ip,country from t_test_table where req_ip is not null limit 100"
# sql = "select mdate,time,username,req_ip,country,city,sourcelog,get_size,opt,port,req_ip2,req_method,response,result,send_size,server_ip,tail_ip,to_page,user_agent,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and req_ip='10.72.63.4' order by 't_test_table' desc limit 10 offset 0"
# sql = "select req_ip from t_test_table where req_ip='222.77.253.68' limit 10"

# sql = "select mdate,time,username,req_ip,country,city,sourcelog,get_size,opt,port,req_ip2,req_method,response,result,send_size,server_ip,tail_ip,to_page,user_agent,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from hxht_maillog_db.t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and req_ip='222.77.253.68' and area='in' and country='中国' order by 'hxht_maillog_db.t_test_table' desc limit 10 offset 0"
# conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
# cursor = conn.cursor()
# cursor.execute(sql)
# print(cursor.fetchall())
# cursor.close()
# conn.close()

"""
/usr/bin/impala-shell -q "select * from hxht_maillog_db.t_test_table where req_ip='222.77.253.68'" -B  --output_delimiter=',' --print_header | /usr/bin/python3 /opt/hxht/hx_logprocess/hx-log/pip_data.py /file/mm
"""


def connnect(sql):
    conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
    cursor = conn.cursor()
    cursor.execute(sql)
    results = cursor.fetchall()
    cursor.close()
    conn.close()
    return results


partitions = ["unit='wang'", "ana_obj='eyou'", "rulegroup='eyou_deliver_mail'"]
fields = ['mdate', 'time', 'username', 'req_ip', 'country', 'city']
sql = f"select {','.join(fields)} from t_eyou_source where {' and '.join(partitions)}  limit 20"
res = connnect(sql)
data = []

for r in res[-10:]:
    t = {}
    for i in range(len(fields)):
        t[fields[i]] = r[i]
    data.append(t)
import pandas as pd
df = pd.DataFrame(data)
pf = df[fields]
print(pf)
# table = 't_eyou_source'
# selectfields = ['auth_type', 'city', 'country', 'ctstr', 'del_type', 'dname', 'from_digest', 'from_email', 'mdate', 'module_type', 'req_ip', 'response', 'result', 'source_log', 'subject_digest', 'time', 'to_digest', 'to_size', 'uid', 'umod', 'url', 'useragent', 'username', 'unit', 'ana_obj', 'rulegroup', 'project']
# partitions = ["unit='wang'", "ana_obj='eyou'", "rulegroup='eyou_auth'"]
# condations = ['1=1']
# a = query(table, selectfields, partitions, condations, offset=0)
# print(a)
