package com.hxht.logprocess.menumanage.model;

import lombok.Data;

import java.util.Date;

@Data
public class MenuDto {
    private String id;

    private String funcName;

    private String grade;

    private Integer module;

    private String description;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;
}
