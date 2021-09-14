from rest_framework import serializers

from .models import BasicRegex, InnerFunc, Analyse, RuleGroup, Rule, Fields


class BasicRgexModelSerializer(serializers.ModelSerializer):
    """
    基本正则序列化
    """
    regex_id = serializers.IntegerField(max_value=10000, min_value=0, required=False)
    regex_name = serializers.CharField(max_length=32, required=False)
    regex_context = serializers.CharField(max_length=256, required=False)
    regex_type = serializers.IntegerField(required=False, default=0)
    regex_description = serializers.CharField(max_length=32, required=False, default="")

    class Meta:
        model = BasicRegex
        fields = '__all__'

    def validate(self, attrs):
        return attrs

    def create(self, validated_data):
        basic_refex = BasicRegex.objects.create(
            regex_name=validated_data['regex_name'],
            regex_context=validated_data['regex_context'],
            regex_type=validated_data['regex_type'],
            regex_description=validated_data['regex_description']
        )
        return basic_refex

    def update(self, instance, validated_data):
        instance.regex_name = validated_data['regex_name']
        instance.regex_context = validated_data['regex_context']
        instance.regex_type = validated_data['regex_type']
        instance.regex_description = validated_data['regex_description']
        instance.save()
        return instance


class InnerFuncModelSerializer(serializers.ModelSerializer):
    """
    内置函数序列化
    """
    func_id = serializers.IntegerField(required=False)
    func_name = serializers.CharField(max_length=32)
    func_input = serializers.CharField(max_length=128)
    func_output = serializers.CharField(max_length=128)
    func_description = serializers.CharField(max_length=128)

    class Meta:
        model = InnerFunc
        fields = '__all__'

    def create(self, validated_data):
        func = InnerFunc.objects.create(
            func_name=validated_data['func_name'],
            func_input=validated_data['func_input'],
            func_output=validated_data['func_output'],
            func_description=validated_data['func_description']
        )
        return func

    def update(self, instance, validated_data):
        instance.func_id = validated_data['func_id']
        instance.func_name = validated_data['func_name']
        instance.func_input = validated_data['func_input']
        instance.func_output = validated_data['func_output']
        instance.func_description = validated_data['func_description']
        instance.save()
        return instance


class FieldModelSerializer(serializers.ModelSerializer):
    """
    字段序列化
    """
    field_id = serializers.IntegerField(required=False)
    field_ename = serializers.CharField(max_length=32)
    field_cname = serializers.CharField(max_length=32)
    field_index = serializers.IntegerField(default=0, allow_null=True)
    field_type = serializers.IntegerField(allow_null=True)
    # rule = serializers.IntegerField(source='rule.rule_id', required=False)  # 所属规则组
    # rulegroup = serializers.IntegerField(source='rulegroup.rulegroup_id', required=False)  # 所属规则组
    # analyse = serializers.IntegerField(source="analyse.ana_id", required=False)  # 所属分析对象
    # rule = serializers.IntegerField(allow_null=True)  # 所属规则组
    # rulegroup = serializers.IntegerField()  # 所属规则组
    # analyse = serializers.IntegerField()  # 所属分析对象
    rule = serializers.IntegerField(source='rule.rule_id', allow_null=True)  # 所属规则组
    rulegroup = serializers.IntegerField(source='rulegroup.rulegroup_id', allow_null=True)  # 所属规则组
    analyse = serializers.IntegerField(source="analyse.ana_id", allow_null=True)  # 所属分析对象
    display_type = serializers.CharField(max_length=18, default="input")  # 该字段展示的类型,默认为输入框
    is_many = serializers.IntegerField(default=0)  # 默认为单值
    is_accurate = serializers.IntegerField(default=1)  # 是否精确  若为多值不允许精确
    option = serializers.CharField(max_length=256, min_length=0, allow_null=True)  # 该字段为下拉选项时该出现的内容
    option_type = serializers.CharField(max_length=256, min_length=0, allow_null=True)  # 该字段为下拉选项时该出现的内容

    class Meta:
        model = Fields
        fields = '__all__'

    def validate(self, attrs):
        # 验证字段不重复
        return attrs

    def create(self, validated_data):
        print(validated_data)
        field = Fields.objects.create(
            analyse_id=validated_data['analyse']['ana_id'],
            rulegroup_id=validated_data['rulegroup']['rulegroup_id'],
            rule_id=validated_data['rule']['rule_id'],
            field_cname=validated_data['field_cname'],
            field_ename=validated_data['field_ename'],
            field_index=validated_data['field_index'],
            field_type=validated_data['field_type'],
            display_type=validated_data["display_type"],
            is_many=validated_data["is_many"],
            option=validated_data["option"],
            option_type=validated_data["option_type"]

        )
        return field

    def update(self, instance, validated_data):
        instance.field_cname = validated_data['field_cname']
        instance.field_ename = validated_data['field_ename']
        instance.rulegroup_id = validated_data['rulegroup']['rulegroup_id']
        instance.rule_id = validated_data['rule']['rule_id']
        instance.display_type = validated_data['display_type']
        instance.ana_id = validated_data['analyse']['ana_id']
        instance.field_type = validated_data['field_type']
        instance.field_index = validated_data['field_index']
        instance.is_many = validated_data['is_many']
        instance.option = validated_data['option']
        instance.option_type = validated_data['option_type']
        instance.save()
        return instance


class RuleModelSerializer(serializers.ModelSerializer):
    """
    规则序列化
    """
    rule_id = serializers.IntegerField(required=False)
    rulegroup = serializers.IntegerField(source='rulegroup.rulegroup_id')
    rule_name = serializers.CharField(max_length=32)
    rule_description = serializers.CharField(max_length=128, required=False, allow_null=True, allow_blank=True)
    rule_index = serializers.IntegerField(default=0)  # 组内顺序
    rule_exfeatures = serializers.CharField(max_length=256, required=False, allow_null=True, allow_blank=True)
    rule_infeatures = serializers.CharField(max_length=256, required=False, allow_null=True, allow_blank=True)
    rule_regex_features = serializers.CharField(max_length=2048, required=False, allow_null=True, allow_blank=True)
    sourcelog = serializers.CharField(max_length=4096)
    extract_rule = serializers.CharField(max_length=4096)
    transform_rule = serializers.CharField(max_length=512, required=False)
    replace_rule = serializers.CharField(max_length=512, required=False, allow_null=True, allow_blank=True)
    supply_rule = serializers.CharField(max_length=512, required=False, allow_null=True, allow_blank=True)
    fields_set = FieldModelSerializer(many=True, required=False)

    class Meta:
        model = Rule
        fields = ['rule_id', 'rulegroup', 'rule_name', 'rule_description', 'rule_index',
                  'rule_exfeatures', 'rule_infeatures', 'rule_regex_features', 'sourcelog', 'extract_rule',
                  'transform_rule', 'replace_rule', 'supply_rule', 'fields_set']

    def create(self, validated_data):
        rule = Rule.objects.create(
            rulegroup_id=validated_data['rulegroup']['rulegroup_id'],
            rule_name=validated_data['rule_name'],
            rule_description=validated_data['rule_description'],
            rule_exfeatures=validated_data['rule_exfeatures'],
            rule_index=Rule.objects.filter(rulegroup_id=validated_data['rulegroup']['rulegroup_id']).count() + 1,
            rule_infeatures=validated_data['rule_infeatures'],
            rule_regex_features=validated_data['rule_regex_features'],
            sourcelog=validated_data['sourcelog'],
            extract_rule=validated_data['extract_rule'],
            transform_rule=validated_data['transform_rule'],
            replace_rule=validated_data['replace_rule'],
            supply_rule=validated_data['supply_rule']
        )
        return rule

    def update(self, instance, validated_data):
        instance.rule_name = validated_data['rule_name']
        instance.rule_description = validated_data['rule_description']
        # instance.rule_index = validated_data['rule_index']  # 更新的时候需要重新安置顺序
        instance.rule_exfeatures = validated_data['rule_exfeatures']
        instance.rule_infeatures = validated_data['rule_infeatures']
        instance.rule_regex_features = validated_data['rule_regex_features']
        instance.sourcelog = validated_data['sourcelog']
        instance.extract_rule = validated_data['extract_rule']
        instance.transform_rule = validated_data['transform_rule']
        instance.replace_rule = validated_data['replace_rule']
        instance.supply_rule = validated_data['supply_rule']
        instance.save()
        return instance


class RuleGroupModelSerializer(serializers.ModelSerializer):
    """
    规则组序列化
    """
    rulegroup_id = serializers.IntegerField(min_value=0, required=False)
    rulegroup_name = serializers.CharField(max_length=32)
    analyse = serializers.IntegerField(source='analyse.ana_id')
    file_features = serializers.CharField(max_length=128, min_length=0)
    rulegroup_description = serializers.CharField(max_length=128, min_length=0, required=False)
    rule_set = RuleModelSerializer(many=True, read_only=True)
    fields_set = FieldModelSerializer(many=True, required=False)

    class Meta:
        model = RuleGroup
        # fields = '__all__'
        fields = ['rulegroup_id', 'rulegroup_name', 'analyse', 'file_features', 'rulegroup_description', 'rule_set',
                  'fields_set']

    def create(self, validated_data):
        rulegroup = RuleGroup.objects.create(
            rulegroup_name=validated_data['rulegroup_name'],
            analyse_id=validated_data['analyse']['ana_id'],
            rulegroup_description=validated_data['rulegroup_description'],
            file_features=validated_data['file_features'])
        return rulegroup

    def update(self, instance, validated_data):
        # 数据更新
        # instance.rulegroup_id = validated_data['rulegroup_id']
        instance.analyse_id = validated_data['analyse']['ana_id']
        instance.rulegroup_name = validated_data['rulegroup_name']
        instance.rulegroup_description = validated_data['rulegroup_description']
        instance.file_features = validated_data['file_features']
        instance.save()
        return instance


class AnalyseModelSerializer(serializers.ModelSerializer):
    """
    分析对象序列化
    """
    ana_id = serializers.IntegerField(required=False)
    ana_name = serializers.CharField(max_length=32, min_length=0, required=False)
    ana_description = serializers.CharField(max_length=128, min_length=0, required=False, allow_null=True,
                                            allow_blank=True)
    rulegroup_set = RuleGroupModelSerializer(many=True, read_only=True)
    fields_set = FieldModelSerializer(many=True, required=False)

    class Meta:
        model = Analyse
        # fields = '__all__'
        fields = ['ana_id', 'ana_name', 'ana_description', 'rulegroup_set', 'fields_set']

    def validate(self, attrs):
        # 验证分析对象名称不能重复
        return attrs

    def create(self, validated_data):
        analyse = Analyse.objects.create(
            ana_name=validated_data['ana_name'],
            ana_description=validated_data['ana_description']
        )
        return analyse

    def update(self, instance, validated_data):
        # instance.ana_id = validated_data['ana_id']
        instance.ana_name = validated_data['ana_name']
        instance.ana_description = validated_data['ana_description']
        instance.save()
        return instance
