import time


def write_flag(flag_filename="data/run.flag"):
    """
    保存程序执行的flag到本地
    :param flag_filename:
    """
    with open(flag_filename, 'w') as writter:
        ts = int(time.time())
        writter.write(str(ts))
