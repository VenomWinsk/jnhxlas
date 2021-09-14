import pandas as pd
import re
from config import read_config
import asyncio
from plugins.inner_func import InnerFunction
from dao import mysql_opt
import datetime
import re
from tools.hx_logger import Logger

logger = Logger(__name__).get_log
import time
from itertools import islice


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


def req(rex,astr):
    rex = re.compile(rex)
    x =rex.match(astr)
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
        elif feature.startswith('re'):
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
            logger.warning(re_feature)
            re_feature = re_feature[3:-1]
            df = df[df[0].apply(lambda x:req(re_feature,x))]
        logger.warning(f'包含正则特征处理后{len(df)}')

    return df




def transformed(df, role, inner_func):
    # 开始进行转换
    logger.warning("开始进行转换")
    if "transformeds" in role.keys() and role["transformeds"]:
        transformeds = role["transformeds"]
        for trans in transformeds:
            foo = getattr(inner_func, trans["function"])
            df = df.fillna("null")
            df = foo(df, trans["params"], trans["results"])
    return df


def switched(df, role):
    if "switched" in role.keys() and role["switched"]:
        switched = role["switched"]
        for oneswitch in switched:
            if oneswitch:
                if (oneswitch["cond2"] == "=="):
                    df.loc[df[oneswitch["cond1"]] == oneswitch["cond3"], oneswitch["result1"]] = oneswitch["result2"]
                else:
                    df.loc[df[oneswitch["cond1"]] != oneswitch["cond3"], oneswitch["result1"]] = oneswitch["result2"]
    return df


def get_one_len(reduce_list):
    total = 0
    for one in reduce_list:
        total += len(one)
    return total


def transOne(srcStr, role, filenamedict, ipc):
    df = pd.DataFrame([srcStr])
    roleTransed = role["role_transed"]
    if roleTransed:
        try:
            reStr = re.compile(roleTransed)
            logger.warning(f"编译后规则： {reStr}")
            pf = df[0].str.extract(reStr)
            logger.warning(pf)
            pf = pf.dropna()
            # 重命名
            pf.columns = role["fileds"]
            inner_func = InnerFunction()
            inner_func.setIpCity(ipc)
            # logger.warning(pf)
            pf = transformed(pf, role, inner_func)
            # logger.warning(pf.columns)
            pf = switched(pf, role)
            for key in filenamedict.keys():
                pf[key] = filenamedict[key]
            if "additions" in role.keys() and role["additions"]:
                # print(type(role["additions"]))
                # role_additions = eval(role["additions"])
                for key in role["additions"]:
                    pf[key] = role["additions"][key]
            # logger.warning(pf)
            # pf["fulltime"] = pf["date"] + " " + pf["time"]
            pf = pf.to_dict("records")
            return pf, None

        except Exception as e1:
            error_str = "请正确填写内置函数名或替换表达式"
            return [], error_str








