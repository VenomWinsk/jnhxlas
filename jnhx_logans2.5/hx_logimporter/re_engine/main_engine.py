import re

import pandas as pd

from config import read_config
# from dao import mysql_opt_default
from dao import mysql_opt
from tools.hx_logger import Logger
from twa import IpCity

logger = Logger(__name__).get_log


class ReEngine:
    rulemapper = None
    import_tree = None
    rule_fields = {}
    error_list = []
    refresh = True
    ipc = None

    def __init__(self):
        self.rulemapper = mysql_opt.get_rulemapper()


    # def refreshRoles(self):
    #     '''
    #     刷新导入项目
    #     :return:
    #     '''
    #     import_tree = mysql_opt.get_import_tree()
    #     if not import_tree:
    #         print("系统未发现新的项目，等待10秒查询")
    #         return False
    #     self.import_tree = import_tree
    #     return True

    def testRoles(self, rule):
        rule["fields"] = []
        extract_rule = rule["extract_rule"]
        rule["extract_rule"] = self.replaceRole(rule, extract_rule)
        # print(f'提取规则编译完毕,编译后规则 + {rule["extract_rule"]}')
        # transform
        if "switch_rule" in rule.keys() and rule["switch_rule"]:
            rule["switch_rule"] = self.getTransforms(rule["switch_rule"])
            # print(f'转换规则{rule["switch_rule"]}')
        # switch
        if "replace_rule" in rule.keys() and rule["replace_rule"]:
            rule["replace_rule"] = self.getSwitch(rule["replace_rule"])
            # print(f'替换规则{rule["replace_rule"]}')
        return rule

    def transOneRole(self, srcStr):
        '''
        替换基本规则
        :return:
        '''
        value = srcStr

        try:
            sql = f"select regex_context from basic_regex where regex_name = '{srcStr}'"
            cursor = mysql_opt.connect()
            cursor.execute(sql)
            value = cursor.fetchone()[0]
            cursor.close()

        except Exception as e:
            if str(type(e))[8:-2] == 'pymysql.err.ProgrammingError':
                error_str = f"数据库中缺少基本正则表basic_regex"
                self.error_list.append(error_str)
            if str(type(e))[8:-2] == 'TypeError':
                error_str = f"请设置{srcStr}正则内容"
                self.error_list.append(error_str)
        return value

    def transOneHxRole(self, srcStr):
        '''
        根据HxGrok语法进行处理
        :param srcStr:
        :return:
        '''
        ss = srcStr[2:-1]
        strArr = ss.split('>>')
        if len(strArr) != 2:
            error_str = f"{srcStr}缺少提取语句或赋值语句"
            self.error_list.append(error_str)
        reStr = self.transOneRole(strArr[0])
        field = strArr[1]

        return reStr, field

    def replacehxGrok(self, rule, srcStr):
        '''
        替换单个互信Grok，并且记录下这个规则给那个字段
        :param rulename:
        :param srcStr:
        :return:
        '''
        if not srcStr:
            return ""
        reStr, field = self.transOneHxRole(srcStr)

        if rule["fields"]:
            rule["fields"].append(field)
        else:
            rule["fields"] = [field]
        return "(" + reStr + ")"

    def replaceRole(self, rule, srcStr):
        '''
        替换整条规则
        :param srcStr:
        :param ruleName:
        :return:
        '''
        srcStr = srcStr.replace("...", ".*?")
        dstStr = re.subn(r'%{.*?}', lambda x: self.replacehxGrok(rule, x.group()), srcStr)
        return dstStr[0]

    def getParams(self, params, srcStr):
        '''
        提取表达式中的参数
        :param params:
        :param srcStr:
        :return:
        '''
        num = 0
        index = 0
        start = 0
        for one in srcStr:
            if one == "'":
                num += 1
                if num % 2 == 0:
                    end = index
                    params.append(srcStr[start:end])
                    num = 0
                else:
                    start = index + 1
            index += 1
        return params

    def getTransforms(self, srcStr):
        '''
        解析转换函数逻辑
        :param srcStr:
        :return:
        '''
        transforms = []
        tsArr = srcStr.split(";")
        if len(tsArr) == 0:
            return transforms

        for ts in tsArr:
            # 拆分函数和原变量
            valueArr = ts.split(">>")
            if len(valueArr) < 2:
                return transforms
            funcStr = valueArr[0]
            tarStr = valueArr[1]
            tarArr = tarStr.split(",")
            reStr = r'(.*?):\$(.*?)\((.*?)\)'
            funcg = re.search(reStr, funcStr)

            if not funcg:
                reStr2 = r'(.*?):\$(.*?)$'
                funcg = re.search(reStr2, funcStr)
            if funcg:
                fir = funcg.group(1)
                func = funcg.group(2)
                params = [fir]
                if len(funcg.groups()) > 2:
                    paramStr = funcg.group(3)
                    self.getParams(params, paramStr)
                transforms.append({
                    "function": func,
                    "params": params,
                    "results": tarArr
                })
        return transforms

    def getSwitch(self, srcStr):
        '''
        :param srcStr:
        :return:
        '''
        switched = []
        swArr = srcStr.split(";")
        if len(swArr) == 0:
            return switched
        for ts in swArr:
            g = re.search(r'(.*?)([=!]=)(.*?)>>(.*?)=(.*?)$', ts)
            if g:
                cond1 = g.group(1)
                cond2 = g.group(2)
                cond3 = g.group(3)
                v1 = g.group(4)
                v2 = g.group(5)
                switched.append({
                    "cond1": cond1,
                    "cond2": cond2,
                    "cond3": cond3,
                    "result1": v1,
                    "result2": v2,
                })
        return switched

    def build(self):
        '''
        编译规则
        :return:
        '''

        for rulegroup_id in self.rulemapper.keys():
            for rule in self.rulemapper[rulegroup_id]["rules"]:
                rule["fields"] = []
                # print(f"{rule['rule_id']}: 编译规则-- {rule['rule_name']}")
                rule["extract_rule"] = self.replaceRole(rule, rule["extract_rule"])
                # print("提取规则编译完毕,编译后规则" + rule["extract_rule"])

                # 转换
                if rule["transform_rule"]:
                    rule["transform_rule"] = self.getTransforms(rule["transform_rule"])
                    # print("转换规则" + str(rule["transform_rule"]))
                # 替换
                if rule["replace_rule"]:
                    rule["replace_rule"] = self.getSwitch(rule["replace_rule"])
                    # print("替换规则" + str(rule["replace_rule"]))

    def runARole(self):
        """
        返回一个分析对象和一个规则组
        :return:
        """
        for ana_object in self.rulemapper['ana_objects']:
            for one_rule_group in ana_object["rule_groups"]:
                rulegroup = one_rule_group['rule_group_name']
                yield ana_object, rulegroup

    def getCond(self):
        """获取筛选字段"""
        # return self.rulemapper["condition"]
        return None


# reEngine = ReEngine()
# reEngine.build()
# print(reEngine.rulemapper[15]['rules'][0]['extract_rule'])
# print(reEngine.rulemapper[15]['rules'][0]['transform_rule'])
