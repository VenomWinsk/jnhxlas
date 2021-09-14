import re
from dao import mysql_opt_default
from dao import mysql_opt
from tools.hx_logger import Logger

logger = Logger(__name__).get_log


class ReEngine(object):
    import_tree = None
    pro_id = None
    rule_fileds = {}
    error_list = []
    refresh = True

    def __init__(self, pro_id):
        self.pro_id = pro_id

    def refreshRoles(self):
        '''
        刷新导入项目
        :return:
        '''
        import_tree = mysql_opt.get_import_tree()
        if not import_tree:
            logger.warning("系统未发现新的项目，等待10秒查询")
            return False
        self.import_tree = import_tree
        # self.pro_id = import_tree['pro_id']
        return True

    def testRoles(self, rule):
        rule["fileds"] = []
        extract_rule = rule["extract_rule"]
        rule["extract_rule"] = self.replaceRole(rule, extract_rule)
        logger.warning(f'提取规则编译完毕,编译后规则 + {rule["extract_rule"]}')
        # transform
        if "switch_rule" in rule.keys() and rule["switch_rule"]:
            rule["switch_rule"] = self.getTransforms(rule["switch_rule"])
            logger.warning(f'转换规则{rule["switch_rule"]}')
        # switch
        if "replace_rule" in rule.keys() and rule["replace_rule"]:
            rule["replace_rule"] = self.getSwitch(rule["replace_rule"])
            logger.warning(f'替换规则{rule["replace_rule"]}')
        return rule

    def transOneRole(self, srcStr):
        '''
        替换基本规则
        :return:
        '''
        value = srcStr
        try:
            sql = f'select regex from basic_regex where name = "{srcStr}"'
            cursor = mysql_opt_default.connect()
            cursor.execute(sql)
            value = cursor.fetchone()[0]
            cursor.close()

        except Exception as e:
            if str(type(e))[8:-2] == 'pymysql.err.ProgrammingError':
                error_str = f"数据库中缺少基本正则表basic_regex"
                self.error_list.append(error_str)
            if str(type(e))[8:-2] == 'TypeError':
                error_str = f" 请设置{srcStr}正则内容"
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
        a, b = self.transOneHxRole(srcStr)

        if rule["fileds"]:
            rule["fileds"].append(b)
        else:
            rule["fileds"] = [b]
        return "(" + a + ")"

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
        for object in self.import_tree["ana_objects"]:
            for rule_group in object["rule_groups"]:
                rule = rule_group['rule']
                rule["extract_rule"] = self.replaceRole(rule, rule["extract_rule"])
                logger.warning("提取规则编译完毕,编译后规则" + rule["extract_rule"])
                # 转换
                if rule["switch_rule"]:
                    rule["switch_rule"] = self.getTransforms(rule["switch_rule"])
                    logger.warning("转换规则" + str(rule["switch_rule"]))
                # 替换
                if rule["replace_rule"]:
                    rule["replace_rule"] = self.getSwitch(rule["replace_rule"])
                    logger.warning("替换规则" + str(rule["replace_rule"]))
                    # rule["transformeds"] = self.getTransforms(rule["transform"])
                    # logger.warning("转换规则" + str(rule["transformeds"]))
                rule_group["fields"] = []
                rule_group["file_regex"] = self.replaceRole(rule_group, rule_group["file_regex"])
                logger.warning("规则" + rule_group["rule_group_name"] + "编译完毕,编译后标题规则" + rule_group["file_regex"])

    def runARole(self):
        """
        返回一个分析对象和一个规则组
        :return:
        """
        for ana_object in self.import_tree['ana_objects']:
            # ana_object = self.import_tree["ana_object"]
            for one_rule_group in ana_object["rule_groups"]:
                rule_group = one_rule_group['rule_group_name']
                yield ana_object, rule_group

    def getCond(self):
        """获取筛选字段"""
        # return self.import_tree["condition"]
        return None
