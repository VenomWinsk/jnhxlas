from flask import Flask, request, make_response
from flask_cors import CORS
from plugins.plugins_api import IpCity
import json
from config import read_config
import pandas as pd
from process import doWork
from re_engine.main_engine import ReEngine
from flask_apscheduler import APScheduler
import time


class Config(object):  # 创建配置，用类
    # 任务列表
    JOBS = [
        {  # 第一个任务，每隔5S执行一次
            'id': 'job1',
            'func': '__main__:write_flag',  # 方法名
            # 'args':(1,2) ,  # 入参
            'trigger': 'interval',  # interval表示循环任务
            'seconds': 5,
        }
    ]


def write_flag():
    """
    保存程序执行的flag到本地
    """
    with open("data/run.flag", 'w') as writter:
        ts = int(time.time())
        writter.write(str(ts))


app = Flask(__name__)
app.config.from_object(Config())  # 为实例化的flask引入配置

print("开始加载基础数据")
ipc = IpCity()
ip_citys = pd.read_parquet(read_config.project_path + "/data/ip_city_results.parquet")

geo_list = ip_citys.to_dict(orient="records")
print("加载ip归属地列表完成")
ipc.set_basedata(geo_list)
del ip_citys

main_engine = ReEngine("")


def de_escape(input):
    # str.replace(input,)
    # str.format()
    result = input.replace("\\", "\\\\")
    return result


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


@app.route("/test_rule", methods=["POST"])
def index():
    '''
    测试规则服务
    :return:
    '''
    result = ""
    t = None
    try:
        data = request.get_data(as_text=True)
        data = json.loads(data)

        src_str = en_escape(safe_get(data, "src_str"))
        src_str.replace(r"\\s", r"\s")

        extract_rule = en_escape(safe_get(data, "extract_rule"))
        switch_rule = en_escape(safe_get(data, "switch_rule"))
        replace_rule = en_escape(safe_get(data, "replace_rule"))
        supplement = en_escape(safe_get(data, "supplement"))
        rule = {
            "extract_rule": extract_rule,
            "switch_rule": switch_rule,
            "replace_rule": replace_rule,
            "supplement": supplement
        }
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
        with open("/opt/hxht/hx_logprocess/webserver/dao/key_word.txt") as f:
            key_words = f.readlines()
            key_words = list(map(lambda x: x.replace("\n", ""), key_words))
            for key in result[0].keys():
                if key.lower() in key_words:
                    main_engine.error_list.append(f"{key}不可作为提取变量名")
                    result = {"error": ';'.join(main_engine.error_list),
                              "flag": 0}
                    break

    jsonstr = json.dumps(result)
    resp = make_response(jsonstr)

    return resp


if __name__ == '__main__':
    scheduler = APScheduler()
    scheduler.init_app(app)
    scheduler.start()
    CORS(app, reprints=r'/*')
    app.run(host=read_config.test_web_host)
    # print(read_config.test_web_host)
