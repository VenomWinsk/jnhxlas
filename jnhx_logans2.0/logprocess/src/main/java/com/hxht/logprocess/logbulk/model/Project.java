package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class Project {
    private String id;

    private String name;

    private String description;

    private String unitId;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private String clues;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Integer step;

    private Integer page;

    private Integer form;

    private Integer size;

    private Double primaryPro;

    private Double totalPro;

    private String type;

    private String unitName;

    private String statusStr;

    private String reasons;



    private List<ProjectAnalysisMapper> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
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