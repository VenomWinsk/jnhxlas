package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;

@Data
public class RuleGroupDTO {


    private String id;

    private String objectId;

    private String name;

    private String code;

    private String description;

    private String fileRegex;

    private String fileEncode;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

}
