from django.http import JsonResponse
from impala.dbapi import connect
import os
from queryset.models import Offline


# conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
# cursor = conn.cursor()
# sql = "select city,country,get_size,mdate,opt,port,req_ip,req_ip2,req_method,response,result,send_size,server_ip,sourcelog,tail_ip,time,to_page,user_agent,username,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and rule='iis_csv_rule' and userename='liuxin8@cnooc.com.cn' limit 10 offset 10"
# sql = "select mdate,time,username,req_ip,country,city,sourcelog,get_size,opt,port,req_ip2,req_method,response,result,send_size,server_ip,tail_ip,to_page,user_agent,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and rule='iis_csv_rule'  order by 't_test_table' desc limit 100 offset 10"
# sql = "select city,country,get_size,mdate,opt,port,req_ip,req_ip2,req_method,response,result,send_size,server_ip,sourcelog,tail_ip,time,to_page,user_agent,username,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and username_part='0' and rule='iis_csv_rule'  order by 't_test_table' desc limit 10 offset 10"
# sql = "select mdate,time,username,req_ip,country,city,sourcelog,get_size,opt,port,req_ip2,req_method,response,result,send_size,server_ip,tail_ip,to_page,user_agent,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and rule='iis_csv_rule'  and username ='liuxin8@cnooc.com.cn' order by 't_test_table'   limit 2 offset 1"
# cursor.execute(sql)
# results = cursor.fetchall()
# print(results)
# cursor.close()
# conn.close()


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


def export(table, selectfields, partitions, condations, qtype="comment", export_file=""):
    """
    查询普通字段
    :return: 查询条件和结果
    """
    offline_names = Offline.objects.values_list("offline_name", flat=True)
    if export_file in offline_names:
        return {"status": "error", "message": "文件名重复"}
    mapper = {
        "req_ipcount": "req_ip",
        "unamecount": "username",
        "countrycount": "country",
        "citycount": "city"
    }

    if qtype in ["req_ipcount", "unamecount", "countrycount", "citycount"]:
        sql = f"select distinct {mapper[qtype]} from {table} where {' and '.join(partitions)} and {' and '.join(condations)} group by {qtype.replace('count', '')} order by '{table}' desc"
    else:
        sql = f"select {','.join(selectfields)} from {table} where {' and '.join(partitions)} and {' and '.join(condations)}"

    offline_sh_path = f"{os.getcwd()}/queryset/utils/offline.sh"
    pip_data_path = f"{os.getcwd()}/queryset/utils/pip_data.py"
    cmd = f'/usr/bin/bash {offline_sh_path} "{sql}" {export_file} {pip_data_path}'
    os.system(cmd)

    Offline.objects.create(
        offline_name=export_file,
        offline_status=0,
        filesize=0,
        download_file="/file/" + export_file
    )
    return {"status": "success", "message": os.getcwd()}


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


# : tmp_F638CAE51A5D42A5E30826075534A6B6 STORED as parquet as select * from hxht_maillog_db.t_iis_source where 1=1 and req_ip in ('60.25.59.246') and unit = 'test'  and rule_group = 'iis_txt_rg'

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

# table = 't_eyou_source'
# selectfields = ['*']
# partitions = ["unit='wang'", "ana_obj='eyou'", "rulegroup='eyou_auth'"]
# condations = ['1=1']
# a = query(table, selectfields, partitions, condations)
# print(a)
