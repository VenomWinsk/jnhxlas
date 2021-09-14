package com.hxht.logprocess.logbulk.model;


import lombok.Data;

import java.util.Date;

@Data
public class RuleDTO {


    private String id;

    private String name;

    private String ruleGroupId;

    private String logData;

    private String logFeature;

    private String extractRule;

    private String switchRule;

    private String replaceRule;

    private String supplementRule;

    private String category;

    private String description;

    private Integer isEnabled;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;
}
