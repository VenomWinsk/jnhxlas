package com.hxht.logprocess.rolemanage.model;

import lombok.Data;

import java.util.Date;
@Data
public class RoleDto {
    private String id;

    private String roleName;

    private String description;

    private String roleType;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;
}
