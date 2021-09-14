import pandas as pd
from config import read_config
import asyncio
from plugins.inner_func import InnerFunction
import dao.parquet_data_outputer as parquet_data_outputer
from tools.hx_logger import Logger
import datetime
import re
from dao import mysql_opt
from dao import impala_opt
logger = Logger(__name__).get_log
import time

import socket


def valid_ip(str):
    try:
        socket.inet_aton(str)
        return True
    except:
        return False


def ip2long(str):
    if str == 0:
        return 0
    check = valid_ip(str)
    if check is False:
        return 0
    tmp = str.split(".")
    long_ip = (int(tmp[0]) << 24) + (int(tmp[1]) << 16) + (int(tmp[2]) << 8) + int(tmp[3])
    return long_ip % 10


def get_username_part(str1):
    username_part = 0
    i = 0
    for s in str1:
        if i > 3:
            break
        username_part += ord(s)
        i += 1
    return username_part % 10


# time.strptime(x, "%Y-%m-%d %H:%M:%S")
def to_mdate(timeStamp):
    timeStamp = timeStamp[:10]
    timeStamp = int(timeStamp)
    timeArray = time.localtime(timeStamp)
    x = time.strftime("%Y-%m-%d %H:%M:%S", timeArray)
    return x[:10]


def to_time(timeStamp):
    timeStamp = timeStamp[:10]
    timeStamp = int(timeStamp)
    timeArray = time.localtime(timeStamp)
    x = time.strftime("%Y-%m-%d %H:%M:%S", timeArray)
    return x[11:]


def req(rex, astr):
    rex = re.compile(rex)
    x = rex.match(astr)
    if x:
        return True
    else:
        return False


def ProcessLogByFeatures(rule, chunk):
    """
    根据规则处理chunk
    :param rule:
    :param chunk:
    :return:
    """
    rule_exfeatures = []
    rule_infeatures = []
    rule_regex_features = rule['rule_regex_features']
    if rule['rule_exfeatures']:
        rule_exfeatures = rule['rule_exfeatures'].split(';')
    if rule['rule_infeatures']:
        rule_infeatures = rule['rule_infeatures'].split(';')
    print(f"不含有特征有{rule_exfeatures}")
    print(f"含有特征有{rule_infeatures}")
    print(f"含有正则特征特征有{rule_regex_features}")

    if rule_exfeatures:
        for rule_exfeature in rule_exfeatures:
            chunk = chunk[~chunk[0].str.contains(rule_exfeature)]
        print(f'不包含特征处理后{len(chunk)}')
    if rule_infeatures:
        for rule_infeature in rule_infeatures:
            chunk = chunk[chunk[0].str.contains(rule_infeature)]
        print(f'包含特征处理后{len(chunk)}')
    if rule_regex_features:
        chunk = chunk[chunk[0].apply(lambda x: req(rule_regex_features, x))]
        print(f'正则特征处理后{len(chunk)}')
    return chunk


def transformed(df, rule, inner_func):
    # 开始进行转换
    if 'transform_rule' in rule.keys():
        if rule["transform_rule"]:
            transform_rules = rule["transform_rule"]
            for switch_rule in transform_rules:
                foo = getattr(inner_func, switch_rule["function"])
                df = df.fillna("null")
                df = foo(df, switch_rule["params"], switch_rule["results"])
            return df
    else:
        return df


def switched(df, rule):
    if "replace_rule" in rule.keys():
        if rule["replace_rule"]:
            switched = rule["replace_rule"]
            for oneswitch in switched:
                if oneswitch:
                    if oneswitch["cond2"] == "==":
                        df.loc[df[oneswitch["cond1"]] == oneswitch["cond3"], oneswitch["result1"]] = oneswitch[
                            "result2"]
                    else:
                        df.loc[df[oneswitch["cond1"]] != oneswitch["cond3"], oneswitch["result1"]] = oneswitch[
                            "result2"]
    return df


def get_one_len(reduce_list):
    total = 0
    for one in reduce_list:
        total += len(one)
    return total


def exe_data(ipc, rulemapper, file, lines):
    """
    处理数据包括日志特征提取，正则提取，地区提取，字段补充
    :param chunk_size:
    :param lines:
    :param file:
    :param rule:
    :param partitions:
    :param ipc:
    :param isfirst:
    :param params:
    :return:
    """
    print("开始处理数据")
    alldf = pd.DataFrame(lines)
    print(f"读取数据{alldf.index.size}条")
    print(f"rule_process---start_time{datetime.datetime.now()}")

    # todo 增加匹配特定规则日志和匹配所有规则模式选择
    # 开始逐个匹配日志对应规则
    allchunk = pd.DataFrame(lines)
    for rule in rulemapper[file['rulegroup_id']]['rules']:
        chunk = allchunk
        # 先使用不包含特征排除不合规则的日志
        chunk = ProcessLogByFeatures(rule, chunk)
        if len(chunk) > 0:
            # 开始特征提取
            source_log = chunk[0]
            try:
                # 正则分析
                print(f'开始正则分析{file["file_path"]}')
                reStr = re.compile(rule["extract_rule"])
                chunk = chunk[0].str.extract(reStr)
                print("正则分析完毕")
                chunk.columns = rule["fields"]
                chunk['source_log'] = source_log
                # 这里再次进行一次去除空值处理
                chunk = chunk.dropna()
                if len(chunk) > 0:
                    # 地区提取
                    print("开始处理内置函数")
                    inner_func = InnerFunction()
                    inner_func.setIpCity(ipc)
                    chunk = transformed(chunk, rule, inner_func)
                    print("开始处理替换规则")
                    chunk = switched(chunk, rule)
                    # 补充相应字段
                    # print(f"现有字段 {chunk.columns}")
                    # 文件名中的日期作为日期
                    if not 'mdate' in chunk.columns:
                        chunk['mdate'] = file['filename_mdate']
                    print("规则补充字段阶段")
                    # 先获取原有数据列名，再补充分区信息
                    for column in rule["allfields"]:
                        if column not in chunk.columns:
                            chunk[column] = ""
                    chunk = chunk[rule["allfields"]]
                    # 补充规则
                    if "supplement_rule" in rule.keys():
                        if rule["supplement_rule"]:
                            for field in rule["supplement_rule"].keys():
                                chunk[field] = rule["supplement_rule"][field]
                    print(f"保存前的列有{chunk.columns}")
                    # ip分区和用户名分区
                    # chunk['ip_part'] = chunk['req_ip'].apply(lambda t: ip2long(t))
                    # chunk['username_part'] = chunk['username'].apply(lambda t: get_username_part(t))
                    print(
                        f"fenqu xinxi {file['unit']} - {file['project']} - {rule['analyse']['ana_name']} - {rule['rulegroup']['rulegroup_name']}")
                    # 在此处添加分区信息
                    chunk['unit'] = file['unit']
                    chunk['project'] = file['project']
                    chunk['ana_obj'] = rule['analyse']['ana_name']
                    chunk['rulegroup'] = rule['rulegroup']['rulegroup_name']
                    chunk.to_parquet("tmp.parquet")
                    chunk['rule'] = rule['rule_name']
                    print("合并结束共需要导出" + str(len(chunk)))
                    parquet_data_outputer.save(chunk, file)
                    mysql_opt.UpdatePRM(file['pro_id'], rule['rule_id'])

            except Exception as e1:
                print(f"导出异常{e1}")
        else:
            print("特征处理后不包含数据")


def transAll(ipc, rulemapper, file):
    """
        打开文件根据编译的规则生成提取处理后数据并根据条件输出
    :param reduce_list:
    :param reduce_lock:
    :param rule:
    :param file:
    :param dir_id:
    :param partitions:
    :param ipc:
    :param isfirst:
    :param params:
    :return:
    """
    try:
        print(f"open_file starttimne{datetime.datetime.now()}")
        chunk_count = 0
        print(f"正在处理{file['file_path']},编码{file['file_encode']},已输出有效数据{file['process_length']}")
        mdate_regex = re.compile(r"(20[0|1|2]\d[_|-|/]?[0|1]\d[_|-|/]?[0|1|3]\d)")

        # 在此处为file添加日期属性
        file['filename_mdate'] = ''
        res_result = mdate_regex.findall(file['file_path'])
        if res_result:
            if len(res_result[0]) == 8:
                res_result = f"{res_result[0][0:4]}-{res_result[0][4:6]}-{res_result[0][6:8]}"
            if '_' in res_result[0]:
                res_result = res_result[0].replace('_', '-')
            if '/' in res_result[0]:
                res_result = res_result[0].replace('/', '-')
            file['filename_mdate'] = res_result
    except:
        print("获取时间失败")

    with open(file['file_path'], encoding=file['file_encode']) as fo:
        # 更新文件为正在处理
        mysql_opt.UpdateStatus('file', file['file_id'], 10)
        line_count = 0
        lines = []
        for one_line in fo:
            line_count += 1
            # 先找到开始处理文件的行数
            if file['process_length'] >= line_count:
                continue
            lines.append(one_line)
            # 如果读取的行数等于项目配置指导行数，则就进行处理
            if len(lines) == read_config.chunk_size:
                chunk_count = chunk_count + len(lines)
                exe_data(ipc, rulemapper, file, lines)
                lines = []
                # 更新文件处理数据长度
                mysql_opt.UpdateFileProcess(file['file_id'], process_length=read_config.chunk_size)

        # 处理剩余的数据
        if len(lines) > 0:
            exe_data(rulemapper, file, lines)
            # 更新文件处理数据长度
            mysql_opt.UpdateFileProcess(file['file_id'], process_length=len(lines))
    # 更新文件状态为完成
    mysql_opt.UpdateStatus(file, file['file_id'], 100)
    # 更新文件夹处理文件数
    mysql_opt.UpdateFolder(file['folder_id'])
