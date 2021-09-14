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
    path('hxgroke/', views.GetHxGroke.as_view()),
    # path('getfields/', views.getfields),  # 获取默认字段
    path('getfields/<int:ana_id>/', views.getfields),  # 获取分析对象字段
    path('getfields/<int:ana_id>/<int:rulegroup_id>/', views.getfields),  # 获取分析对象和规则组字段
    path('getfields/<int:ana_id>/<int:rulegroup_id>/<int:rule_id>/', views.getfields)  # 获取分析对象和规则组以及规则字段

    # path('system/unit/', views.UnitModelViewSet.as_view({'get': 'list', 'post': 'create'}))
]

router = DefaultRouter()
router.register('basicregex', views.BasicRgexModelViewSet, basename='basicregex')
router.register('innerfunc', views.InnerFuncModelViewSet, basename='innerfunc')
router.register('analyse', views.AnalyseModelViewSet, basename='analyse')
router.register('rulegroup', views.RuleGroupModelViewSet, basename='rulegroup')
router.register('rule', views.RuleModelViewSet, basename='rule')
router.register('field', views.FieldModelViewSet, basename='field')
urlpatterns += router.urls
print("operation")
print(urlpatterns)
