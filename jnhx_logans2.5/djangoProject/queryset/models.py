import datetime

from django.db import models

from entry.models import Project
from operation.models import Analyse, RuleGroup, Rule


class BookMark(models.Model):
    """
    书签表
    """
    mark_id = models.AutoField(primary_key=True)  # 书签id
    mark_name = models.CharField(max_length=200)  # 书签名称
    mark_type = models.IntegerField()  # 书签类型 1 是书签 0是文件夹
    mark_qtype = models.CharField(max_length=18, null=True, blank=True)  # 查询类型
    # mark_pid = models.IntegerField(null=True, blank=True)
    # mark_pid = models.ForeignKey("self", on_delete=models.CASCADE, null=True, blank=True)
    mark_pid = models.IntegerField(null=True, blank=True)
    is_root = models.IntegerField()  # 是否是一级分类
    mark_condations = models.CharField(max_length=2048, null=True, blank=True)  # 查询条件
    is_cached = models.IntegerField(default=0)  # 是否缓存
    cache_name = models.CharField(max_length=32, null=True, blank=True)  # 缓存名称
    createtime = models.DateTimeField(default=datetime.datetime.now())  # 创建时间

    class Meta:
        db_table = 'bookmark'


class Offline(models.Model):
    """
    离线任务表
    """
    off_id = models.AutoField(primary_key=True)
    offline_name = models.CharField(max_length=32)
    offline_status = models.IntegerField()
    filesize = models.FloatField()
    download_file = models.CharField(max_length=32)  # 保存路径
    sdatetime = models.DateTimeField(default=datetime.datetime.now())
    edatetime = models.DateTimeField(default=datetime.datetime.now())

    class Meta:
        db_table = 'offline'


class ProjectRuleMapper(models.Model):
    """
    导入任务和使用规则对应
    """
    # prm_id = models.AutoField(primary_key=True)
    project = models.ForeignKey(Project, on_delete=models.CASCADE)
    rule = models.ForeignKey(Rule, on_delete=models.CASCADE)

    class Meta:
        db_table = 'prm'
        unique_together = (("project", "rule"),)

# class DataCache()
