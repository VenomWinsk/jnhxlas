package com.hxht.logprocess.logbulk.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class AnalysisObject {
    private String id;

    private String objectName;

    private String objectType;

    private String description;

    private Integer isBind;

    private String code;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer page;

    private Integer form;

    private Integer size;

    private Integer status;

    private Double primaryPro;

    private Double totalPro;

    private String type;

    private String name;

    private List<UsedRuleGroup> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}