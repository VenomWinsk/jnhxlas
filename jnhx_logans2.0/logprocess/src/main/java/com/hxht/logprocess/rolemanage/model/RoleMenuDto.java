package com.hxht.logprocess.rolemanage.model;

import lombok.Data;

import java.util.Date;

@Data
public class RoleMenuDto {
    private String id;

    private String roleId;

    private String menuId;

    private String buttonIds;//以英文逗号分隔 如: id1,id2,id3

    private Date createTime;

    private Date updateTime;

    private String createUser;
}
