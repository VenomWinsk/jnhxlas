"""djangoProject URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.urls import path
from . import views
from rest_framework.routers import SimpleRouter, DefaultRouter

urlpatterns = [

    path('verypath/', views.VeryPath),  # 查询任务下所有文件夹
    path('folders/<int:pro_id>/', views.GetFolersByProId),  # 查询任务下所有文件夹
    path('folder/<int:folder_id>/', views.GetFolerByFolderId),  # 查询任务下所有文件夹
    path('files/<int:folder_id>/', views.GetFilesByFolderId)  # 查询任务下所有文件夹
    # path('entry/folder_records/<int:folder_id>/', views.InitPro.as_view()) # 查询指定文件夹
]

router = DefaultRouter()
router.register('project', views.ProjectModelViewSet, basename='project')
# router.register('folder_records', views.FolderRecordsModelViewSet, basename='folder_records')
router.register('file_records', views.FileRecordsModelViewSet, basename='file_records')
urlpatterns += router.urls
print(urlpatterns)
