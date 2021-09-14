import os
import re
import pymysql
import sys
import json
import uuid


def connect(mysql_infos):
    # 连接数据库
    conn = pymysql.Connect(
        host=mysql_infos['mysql_host'],
        port=int(mysql_infos['mysql_port']),
        user=mysql_infos['mysql_user'],
        passwd=mysql_infos['mysql_password'],
        db=mysql_infos['mysql_db'],
        charset='utf8'
    )
    try:
        conn.autocommit(True)
        cursor = conn.cursor()
        return cursor
    except:
        print("数据库链接失败" + mysql_infos['mysql_host'] + "" + str(mysql_infos['mysql_port']))


def dirpath(lpath, lfilelist=None, file_re=None):
    """
    输出一个文件夹下所有文件（包括子文件夹的文件）,输入肯定不含空格
    """
    if lfilelist is None:
        lfilelist = []
    dirlist = os.listdir(lpath)
    for f in dirlist:
        file = os.path.join(lpath, f)
        if " " in f:
            file = f.replace(" ", "")
            os.rename(file, f)
        if os.path.isdir(file):
            dirpath(file.replace(" ", ""), lfilelist, file_re)
        else:
            if file_re:
                if file_re.search(file):
                    lfilelist.append(file)
            else:
                lfilelist.append(file)
    return lfilelist


def get_file_infos(lfilelist):
    """
    输出文件的编码及大小
    """
    file_infos = []
    one_file = {}

    for filename in lfilelist:
        # 获取文件编码
        one_file = {
            "filename": filename,
            "status": 0,
            "data_length": 0
        }
        try:
            check_file = '"' + filename + '"'
            check_code = f'/usr/bin/file -b {check_file}'
            res = os.popen(check_code).readlines()[0]
            one_file['filecode'] = res.split()[0]
            # 获取文件大小
            check_size = f'du -s {check_file}'
            res = os.popen(check_size).readlines()[0]
            rex = re.compile("^\d{0,15}")
            one_file['file_size'] = int(rex.findall(res)[0])
        except Exception as e:
            one_file['file_size'] = -1,
            one_file['filecode'] = -1
        file_infos.append(one_file)
    return file_infos


def query_files_by_dir(dir_path, file_re):
    file_re = re.compile(file_re)
    lfilelist = dirpath(dir_path, file_re=file_re)
    file_infos = get_file_infos(lfilelist)
    return file_infos


def insert_file_records(cursor, filedir_id, files):
    # sql = f'delete from file_records where filedir_id = "{filedir_id}"'
    # print("清空文件夹")
    # cursor.execute(sql)
    data = [(str(filedir_id), file['filename'], file['file_size'], file['filecode']) for file in files]
    if data:
        sql = "insert into file_records(filedir_id, filename, file_size,  file_code) values(%s, %s, %s,%s)"
        cursor.executemany(sql, data)


def insert_dir_records(cursor, used_rule_group_id, dir_name, filenums, files):
    """
    在数据库中更新文件夹状态
    :param cursor:
    :param used_rule_group_id:
    :param dir_name:
    :param filenums:
    :return:
    """
    sql = f'select id from dir_records where used_rule_group_id = "{used_rule_group_id}" and dirname = "{dir_name}"'
    cursor.execute(sql)
    dir_id = cursor.fetchone()
    if dir_id:
        print(f"{dir_name} 文件记录已存在 - rulegroupid {used_rule_group_id}")
        # 若该文件夹的文件记录数是否和files不相等，则删除后重新插入
        sql = f'select count(id) from file_records where filedir_id = "{dir_id[0]}"'
        cursor.execute(sql)
        if cursor.fetchone()[0] != len(files):
            sql = f'delete from file_records where filedir_id="{dir_id[0]}"'
            cursor.execute(sql)
            print(f"{dir_name}数据库记录和现有记录不匹配，删除数据库已存在记录")
            insert_file_records(cursor, dir_id[0], files)
            print(f"{dir_name} 缺少文件记录{len(files)}条，已补全")
    else:
        try:
            dir_id = str(uuid.uuid4())
            sql = f'insert into dir_records(id,used_rule_group_id,dirname,filenums) values("{dir_id}","{used_rule_group_id}","{dir_name}","{filenums}") '
            cursor.execute(sql)
            print(f" 记录 {dir_name} 相关数据 - rulegroupid {used_rule_group_id}")
            insert_file_records(cursor, dir_id, files)
            print(f" 记录文件 {len(files)} 条，- rulegroupid {used_rule_group_id}")
        except Exception as e:
            print(f"插入文件夹失败,{e}")


def query_dirs(cursor, used_rule_group_id, inputpath, file_re):
    """
    查询文件夹
    :param cursor:
    :param used_rule_group_id:
    :param inputpath:
    :param file_re:
    :param used_rule_group_status: 应该是规则状态，但是一个规则组绑定了一个规则，所以跟规则组状态一样
    :return:
    """
    file_re = re.compile(file_re)
    file_dirs = []
    for file_dir in inputpath.split(";"):
        if os.path.exists(file_dir):
            filedir = {
                "dir_name": file_dir,
                "dir_status": 1,
                "wasscanned": 1,
                "files": query_files_by_dir(file_dir, file_re),
                "processednums": 0,
            }
            filedir['filenums'] = len(filedir['files'])

            for i in filedir["files"]:
                print(i["filename"])

            import time
            time.sleep(100)
            insert_dir_records(cursor, used_rule_group_id, filedir['dir_name'], filedir['filenums'], filedir['files'])
            file_dirs.append(filedir)
    if file_dirs:
        return file_dirs
    else:
        return None


def query_file_re(cursor, used_rule_group_id):
    sql = f'select file_regex from used_rule_group where id="{used_rule_group_id}"'
    cursor.execute(sql)
    file_re = cursor.fetchone()[0]
    return file_re


if __name__ == '__main__':
    mysql_infos = {
        "mysql_host": '192.168.100.81',
        "mysql_port": 3306,
        "mysql_user": 'root',
        "mysql_password": 'fengxiaoxiaoxi',
        "mysql_db": 'logprocess_re'
    }

    used_rule_group_id = "194c70dd-91f4-4c0a-be76-1a062cea17e2"
    dirpaths = "/data/data2/kangxiaofan0218/source/txt/6/"
    file_re = ".*\.txt"

    # x = sys.argv[1].split("+")
    # print(x)
    # mysql_infos = {
    #     "mysql_host": x[0],
    #     "mysql_port": x[1],
    #     "mysql_user": x[2],
    #     "mysql_password": x[3],
    #     "mysql_db": x[4]
    # }
    #
    # used_rule_group_id = sys.argv[2]
    # dirpaths = sys.argv[3]
    # file_re = sys.argv[4]

    cursor = connect(mysql_infos)
    file_re = query_file_re(cursor, used_rule_group_id)
    print(file_re)
    # query_dirs(cursor, used_rule_group_id, dirpaths, file_re)
    # mysql_infos = '{"mysql_host": "192.168.100.81", "mysql_port": 3306, "mysql_user": "root", "mysql_password": "fengxiaoxiaoxi", "mysql_db": "logprocess_re"}'
    # used_rule_group_id = '1f051f3e-b4bf-4e5d-abf0-40e09f0d0c58'
    # dirpaths = '/data/data2/kangxiaofan0218/source/12月31日/'
    # file_re = '.*'
    # print(mysql_infos)
