import os


def create_partitions(tablename, unit, project, ana_obj, rule_group, ip_part=0, username_part=0):
    tablename = tablename
    cmdstr = f"/usr/bin/bash /opt/hxht/hx_logprocess/email_ct/bin/hxht-task-createPartitions.sh   {tablename} {unit} {project} {ana_obj} {rule_group} {ip_part} {username_part}"
    print(cmdstr)
    s = os.popen(cmdstr).read()
    print(s)


for i in range(1, 10):
    for j in range(0, 10):
        tablename = "t_iis_source"
        unit = "test"
        project = "fccd304a-1814-4925-a390-9e86ff1ee1d6"
        ana_obj = "iis"
        rule_group = "t_iis_txt"
        ip_part = 0
        username_part = 0
        create_partitions(tablename, unit, project, ana_obj, rule_group, i,  j)

shuaxin = f'impala-shell -q " INVALIDATE METADATA;"'
os.popen(shuaxin)