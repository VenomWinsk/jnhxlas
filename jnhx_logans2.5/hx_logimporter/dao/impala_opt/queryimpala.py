from django.http import JsonResponse
from impala.dbapi import connect
import os


def queryimpala(sql):
    conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
    cursor = conn.cursor()
    cursor.execute(sql)
    res = cursor.fetchall()
    cursor.close()
    conn.close()
    return res


"""
/usr/bin/impala-shell -q "select * from hxht_maillog_db.t_test_table where req_ip='222.77.253.68'" -B  --output_delimiter=',' --print_header | /usr/bin/python3 /opt/hxht/hx_logprocess/hx-log/pip_data.py /file/mm
"""

# table = 't_eyou_source'
# selectfields = ['*']
# partitions = ["unit='wang'", "ana_obj='eyou'", "rulegroup='eyou_auth'"]
# condations = ['1=1']
# a = query(table, selectfields, partitions, condations)
# print(a)
