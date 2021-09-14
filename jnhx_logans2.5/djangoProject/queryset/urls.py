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
from django.urls import path, re_path
from . import views
from rest_framework.routers import SimpleRouter, DefaultRouter

urlpatterns = [
    path('qprojects/<int:unit_id>/', views.qprojects),  # 获取单位下指定任务
    path('qindex/<int:unit_id>/', views.qindex),  # 获取单位包含的所有使用的分析对象(包含字段)-规则组(包含字段)-规则(包含字段)
    path('qindex/<int:unit_id>/<int:pro_id>/', views.qindex),  # 获取指定任务下所有使用的分析对象(包含字段)-规则组(包含字段)-规则(包含字段)
    path('queryinfo/', views.queryinfo.as_view()),  # 获取查询结果
    path('makeoffline/', views.queryinfo.as_view()),  # 创建离线任务
    path('bookmarks/', views.BookMarkALL.as_view()),  # 获取书签查询结果
    path('bookmarks/<int:mark_pid>/', views.BookMarkALL.as_view()),  # 获取指定书签查询结果

    path('countrydata/', views.CountryData.as_view()),  # 获取国家数据
    path('citydata/', views.CityData.as_view()),  # 获取城市数据
    # path('test/', views.testMy)  # 获取查询结果
]

router = DefaultRouter()

router.register('offline', views.OfflineModelViewSet, basename='offline')
router.register('bookmark', views.BookMarkModelViewSet, basename='bookmark')
# router.register('rulegroup', views.RuleGroupModelViewSet, basename='rulegroup')
# router.register('rule', views.RuleModelViewSet, basename='rule')
# router.register('field', views.FieldModelViewSet, basename='field')
urlpatterns += router.urls
print(urlpatterns)
