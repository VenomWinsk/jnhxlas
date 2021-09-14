from configparser import ConfigParser
import os

project_path = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# 读取配置文件

cp = ConfigParser()
cp.read(project_path + "/config/config.cfg", encoding='UTF-8')

log_output_path = cp.get("log", "log_output_path")

# 导入索引名称
process_mode = cp.get("process", "process_mode")
work_pool = cp.getint("process", "work_pool")
refresh = cp.get("process", "refresh")
chunk_size = cp.getint("process", "chunk_size")

output_type = cp.get("output", "type")
output_fs_type = cp.get("output", "fs_type")
hdfs_ha = cp.getboolean("output", "hdfs_ha")
hdfs_host = cp.get("output", "hdfs_host")

hdfs_output_dir = cp.get("output", "hdfs_output_dir")
csv_output_dir = cp.get("output", "csv_output_dir")
es_host = cp.get("output", "es_host")
es_chunksize = cp.get("output", "es_chunksize")
impala_create_partition_shell = cp.get("output", "impala_create_partition_shell")
impala_create_table_shell = cp.get("output", "impala_create_table_shell")

# mysql
mysql_host = cp.get("mysql", "mysql_host")
mysql_port = cp.getint("mysql", "mysql_port")
mysql_user = cp.get("mysql", "mysql_user")
mysql_password = cp.get("mysql", "mysql_password")
mysql_db = cp.get("mysql", "mysql_db")

# testweb
test_web_host = cp.get("testweb", "host")
