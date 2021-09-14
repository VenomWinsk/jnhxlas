from django.utils import timezone as datetime

from django.db import models
from django.utils import timezone

from system.models import Unit
from operation.models import RuleGroup, Rule


class Project(models.Model):
    """
    定义导入任务
    """
    pro_id = models.AutoField(primary_key=True)
    pro_name = models.CharField(max_length=32)
    pro_status = models.IntegerField(default=0)
    last_status = models.IntegerField(default=0)
    data_path = models.CharField(max_length=1024)
    unit = models.ForeignKey(Unit, on_delete=models.CASCADE)
    pro_description = models.CharField(max_length=128)
    create_time = models.DateTimeField(default=datetime.datetime.now())
    start_time = models.DateTimeField(default=datetime.datetime.now())
    end_time = models.DateTimeField(default=datetime.datetime.now())

    class Meta:
        db_table = 'project'


class FolderRecords(models.Model):
    """
    文件夹类
    """
    folder_id = models.AutoField(primary_key=True)
    folderpath = models.CharField(max_length=128, null=True)
    project = models.ForeignKey(Project, on_delete=models.CASCADE)
    folder_status = models.IntegerField(default=0, null=True)
    last_status = models.IntegerField(default=0, null=True)
    filecounts = models.IntegerField(default=0, null=True)
    processed_files = models.IntegerField(default=0, null=True)

    class Meta:
        db_table = 'folder_records'


class FileRecords(models.Model):
    """
    文件
    """
    file_id = models.AutoField(primary_key=True)
    file_path = models.CharField(max_length=128)
    folder_records = models.ForeignKey(FolderRecords, on_delete=models.CASCADE, null=True, blank=True)
    rulegroup = models.ForeignKey(RuleGroup, on_delete=models.CASCADE)
    rule = models.ForeignKey(Rule, on_delete=models.CASCADE, null=True, blank=True)
    file_status = models.IntegerField(default=0, blank=True)
    last_status = models.IntegerField(default=0, null=True, blank=True)
    file_encode = models.CharField(max_length=8, null=True, blank=True)
    data_length = models.IntegerField(default=0, null=True, blank=True)
    process_length = models.IntegerField(default=0, null=True, blank=True)
    output_data = models.IntegerField(default=0, null=True, blank=True)

    class Meta:
        db_table = 'file_records'
