package com.hxht.logprocess.usermange.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleDto {
    private String id;

    private String userId;

    private String userName;

    private String roleId;

    private String roleName;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
