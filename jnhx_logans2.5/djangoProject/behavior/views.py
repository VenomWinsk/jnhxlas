from django.http import JsonResponse
from django.views import View

from behavior import behavior
from behavior.behavior import queryimpala
from operation.models import RuleGroup, Analyse
from system.models import Unit


class Behavior(View):
    """
    常规行为分析
    """

    def get(self, request):
        pass

    def post(self, request):
        body = eval(request.body)
        print(body)
        partitions = []  # 查询分区，会并入到查询条件中
        unit_id = body.get('unit_id', False)
        if not unit_id:
            return JsonResponse({"data": []}, safe=False)
        unit_name = Unit.objects.get(unit_id=unit_id).unit_name
        partitions.append(f"unit={unit_name}")
        analyse_id = body.get('analyse_id', False)
        if not analyse_id:
            return JsonResponse({"data": []}, safe=False)
        analyse_name = Analyse.objects.get(ana_id=analyse_id).ana_name
        partitions.append(f"ana_obj='{analyse_name}'")

        rulegroup_id = body.get('rulegroup_id', False)
        # 获取规则组id
        if rulegroup_id:
            rulegroup_name = RuleGroup.objects.get(rulegroup_id=rulegroup_id).rulegroup_name
            partitions.append(f"rulegroup='{rulegroup_name}'")

        condations = []
        mdate = body.get("mdate", False)
        if mdate:
            mdate = mdate.split(";")
            condations.append(f"mdate>='{mdate[0]}' and mdate<='{mdate[1]}'")

        time = body.get("mdate", False)
        if time:
            time = time.split(";")
            condations.append(f"time>='{time[0]}' and time<='{time[1]}'")

        table = f"t_{analyse_name}_source"

        qtype = body.get('qtype')

        if qtype == 'forcelogana':
            res = behavior.forcelogana(table, partitions, condations, body)
        elif qtype == 'illegalogana':
            res = behavior.illegalogana(table, partitions, condations, body)
        elif qtype == 'strangelogana':
            res = behavior.strangelogana(table, partitions, condations, body)
        elif qtype == 'illegavisana':
            res = behavior.illegavisana(table, partitions, condations, body)
        elif qtype == 'strangevisana':
            res = behavior.illegalogana(table, partitions, condations, body)
        else:
            return JsonResponse({"data": []}, safe=False)

        return JsonResponse({"data": res}, safe=False)
