import urllib
# import logging
# import ujson
import json
import aiohttp
from config import read_config
# from tools.hx_logger import Logger
# logger = Logger(__name__).get_log
host = read_config.es_host

if True:
    pass
def create_max_results_window(index, size):
    try:
        url = "http://" + host + "/" + index + "/_settings"
        headers = {'Content-Type': 'application/json'}  # 设置请求头 告诉服务器请求携带的是json格式的数据
        data = ('{"max_result_window": '+str(size)+'}').encode(encoding="UTF8")
        request = urllib.request.Request(url=url, headers=headers,
                                         data=data)  # 需要通过encode设置编码 要不会报错
        request.get_method = lambda: 'PUT'
        response = urllib.request.urlopen(request)  # 发送请求

        logInfo = response.read().decode()
        # logger.warning("修改max_result_window>"+str(size)+logInfo)
    except Exception as e:
        # logger.error("索引不存在" + index + ":" + str(e))
        pass



def setReplicas(index, size):
    try:
        url = "http://" + host + "/" + index + "/_settings"
        headers = {'Content-Type': 'application/json'}  # 设置请求头 告诉服务器请求携带的是json格式的数据
        data = ('{"index":{"number_of_replicas": '+str(size)+'}}').encode(encoding="UTF8")
        request = urllib.request.Request(url=url, headers=headers,
                                         data=data)  # 需要通过encode设置编码 要不会报错
        request.get_method = lambda: 'PUT'
        response = urllib.request.urlopen(request)  # 发送请求

        logInfo = response.read().decode()
        # logger.warning("修改max_result_window>"+str(size)+logInfo)
    except Exception as e:
        # logger.error("索引不存在" + index + ":" + str(e))
        pass


def create_mapping(mapping_str, url):
    try:
        headers = {'Content-Type': 'application/json'}  # 设置请求头 告诉服务器请求携带的是json格式的数据
        request = urllib.request.Request(url=url, headers=headers,
                                         data=mapping_str.encode(encoding='UTF8'))  # 需要通过encode设置编码 要不会报错
        request.get_method = lambda: 'PUT'
        response = urllib.request.urlopen(request)  # 发送请求

        logInfo = response.read().decode()
        # logger.warning(logInfo)
    except Exception as e:
        # logger.error("索引已存在" + url + ":" + str(e))
        pass



def create_index(map_filename, index_name):
    mapping_url = "http://" + host + "/" + index_name
    # 创建索引
    with open(map_filename) as buf:
        mapping_str = buf.read()
        create_mapping(mapping_str, mapping_url)
    return mapping_str

async def aio_post(url, post_data):
    headers = {'content-type': 'application/json'}
    strd = json.dumps(post_data)
    async with aiohttp.ClientSession() as session:
        async with session.post(url,
                                data=strd, headers=headers) as resp:
            ss = await resp.text()
            # logger.warning("查询结束")

            results = json.loads(ss)
            return results


async def aio_get(url):
    async with aiohttp.ClientSession() as session:
        async with session.get(url) as resp:
            ss = await resp.text()
            # logger.warning("查询结束")

            results = json.loads(ss)
            return results


async def find_by_time(index, beginTime, endTime, size):
    scroll_id = False
    result_list = []
    ctotal = 0
    try:
        while (True):

            results = await findAfter(index, scroll_id=scroll_id, beginTime=beginTime, endTime=endTime, size=size)
            # logger.warning(results)
            scroll_id = results["_scroll_id"]
            data = results["hits"]["hits"]
            if (len(data) == 0): break
            data = results["hits"]["hits"]
            count = len(data)
            ctotal += count
            list = []
            for one in data:
                list.append(one["_source"])
            del data
            result_list.extend(list)
            # logger.warning("读取" + str(count) + "条完毕,总" + str(ctotal))

        return result_list
    except  Exception as e1:
        # logger.warning(str(e1))
        pass

async def find_last_record(index):
    postdata = {
        "query": {
            "match_all": {
            }
        },
        "sort": [
            {
                "startTime": {
                    "order": "desc"
                }
            }
        ],
        "size": 1
    }
    url = 'http://' + host + "/" + index + '/_search'

    results = await aio_post(url, postdata)
    data = results["hits"]["hits"]

    return data[0]


async def findAll(index, size):
    scroll_id = False
    result_list = []
    ctotal = 0
    query = {

        "match_all": {}

    }
    try:
        while (True):

            results = await asyncScan(index=index,
                                      scroll_id=scroll_id, query=query, size=size)
            # logger.warning(results)
            scroll_id = results["_scroll_id"]
            data = results["hits"]["hits"]
            if (len(data) == 0): break
            data = results["hits"]["hits"]
            count = len(data)
            ctotal += count
            list = []
            for one in data:
                list.append(one["_source"])
            del data
            result_list.extend(list)
            # logger.warning("读取" + str(count) + "条完毕,总" + str(ctotal))

        return result_list
    except  Exception as e1:
        # logger.warning(str(e1))
        pass


# 查询一段时间内的数据
async def findAfter(index, scroll_id, beginTime, endTime, size):
    query = {
        "range": {
            "startTime": {
                "gte": beginTime,
                "lte": endTime
            }
        }}

    return await asyncScan(index=index,
                           scroll_id=scroll_id, query=query, size=size)


# 异步扫描数据
async def asyncScan(index, scroll_id, query, size, scroll="5m"):
    url = ""
    if scroll_id:
        url = "http://" + host + "/_search/scroll"
        post_data = {
            "scroll": scroll,
            "scroll_id": scroll_id
        }
    else:
        url = 'http://' + host + "/" + index + '/_search?scroll=' + scroll
        post_data = {
            "query": query,
            "size": size
            # "_source": ["srcAddress", "destAddress", "destPort", "startTime"]
        }
    # logger.warning("开始查询")

    return await aio_post(url, post_data)


async def findIndeisByAlias(alias):
    url = "http://" + host + "/" + alias + "/_alias"
    data = await aio_get(url=url)
    results = []
    for i in data.keys():
        results.append(i)
    results.sort(reverse=True)
    return results


# 利用协程批量添加
async def asyncBulk(strd, host):
    #logger.warning("post请求开始写入es")
    headers = {'content-type': 'application/json'}
    async with aiohttp.ClientSession() as session:
        async with session.post('http://' + host + '/_bulk',
                                data=strd, headers=headers) as resp:
            return await resp.text()
            # logger.warning(a)
            # a = json.loads(a)
            # return a


# 生成批量添加到ES的语句
cdef transdata(index, onelist):
    finalStrs = []
    onearr = onelist.to_dict("records")
    cdef list arr = onearr
    cdef str opt = '{"index": {"_index": "' + index + '", "_type": "doc","_id":"'
    cdef str opt2 = '"}}\n'
    cdef str opt_no_id = '{"index": {"_index": "' + index + '", "_type": "doc"}}\n'
    for one in arr:
        if "_id" in one.keys():
            finalStrs.append(opt)
            if one["_id"]:
                finalStrs.append(one["_id"])
            finalStrs.append(opt2)
        else:
            finalStrs.append(opt_no_id)
        if "_id" in one.keys():
            del one["_id"]
        s = json.dumps(one)
        finalStrs.append(s)
        finalStrs.append("\n")
    final_str = "".join(finalStrs)
    # final_str = str.strip(final_str)
    return final_str


# 调用此函数完成ES添加
async def import2ES(index, list):
    # logger.warning("开始导入数据")
    arr = transdata(index, list)
    #logger.warning("转换之后的格式"+str(arr))
    # filename = str(uuid.uuid1())
    # logger.warning("转化完毕")
    result = await asyncBulk(arr, host)

    #logger.warning("导入返回值"+str(result))
    # logger.warning(str(jsons["error"])+"-"+filename)
    # jsons = json.loads(result)
    # logger.warning(str(result))
    # if "error" in jsons.keys():
    #     logger.warning("错误信息"+str(jsons["error"]))

    # logger.warning("批量添加ES完成" + index + str(len(list)) + "条已完成")
