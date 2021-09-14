import os
import sys

filedir = sys.argv[1]
exec_sql = f"mkdir {filedir}"
os.system(exec_sql)
i = 0
j = 1
fo = None
while 1:
    res = sys.stdin.readline()
    if not res:
        # with open('/opt/hxht/logimporter/hx-log/a.txt','w')as f:
        #    f.write(str(res))
        #    f.write(str(i))
        break
    if i % 100000 == 0:
        if fo:
            fo.flush()
            fo.close()
        fo = open(f"{filedir}/f{j}.csv", "a")
        j += 1
    fo.write(res)
    i += 1
fo.flush()
fo.close()
exec_sql = f"/usr/bin/zip -r {filedir}.zip {filedir}"
os.system(exec_sql)
exec_sql = f"rm -r {filedir}"
os.system(exec_sql)
