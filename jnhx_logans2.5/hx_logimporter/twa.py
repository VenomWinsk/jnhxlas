# from plugins import ip_city as ip_city
from plugins import test as ip_city

IpCity = ip_city.IpCity

# import pandas as pd

# print()
# ipc = IpCity()
# ip_citys = pd.read_parquet("data/ip_city_results.parquet")
# ip_citys = pd.read_csv(read_config.dir_path + "/ip_city_results.csv", header=None, sep=",",
#                        names=["num", "start_ip", "end_ip", "qcc", "contry"], low_memory=False)
# geo_list = ip_citys.to_dict(orient="records")
# print(geo_list)
# ipc.set_basedata(geo_list)
