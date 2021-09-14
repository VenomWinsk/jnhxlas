import socket


def valid_ip(ip):
    # 验证ip是否合法
    try:
        socket.inet_aton(ip)
        return True
    except:
        return False


def ip2long(ip_list):
    # 获取ip分区
    ipparts = []
    for ip in ip_list:
        check = valid_ip(ip)
        if check is False:
            ipparts.append(0)
            continue
        tmp = ip.split(".")
        long_ip = (int(tmp[0]) << 24) + (int(tmp[1]) << 16) + (int(tmp[2]) << 8) + int(tmp[3])
        ipparts.append(long_ip % 10)
    return ipparts


def get_username_part(username_list):
    # 获取用户名分区
    username_parts = []
    for username in username_list:
        username_part = 0
        i = 0
        for s in username:
            if i > 3:
                break
            username_part += ord(s)
            i += 1
        username_parts.append(username_part % 10)
    return username_parts

