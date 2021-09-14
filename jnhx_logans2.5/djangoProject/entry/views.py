import os

from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.viewsets import ModelViewSet
from .serizlizer import ProjectModelSerializer, FolderRecordsModelSerializer, FileRecordsModelSerializer
from .models import Project, FolderRecords, FileRecords
from django.views import View


def VeryPath(request):
    """
    验证datapath是否存在
    :param request:
    :param datapaths:
    :return:
    """
    if request.method == 'POST':
        for i in list(request.POST.values())[0].split(";"):
            if not os.path.exists(i):
                return HttpResponse("false")
    return HttpResponse("true")


def GetFolersByProId(request, pro_id):
    """
    获取指定任务的文件夹的数据
    :param request:
    :param pro_id:
    :return:
    """
    data = FolderRecords.objects.filter(project_id=pro_id)
    ser = FolderRecordsModelSerializer(data, many=True)
    return JsonResponse(ser.data, safe=False)


def GetFolerByFolderId(request, folder_id):
    """
    获取指定文件夹id的文件夹数据
    :param request:
    :param pro_id:
    :return:
    """
    data = FolderRecords.objects.get(folder_id=folder_id)
    ser = FolderRecordsModelSerializer(data)
    return JsonResponse(ser.data, safe=False)


def GetFilesByFolderId(request, folder_id):
    """
    获取指定文件夹id的文件数据
    :param request:
    :param pro_id:
    :return:
    """
    data = FileRecords.objects.filter(folder_records_id=folder_id)
    ser = FileRecordsModelSerializer(data, many=True)
    return JsonResponse(ser.data, safe=False)


class ProjectModelViewSet(ModelViewSet):
    """
    规则ViewSet
    """
    queryset = Project.objects.all()
    serializer_class = ProjectModelSerializer


class FolderRecordsModelViewSet(ModelViewSet):
    """
    规则ModelViewSet
    """
    queryset = FolderRecords.objects.all()
    serializer_class = FolderRecordsModelSerializer


class FileRecordsModelViewSet(ModelViewSet):
    """
    规则ModelViewSet
    """
    queryset = FileRecords.objects.all()
    serializer_class = FileRecordsModelSerializer


class Model(View):
    """
    获取
    """

    def post(self, request):
        print(request)
        return HttpResponse("hello")
