package com.hxht.logprocess.logbulk.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LogMapperParams {


    List<ProjectAnalysisMapper> objectList =new ArrayList<>();

    List<UsedRuleGroup> ruleGroupList =new ArrayList<>();

    List<UsedRule> ruleList =new ArrayList<>();


}
