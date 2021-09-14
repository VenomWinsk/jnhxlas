import pandas as pd
import os
from tools.hx_logger import Logger

logger = Logger(__name__).get_log
dir = "/alldata/mlogs/26/"

fns = os.listdir(dir)
allchunk = []
for one in fns:
    if "csv" not in one: continue

    onechunk = pd.read_csv(dir + one)
    ff = onechunk["date"].value_counts()
    logger.warning(ff)
    allchunk.append(onechunk)

df = pd.concat(allchunk)

logger.warning("读取完毕")
df = df[["req_ip", "geoip", "fulltime", "result", "username"]]
logger.warning("写入完毕")
df.to_csv("/alldata/mlogs/check26.csv")
df = df[~df["geoip"].str.contains("中国")]
logger.warning("写入完毕")
df.to_csv("/alldata/mlogs/new26.csv")
# df.to_parquet("/alldata/mlogs/all24.parq")
