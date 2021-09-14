from django.utils import timezone as datetime

from rest_framework import serializers
from operation.models import RuleGroup
from .models import Project, FolderRecords, FileRecords
import os
from .utils import validate_file, validate_status


class FileRecordsModelSerializer(serializers.ModelSerializer):
    """
    文件序列化
    """
    file_id = serializers.IntegerField(read_only=True)
    file_path = serializers.CharField(read_only=True)
    folder_records = serializers.IntegerField(source="folder_records.folder_id")
    file_status = serializers.IntegerField(read_only=True)
    last_status = serializers.IntegerField(read_only=True)
    file_encode = serializers.CharField(read_only=True)
    data_length = serializers.IntegerField(read_only=True)
    rulegroup = serializers.IntegerField(source="rulegroup.rulegroup_id")
    process_length = serializers.IntegerField(read_only=True)
    output_data = serializers.IntegerField(read_only=True)

    class Meta:
        model = FileRecords
        fields = '__all__'


class FolderRecordsModelSerializer(serializers.ModelSerializer):
    """
    文件夹序列化
    """
    folder_id = serializers.IntegerField(required=False)
    folderpath = serializers.CharField(read_only=True)
    project = serializers.IntegerField(source="project.pro_id", read_only=True)
    folder_status = serializers.IntegerField(read_only=True)
    last_status = serializers.IntegerField(read_only=True)
    filecounts = serializers.IntegerField(read_only=True)
    processed_files = serializers.IntegerField(read_only=True)
    process = serializers.SerializerMethodField()

    class Meta:
        model = FolderRecords
        fields = '__all__'

    def get_process(self, obj):
        data = obj.processed_files / obj.filecounts
        return data


class ProjectModelSerializer(serializers.ModelSerializer):
    """
    导入任务序列化
    """

    pro_id = serializers.IntegerField(required=False)
    pro_name = serializers.CharField(required=False, max_length=32, min_length=0)
    pro_status = serializers.IntegerField(required=False, default=0)
    last_status = serializers.IntegerField(required=False, default=0)
    data_path = serializers.CharField(required=False, max_length=128, min_length=0, default=0)
    unit = serializers.IntegerField(required=False, source='unit.unit_id')
    pro_description = serializers.CharField(required=False, max_length=128, allow_null=True, allow_blank=True)
    create_time = serializers.DateTimeField(required=False, default=datetime.datetime.now())
    process = serializers.SerializerMethodField()

    def get_process(self, obj):

        tmp = FolderRecords.objects.filter(project=obj.pro_id).values_list("filecounts", "processed_files")
        sum1 = sum([i[0] for i in tmp])
        if sum1 == 0:
            return 0
        data = sum([i[1] for i in tmp]) / sum1

        return data

        # return FolderRecords.objects.values_list("")
        # return dir(obj)

    class Meta:
        model = Project
        fields = '__all__'

    def validate(self, attrs):
        """
        判断路径是否存在或者是是否包含识别日志
        :param attrs:
        :return:
        """
        # paths = attrs['data_path'].split(";")
        # file_features = RuleGroup.objects.values_list('file_features', flat=True)
        # for path in paths:
        #     if not os.path.exists(path):
        #         raise serializers.ValidationError(f"{path}不存在")
        # if not validate_file(file_features, path):
        #     raise serializers.ValidationError(f"{path}未找到识别日志")
        return attrs

    def create(self, validated_data):
        """
        创建项目
        :param validated_data:
        :return:
        """
        print(validated_data)
        project = Project.objects.create(
            data_path=validated_data['data_path'],
            pro_name=validated_data['pro_name'],
            unit_id=validated_data['unit']['unit_id'],
            pro_description=validated_data['pro_description'],
            pro_status=0,
            last_status=0,
            create_time=validated_data['create_time']

        )
        # for path in validated_data['data_path'].split(";"):
        #     FolderRecords.objects.create(project_id=validated_data['id'], folder_status=0, last_status=0, filecounts=0,
        #                                  processed_files=0, folderpath=path)
        #     FolderRecords.save()
        return project

    def update(self, instance, validated_data):
        """
        更改任务状态
        :param instance:
        :param validated_data:
        :return:
        """
        # 获取当前的任务状态，#validated_data['pro_status']中包含要改的数据状态
        # now_status = Project.objects.get(pro_id=validated_data['pro_id']).pro_status
        # 任务验证
        instance.pro_status = validated_data['pro_status']

        # if validate_status(validated_data['pro_status'], now_status):
        #     instance.pro_status = validated_data['pro_status']
        #     """
        #     这里需要设置该任务下文件夹状态和文件状态
        #     """
        #     instance.last_status = now_status
        instance.save()
        return instance
