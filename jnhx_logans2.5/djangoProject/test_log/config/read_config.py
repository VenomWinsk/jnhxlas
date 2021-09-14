from configparser import ConfigParser
import os

project_path = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# 读取配置文件

cp = ConfigParser()
cp.read(project_path + "/config/config.cfg", encoding='UTF-8')

log_output_path = cp.get("log", "log_output_path")


# mysql
mysql_host = cp.get("mysql", "mysql_host")
mysql_port = cp.getint("mysql", "mysql_port")
mysql_user = cp.get("mysql", "mysql_user")
mysql_password = cp.get("mysql", "mysql_password")
mysql_db = cp.get("mysql", "mysql_db")

# testweb
test_web_host = cp.get("testweb", "host")
