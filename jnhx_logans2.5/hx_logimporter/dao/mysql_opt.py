import json
import os
import time
import re
import pymysql

from config import read_config
from tools.hx_logger import Logger

logger = Logger(__name__).get_log

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


def process_file(cursor, folder_id, folder_files_list):
    # 查询规则组id和对应的正则表达式
    sql = f"select rulegroup_id,file_features from rulegroup"
    cursor.execute(sql)
    rg_features_res = cursor.fetchall()
    rg_features_res_mapper = dict(rg_features_res)
    for i in rg_features_res_mapper.keys():
        rg_features_res_mapper[i] = re.compile(rg_features_res_mapper[i])
    # 入库识别出的文件及其规则组
    entry_count = 0
    for file in folder_files_list:
        for i in rg_features_res_mapper:
            if rg_features_res_mapper[i].search(file):
                check_file = '"' + file + '"'
                check_code = f'/usr/bin/file -b {check_file}'
                res = os.popen(check_code).readlines()[0]
                filecode = res.split()[0]
                check_code = f'/usr/bin/wc -l {check_file}'
                res = os.popen(check_code).readlines()[0]
                data_length = res.split()[0]
                print(f"发现文件{file}-{data_length}—{filecode}")
                sql = f"insert into file_records(file_path,file_encode,folder_records_id,rulegroup_id,file_status, last_status,  data_length, process_length, output_data) values('{file}','{filecode}',{folder_id},{i},1,1,{data_length},0,0)"
                # print(sql)
                cursor.execute(sql)
                entry_count += 1
    print(f"总文件数{len(folder_files_list)} 入库文件数目{entry_count}")
    sql = f"update folder_records set filecounts={entry_count} where folder_id={folder_id}"
    cursor.execute(sql)
    sql = f"update folder_records set folder_status=1 where folder_id={folder_id}"
    cursor.execute(sql)
    print(f"更新文件夹{folder_id}文件数目")


def Preatement_files():
    # 一、首先获取状态为0的任务，找到状态为未完成的文件夹，获取文件对应的规则组并入库
    cursor = connect()
    fields = ["pro_id", "pro_name", "data_path"]
    sql = f"""
    select {','.join(fields)}
    from project
    where pro_status = 0 order by create_time asc; 
    """
    cursor.execute(sql)
    project = cursor.fetchone()
    if project:
        print(f"发现预处理任务{project[1]}")
        for folder in project[2].split(";"):
            sql = f"insert into folder_records(folderpath, folder_status, last_status, project_id,processed_files) values ('{folder}',0,0,{project[0]},0)"
            cursor.execute(sql)
        sql = f"select folder_id,folderpath from folder_records where project_id={project[0]} and folder_status=0"
        cursor.execute(sql)
        foldersres = cursor.fetchall()
        print(foldersres)
        for folder in foldersres:
            folder_files_list = dirpath(folder[1])
            process_file(cursor, folder[0], folder_files_list)
        sql = "update project set pro_status=1"
        cursor.execute(sql)
        print(f"任务{project}预处理完成，可以导入")
    else:
        print(f"未发现等待预处理任务")
    cursor.close()


# get_value_files()
####################################################################3
# 正式处理任务
# 获取查询树
def get_import_tree():
    # 先发正在处理的任务
    # 再发现等待处理的任务
    # 再发现预处理的任务
    # 再发现等待处理的任务
    Preatement_files()
    cursor = connect()
    sql = f"select pro_id,pro_name,unit_id from project where pro_status=1"
    cursor.execute(sql)
    prores = cursor.fetchone()
    if not prores:
        # print(f"未发现待处理任务")
        return False
    # print(f"发现任务{prores[1]}")
    # 2.查询任务所属单位
    sql = f"select unit_name from unit where unit_id ={prores[2]}"
    cursor.execute(sql)
    query_tree = {
        "pro_id": prores[0],
        "pro_name": prores[1],
        "unit_name": cursor.fetchone()[0],
        "folders": []
    }
    # 3.查询任务下未完成的文件夹
    sql = f"select folder_id,folderpath from folder_records where project_id={prores[0]} and folder_status in (1,10) order by processed_files desc "
    cursor.execute(sql)
    folderres = cursor.fetchall()

    for onefolder in folderres:
        # 查询每个folder下的文件
        folder = {
            "folder_id": onefolder[0],
            "folderpath": onefolder[1],
            "files": []
        }
        sql = f"select file_id,file_path,file_encode,data_length,process_length,output_data,rule_id,rulegroup_id from file_records where folder_records_id={onefolder[0]} and file_status in (1,10) order by file_status desc"
        cursor.execute(sql)
        files = cursor.fetchall()
        # print(f"查询未完成文件夹{onefolder[1]}下文件，发现{len(files)}个文件")
        for onefile in files:
            file = {
                "file_id": onefile[0],
                "file_path": onefile[1],
                "file_encode": onefile[2],
                "data_length": onefile[3],
                "process_length": onefile[4],
                "output_data": onefile[5],
                "rule_id": onefile[6],
                "rulegroup_id": onefile[7],
                "unit": query_tree["unit_name"],
                "project": query_tree["pro_name"],
                "pro_id": query_tree["pro_id"],
                "folder_id": onefolder[0]
            }
            folder['files'].append(file)
        query_tree['folders'].append(folder)
    cursor.close()
    return query_tree


##########################################


def GetPureFields(ana_fields, cursor, sql):
    """
    获取纯净字段集合
    :return:
    """
    cursor.execute(sql)
    new_fields = cursor.fetchall()
    for new_field in new_fields:
        if new_field[0] in ana_fields:
            continue
        else:
            ana_fields.append(new_field[0])
    return ana_fields


# 查询规则组-规则
def get_rulemapper():
    """
    查询规则组-规则
    :return:
    """
    rulemapper = {}
    cursor = connect()
    sql = f"select ana_id,ana_name,ana_description from analyse"
    # print(sql)
    cursor.execute(sql)
    anares = cursor.fetchall()
    all_fields = {}
    for ana in anares:
        ana_fields = []
        sql = f"select field_ename from fields where analyse_id={ana[0]} order by field_index asc"
        ana_fields = GetPureFields(ana_fields, cursor, sql)

        sql = f"select rulegroup_id,rulegroup_name from rulegroup where analyse_id={ana[0]}"
        cursor.execute(sql)
        rulegroupres = cursor.fetchall()
        for rulegroup in rulegroupres:
            sql = f"select field_ename from fields where rulegroup_id={rulegroup[0]} order by field_index asc"
            ana_fields = GetPureFields(ana_fields, cursor, sql)
            sql = f"select rule_id,rule_name,rule_index,rule_exfeatures,rule_infeatures,rule_regex_features,extract_rule,transform_rule,replace_rule,supply_rule from rule where rulegroup_id={rulegroup[0]} order by rule_index"
            cursor.execute(sql)
            # rules = {}
            rules = []
            ruleres = cursor.fetchall()
            for rule in ruleres:
                sql = f"select field_ename from fields where rule_id={rule[0]} order by field_index asc"
                ana_fields = GetPureFields(ana_fields, cursor, sql)
                rules.append({
                    "rule_id": rule[0],
                    "rule_name": rule[1],
                    "rule_index": rule[2],
                    "rule_exfeatures": rule[3],
                    "rule_infeatures": rule[4],
                    "rule_regex_features": rule[5],
                    "extract_rule": rule[6],
                    "transform_rule": rule[7],
                    "replace_rule": rule[8],
                    "supply_rule": rule[9],
                    "analyse": {
                        "ana_id": ana[0],
                        "ana_name": ana[1]
                    },
                    "rulegroup": {
                        "rulegroup_id": rulegroup[0],
                        "rulegroup_name": rulegroup[1],
                    },
                    # "fields": GetRuleFields()
                })

            # rulegroups.append(
            #     {
            #         "rulegroup_id": rulegroup[0],
            #         "rulegroup_name": rulegroup[1],
            #         "rules": rules
            #     }
            # )
            rulemapper[rulegroup[0]] = {
                "rules": rules
            }
            # rulemapper[rulegroup[0]] = rules

        all_fields[ana[0]] = ana_fields
        # anas.append({"ana_id": ana[0],
        #              "ana_name": ana[1],
        #              "rulegroup": rulegroups
        #              })
        # print(anas)
    # print(rulemapper)
    for rule_id in rulemapper.keys():
        for rule in rulemapper[rule_id]['rules']:
            rule['allfields'] = all_fields[rule['analyse']['ana_id']]
            rule['allfields'].sort()
    # print(all_fields)
    cursor.close()
    return rulemapper


########### 得到所有分析对象的字段  #################

def GetAnaFields(rulemapper=None):
    """
    获取分析对象的字段，主要用于建立impala表用
    :return:
    """
    anas = {}
    res = []
    if not rulemapper:
        rulemapper = get_rulemapper()
    for rg in rulemapper.values():
        for rule in rg['rules']:
            if not rule['analyse']['ana_name'] in anas.keys():
                anas[rule['analyse']['ana_name']] = rule['allfields']
            else:
                anas[rule['analyse']['ana_name']].extend(rule['allfields'])
    for ana in anas.keys():
        anas[ana] = list(set(anas[ana]))
        anas[ana].sort()
        res.append({ana: anas[ana]})
    # return anas
    return res


############ 从prm中获取分区 #########

def GetPartitionsFromPRM(project_id):
    """
    根据传进来的任务号码，查询该任务用到的rule，进而查询分区
    :return:
    """
    cursor = connect()
    sql = f"select unit_id,pro_name from project where pro_id={project_id}"
    cursor.execute(sql)
    res = cursor.fetchone()
    if not res:
        return False
    unit_id = res[0]
    pro_name = res[1]
    sql = f"select unit_name from unit where unit_id={unit_id}"
    cursor.execute(sql)
    unit_name = cursor.fetchone()[0]
    sql = f"select rule_id from prm where project_id={project_id}"
    cursor.execute(sql)
    rules = cursor.fetchall()
    partitions = []
    if rules:
        for rule_id in rules:
            sql = f"select rulegroup_id from rule where rule_id={rule_id[0]}"
            cursor.execute(sql)
            rulegroup_id = cursor.fetchone()[0]
            sql = f"select rulegroup_name,analyse_id from rulegroup where rulegroup_id={rulegroup_id}"
            cursor.execute(sql)
            rulegroup_name, analyse_id = cursor.fetchone()
            sql = f"select ana_name from analyse where ana_id={analyse_id}"
            cursor.execute(sql)
            ana_name = cursor.fetchone()[0]
            tmp = {
                'unit': unit_name,
                'ana_obj': ana_name,
                'rulegroup': rulegroup_name,
                'project': pro_name
            }
            partitions.append(tmp)
            # partitions.append(f"unit={unit_name}/ana_obj={ana_name}/rulegroup={}/project={pro_name}")
    cursor.close()
    # print(partitions)
    # partitions = list(set(partitions))
    # print(partitions)
    return partitions


# GetPartitionsFromPRM(1)


########### 更新状态 #########
def UpdatePRM(project_id, rule_id):
    """
    更新prm表
    :param project_id: 
    :param rule_id: 
    :return: 
    """""
    cursor = connect()
    try:
        sql = f"insert into prm(project_id, rule_id) values ({project_id},{rule_id})"
        cursor.execute(sql)
    except:
        pass
    cursor.close()


def UpdateStatus(type, pk, status):
    """
    更新状态
    :param type:project,folder,file
    :param pro_id:
    :param status:
    :return:
    """
    if type == 'project':
        sql = f"update project set pro_stauts={status} where pro_id={pk}"
    elif type == 'folder':
        sql = f"update folder_records set folder_stauts={status} where folder_id={pk}"
    else:
        sql = f"update file_records set file_status={status} where file_id={pk}"
    cursor = connect()
    cursor.execute(sql)
    cursor.close()


def UpdateFileProcess(pk, process_length=None, output_data=None):
    """
    更新文件处理进度
    :param file_id:
    :return:
    """
    if process_length:
        sql = f"update file_records set process_length=process_length+{process_length} where file_id={pk}"
    else:
        sql = f"update file_records set output_data=output_data+{output_data} where file_id={pk}"

    cursor = connect()
    cursor.execute(sql)
    cursor.close()


def UpdateFolder(pk, only_processed_files=True):
    """
    更新文件夹状态和处理进度
    :param pk:
    :param only_processed_files:
    :return:
    """
    if only_processed_files:
        sql = f"update folder_records set  processed_file=processed_file+1 where folder_id={pk}"
    else:
        sql = f"update folder_records set folder_status=100  where folder_id={pk}"
    cursor = connect()
    cursor.execute(sql)
    cursor.close()


def query_project_status(pro_id):
    """
    查询任务状态
    :param pro_id:
    :return:
    """
    sql = f"select pro_status from project where pro_id = {pro_id}"
    cursor = connect()
    cursor.execute(sql)
    res = cursor.fetchone()[0]
    cursor.close()
    return res


# print(get_import_tree())
# print(GetAnaFields())

# ana_fields = GetAnaFields()
# for res in ana_fields:
#     ana_name = list(res.keys())[0]
#     print(ana_name)
# print(ana_fields)
# partitions = GetPartitionsFromPRM(2)
# print(partitions)