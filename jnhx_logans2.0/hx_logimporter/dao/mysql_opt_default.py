import pymysql
from config import read_config
import json
from tools.hx_logger import Logger

logger = Logger(__name__).get_log

# 默认
STATUS_DEFAULT = 0
# 可以开始
STATUS_CANSTART = 1
# 运行中
STATUS_RUNING = 10
# 运行成功
STATUS_SUCCESS = 100
# 运行失败
STATUS_FAIL = -1
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
        logger.error("数据库链接失败" + read_config.mysql_host + "" + str(read_config.mysql_port))


def query_project_and_unit_by_projectId(cursor):
    """
    通过任务id查询任务和所在单位
    :param cursor:
    :return:
    """
    sql_project = f'select project.name,project.clues,unit.cname,unit.ename,project.id from project,unit where  unit.id = unit_id and status=1 limit 1'
    try:
        cursor.execute(sql_project)
        return cursor.fetchone()
    except Exception as e:
        logger.error("查询导入项目id出错，返回空值，project_id :" + str(e))
    return ""


def query_analysis_by_projectId(cursor, project_id):
    sql_project_analysis_mapper = f'select id,object_name,code,object_id from project_analysis_mapper where project_id = "{project_id}"'
    try:
        cursor.execute(sql_project_analysis_mapper)
        for analysis_object in cursor.fetchall():
            yield analysis_object
    except Exception as e:
        logger.error("查询分析对象出错，返回空值，project_id ：" + str(project_id) + str(e))
    return ""


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


def query_rule_by_urgId(cursor, urg_id):
    """
    查询规则
    :param cursor:
    :param urg_id:
    :return:
    """
    sql_used_rule = f'select name,log_feature,extract_rule,switch_rule,replace_rule,supplement_rule,rule_id from used_rule where use_rule_group_id = "{urg_id}" and is_enabled=1'
    try:
        cursor.execute(sql_used_rule)
        rule_tumple = cursor.fetchall()
        return rule_tumple
    except Exception as e:
        logger.error("查询规则出错，返回空值，urg_id : " + str(urg_id) + str(e))
    return ""


def update_project_status(project_id, start_time, end_time, success=True):
    """
    更新任务进度
    :param project_id:
    :param start_time:
    :param end_time:
    :param success:
    :return:
    """
    sql = None
    cursor = connect()
    if success:
        status = 100
    else:
        status = -1
    if start_time:
        sql = f"update project set status=10,start_time='{start_time}' where id='{project_id}'"
    if end_time:
        sql = f"update project set status={status},end_time='{end_time}' where id='{project_id}'"
    print(sql)
    try:
        cursor.execute(sql)
    except Exception as e:
        logger.error("修改项目状态失败 : " + str(e))
    cursor.close()


def str_to_json(to_json):
    done = None
    if len(to_json) == 0:
        return None
    try:
        done = json.loads(to_json)
    except Exception as e:
        logger.error("转换json失败，转化字段为 : " + str(to_json) + str(e))
    return done


def update_project_reason_by_projectId(pro_id, error_str):
    """
    更新导出失败的原因
    :param pro_id:
    :param error_str:
    :return:
    """
    cursor = connect()
    sql = f"update project set reasons='{error_str}' where id='{pro_id}'"
    cursor.execute(sql)
    cursor.close()


def uopdate_progress(isfirst, urg_id, finder_filelist_length):
    """
    更新进度
    :param isfirst:
    :param urg_id:
    :param finder_filelist_length:
    :return:
    """
    logger.warning(f'-----isfirst  {isfirst}')
    logger.warning(f'-----urg_id  {urg_id}')
    logger.warning(f'-----finder_filelist_length  {finder_filelist_length}')
    # gmt_create = time.strftime("%Y-%m-%d %X", time.localtime())
    # gmt_modified = time.strftime("%Y-%m-%d %X", time.localtime())
    # 变更进度信息
    file_total = finder_filelist_length
    primary_file_total = finder_filelist_length
    if isfirst is True:
        primary_file_handle = 1
        normal_file_handle = 0
        sql = f'INSERT INTO  progress(id,use_rule_group_id,file_total,primary_file_total,primary_file_handle,normal_file_handle) VALUES ("{urg_id}","{urg_id}","{file_total}","{primary_file_total}","{primary_file_handle}","{normal_file_handle}") ON DUPLICATE KEY UPDATE primary_file_handle=primary_file_handle + 1;'
    else:
        primary_file_handle = 0
        normal_file_handle = 1
        sql = f'INSERT INTO  progress(id,use_rule_group_id,file_total,primary_file_total,primary_file_handle,normal_file_handle) VALUES ("{urg_id}","{urg_id}","{file_total}","{primary_file_total}","{primary_file_handle}","{normal_file_handle}") ON DUPLICATE KEY UPDATE normal_file_handle=normal_file_handle+1;'
    cursor = connect()
    try:
        logger.warning(sql)
        cursor.execute(sql)
        logger.warning("更新进度至数据库")
    except Exception as e:
        logger.warning("更新进度至数据库失败" + str(e))
    cursor.close()


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


def query_import_tree_by_projectId():
    """
    查询导出树
    :return:
    """
    cursor = connect()
    project_and_unit = query_project_and_unit_by_projectId(cursor)
    if not project_and_unit:
        cursor.close()
        return None
    project_id = project_and_unit[4]
    try:
        import_tree = {
            "import_name": project_and_unit[0],
            "unit": project_and_unit[2],
            "unit_code": project_and_unit[3],
            "condition": project_and_unit[1],
            "ana_object": [],
            "id": project_and_unit[4]
        }

        for analysis_object in query_analysis_by_projectId(cursor, project_id):
            try:
                ana_object = {
                    "id": analysis_object[0],
                    "name": analysis_object[1],
                    "code": analysis_object[2],
                    "object_id": analysis_object[3],
                    "filedir": "",
                    "rule_groups": {}}
                for rule_group in query_rule_group_by_pamId(cursor, analysis_object[0]):
                    try:
                        rule_groups = {
                            "id": rule_group[0],
                            "code": rule_group[2],
                            "filedir": rule_group[3],
                            "role_content": rule_group[4],
                            "file_encode": rule_group[5],
                            "roles": []
                        }
                        for rule_info in query_rule_by_urgId(cursor, rule_group[0]):
                            try:
                                rule = {
                                    "rule_name": rule_info[0],
                                    "condition": rule_info[1],
                                    "rule_content": rule_info[2],
                                    "transform": rule_info[3],
                                    "switch": rule_info[4],
                                    "rule_transed": "",
                                    "additions": str_to_json(rule_info[5])
                                }
                            except Exception as e:
                                logger.warning("导出规则失败，参数" + str(rule_group[0])) + str(project_id) + "   " + str(e)
                                continue
                            rule_groups['rules'].append(rule)
                    except Exception as e:
                        logger.warning("导出规则组失败，参数" + str(analysis_object[0])) + str(project_id) + "   " + str(e)
                        continue
                    ana_object['rule_groups'][rule_group[1]] = rule_groups
            except Exception as e:
                logger.warning("导出分析对象失败，参数" + str(project_id)) + str(project_id) + "   " + str(e)
                continue
            import_tree['ana_object'].append(ana_object)

    except Exception as e:
        # logger.warning("import_tree失败，返回空值，参数 " + str(project_id) + "   " + str(e))
        cursor.close()
        return None
    cursor.close()
    return import_tree


if __name__ == '__main__':
    x = query_import_tree_by_projectId()
    print(x)