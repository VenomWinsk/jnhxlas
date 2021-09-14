from django.shortcuts import render
from rest_framework.viewsets import ModelViewSet
from rest_framework.filters import OrderingFilter
from .models import Unit
from .serialzier import UnitSerializer, UserSerializer
from django.http import HttpResponse


def say_hello(request):
    return HttpResponse("访问成功")


class UnitModelViewSet(ModelViewSet):
    """
    单位ModelViewSet
    """
    queryset = Unit.objects.all()
    serializer_class = UnitSerializer


class UserModelViewSet(ModelViewSet):
    """
    用户ModelViewSet
    """
    queryset = Unit.objects.all()
    serializer_class = UserSerializer
    # def get_serializer_class(self):
    #     if self.action == 'lastdata':
    #         return UnitSerializer
    #     else:
    #         return UnitSerializer

    # # 指定排序方法类
    # filter_backends = [OrderingFilter]
    # # 指定排序字段
    # ordering_fields = ('unit_id',)
