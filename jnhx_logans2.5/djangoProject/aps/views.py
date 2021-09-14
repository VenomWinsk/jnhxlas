import datetime
import os
from queryset.models import Offline

from django.shortcuts import render

# Create your views here.
from apscheduler.schedulers.background import BackgroundScheduler
from django_apscheduler.jobstores import DjangoJobStore, register_events, register_job
from django_apscheduler.models import DjangoJobExecution

# 实例化调度器
scheduler = BackgroundScheduler()
# 调度器使用默认的DjangoJobStore()
scheduler.add_jobstore(DjangoJobStore(), 'default')


#  参考资料
# https://blog.csdn.net/weixin_41524366/article/details/106832708

# 每天8点半执行这个任务
# @register_job(scheduler, 'cron', id='test', hour=8, minute=30)
# @register_job(scheduler, 'interval', id='test2', hours=0, minutes=0, seconds=2)
def test2():
    for one in Offline.objects.all():
        if one.offline_status == 100:
            continue
        if os.path.exists(one.download_file + ".zip"):
            one.filesize = os.stat(one.download_file + ".zip").st_size
            one.download_file = one.download_file + ".zip"
            one.edatetime = datetime.datetime.now()
            one.offline_status = 100
        else:
            one.filesize = os.stat(one.download_file).st_size
            one.offline_status = 10
        # print(one.filesize)
        one.save()


# @register_job(scheduler, 'interval', id='delete', hours=0, minutes=1)
def delete_old_job_executions():
    DjangoJobExecution.objects.delete_old_job_executions(150)


# 注册定时任务并开始
register_events(scheduler)
scheduler.start()
"""
# create table tmp_1DBF8B1233C43130AE3DD6B8E1134AA9 STORED as parquet as select * from hxht_maillog_db.t_iis_source where 1=1 and req_ip in ('111.196.69.104') and unit = 'test'  and rule_group = 'iis_txt_rg'
# create table tmpaaaa STORED as parquet as select mdate,time,username,req_ip,country,city,sourcelog,get_size,opt,port,req_ip2,req_method,response,result,send_size,server_ip,tail_ip,to_page,user_agent,rule,unit,project,ana_obj,rulegroup,ip_part,username_part from hxht_maillog_db.t_test_table where unit='test_unit' and project='test_pro' and rulegroup='test_rg' and req_ip like '223.104.158.10%'
"""