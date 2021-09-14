
import re
from test_log.dao import mysql_opt


class ReEngine(object):
    import_role = None
    pro_id = None
    role_fileds = {}
    error_list = []

    def __init__(self, pro_id):
        self.pro_id = pro_id

    # def refreshRoles(self):
    #     '''
    #     刷新导入项目
    #     :return:
    #     '''
    #     import_tree = mysql_opt.get_import_tree()
    #     if not import_tree:
    #         print("系统未发现新的项目")
    #         return False
    #     self.import_role = import_tree
    #     self.pro_id = import_tree['id']
    #     return True

    def testRoles(self, rule):
        role = {
            "role_name": "test",
            "role_content": rule["extract_rule"],
            "transform": rule["switch_rule"],
            "switch": rule["replace_rule"],
            "role_transed": "",
            "additions": rule["supplement"]
        }
        role["fileds"] = []
        role_content = role["role_content"]
        role["role_transed"] = self.replaceRole(role, role_content)
        print("提取规则" + role["role_content"] + "编译完毕,编译后规则" + role["role_transed"])
        # transform
        if "transform" in role.keys() and role["transform"]:
            role["transformeds"] = self.getTransforms(role["transform"])
            print("转换规则" + str(role["transformeds"]))
        # switch
        if "switch" in role.keys() and role["switch"]:
            role["switched"] = self.getSwitch(role["switch"])
            print("替换规则" + str(role["switched"]))
        return role

    def transOneRole(self, srcStr):
        '''
        替换基本规则
        :return:
        '''
        value = srcStr
        try:
            sql = f'select regex from basic_regex where name = "{srcStr}"'
            cursor = mysql_opt.connect()
            cursor.execute(sql)
            value = cursor.fetchone()[0]
            cursor.close()

            # value = getattr(BaseRe, srcStr)
        except Exception as e:
            if str(type(e))[8:-2] == 'pymysql.err.ProgrammingError':
                error_str = f"数据库中缺少基本正则表basic_regex"
                self.error_list.append(error_str)
            if str(type(e))[8:-2] == 'TypeError':
                error_str = f" 请设置{srcStr}正则内容"
                self.error_list.append(error_str)
        return value

    # def transOneHxRole(self, matchStr):
    #     '''
    #     根据HxGrok语法进行处理
    #     :param matchStr:
    #     :return:
    #     '''
    #     matchStr = matchStr[2:-1]   # 只提取{}内的内容
    #     strArr = matchStr.split('>>')
    #     if len(strArr) != 2:
    #         error_str = f"{matchStr}缺少提取语句或赋值语句"
    #         self.error_list.append(error_str)
    #     reStr = self.transOneRole(strArr[0])  # 从数据库找出正则表达式
    #     field = strArr[1]
    #     return reStr, field

    def replacehxGrok(self, role, matchStr):   # role -->
        '''
        替换单个互信Grok，并且记录下这个规则给那个字段
        :param rolename:
        :param matchStr:
        :return:
        '''
        if not matchStr:
            return ""
        matchStr = matchStr[2:-1]  # 只提取{}内的内容
        strArr = matchStr.split('>>')
        if len(strArr) != 2:
            error_str = f"{matchStr}缺少提取语句或赋值语句"
            self.error_list.append(error_str)
        reStr = self.transOneRole(strArr[0])  # 从数据库找出正则表达式
        field = strArr[1]
        if role["fileds"]:
            role["fileds"].append(field)
        else:
            role["fileds"] = [field]
        return f"({reStr})"

    def replaceRole(self, role, extract_rule):
        '''
        替换整条规则
        :param srcStr:
        :param roleName:
        :return:
        '''
        extract_rule = extract_rule.replace("...", ".*?")
        dst_rule = re.subn(r'%\{.*?\}', lambda x: self.replacehxGrok(role, x.group()), extract_rule) # 把匹配到的替换为对应正则表达式
        return dst_rule[0]

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
                if (len(funcg.groups()) > 2):
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
        ana_object = self.import_role["ana_object"]
        for one in ana_object:
            role_groups = one["role_groups"]
            for role_group_name in role_groups.keys():
                role_group = role_groups[role_group_name]
                for role in role_group["roles"]:
                    # 正则表达式
                    role["fileds"] = []
                    role_content = role["role_content"]
                    role["role_transed"] = self.replaceRole(role, role_content)
                    print("提取规则" + role["role_content"] + "编译完毕,编译后规则" + role["role_transed"])
                    # transform
                    if "transform" in role.keys() and role["transform"]:
                        role["transformeds"] = self.getTransforms(role["transform"])
                        print("转换规则" + str(role["transformeds"]))
                    # switch
                    if "switch" in role.keys() and role["switch"]:
                        role["switched"] = self.getSwitch(role["switch"])
                        print("替换规则" + str(role["switched"]))
                        # role["transformeds"] = self.getTransforms(role["transform"])
                        # print("转换规则" + str(role["transformeds"]))

                if "role_content" in role_group.keys() and role_group["role_content"]:
                    role_group["fileds"] = []
                    role_group["role_transed"] = self.replaceRole(role_group, role_group["role_content"])
                    print("规则" + role_group["code"] + "编译完毕,编译后标题规则" + role_group["role_transed"])

    def runARole(self):
        """"""
        ana_object = self.import_role["ana_object"]
        for one in ana_object:
            role_groups = one["role_groups"]
            for role_group_name in role_groups.keys():
                role_group = role_groups[role_group_name]
                yield one, role_group

    def getCond(self):
        """获取筛选字段"""
        # return None
        return self.import_role["condition"]
