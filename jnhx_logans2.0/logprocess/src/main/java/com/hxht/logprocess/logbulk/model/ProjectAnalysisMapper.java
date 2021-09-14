package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectAnalysisMapper {
    private String id;

    private String projectId;

    private String objectId;

    private String objectName;

    private String code;

    private Integer status;

    private Double primaryPro;

    private Double totalPro;

    private String type;

    private String name;

    private Date createAt;

    private Date updateAt;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String statusStr;

    private List<UsedRuleGroup> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }


    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}