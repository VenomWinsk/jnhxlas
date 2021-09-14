import resource
import time
import psutil

p = psutil.Process()
print(p.pid)


def limit_memory(maxsize):
    soft, hard = resource.getrlimit(resource.RLIMIT_AS)
    resource.setrlimit(resource.RLIMIT_AS, (maxsize, hard))

limit_memory(1024*1024*180)   # 限制180M ，可申请内存，对应虚拟内存

lst = []
while True:
    lst.append("a"*1000000)
    time.sleep(0.5)