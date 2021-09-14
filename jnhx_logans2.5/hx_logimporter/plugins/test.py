import struct
import socket
import pandas as pd


class IpCity:
    def set_databases(self, geo_list):
        self.geo_list = geo_list

    def binary_search(self, begin, end, input):
        if end >= begin:
            mid = (begin + end) / 2
            mid_data = self.geo_list[mid]
            if input >= mid_data["start_ip"] and input <= mid_data["end_ip"]:
                return mid_data
            elif input > mid_data["start_ip"]:
                begin = mid + 1
                return self.binary_search(begin=begin, end=end, input=input)
            elif input < mid_data["end_ip"]:
                end = mid - 1
                return self.binary_search(begin=begin, end=end, input=input)
        return {"qcc": "未知地区", "contry": "未知国家"}

    def get_geo(self, input):
        if input == "null":
            return ""
        s = socket.inet_aton(input)
        _long_struct = struct.Struct('!L')
        lone = _long_struct.unpack(s)
        lone = lone[0]
        lone_int = lone
        mid_data = self.binary_search(begin=1, end=len(self.geo_list) - 1, input=lone_int)
        return mid_data


# import os
# print(os.getcwd())
# file = "/opt/hxht/hx_logana3.0/hx_logimporter/data/ip_city_results.parquet"
#
#
# ipc = IpCity()
# ip_citys = pd.read_parquet(file)
# print("11")
# # ip_citys = pd.read_csv(read_config.dir_path + "/ip_city_results.csv", header=None, sep=",",
# #                        names=["num", "start_ip", "end_ip", "qcc", "contry"], low_memory=False)
# geo_list = ip_citys.to_dict(orient="records")
# print(geo_list)
# print(1)