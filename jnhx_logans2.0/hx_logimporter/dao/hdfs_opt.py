import pyarrow as pa
import os
import time
from tools.hx_logger import Logger
from config import read_config

logger = Logger(__name__).get_log


class HDFSOpt():
    connected = False
    current_url = ""
    current_num = 0
    host = ""
    port = 8020

    def __init__(self):
        if read_config.hdfs_ha == True:
            logger.warning("当前模式为高可用")
        else:
            logger.warning("当前模式关闭高可用")

    def is_connected(self, fs):
        try:
            # logger.warning("开始检测在不在")
            fs.ls("/home")
            return True
        except:
            return False

    def dis_connected(self):
        self.connected = False

    def get_host(self):
        if read_config.hdfs_ha:
            cmdresult = os.popen(
                "sudo -u hdfs hdfs haadmin -getAllServiceState | grep 'active' | awk '{print $1}'").read()
            ss = cmdresult.split(":")
            host = ss[0]
        else:
            host = read_config.hdfs_host
        return host

    def get_fs(self):
        try:
            if not self.connected:
                self.host = self.get_host()
            fs = pa.hdfs.connect(self.host, self.port, user='root', driver='libhdfs')
            logger.warning("hdfs预连接成功，hdfs NameNode地址" + self.host)
            self.connected = True

            return fs

        except Exception as e1:
            logger.warning("获取HDFS请求失败" + str(e1))
            self.connected = False
            time.sleep(15)
            return self.get_fs()
