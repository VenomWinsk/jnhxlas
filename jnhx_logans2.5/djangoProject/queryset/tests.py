from django.test import TestCase



# Create your tests here.


from entry.models import Project
from queryset.models import ProjectRuleMapper
from system.models import Unit
from operation.models import RuleGroup, Rule, Analyse

projectset = Project.objects.filter(unit_id=1)
# rule_set = None
rule_set = ProjectRuleMapper.objects.filter(project_id=projectset[0].pro_id)
# for i in projectset:
#     rule_set

print(rule_set)