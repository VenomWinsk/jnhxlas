package com.hxht.logprocess.logbulk.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;


@Data
public class RuleGroup {
    private String id;

    private String objectId;

    private String name;

    private String description;

    private String fileRegex;

    private String fileEncode;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private String code;

    private Integer page;

    private Integer form;

    private Integer size;

    private Integer priorityRulePro;

    private Integer remainRulePro;

    private String order;

    private String field;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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