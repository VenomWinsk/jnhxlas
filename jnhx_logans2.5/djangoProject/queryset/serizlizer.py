import datetime
import uuid

from rest_framework import serializers
from operation.models import Analyse, RuleGroup, Rule, Fields
from queryset import pretreatment
from .models import Offline, BookMark
from .utils import queryimpala


class BookMarkModelSerializer(serializers.ModelSerializer):
    """
    书签序列化
    """
    mark_id = serializers.IntegerField(required=False)
    mark_name = serializers.CharField(max_length=200, required=False)
    mark_type = serializers.IntegerField(required=False, )
    mark_qtype = serializers.CharField(required=False, allow_null=True)
    # mark_pid = serializers.IntegerField(source="mark.mark_id", allow_null=True)
    mark_pid = serializers.IntegerField(required=False, allow_null=True)
    is_root = serializers.IntegerField(required=False, allow_null=True)
    mark_condations = serializers.CharField(required=False, max_length=2048, allow_null=True)
    is_cached = serializers.IntegerField(required=False, default=0)
    cache_name = serializers.CharField(required=False, read_only=True)
    createtime = serializers.DateTimeField(required=False, default=datetime.datetime.now())

    class Meta:
        model = BookMark
        fields = '__all__'

    def validate(self, attrs):
        # mark_names = BookMark.objects.values_list('mark_name', flat=True)
        # if attrs['mark_name'] in mark_names:
        #     raise serializers.ValidationError(f"已存在{attrs['mark_name']}")
        # if attrs['mark_condations'] in mark_names:
        #     raise serializers.ValidationError(f"已存在同样查询条件")
        return attrs

    def create(self, validated_data):
        bookmark = BookMark.objects.create(
            mark_name=validated_data['mark_name'],
            mark_type=validated_data['mark_type'],
            mark_qtype=validated_data["mark_qtype"],
            mark_pid=validated_data["mark_pid"],
            is_root=validated_data["is_root"],
            mark_condations=validated_data['mark_condations'],
            createtime=validated_data['createtime'],
            is_cached=validated_data["is_cached"],
            # cache_name=validated_data["cache_name"],
        )
        if validated_data["is_cached"]:
            print("设置缓存")
        return bookmark

    def update(self, instance, validated_data):
        print(validated_data)
        if 'mark_name' in validated_data.keys():
            instance.mark_name = validated_data['mark_name']
        if 'is_cached' not in validated_data.keys():
            instance.save()
            return instance

        if validated_data['is_cached'] == 1:
            if not instance.cache_name and instance.mark_condations:
                body = eval(instance.mark_condations)
                # 创建缓存
                res = pretreatment.process(body)
                selectfields = res.get("selectfields", False)
                partitions = res.get("partitions", False)
                condations = res.get("condations", False)

                # 这里默认获取到bookmark的参数就是创建bookmark
                table = res.get('table')
                instance.cache_name = f'hxht_maillog_db.cache_{str(uuid.uuid4()).replace("-", "")}'
                queryimpala.gen_cache(table, instance.cache_name, selectfields, partitions, condations)
                instance.is_cached = validated_data['is_cached']
        instance.save()
        return instance


class OfflineModelSerializer(serializers.ModelSerializer):
    """
    离线任务序列化
    """
    off_id = serializers.IntegerField()
    offline_name = serializers.CharField(max_length=32)
    download_file = serializers.CharField(max_length=32)
    offline_status = serializers.IntegerField()
    filesize = serializers.FloatField()
    sdatetime = serializers.DateTimeField(read_only=True)
    datetime = serializers.DateTimeField(read_only=True)

    class Meta:
        model = Offline
        fields = '__all__'
