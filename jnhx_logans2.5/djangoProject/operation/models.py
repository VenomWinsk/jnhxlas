from django.db import models


class BasicRegex(models.Model):
    """
    基本正则表
    """
    regex_id = models.AutoField(primary_key=True)
    regex_name = models.CharField(max_length=32, unique=True)
    regex_context = models.CharField(max_length=256)
    regex_type = models.CharField(max_length=32)
    regex_description = models.CharField(max_length=32)

    class Meta:
        db_table = 'basic_regex'


class InnerFunc(models.Model):
    """
    内置函数表
    """
    func_id = models.AutoField(primary_key=True)
    func_name = models.CharField(max_length=32, unique=True)
    func_input = models.CharField(max_length=128)
    func_output = models.CharField(max_length=128)
    func_description = models.CharField(max_length=128)

    class Meta:
        db_table = 'inner_func'


class Analyse(models.Model):
    """
    分析对象表
    """
    ana_id = models.AutoField(primary_key=True)
    ana_name = models.CharField(max_length=32, unique=True, null=True, blank=True)
    ana_description = models.CharField(max_length=128, null=True, blank=True)


    class Meta:
        db_table = 'analyse'


class RuleGroup(models.Model):
    """
    规则组表
    """
    rulegroup_id = models.AutoField(primary_key=True)
    rulegroup_name = models.CharField(max_length=32, unique=True)
    analyse = models.ForeignKey(Analyse, on_delete=models.CASCADE)
    file_features = models.CharField(max_length=128)
    rulegroup_description = models.CharField(max_length=128, null=True)

    class Meta:
        db_table = 'rulegroup'


class Rule(models.Model):
    """
    规则表
    """
    rule_id = models.AutoField(primary_key=True)
    rulegroup = models.ForeignKey(RuleGroup, on_delete=models.CASCADE)
    rule_name = models.CharField(max_length=32, unique=True)
    rule_description = models.CharField(max_length=128, null=True)
    rule_index = models.IntegerField(null=True)
    rule_exfeatures = models.CharField(max_length=256, null=True)
    rule_infeatures = models.CharField(max_length=256, null=True)
    rule_regex_features = models.CharField(max_length=2048, null=True)
    sourcelog = models.CharField(max_length=4096)
    extract_rule = models.CharField(max_length=4096)
    transform_rule = models.CharField(max_length=512, null=True)
    replace_rule = models.CharField(max_length=512, null=True)
    supply_rule = models.CharField(max_length=512, null=True)

    class Meta:
        db_table = 'rule'


class Fields(models.Model):
    """
    字段表
    """
    field_id = models.AutoField(primary_key=True)  # 字段id
    field_ename = models.CharField(max_length=32)  # 英文名
    field_cname = models.CharField(max_length=32)  # 中文名
    field_index = models.IntegerField(null=True)  # 字段在分析对象中的排序，每次添加新的规则，该顺序变化
    field_type = models.IntegerField(null=True)  # 字段属于分析对象还是规则组还是规则
    rule = models.ForeignKey(Rule, on_delete=models.CASCADE, null=True)  # 所属规则
    rulegroup = models.ForeignKey(RuleGroup, on_delete=models.CASCADE, null=True)  # 所属规则组
    analyse = models.ForeignKey(Analyse, on_delete=models.CASCADE, null=True)  # 所属分析对象
    display_type = models.CharField(max_length=18, default="input")  # 该字段展示的类型,默认为输入框
    is_many = models.IntegerField(default=0)  # 默认为单值
    is_accurate = models.IntegerField(default=0)  # 是否精确  若为多值不允许精确
    option = models.CharField(max_length=256, null=True)  # 该字段为下拉选项时该出现的内容
    option_type = models.CharField(max_length=256, null=True)  # 该字段为下拉选项时该出现的内容

    # analyse_id = models.ForeignKey(rulegroup, on_delete=models.CASCADE)
    class Meta:
        db_table = 'fields'
