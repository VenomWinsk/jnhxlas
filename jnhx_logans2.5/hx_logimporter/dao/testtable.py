from dao import impala_opt

#
# fileds = [{'ename': 'time', 'cname': '时间', 'ftype': 'string'}, {'ename': 'mdate', 'cname': '日期', 'ftype': 'string'},
#           {'ename': 'city', 'cname': '城市', 'ftype': 'string'}, {'ename': 'country', 'cname': '国家', 'ftype': 'string'},
#           {'ename': 'result', 'cname': '是否成功', 'ftype': 'string'}, {'ename': 'opt', 'cname': '类型', 'ftype': 'string'},
#           {'ename': 'source_log', 'cname': '原始日志', 'ftype': 'string'},
#           {'ename': 'req_ip', 'cname': '请求ip', 'ftype': 'string'},
#           {'ename': 'username', 'cname': '用户名', 'ftype': 'string'}]
#
# unit = 'kang0219'
# ana_obj = 'iis'
# rulegroup = 'iis_csv_rg'
# rule = 'iis_csv_rule'
# impala_opt.create_table("t_exchange_source", fileds)
#
# projetc_id = ['017da471-ae05-4a69-bd76-22a85d92df11',
#               '44e4d909-4650-4796-a35d-b9cea4797651']
# for i, project in enumerate(projetc_id):
#
#     impala_opt.create_partitions("t_iis_source", unit=unit, project=project, ana_obj=ana_obj, rulegroup=rulegroup,
#                                  rule=rule)
#     print(f"建立第{i}分区{project}")
import uuid
