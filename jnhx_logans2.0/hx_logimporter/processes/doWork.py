import pandas as pd
from config import read_config
import dao.es_opt_api as es_opt
import asyncio
from plugins.inner_func import InnerFunction
import dao.parquet_data_outputer as parquet_data_outputer
from tools.hx_logger import Logger
import datetime
import re
from dao import mysql_opt

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


def get_real_log(df, conditions, csv=False):
    df = df
    true_featurs = []
    false_featurs = []
    re_features = []
    condition_list = conditions.split(';')
    for feature in condition_list:
        if feature.startswith("``"):
            false_featurs.append(feature.lstrip('`'))
        elif feature.startswith('re('):
            re_features.append(feature)
        else:
            true_featurs.append(feature)
    logger.warning(f"含有特征有{true_featurs}")
    logger.warning(f"不含有特征有{false_featurs}")
    logger.warning(f"含有正则特征特征有{re_features}")
    if false_featurs:
        for false_featur in false_featurs:
            if csv:
                a = df['req_ip'].str.contains(false_featur)
                a.fillna(True, inplace=True)
                df = df[~a]
                continue
            df = df[~df[0].str.contains(false_featur)]
        logger.warning(f'不包含特征处理后{len(df)}')
    if true_featurs:
        for true_featur in true_featurs:
            df = df[df[0].str.contains(true_featur)]
        logger.warning(f'包含特征处理后{len(df)}')

    if re_features:
        for re_feature in re_features:
            re_feature = re_feature[3:-1]
            df = df[df[0].apply(lambda x: req(re_feature, x))]
        logger.warning(f'包含正则特征处理后{len(df)}')
    return df


def send2ES(loop, index, data):
    limit = 10000
    totalnum = len(data)
    totalpage = int(totalnum / limit) + 1
    logger.warning("总数" + str(totalnum) + "总页数" + str(totalpage))
    cors = []
    for ipage in range(0, totalpage):
        cheng = ipage * limit
        if cheng > totalnum:
            offset = cheng - totalnum
        else:
            offset = limit

        chunk = data[cheng:cheng + offset]
        cors.append(es_opt.import2ES(index=index, list=chunk))
    # cors.append(es_opt.import2ES(index, chunk))
    loop.run_until_complete(asyncio.wait(cors))


def transformed(df, rule, inner_func):
    # 开始进行转换
    logger.warning("开始进行转换")
    if rule["switch_rule"]:
        switch_rule = rule["switch_rule"]
        logger.warning(switch_rule)
        for switch_rule in switch_rule:
            foo = getattr(inner_func, switch_rule["function"])
            df = df.fillna("null")
            df = foo(df, switch_rule["params"], switch_rule["results"])
        return df


def switched(df, rule):
    if "switched" in rule.keys() and rule["switched"]:
        switched = rule["switched"]
        for oneswitch in switched:
            if oneswitch:
                if oneswitch["cond2"] == "==":
                    df.loc[df[oneswitch["cond1"]] == oneswitch["cond3"], oneswitch["result1"]] = oneswitch["result2"]
                else:
                    df.loc[df[oneswitch["cond1"]] != oneswitch["cond3"], oneswitch["result1"]] = oneswitch["result2"]
    return df


def get_one_len(reduce_list):
    total = 0
    for one in reduce_list:
        total += len(one)
    return total


def transOne(srcStr, rule, filenamedict, ipc):
    df = pd.DataFrame([srcStr])
    extract_rule = rule["extract_rule"]
    if extract_rule:
        try:
            reStr = re.compile(extract_rule)
            logger.warning(df)
            logger.warning("++++")
            pf = df[0].str.extract(reStr)
            logger.warning(pf)
            pf = pf.dropna()
            logger.warning("+_+_+_+_+")
            logger.warning(pf)
            # 重命名
            logger.warning("=---------=")
            pf.columns = rule["fileds"]
            inner_func = InnerFunction()
            inner_func.setIpCity(ipc)
            logger.warning(pf.info())
            if pf:
                logger.warning(f"转换11：")
                pf = transformed(pf, rule, inner_func)  # 转换
            logger.warning(f"转换：")
            logger.warning(pf)
            pf = switched(pf, rule)  # 替换
            if filenamedict:
                for key in filenamedict.keys():
                    pf[key] = filenamedict[key]
            if rule["supplement_rule"]:
                for key in rule["supplement_rule"].keys():
                    pf[key] = rule["supplement_rule"][key]
            pf = pf.to_dict("records")
            logger.warning(pf)
            return pf, None

        except Exception as e1:
            error_str = "请正确填写内置函数名或替换表达式"
            return [], error_str


def saveReduce(reduce_list):
    try:
        if read_config.output_type == "parquet":
            pf = pd.concat(reduce_list)
            logger.info("合并结束共需要导出" + str(len(pf)))
            parquet_data_outputer.save(read_config.hdfs_output_dir, pf)
            reduce_list[:] = []
    except Exception as e2:
        logger.warning("汇总数据失败,解锁" + str(e2))


def exe_data(lines, file, rule, partitions, ipc, isfirst, params, chunk_count):
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
    logger.warning("开始处理数据")
    alldf = pd.DataFrame(lines)
    logger.warning(f"读取数据{alldf.index.size}条")
    if params["whereCond"]:
        cond = alldf[0].str.contains(params["whereCond"])
        if not isfirst:
            alldf = alldf[~cond]
        else:
            alldf = alldf[cond]
    if len(alldf) == 0:
        logger.warning(f'{file["filename"]}中没有符合优先条件的数据')
        mysql_opt.update_file_by_id(ftype="data_length",
                                    param={"data_length": 0, "file_id": file['id'], "chunk_count": chunk_count})
        return
    loop = asyncio.get_event_loop()
    logger.warning(f"rule_process---start_time{datetime.datetime.now()}")

    logger.warning("处理日志特征")
    logger.warning(rule["features"])
    if rule["features"]:
        df = get_real_log(alldf, rule["features"])
    else:
        df = alldf
    if len(df) > 0:
        # 开始特征提取
        logger.warning(f"特征处理得到{len(df)}条")
        source_log = df[0]
        try:
            # 正则分析
            logger.warning(f'开始正则分析{file["filename"]}')
            reStr = re.compile(rule["extract_rule"])
            pf = df[0].str.extract(reStr)
            logger.warning("正则分析完毕")
            pf.columns = rule["fileds"]
            pf['source_log'] = source_log
            pf = pf.dropna()
            if len(pf) > 0:
                # 地区提取
                logger.warning("开始地区提取")
                inner_func = InnerFunction()
                inner_func.setIpCity(ipc)
                pf = transformed(pf, rule, inner_func)
                pf = switched(pf, rule)
                # 补充相应字段
                logger.warning(f"现有字段 {pf.columns}")
                # 文件名中的日期作为日期
                if not 'mdate' in pf.columns:
                    pf['mdate'] = params['filename_mdate']
                logger.warning("规则补充字段阶段")
                # 先获取原有数据列名，再补充分区信息
                for column in params["orders"]:
                    if column not in pf.columns:
                        pf[column] = ""
                pf = pf[params["orders"]]
                # 补充规则
                logger.warning(pf.columns)
                if rule["supplement_rule"]:
                    for filed in rule["supplement_rule"].keys():
                        pf[filed] = rule["supplement_rule"][filed]

                # logger.warning("ip_partstarg ")
                # pf['ip_part'] = pf['req_ip'].apply(lambda t: ip2long(t))
                # logger.warning("ip_partzhengchang ")
                # pf['username_part'] = pf['username'].apply(lambda t: get_username_part(t))
                # logger.warning("yusername_partzhengchang ")

                # 在此处添加分区信息
                for partition in partitions.keys():
                    if partition in ["ip_part", "username_part"]:
                        continue
                    pf[partition] = partitions[partition]

                if read_config.output_type == "csv":
                    fpath = read_config.csv_output_dir + file["filename"].split('/')[-1] + ".csv"
                    pf.to_csv(fpath)
                    logger.warning("输出" + str(len(pf)) + "条数据>>" + fpath)
                elif read_config.output_type == "es":
                    send2ES(loop, params["esIndex"], pf)
                    logger.warning("输出" + str(len(pf)) + "条数据>>es:" + params["esIndex"])
                elif read_config.output_type == "logger":
                    logger.warning(str(pf))
                elif read_config.output_type == "parquet":
                    logger.info("合并结束共需要导出" + str(len(pf)))
                    parquet_data_outputer.save(read_config.hdfs_output_dir, pf, file, chunk_count)
        except Exception as e1:
            logger.warning(f"导出异常{e1}")
    else:
        mysql_opt.update_file_by_id(ftype="data_length",
                                    param={"data_length": 0, "file_id": file['id'], "chunk_count": chunk_count})
        logger.warning("未发现数据，跳过")


def transAll(reduce_list, reduce_lock, rule, file, dir_id, partitions, ipc, isfirst, params):
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
        logger.warning("sub process start!!!")
        logger.warning(f"open_file starttimne{datetime.datetime.now()}")
        lines = []
        chunk_count = 0
        logger.warning(f"正在处理{file['filename']},编码{file['file_code']},已输出有效数据{file['data_length']}")
        # mdate_regex = re.compile(r'.*(\d{4}[-|/|_]\d{2}[-|/|_]\d{2}).*')
        mdate_regex = re.compile(r"(20[0|1|2]\d[_|-|/]?[0|1]\d[_|-|/]?[0|1|3]\d)")
        logger.warning(f"{file}")
        res_result = mdate_regex.findall(file['filename'])
        if res_result:
            params['filename_mdate'] = res_result[0]
            if '_' in params['filename_mdate']:
                params['filename_mdate'] = params['filename_mdate'].replace('_', '-')
            if '/' in params['filename_mdate']:
                params['filename_mdate'] = params['filename_mdate'].replace('/', '-')

        logger.warning(f"日期{params['filename_mdate']}")
        with open(file['filename'], encoding=file['file_code']) as fo:
            mysql_opt.update_file_by_id(ftype="status", param={"status": 10, "file_id": file['id']})  # 设置文件状态为正在处理
            for one_line in fo:
                lines.append(one_line)
                if len(lines) == read_config.chunk_size:
                    chunk_count = chunk_count + len(lines)
                    logger.warning(f"{file['filename']} 编码{file['file_code']} 已读 {chunk_count}")
                    # 只有当前读取的数据大于已处理的数据，才进入下一步处理
                    if chunk_count > mysql_opt.query_file_read_size_by_id(file["id"]):
                        exe_data(lines, file, rule, partitions, ipc, isfirst, params, chunk_count)
                    lines = []

            # 执行剩下的数据块，并更新数据库进度
            if len(lines) > 0:
                chunk_count = chunk_count + len(lines)
                if chunk_count > mysql_opt.query_file_read_size_by_id(file["id"]):
                    exe_data(lines, file, rule, partitions, ipc, isfirst, params, chunk_count)
        # 更新文件状态
        mysql_opt.update_file_by_id(ftype="status",
                                    param={"status": 100, "dir_id": dir_id, "file_id": file['id']})  # 设置文件状态为处理完毕
        logger.warning(f"rule_process---end_time{datetime.datetime.now()}")
        logger.warning(f"sql---start_time{datetime.datetime.now()}")
        # mysql_opt_default.update_progress(isfirst, urg_id, len_finder_filelist, filename)
        logger.warning(f"sql---end_time{datetime.datetime.now()}")
    except:
        return False
    return True


def call_transall(reduce_list, reduce_lock, rule, file, dir_id, partitions, ipc, isfirst, params):
    for file_code in ["latin1", "UTF-8", "gbk"]:
        file['file_code'] = file_code
        res = transAll(reduce_list, reduce_lock, rule, file, dir_id, partitions, ipc, isfirst, params)
        if res:
            mysql_opt.update_file_by_id(ftype="file_code",
                                        param={"file_id": file["id"], "file_code": file["file_code"]})
            break
