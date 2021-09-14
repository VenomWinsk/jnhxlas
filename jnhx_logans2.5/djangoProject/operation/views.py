from django.db.models import Q
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework.viewsets import ModelViewSet, ViewSet
from .serizlizer import BasicRgexModelSerializer, InnerFuncModelSerializer, AnalyseModelSerializer, \
    RuleGroupModelSerializer, RuleModelSerializer, FieldModelSerializer
from .models import BasicRegex, InnerFunc, Analyse, RuleGroup, Rule, Fields
from django.views import View


class BasicRgexModelViewSet(ModelViewSet):
    """
    基础正则ModelViewSet
    """
    queryset = BasicRegex.objects.all()
    serializer_class = BasicRgexModelSerializer


class InnerFuncModelViewSet(ModelViewSet):
    """
    内置函数ModelViewSet
    """
    queryset = InnerFunc.objects.all()
    serializer_class = InnerFuncModelSerializer


class AnalyseModelViewSet(ModelViewSet):
    """
    分析对象ModelViewSet
    """
    queryset = Analyse.objects.all()
    serializer_class = AnalyseModelSerializer


class RuleGroupModelViewSet(ModelViewSet):
    """
    规则组ModelViewSet
    """
    queryset = RuleGroup.objects.all()
    serializer_class = RuleGroupModelSerializer


class RuleModelViewSet(ModelViewSet):
    """
    规则ModelViewSet
    """
    queryset = Rule.objects.all()
    serializer_class = RuleModelSerializer


class FieldModelViewSet(ModelViewSet):
    """
    字段ModelViewSet
    """
    queryset = Fields.objects.all()
    serializer_class = FieldModelSerializer


class GetHxGroke(View):
    """
    获取分析对象-规则组-规则以及字段
    """

    def get(self, request):
        analysis = Analyse.objects.all()
        ser = AnalyseModelSerializer(analysis, many=True)
        return JsonResponse(ser.data, safe=False, json_dumps_params={'ensure_ascii': False})  # safe是放到列中


# class FieldViewSet(ViewSet):

def getfields(request, ana_id=None, rulegroup_id=None, rule_id=None):
    # 根据不同条件查询不同字段
    # 分析对象id是必须的，可以根据分析对象id查找

    if ana_id and not rulegroup_id and not rule_id:
        data = Fields.objects.filter(analyse_id=ana_id)
        ser = FieldModelSerializer(data, many=True)
        # print(ser.data)
        return JsonResponse(ser.data, safe=False)

    # 获取指定规则组包含的字段
    if ana_id and rulegroup_id and not rule_id:
        # if ana_id==0:
        # data = Fields.objects.filter(Q(analyse_id=ana_id) | Q(rulegroup_id=rulegroup_id))
        data = Fields.objects.filter(rulegroup_id=rulegroup_id)
        ser = FieldModelSerializer(data, many=True)
        return JsonResponse(ser.data, safe=False)

    # 如果分析对象id、规则组id和规则id都全，那么根据所有id查找
    if ana_id and rulegroup_id and rule_id:
        # data = Fields.objects.filter(Q(analyse_id=ana_id) | Q(rulegroup_id=rulegroup_id) | Q(rule_id=rule_id))
        data = Fields.objects.filter(rule_id=rule_id)
        ser = FieldModelSerializer(data, many=True)
        return JsonResponse(ser.data, safe=False)
