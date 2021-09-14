import socket
import struct

cdef class IpCity():
    cdef list geo_list

    def set_basedata(self, geo_list):
        self.geo_list = geo_list

    def binary_search(self, int begin, int end, long long input):
            if end >= begin:
                mid = (begin + end) / 2
                mid_data = self.geo_list[mid]
                if (input >= mid_data["start_ip"] and input <= mid_data["end_ip"]):
                    return mid_data
                elif input > mid_data["start_ip"]:
                    begin = mid + 1
                    return self.binary_search(begin=begin, end=end, input=input)
                elif input < mid_data["end_ip"]:
                    end = mid - 1
                    return self.binary_search(begin=begin, end=end, input=input)
            return {"qcc":"未知地区","contry":"未知国家"}

    def get_geo(self, input):
        # cdef char* input_str = input
        if input=="null": return ""
        s = socket.inet_aton(input)
        _long_struct = struct.Struct('!L')
        lone = _long_struct.unpack(s)
        lone = lone[0]
        cdef long long lone_int = lone
        mid_data = self.binary_search(begin=1, end=len(self.geo_list) - 1, input=lone_int)
        # cdef str qcc = qcc
        return mid_data

