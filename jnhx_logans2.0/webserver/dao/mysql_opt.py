import pymysql
from config import read_config
# from tools.hx_logger import Logger
import os
import re

import json
import time


# 默认
STATUS_DEFAULT = 0
# 准备运行，准备处理
STATUS_CANSTART = 1
# 运行中，处理中
STATUS_RUNING = 10
# 运行完毕，处理完毕，运行成功
STATUS_SUCCESS = 100
# 运行失败
STATUS_FAIL = -1
# 暂停执行
STATUS_SUSPEND = -2
# 跳过执行
STATUS_JUMP = -3
# 准备取消
STATUS_CANCEL = -10
# 取消成功
STATUS_CANCELSUC = -11


def connect():
    # 连接数据库
    conn = pymysql.Connect(
        host=read_config.mysql_host,
        port=read_config.mysql_port,
        user=read_config.mysql_user,
        passwd=read_config.mysql_password,
        db=read_config.mysql_db,
        charset='utf8'
    )
    try:
        conn.autocommit(True)
        cursor = conn.cursor()
        return cursor
    except:
        print("数据库链接失败" + read_config.mysql_host + "" + str(read_config.mysql_port))


# conn = pymysql.Connect(
#     host=read_config.mysql_host,
#     port=read_config.mysql_port,
#     user=read_config.mysql_user,
#     passwd=read_config.mysql_password,
#     db=read_config.mysql_db,
#     charset='utf8'
# )
# try:
#     conn.autocommit(True)
#     cursor = conn.cursor()
# except:
#     print("数据库链接失败" + read_config.mysql_host + "" + str(read_config.mysql_port))


def dirpath(lpath, lfilelist=None, file_re=None):
    """
    输出一个文件夹下所有文件（包括子文件夹的文件）,输入肯定不含空格
    """
    if lfilelist is None:
        lfilelist = []
    dirlist = os.listdir(lpath)
    for f in dirlist:
        file = os.path.join(lpath, f)
        if " " in f:
            file = f.replace(" ", "")
            os.rename(file, f)
        if os.path.isdir(file):
            dirpath(file.replace(" ", ""), lfilelist, file_re)
        else:
            if file_re:
                if file_re.search(file):
                    lfilelist.append(file)
            else:
                lfilelist.append(file)
    return lfilelist


def get_file_infos(lfilelist):
    """
    输出文件的编码及大小
    """
    file_infos = []
    one_file = {}

    for filename in lfilelist:
        # 获取文件编码
        one_file = {
            "filename": filename,
            "status": 0,
            "data_length": 0
        }
        try:
            check_file = '"' + filename + '"'
            check_code = f'/usr/bin/file -b {check_file}'
            res = os.popen(check_code).readlines()[0]
            one_file['filecode'] = res.split()[0]
            # 获取文件大小
            check_size = f'du -s {check_file}'
            res = os.popen(check_size).readlines()[0]
            rex = re.compile("^\d{0,15}")
            one_file['file_size'] = int(rex.findall(res)[0])
        except Exception as e:
            one_file['file_size'] = -1,
            one_file['filecode'] = -1
        file_infos.append(one_file)
    return file_infos


def query_project_and_unit_by_status(cursor, status=STATUS_RUNING, order_by='project.start_time'):
    """
    根据状态查询任务id、名称、单位信息
    :param cursor:
    :param status:
    :param order_by:
    :return:
    """
    sql = f"select project.id,project.name,project.status,project.clues,unit.id,unit.cname from project,unit where  unit.id = unit_id and status = {status} order by {order_by}"
    cursor.execute(sql)
    one_project = cursor.fetchone()
    if one_project:
        project = {
            "pro_id": one_project[0],
            "pro_name": one_project[1],
            "pro_status": one_project[2],
            "pro_schedule": "进度",
            "is_first": "是否优先",
            "unit_id": one_project[4],
            "unit_name": one_project[5],
            "objects": []
        }
        return project
    else:
        return None


#
# def query_files_by_dir(dir_path, file_re):
#     file_re = re.compile(file_re)
#     lfilelist = dirpath(dir_path, file_re=file_re)
#     file_infos = get_file_infos(lfilelist)
#     return file_infos
#
#
# def insert_file_records(cursor, filedir_id, files):
#     # sql = f'delete from file_records where filedir_id = "{filedir_id}"'
#     # print("清空文件夹")
#     # cursor.execute(sql)
#     data = [(str(filedir_id), file['filename'], file['file_size'], file['filecode']) for file in files]
#     if data:
#         sql = "insert into file_records(filedir_id, filename, file_size,  file_code) values(%s, %s, %s,%s)"
#         cursor.executemany(sql, data)
#
#
# def insert_dir_records(cursor, used_rule_group_id, dir_name, filenums, files):
#     """
#     在数据库中更新文件夹状态
#     :param cursor:
#     :param used_rule_group_id:
#     :param dir_name:
#     :param filenums:
#     :return:
#     """
#     sql = f'select id from dir_records where used_rule_group_id = "{used_rule_group_id}" and dirname = "{dir_name}"'
#     cursor.execute(sql)
#     dir_id = cursor.fetchone()
#     if dir_id:
#         print(f"{dir_name} 文件记录已存在 - rulegroupid {used_rule_group_id}")
#         # 若该文件夹的文件记录数是否和files不相等，则删除后重新插入
#         sql = f'select count(id) from file_records where filedir_id = "{dir_id[0]}"'
#         cursor.execute(sql)
#         if cursor.fetchone()[0] != len(files):
#             sql = f'delete from file_records where filedir_id="{dir_id[0]}"'
#             cursor.execute(sql)
#             print(f"{dir_name}数据库记录和现有记录不匹配，删除数据库已存在记录")
#             insert_file_records(cursor, dir_id[0], files)
#             print(f"{dir_name} 缺少文件记录{len(files)}条，已补全")
#     else:
#         try:
#             dir_id = str(uuid.uuid4())
#             sql = f'insert into dir_records(id,used_rule_group_id,dirname,filenums) values("{dir_id}","{used_rule_group_id}","{dir_name}","{filenums}") '
#             cursor.execute(sql)
#             print(f" 记录 {dir_name} 相关数据 - rulegroupid {used_rule_group_id}")
#             insert_file_records(cursor, dir_id, files)
#             print(f" 记录文件 {len(files)} 条，- rulegroupid {used_rule_group_id}")
#         except Exception as e:
#             print(f"插入文件夹失败,{e}")
#
#
# def query_dirs(cursor, used_rule_group_id, inputpath, file_re, used_rule_group_status):
#     """
#     查询文件夹
#     :param cursor:
#     :param used_rule_group_id:
#     :param inputpath:
#     :param file_re:
#     :param used_rule_group_status: 应该是规则状态，但是一个规则组绑定了一个规则，所以跟规则组状态一样
#     :return:
#     """
#
#     file_dirs = []
#     for file_dir in inputpath.split(";"):
#         if os.path.exists(file_dir):
#             filedir = {
#                 "dir_name": file_dir,
#                 "dir_status": 1,
#                 "wasscanned": 1,
#                 "files": query_files_by_dir(file_dir, file_re),
#                 "processednums": 0,
#             }
#             filedir['filenums'] = len(filedir['files'])
#             insert_dir_records(cursor, used_rule_group_id, filedir['dir_name'], filedir['filenums'], filedir['files'])
#             file_dirs.append(filedir)
#     if file_dirs:
#         return file_dirs
#     else:
#         return None


def query_files_by_dir_id(cursor, dir_id):
    """
    查询文件
    :param cursor:
    :param dir_id:
    :return:
    """
    sql = f'select id,filename,data_length,file_code,run_status from file_records where filedir_id = "{dir_id}"'
    cursor.execute(sql)
    files = cursor.fetchall()
    res = []
    if not files:
        return None
    else:
        for file in files:
            one_file = {
                "id": file[0],
                "filename": file[1],
                "data_length": file[2],
                "file_code": file[3],
                "run_status": file[4]
            }
            res.append(one_file)
        return res


def query_dirs_by_use_group_id(cursor, use_rule_group_id):
    """
    查询文件夹
    :param cursor:
    :param use_rule_group_id:
    :return:
    """
    sql = f'select id, dirname from dir_records where used_rule_group_id= "{use_rule_group_id}" and dir_status=1'
    cursor.execute(sql)
    dirs = cursor.fetchall()
    res = []
    if not dirs:
        return None
    else:
        for dir in dirs:
            onedir = {
                "id": dir[0],
                "dirname": dir[1],
                "files": query_files_by_dir_id(cursor, dir[0])
            }
            res.append(onedir)
        return res


def str_to_json(to_json):
    """
    将规则补充字段转为python格式
    :param to_json:
    :return:
    """
    done = None
    try:
        if len(to_json) == 0:
            return None
        done = json.loads(to_json)
    except Exception as e:
        logger.error("转换json失败，转化字段为 : " + str(to_json) + str(e))
    return done


def query_rule_by_used_rule_group_id(cursor, used_rule_group_id):
    """
    查询规则
    :param cursor:
    :param used_rule_group_id:
    :return:
    """
    sql = f"select id, name,log_feature,extract_rule,switch_rule,replace_rule,supplement_rule from used_rule where use_rule_group_id = '{used_rule_group_id}'"
    cursor.execute(sql)
    # 因为此时一个规则组只包含一个规则，所以用fetchone
    one_rule = cursor.fetchone()
    if one_rule:
        rule = {
            "rule_id": one_rule[0],
            "rule_name": one_rule[1],
            "features": one_rule[2],
            "extract_rule": one_rule[3],
            "switch_rule": one_rule[4],
            "replace_rule": one_rule[5],
            "supplement_rule": str_to_json(one_rule[6]),
            "fileds": []
        }
        return rule


def query_rulegroups_by_porject_analysis_id(cursor, project_analysis_id, object_id):
    """
    查询分析对象下的所有规则组
    :param cursor:
    :param porject_analysis_id:
    :param pro_status:
    :return:
    """
    rule_groups = []
    sql = f"select id,name,status,input_path,file_regex,status,file_regex from used_rule_group where project_analysis_id  = '{project_analysis_id}'"
    cursor.execute(sql)
    for one_rulegroup in cursor.fetchall():
        rule_group = {
            "used_rule_group_id": one_rulegroup[0],
            "rule_group_name": one_rulegroup[1],
            "rule_group_status": one_rulegroup[2],
            "rule": query_rule_by_used_rule_group_id(cursor, used_rule_group_id=one_rulegroup[0]),
            "status": one_rulegroup[5],
            # "file_dirs": query_dirs(cursor, one_rulegroup[0], one_rulegroup[3], one_rulegroup[4], one_rulegroup[5])
            "file_dirs": query_dirs_by_use_group_id(cursor, one_rulegroup[0]),
            "file_regex": one_rulegroup[6]
        }
        rule_groups.append(rule_group)
    return rule_groups


def query_objects_by_project_id(cursor, project_id, pro_status=None):
    """
    查询所有分析对象
    :param cursor:
    :param project_id:
    :param pro_status:
    :return:
    """
    objects = []
    sql = f'select id,object_id,object_name,status from project_analysis_mapper where project_id = "{project_id}"'
    cursor.execute(sql)
    for ana_object in cursor.fetchall():
        object = {
            "porject_analysis_id": ana_object[0],
            "object_id": ana_object[1],
            "object_name": ana_object[2],
            "object_status": ana_object[3],
            "object_schedule": "进度",
            "rule_groups": query_rulegroups_by_porject_analysis_id(cursor, ana_object[0], ana_object[1]),
            "fields": query_fields_by_obj(ana_object[1]),

        }
        objects.append(object)
    return objects


def query_import_tree_by_project_id(cursor, project, pro_status=None):
    """
    查询导出树
    :param cursor:
    :param project_id:
    :param pro_status:
    :return:
    """
    import_tree = {
        "pro_id": project['pro_id'],
        "pro_name": project['pro_name'],
        "pro_status": project['pro_status'],
        "pro_schedule": project['pro_schedule'],
        "is_first": "是否优先",
        "unit_id": project['unit_id'],
        "unit_name": project['unit_name'],
        "ana_objects": query_objects_by_project_id(cursor=cursor, project_id=project['pro_id'], pro_status=pro_status)
    }
    return import_tree


def get_import_tree():
    """
    不输入任何参数，根据数据库中的任务状态查询相关任务
    :return:
    """
    cursor = connect()
    project = query_project_and_unit_by_status(cursor, STATUS_RUNING)
    import_tree = None
    if project:
        logger.warning(f"查询上次未运行完毕任务 {project['pro_name']} 相关信息")
        time.sleep(10)
        import_tree = query_import_tree_by_project_id(cursor, project, project['pro_status'])
    else:
        project = query_project_and_unit_by_status(cursor, status=STATUS_CANSTART, order_by="project.gmt_create")
        if project:
            logger.warning(f"查询新任务 {project['pro_name']} 相关信息")
            time.sleep(20)
            import_tree = query_import_tree_by_project_id(cursor, project)
    cursor.close()
    return import_tree


def update_project_status(project_id, status=STATUS_SUCCESS):
    """
    更新任务状态
    :param status:
    :param project_id:
    :param start_time:
    :param end_time:
    :param success:
    :return:
    """
    sql = None
    cursor = connect()
    if status == STATUS_CANSTART:
        start_time = time.strftime("%Y-%m-%d %X", time.localtime())
        sql = f"update project set status=10,start_time='{start_time}' where id='{project_id}'"
    if status == STATUS_SUCCESS:
        end_time = time.strftime("%Y-%m-%d %X", time.localtime())
        sql = f"update project set status={status},end_time='{end_time}' where id='{project_id}'"
    try:
        cursor.execute(sql)
    except Exception as e:
        logger.error("修改项目状态失败 : " + str(e))
    cursor.close()


def query_rule_group_by_pamId(cursor, pam_id, field=False):
    """
    查询规则组
    :param cursor:
    :param pam_id:
    :param field:
    :return:
    """
    if field:
        sql_used_rule_group = f"select id from rule_group where object_id='{pam_id}'"
    else:
        sql_used_rule_group = f'select id,name,code,input_path,file_regex,file_encode from used_rule_group where project_analysis_id="{pam_id}"'
    rule_groups = []
    try:
        cursor.execute(sql_used_rule_group)
        for rule_group in cursor.fetchall():
            rule_groups.append(rule_group)
    except Exception as e:
        logger.error("查询规则组出错，返回空值, pam_id : " + str(pam_id) + str(e))
    return rule_groups


def query_fields(cursor, has_fields, level, tid):
    """
    查询创建impala表的字段
    :param cursor:
    :param has_fields:
    :param level:
    :param tid:
    :return:
    """
    sql = f"select field.ename as ename,field.cname as cname,field.type from field where field.{level}='{tid}'"
    fields = []
    try:
        cursor.execute(sql)
        for one in cursor.fetchall():
            # logger.warning(one)
            if one[0] not in has_fields:
                fields.append({
                    "ename": one[0],
                    "cname": one[1],
                    "ftype": one[2]
                })
                has_fields.append(one[0])
            else:
                continue
    except Exception as e:
        logger.error("查询 ：" + str(e))
    return fields, has_fields


def query_fields_by_obj(obj_id):
    """
    通过分析对象查询字段
    :param obj_id:
    :return:
    """
    cursor = connect()
    has_fields = []
    all_fields = []
    new_fields, has_fields = query_fields(cursor, has_fields, level='object_id', tid=obj_id)
    all_fields.extend(new_fields)

    for rule_group in query_rule_group_by_pamId(cursor, obj_id, field=True):
        new_fields, has_fields = query_fields(cursor, has_fields, level='rule_group_id', tid=rule_group[0])
        all_fields.extend(new_fields)
        sql = f"select id from rule where rule_group_id ='{rule_group[0]}'"
        cursor.execute(sql)
        for rule_id in cursor.fetchall():
            new_fields, has_fields = query_fields(cursor, has_fields, level="rule_id", tid=rule_id[0])
            all_fields.extend(new_fields)
    all_fields.sort(key=lambda x: x['ename'])
    cursor.close()
    return all_fields


# 测试任务就是测试树
# import json


# with open('import_tree.json', 'w')as f:
#     json.dump(get_import_tree(), f,ensure_ascii=False)
# print(get_import_tree())
# cursor = connect()
# cursor.executemany()
# print(query_fields_by_obj('e79db0d4-ee79-4165-ba55-d47342bbf8de'))

# pass

# cursor = connect()
# print(query_rule_by_used_rule_group_id(cursor, used_rule_group_id='ef0247d6-6fb2-49fc-b7ab-9f0232e8cc00'))

# import json
#
# a = "{'pro_id': '7ffac19c-a81a-4770-b4f7-136b3cb4bda2', 'pro_name': '任务名称', 'pro_status': None, 'pro_schedule': '进度', 'is_first': '是否优先', 'unit_id': '所属单位id', 'unit_name': '所属单位名', 'ana_objects': [{'porject_analysis_id': '1c1db0fa-3069-42ce-bca4-a862caae1920', 'object_id': 'e79db0d4-ee79-4165-ba55-d47342bbf8de', 'object_name': 'iis', 'object_status': 0, 'object_schedule': '进度', 'rule_groups': [{'used_rule_group_id': '095b6eb2-55f0-4cb1-a466-02935d8b8fa0', 'rule_group_name': 'iis_log_rg', 'rule_group_status': 0, 'rule': {'rule_id': '3214a70a-aa81-4cb3-beae-08ee7c349b35', 'rule_name': 'iis_log_rule', 'extract_rule': '``fe80;``::1;re(.*-\\s\\w{1,4}\\s\\d{1,15}\\s\\d{1,15}\\s\\d{1,15}\\s(?<![0-9])(?:(?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5]))(?![0-9]))', 'swithch_rule': '%{DATE>>mdate}\\s%{TIME>>time}\\s%{IPV4>>server_ip}\\s%{DATA>>req_method}\\s%{DATA>>to_page}\\s...\\s%{PORT>>port}\\s%{DATA>>username}\\s%{DATA>>req_ip2}\\s%{DATA>>user_agent}\\s...\\s%{PORT>>response}\\s\\d{1,3}\\s%{DATA>>get_size}\\s%{AUSIZE>>send_size}\\s%{IPV4>>req_ip},?%{ALLDATA>>tail_ip}', 'supplement_rule': 'req_ip:$getGeo>>country,city', 'replace_rule': {'opt': '', 'result': ''}}, 'status': 0, 'file_dirs': [{'id': 'd1f2a1d7-734a-4b96-82f5-f25d37d658d1', 'dirname': '/data/data3/kangxiaofan0218/1111/9/', 'files': [{'id': 1079, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210117.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1080, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210112.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1081, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210115_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1082, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210116_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1083, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210106.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1084, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210108.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1085, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210115.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1086, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210118_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1087, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210111.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1088, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210114_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1089, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210112_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1090, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210110.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1091, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210111_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1092, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210113.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1093, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210114.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1094, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210116.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1095, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210110_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1096, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210109.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1097, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210106_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1098, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210104_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1099, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210105.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1100, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210118.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1101, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210109_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1102, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210104.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1103, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210105_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1104, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210107.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1105, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210108_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1106, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210117_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1107, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210107_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}, {'id': 1108, 'filename': '/data/data3/kangxiaofan0218/1111/9/u_ex210113_x.log', 'data_length': None, 'file_code': 'ASCII', 'run_status': 1}]}]}]}]}"
#
# with open('import_tree.json', 'w') as f:
#     json.dump(get_import_tree(), f)

def query_status_by_id(qtype, id):
    """
    查询规则组、规则、文件、文件夹状态 是否可执行
    :param qtype:
    :param f_id:
    :return:
    """
    cursor = connect()
    sql = None
    if qtype == "file":  # 文件
        sql = f'select run_status from file_records where id={id}'

    if qtype == "rule":  # 规则
        sql = f'select status from used_rule where id="{id}"'

    elif qtype == "object":  # 分析对象
        sql = f'select status from project_analysis_mapper where id="{id}"'
    cursor.execute(sql)
    status = cursor.fetchone()[0]
    cursor.close()
    if status != STATUS_CANSTART and status != STATUS_RUNING:
        # 若不是正在运行或者等待运行则返回False
        return False
    else:
        return True


def query_file_read_size_by_id(f_id):
    """
    查询文件已读数据量
    :param f_id:
    :return:
    """
    cursor = connect()
    sql = f"select read_size from file_records where id = {f_id}"
    cursor.execute(sql)
    read_size = cursor.fetchone()[0]
    cursor.close()
    return read_size


def update_status_by_id(utype, param):
    """
    更新规则、规则组、分析对象状态
    :param file_id:
    :param ftype:
    :param param:
    :return:
    """
    cursor = connect()
    sql = None
    if utype == "rule":  # 规则
        sql = f'update used_rule set status={param["status"]} where id="{param["id"]}"'
    elif utype == "rule_group":  # 规则组
        sql = f'update used_rule_group set status={param["status"]} where id="{param["id"]}"'
    if utype == "object":  # 分析对象
        sql = f'update project_analysis_mapper set status={param["status"]} where id="{param["id"]}"'
    cursor.execute(sql)
    cursor.close()


def update_file_by_id(ftype, param):
    """
    更新文件状态和所在文件夹处理文件数
    :param file_id:
    :param ftype:
    :param param:
    :return:
    """
    cursor = connect()
    if ftype == "status":  # 若更改状态
        sql = f'update file_records set run_status = {param["status"]} where id = {param["file_id"]}'
        cursor.execute(sql)
        if param["status"] == STATUS_SUCCESS:  # 若状态为已经完成，则所在文件夹处理文件数+1
            # print(f'文件状态已经更新{param["status"]} {param["dir_id"]}')
            sql = f'update dir_records set processednums = processednums+1 where id = "{param["dir_id"]}"'
            cursor.execute(sql)

    if ftype == "data_length":  # 更新该文件数据输出条数
        sql = f'update file_records set data_length=data_length+{param["data_length"]}, read_size = {param["chunk_count"]} where id = {param["file_id"]}'
        logger.warning(sql)
        cursor.execute(sql)
    if ftype == "file_code":
        file_code = param["file_code"]
        sql = f'update file_records set file_code="{file_code}" where id = {param["file_id"]}'
        print(sql)
        cursor.execute(sql)
    cursor.close()


# def update_rulegroup_by_id(rule_group_id,rtype,param):
#     """
#     更新规则组状态
#     :param rule_group_id:
#     :param rtype:
#     :param param:
#     :return:
#     """
#     cursor = connect()
#     sql = None
#     if rtype == "status":
#         sql = f"update file_records set run_status = {param} where id = {file_id}"
#     if rtype == "data_length":
#         sql = f"update file_records set data_length=data_length+{param} where id = {file_id}"
#     cursor.execute(sql)
#     cursor.close()


# print(query_status_by_id(qtype="object", id="1c1db0fa-3069-42ce-bca4-a862caae1920"))
# print(get_import_tree())
# update_file_by_id(ftype="file_code", param={"file_id": 12903, "file_code": "UTF-8"})
