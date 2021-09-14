from django.core.serializers.json import DjangoJSONEncoder
from operation.serizlizer import FieldModelSerializer
from queryset.serizlizer import BookMarkModelSerializer, OfflineModelSerializer
from system.models import Unit
from rest_framework.viewsets import ModelViewSet
from django.views import View
from django.http import JsonResponse
from entry.models import Project
from operation.models import Fields, Rule, RuleGroup, Analyse
from queryset.models import Offline, ProjectRuleMapper, BookMark
from entry.serizlizer import ProjectModelSerializer
from queryset.utils import queryimpala, getpartitions
import json
import uuid


class queryinfo(View):
    """
    获取查询详情
    """

    def get(self, requets):
        pass

    def post(self, request):
        body = eval(request.body)
        print(body)
        partitions = []  # 查询分区，会并入到查询条件中
        condations = []  # 查询条件,

        # 根据单位、任务、规则组、规则获取该层面上的字段，得到查询内容
        # 获取剩余的查询字段，并组成查询条件
        unit_id = body.get("unit_id", False)
        unit_name = Unit.objects.get(unit_id=int(unit_id)).unit_name
        partitions.append(f"unit='{unit_name}'")

        project_id = body.get('project_id', False)
        if project_id:
            project = Project.objects.get(pro_id=int(project_id)).pro_name
            partitions.append(f"project='{project}'")

        analyse_id = body.get('analyse_id', False)
        analyse_name = Analyse.objects.get(ana_id=int(analyse_id)).ana_name
        partitions.append(f"ana_obj='{analyse_name}'")

        table = f"t_{analyse_name}_source"

        rulegroup_id = body.get('rulegroup_id', False)
        if rulegroup_id:
            rulegroup_name = RuleGroup.objects.get(rulegroup_id=int(rulegroup_id)).rulegroup_name
            partitions.append(f"rulegroup='{rulegroup_name}'")

        rule_id = body.get('rule_id', False)
        if rule_id:
            rule_name = Rule.objects.get(rule_id=rule_id)
            condations.append(f"rule={rule_name}")
        fields = []  # 获取field集合
        if rule_id:
            tmp = list(Fields.objects.filter(rule_id=rule_id).values_list("field_cname", "field_ename", "field_index"))
            fields.extend(tmp)
        if rulegroup_id:
            tmp = list(Fields.objects.filter(rulegroup_id=rulegroup_id).values_list("field_cname", "field_ename",
                                                                                    "field_index"))
            fields.extend(tmp)
        if analyse_id:
            tmp = list(
                Fields.objects.filter(analyse_id=analyse_id).values_list("field_cname", "field_ename", "field_index"))
            fields.extend(tmp)
        fields.sort(key=lambda field: field[2])
        # 获取查询字段
        selectfields = [i[1] for i in fields]

        partitions = []  # 查询分区，会并入到查询条件中
        condations = []  # 查询条件,
        allfields = list(body)
        qtype = body.get("qtype", "comment")
        # 获取查询条件
        for field in allfields:

            if field in ["area", "export_file", "offset", "qtype", "unit_id", "analyse_id", "project_id",
                         "rulegroup_id", "rule_id", "bookmark_id",
                         "is_ip", "rule_id", "qtype"]:
                continue
            # 获取分区条件
            condation = body.get(field, False)
            if not condation:
                continue
            if field in ["req_ip", "username"]:
                if field == 'req_ip':
                    # 如果查找字段是ip，则可以多值，但是无法设置分区
                    # 如果单值,可以设置分区
                    # 如果查找字段是网段,则不能有分区
                    is_ip = body.get("is_ip")
                    condation = condation.split(";")
                    if str(is_ip) == 1:
                        if len(condation) == 1:
                            ip_part = getpartitions.ip2long(condation)  # 获取ip_part
                            partitions.append(f"ip_part='{ip_part[0]}'")
                            condations.append(f"req_ip='{condation[0]}'")
                        else:
                            condations.append(f"req_ip in {tuple(condation)}")
                    else:
                        # 网段查询,无ip_part
                        if len(condation) == 1:
                            condations.append(f"req_ip like '{condation[0]}%'")
                        else:
                            condations.append(f"req_ip like {'and req_ip like'.join(condation)} ")
                    continue

                if field == 'username':
                    # 多值无分区，单值有分区  # 不模糊
                    condation = body.get(field, False)
                    if not condation:
                        continue
                    condation = condation.split(";")
                    if len(condation) == 1:
                        usernamepart = getpartitions.get_username_part(condation)  # 获取分区
                        partitions.append(f"username_part={usernamepart}")
                        condations.append(f"username like '%{condation[0]}%'")
                    else:
                        condations.append(f"username in {tuple(condation)}")
                    continue
            if field in ["country", "city"]:
                condation = body.get(field, False)
                if not condation:
                    continue
                condation = condation.split(";")
                if len(condation) == 1:
                    condations.append(f"{field}='{condation}'")
                # 获取普通字段
                else:
                    condations.append(f"{field} in {tuple(condation)}")
                continue
            if field in ["user_agent", "sourcelog"]:
                condation = body.get(field, False)
                condations.append(f"{field} like '%{condation}%'")
                continue
            if field in ["mdate", "time"]:
                condation = body.get(field, False)
                condation = condation.split(";")
                condations.append(f"{field}>='{condation[0]}' and {field}<='{condation[1]}'")
                continue
            if field == "rule_name":
                condations.append(f"rule='{condation}'")
                continue
            condations.append(f"{field}='{condation}'")

        offset = body.get("offset", 0)
        table = "hxht_maillog_db.t_test_table"
        partitions = ["unit='test_unit'", "project='test_pro'", "rulegroup='test_rg'"]
        selectfields = ['mdate', 'time', 'username', 'req_ip', 'country', 'city', 'sourcelog', 'get_size', 'opt',
                        'port',
                        'req_ip2', 'req_method', 'response', 'result', 'send_size', 'server_ip',
                        'tail_ip', 'to_page', 'user_agent',
                        'rule', 'unit', 'project', 'ana_obj', 'rulegroup', 'ip_part',
                        'username_part']

        area = body.get("area", "all")
        if area == "in":
            condations.append(f"country='中国'")
        if area == "out":
            condations.append(f"country!='中国'")

        export_file = body.get("export_file", False)
        cache_table = body.get("cache_table", False)

        bookmark_id = body.get("bookmark_id", False)
        # 这里默认获取到bookmark的参数就是创建bookmark
        if bookmark_id and qtype == "comment":
            bookmark = BookMark.objects.get(mark_id=bookmark_id)
            bookmark.cache_name = f'hxht_maillog_db.cache_{str(uuid.uuid4()).replace("-", "")}'
            bookmark.cache_name = f'hxht_maillog_db.cache_test'
            bookmark.is_cached = 1
            bookmark.save()
            # 只能创建普通查询的缓存
            queryimpala.gen_cache(table, bookmark.cache_name, selectfields, partitions, condations)
            return JsonResponse({"data": "ok"})

        if cache_table:
            table = cache_table

        # 不存在导出文件名，查询结果
        if not export_file:
            res = queryimpala.query(table, selectfields, partitions, condations, offset, qtype)
            return JsonResponse(res, safe=False)
        else:
            # 存在导出文件名，离线导出
            if len(export_file) > 0:
                res = queryimpala.export(table, selectfields, partitions, condations, qtype, export_file)
                return JsonResponse(res, safe=False)


def getrule(rulegroup_id, rule_set):
    """
    获取指定rulegroup下的规则id
    :param rulegroup_id:
    :param rule_set:
    :return:
    """
    rules = []
    for rule in rule_set:
        if rule[2] == rulegroup_id:
            tmp = {
                "rule_id": rule[0],
                "rule_name": rule[1],
                "fields_set": FieldModelSerializer(Fields.objects.filter(rule_id=rule[0]), many=True).data
            }
            rules.append(tmp)
    return rules


def getrulegroup(ana_id, rulegroup_set, rule_set):
    """
    传入分析对象id，规则组集合和规则集合，返会该分析对象下包含的规则组以及规则组包含的规则
    将规则组按照分析对象分组
    :param ana_id:
    :param rulegroup_set:
    :param rule_set:
    :return:
    """
    rulegroups = []
    for rulegroup in rulegroup_set:
        if rulegroup[2] == ana_id:
            tmp = {
                "rulegroup_id": rulegroup[0],
                "rulegroup_name": rulegroup[1],
                "rule_set": getrule(rulegroup[0], rule_set),
                "fields_set": FieldModelSerializer(Fields.objects.filter(rulegroup_id=rulegroup[0]), many=True).data
            }
            rulegroups.append(tmp)
        return rulegroups


def getTree(anaset, rulegroup_set, rule_set):
    """
    传入分析对象集合、规则组集合以及规则集合，得到分析对象下的规则组、规则
    :param anaset:
    :param rulegroup_set:
    :param rule_set:
    :return:
    """
    jsontree = []
    for ana in anaset:
        tmp = {
            "ana_id": ana[0],
            "ana_name": ana[1],
            "rulegroup_set": getrulegroup(ana[0], rulegroup_set, rule_set),
            "fields_set": FieldModelSerializer(Fields.objects.filter(analyse_id=ana[0]), many=True).data
        }
        jsontree.append(tmp)
    return jsontree


def qindex(request, unit_id=None, pro_id=None, ana_id=None, rulegroup_id=None, rule_id=None):
    # 首先获取单位下任务
    # if pro_id:
    # 判断有无指定任务
    if not pro_id:
        # 没有任务id参数
        projectset = Project.objects.filter(unit_id=unit_id)
        if len(projectset) == 0:
            return JsonResponse([], safe=False)
        prm_set = ProjectRuleMapper.objects.filter(project_id=projectset[0].pro_id)
        for prm in projectset[1:]:
            prm_tmp = ProjectRuleMapper.objects.filter(project_id=prm.pro_id)
            prm_set = prm_tmp | prm_set
    else:
        prm_set = ProjectRuleMapper.objects.filter(project_id=pro_id)

    ruleidset = set()
    for prm in prm_set:
        ruleidset.add(prm.rule_id)
    rule_set = set()

    # 获取rule(rule_id,rule_name,rulegroup_id)
    for ruleid in ruleidset:
        rule = Rule.objects.get(rule_id=ruleid)
        rule_set.add((rule.rule_id, rule.rule_name, rule.rulegroup_id))
    # 获取rulegroup
    rulegroup_set = set()
    for rule in rule_set:
        rulegroup = RuleGroup.objects.get(rulegroup_id=rule[2])
        rulegroup_set.add((rulegroup.rulegroup_id, rulegroup.rulegroup_name, rulegroup.analyse_id))
    anaset = set()
    for rulegroup in rulegroup_set:
        analyse = Analyse.objects.get(ana_id=rulegroup[2])
        anaset.add((analyse.ana_id, analyse.ana_name))

    data = getTree(anaset, rulegroup_set, rule_set)
    return JsonResponse({"data": data}, safe=True)


def qprojects(request, unit_id):
    projectset = Project.objects.filter(unit_id=unit_id)
    ser = ProjectModelSerializer(projectset, many=True)
    return JsonResponse(ser.data, safe=False)


class BookMarkModelViewSet(ModelViewSet):
    """
    书签ModelViewSet 查询一个，修改一个，新建一个
    """
    queryset = BookMark.objects.all()
    serializer_class = BookMarkModelSerializer


class OfflineModelViewSet(ModelViewSet):
    """
    离线任务 查询
    """
    queryset = Offline.objects.all()
    serializer_class = OfflineModelSerializer


class BookMarkALL(View):
    """
    查询所有书签
    :param request: 
    :return: 
    """

    def get(self, request, mark_pid=0):
        # mark_id = request.GET.get("root", 0)
        if mark_pid == 0:
            bookmarks = BookMark.objects.filter(is_root=1)
        else:
            bookmarks = BookMark.objects.filter(mark_pid=mark_pid)
        ser = BookMarkModelSerializer(bookmarks, many=True)
        encoder = DjangoJSONEncoder
        data = json.dumps(ser.data, cls=encoder, **{})
        data = json.loads(data)
        return JsonResponse({"data": data}, safe=True)


class CountryData(View):
    """
    获取国家数据
    """

    def get(self, request):
        data = {
            "counrty": ["中国", "美国"]

        }
        return JsonResponse(data, safe=False)


class CityData(View):
    """
    获取城市数据
    """

    def get(self, request):
        # print(country)
        chinadata = {"city": ["北京", "上海"]}
        americanda = {"city": ["纽约", "华盛顿"]}
        # if country == "中国":
        return JsonResponse(chinadata, safe=False)
        #
        # if country == "美国":
        #     return JsonResponse(americanda, safe=False)
