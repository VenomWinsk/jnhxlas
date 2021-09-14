from entry.models import Project
from operation.models import Analyse, RuleGroup, Rule, Fields
from queryset.utils import getpartitions
from system.models import Unit


def process(body):
    # print(dir(request))
    # print(request.META)
    res = {}
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
                     "rulegroup_id", "rule_id",
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
                    condations.append(f"username like '{condation[0]}%'")
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

    bookmark_id = body.get("bookmark_id", False)

    res['bookmark_id'] = bookmark_id
    res['selectfields'] = selectfields
    res['partitions'] = partitions
    res['table'] = table
    res['offset'] = offset

    return res
