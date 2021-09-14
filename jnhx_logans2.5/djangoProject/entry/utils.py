import os
import re


def get_file(path, file_list=None):
    if file_list is None:
        file_list = []
    dir_list = os.listdir(path)
    for x in dir_list:
        new_x = os.path.join(path, x)
        if os.path.isdir(new_x):
            get_file(new_x, file_list)
        else:
            file_list.append(new_x)
    return file_list


def validate_file(regex_list, filepath):
    """
    验证文件是否符合正则
    :param regex_list:
    :param filepath:
    :return:
    """
    regexs = []
    for regex in regex_list:
        regex = re.compile(regex)
        regexs.append(regex)
    for file in get_file(filepath):
        for regex in regexs:
            if regex.match(file):
                return True
    return False


def validate_status(fulture_status, now_status):
    if now_status in [0, 100, -100]:
        return False

    if now_status == 1:
        if fulture_status in [-1, 10]:
            return True
        return False

    if now_status == 10:
        if fulture_status in [-10, -100, 100, ]:
            return True
        return False

    if now_status == -10:
        if fulture_status in [10, -100]:
            return True
        return False
