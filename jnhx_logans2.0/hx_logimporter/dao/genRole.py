# from dao import mysql_opt
# import_tree = mysql_opt.query_import_tree_by_projectId('pro_1')
# logger.warning(import_tree)

# import_tree = {
#     "import_name": "武汉大学导入",
#     "unit": "武汉大学89",
#     "unit_code": "wd89",
#     "condition": "",
#     "ana_object": [{
#         "id": "ana_1",
#         "name": "core_mail",
#         "code": "core_mail",
#         "filedir": "/data/home/songcong/dev/logs/zhengzhoulogs/",
#         "rule_groups": {
#             "wms": {
#                 "id": "urg_1",
#                 "code": "wms",
#                 "filedir": "/alldata/mlogs/tang_1/WD/89/mail_log/",
#                 "rule_content": ".*?wmsvr\.log\.%{DATE>>date}$",
#                 "rules": [
#                     {
#                         "rule_name": "wms1",
#                         "condition": "login for DAV",
#                         "rule_content": "%{TIME>>time}\s%{LEVEL>>level}\s*?\[tid:%{DATA>>tid}\]...DAV\s%{DATA>>result},%{ALLDATA>>add_data}",
#                         "transform": "add_data:$splitParams(',','=')>>username,,req_ip,,req_url;req_ip:$getGeo>>geoip",
#                         "switch": "result==succeed>>result=success;result==failed>>result=fail",
#                         "rule_transed": "",
#                         "additions": {
#                             "opt": "login",
#                             "type": "HTTP"
#                         }
#                     },
#                     {
#                         "rule_name": "wms2",
#                         "condition": "user:login",
#                         # 23:59:43 INFO    [tid:4199,Q:W001ANDnBYfcpMbfthSa] [PHONE]func=user:login,remote=59.174.236.96,requestParameters={service:PHONE,locale:zh_CN,destURL:/coremail/xphone/main.jsp,uid:danielleguan,password:***,lang:zh_CN,'action:login':''},requestVar={returnMainURL:true,uid:danielleguan@whu.edu.cn,password:***,category:PHONE,locale:zh_CN,destURL:/coremail/xphone/main.jsp},result=S_OK,resultVar={sid:BAGFsToopiiNlpeUWgooAmYJWEuBntRz,mainURL:'http://whu.edu.cn/coremail/xphone/main.jsp?sid=BAGFsToopiiNlp... 18 more'},opTime=14,requestURL=http://whu.edu.cn/coremail/xphone/index.jsp
#                         "rule_content": "%{TIME>>time}\s%{LEVEL>>level}\s*?\[tid:%{DATA>>tid},...\]\s...,remote=%{IPV4>>req_ip},...requestVar...uid:%{DATA>>username},...result=%{DATA>>result},...requestURL=%{ALLDATA>>req_url}",
#                         "transform": "req_ip:$getGeo>>geoip",
#                         "switch": "result==S_OK>>result=success",
#                         "rule_transed": "",
#                         "additions": {
#                             "opt": "login",
#                             "type": "HTTP"
#                         }
#                     }]
#                 # ]
#             },
#             "imap": {
#                 "id": 'urg_2',
#                 "code": "imap",
#                 "filedir": "/alldata/mlogs/tang_1/WD/89/mail_log/",
#                 "rule_content": ".*?imapsvr\.log\.%{DATE>>date}$",
#                 "rules": [{
#                     "rule_name": "imap1",
#                     "condition": "login",
#                     "rule_content": "...\(%{TIME>>time}\)...\s*User\s%{DATA>>username}\s...\[%{IPV4>>req_ip}\]\s...\s%{ALLDATA>>result}",
#                     "transform": "req_ip:$getGeo>>geoip",
#                     "switch": "",
#                     "rule_transed": "",
#                     "additions": {
#                         "opt": "login",
#                         "type": "IMAP"
#                     }
#                 }]
#             },
#             "pop3": {
#                 'id': 'urg_3',
#                 "code": "pop3",
#                 "filedir": "/alldata/mlogs/tang_1/WD/89/mail_log/",
#                 "rule_content": ".*?pop3svr\.log\.%{DATE>>date}.tmp$",
#                 "rules": [
#                     {
#                         "rule_name": "pop3",
#                         "condition": "retr msg",
#                         "rule_content": "...\(%{TIME>>time}\)...\s*User\s%{DATA>>username}\s...\[%{IPV4>>req_ip}\].*",
#                         "transform": "req_ip:$getGeo>>geoip",
#                         "switch": "",
#                         "rule_transed": "",
#                         "additions": {
#                             "opt": "login",
#                             "type": "POP",
#                             "result": "success"
#                         }
#                     }, {
#                         "rule_name": "pop32",
#                         "condition": "login fail",
#                         "rule_content": "...\(%{TIME>>time}\)...\s*User\s%{DATA>>username}\s...\[%{IPV4>>req_ip}\].*",
#                         "transform": "req_ip:$getGeo>>geoip",
#                         "switch": "",
#                         "rule_transed": "",
#                         "additions": {
#                             "opt": "login",
#                             "type": "POP",
#                             "result": "fail"
#                         }
#                     }]
#             }
#         }
#     }]}
