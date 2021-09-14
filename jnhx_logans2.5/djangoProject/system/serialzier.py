from rest_framework import serializers
from .models import Unit, User


class UnitSerializer(serializers.ModelSerializer):
    # 显示指明字段
    unit_id = serializers.IntegerField(max_value=10000, min_value=0, required=False)
    unit_name = serializers.CharField(max_length=100, min_length=3,
                                      error_messages={'min_length': '最短必须超过3', 'required': '必须填写'})
    unit_description = serializers.CharField(max_length=200, min_length=0, required=False, allow_blank=True,
                                             allow_null=True)

    class Meta:
        model = Unit  # 指定生成字段的模型类
        # fields = ('btitle', 'bread')  # 指定模型类中的字段
        fields = '__all__'  # 指定模型类中所有字段


class UserSerializer(serializers.ModelSerializer):
    # 显示指明字段

    user_id = serializers.IntegerField(max_value=10000, min_value=0)
    username = serializers.CharField(max_length=20)
    password = serializers.CharField(max_length=20)
    user_description = serializers.CharField(max_length=100)

    class Meta:
        model = Unit  # 指定生成字段的模型类
        # fields = ('btitle', 'bread')  # 指定模型类中的字段
        fields = '__all__'  # 指定模型类中所有字段
