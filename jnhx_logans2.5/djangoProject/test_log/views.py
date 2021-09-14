import json
import os
import time
from rest_framework.views import APIView
from rest_framework.response import Response
from test_log.re_engine.main_engine import ReEngine
from test_log.plugins.plugins_api import IpCity
from test_log.process import doWork
import pandas as pd
from test_log.config import read_config


# Create your views here.

def en_escape(input):
    # input2 = re.escape(input)
    # logger.warning(input2)
    # result = input.replace("\\>", "\\")
    return input


def safe_get(input, key):
    if key in input.keys() and input[key]:
        return input[key]
    else:
        return ""


print("开始加载基础数据")
ipc = IpCity()
ip_citys = pd.read_parquet(os.path.join(read_config.project_path, "data\ip_city_results.parquet"))


geo_list = ip_citys.to_dict(orient="records")
print("加载ip归属地列表完成")
ipc.set_basedata(geo_list)
del ip_citys

main_engine = ReEngine("")

class testRuleVIew(APIView):

    def post(self, request):

        try:
            data = request.data.copy()
            print(data)
            src_str = en_escape(safe_get(data, "src_str"))
            src_str.replace(r"\\s", r"\s")
            rule = request.data.copy()
            rule = main_engine.testRoles(rule)
            t1 = time.time()
            pf, error_str = doWork.transOne(src_str, rule, {}, ipc)
            print(pf, error_str)
            t2 = time.time()
            t = (t2 - t1) * 1000
            result = pf
            print(result)
            if len(result) > 0:
                result[0]['executetime'] = t
            result.append({"flag": 1})
            if error_str:
                main_engine.error_list.append(error_str)
        except Exception as e1:
            main_engine.error_list.append("测试程序启动失败，请联系管理员")

        result_is_list = True
        if result is None or len(main_engine.error_list) != 0:
            result = {"error": ';'.join(main_engine.error_list),
                      "flag": 0}
            main_engine.error_list = []
            result_is_list = False
        if result is not None and result_is_list:
            with open(os.path.join(os.path.dirname(os.path.abspath(__file__)), "dao", "key_word.txt")) as f:
                key_words = f.readlines()
                key_words = list(map(lambda x: x.replace("\n", ""), key_words))
                for key in result[0].keys():
                    if key.lower() in key_words:
                        main_engine.error_list.append(f"{key}不可作为提取变量名")
                        result = {"error": ';'.join(main_engine.error_list),
                                  "flag": 0}
                        break

        jsonstr = json.dumps(result)



        return Response(result)
