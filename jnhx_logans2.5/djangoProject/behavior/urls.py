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

urlpatterns = [

    # path('', views.Behavior.as_view())  # 爆破登录分析
    path('forcelogana/', views.Behavior.as_view()),  # 爆破登录分析
    path('illegalogana/', views.Behavior.as_view()),  # 非法登录分析
    path('strangelogana/', views.Behavior.as_view()),  # 异地登录分析
    path('illegavisana/', views.Behavior.as_view()),  # 非法访问分析
    path('strangevisana/', views.Behavior.as_view())  # 异地访问分析
]
