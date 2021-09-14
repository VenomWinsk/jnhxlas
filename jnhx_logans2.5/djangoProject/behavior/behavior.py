from impala.dbapi import connect
import os
from queryset.models import Offline


def queryimpala(sql1, sql2, selectfields):
    conn = connect(host='192.168.100.83', port=21050, database='hxht_maillog_db')
    cursor = conn.cursor()
    cursor.execute(sql1)
    results = cursor.fetchall()
    if sql2:
        cursor.execute(sql2)
    count = cursor.fetchone()[0]
    cursor.close()
    conn.close()
    data = []
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": count, "data": data}
    return fin


def forcelogana(table, partitions, condations, body):
    """
    爆破登录
    :param table:
    :param partitions:
    :param condations:
    :param body:
    :return:
    """
    area = body.get("body", "all")
    if area == "in":
        condations.append(f"country={area}")
    elif area == "out":
        condations.append(f"country!={area}")
    else:
        pass

    success_count = body.get("success_count", 1)
    fail_count = body.get("fail_count", 1)

    #########

    results = [('144.44.253.15', '中国', 21, 35),
               ('67.229.241.75', '中国', 8, 35),
               ('190.103.43.156', '中国', 11, 35),
               ('151.192.19.178', '中国', 13, 35),
               ('57.231.197.251', '中国', 7, 35),
               ('209.209.254.163', '中国', 29, 35),
               ('19.26.208.227', '中国', 4, 35),
               ('41.99.51.87', '中国', 18, 35),
               ('83.180.27.45', '中国', 25, 35),
               ('108.225.130.114', '中国', 9, 35)]

    selectfields = ['req_ip', 'area', 'fail_count', 'success_count']
    data = []
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": 120, "data": data}
    return fin


def illegalogana(table, partitions, condations, body):
    """
    非法登录分析
    """
    area = body.get("body", "all")
    if area == "in":
        condations.append(f"country={area}")
    elif area == "out":
        condations.append(f"country!={area}")
    else:
        pass
    success_count = body.get("success_count", 1)

    results = [('56.239.141.66', 5, '中国'),
               ('150.172.162.20', 29, '中国'),
               ('79.95.20.255', 17, '中国'),
               ('174.206.249.101', 6, '中国'),
               ('209.184.212.122', 2, '中国'),
               ('215.139.20.56', 12, '中国'),
               ('129.96.254.249', 5, '中国'),
               ('183.24.193.139', 4, '中国'),
               ('177.110.147.192', 20, '中国'),
               ('223.60.96.45', 24, '中国')]

    selectfields = ['req_ip', 'ipcount', 'area']
    data = []
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": 120, "data": data}
    return fin


def strangelogana(table, partitions, condations, body):
    """
    异地登录分析
    """

    country_count = body.get("country_count", 1)
    city_count = body.get("city_count", 1)
    results = [('haojie', 1, 10, 30),
               ('na56', 3, 13, 26),
               ('jingjin', 3, 15, 18),
               ('weichen', 10, 10, 28),
               ('dzeng', 5, 10, 19),
               ('xuexiulan', 5, 15, 28),
               ('yangcai', 6, 15, 16),
               ('tao84', 7, 14, 24),
               ('yong71', 1, 12, 23),
               ('juntan', 9, 15, 27)]

    selectfields = ['username', 'countrycount', 'citycount', 'ipcount']
    data = []
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": 120, "data": data}
    return fin


def illegavisana(table, partitions, condations, body):
    """
    非法访问分析
    """
    area = body.get("body", "all")
    if area == "in":
        condations.append(f"country={area}")
    elif area == "out":
        condations.append(f"country!={area}")
    else:
        pass
    username = body.get("username", 1)

    results = [('16.37.190.236', 6, '中国'), ('177.147.16.192', 7, '中国'), ('206.141.215.191', 10, '中国'),
               ('9.108.47.27', 3, '中国'), ('115.204.195.102', 3, '中国'), ('57.248.5.189', 5, '中国'),
               ('135.68.196.104', 8, '中国'), ('53.15.137.253', 9, '中国'), ('56.33.151.217', 8, '中国'),
               ('128.212.200.60', 8, '中国')]

    data = []
    selectfields = ['req_ip', 'usernamecount', 'area']
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": 120, "data": data}
    return fin


def strangevisana(table, partitions, condations, body):
    """
    异地访问分析
    """

    country_count = body.get("country_count", 1)
    city_count = body.get("city_count", 1)
    results = [('haojie', 1, 10, 30),
               ('na56', 3, 13, 26),
               ('jingjin', 3, 15, 18),
               ('weichen', 10, 10, 28),
               ('dzeng', 5, 10, 19),
               ('xuexiulan', 5, 15, 28),
               ('yangcai', 6, 15, 16),
               ('tao84', 7, 14, 24),
               ('yong71', 1, 12, 23),
               ('juntan', 9, 15, 27)]

    selectfields = ['username', 'countrycount', 'citycount', 'ipcount']
    data = []
    for result in results:
        tmp = {}
        for i in range(len(selectfields)):
            tmp[selectfields[i]] = result[i]
        data.append(tmp)
    fin = {"count": 120, "data": data}
    return fin
