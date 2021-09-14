package com.hxht.logprocess.logbulk.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class UsedRuleTemplate {
    private String id;

    private String projectId;

    private String projectAnalysisId;

    private String objectId;

    private String ruleId;

    private String ruleTemplateId;

    private Byte isEnabled;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer form;

    private Integer size;

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

    public String getProjectAnalysisId() {
        return projectAnalysisId;
    }

    public void setProjectAnalysisId(String projectAnalysisId) {
        this.projectAnalysisId = projectAnalysisId == null ? null : projectAnalysisId.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    public String getRuleTemplateId() {
        return ruleTemplateId;
    }

    public void setRuleTemplateId(String ruleTemplateId) {
        this.ruleTemplateId = ruleTemplateId == null ? null : ruleTemplateId.trim();
    }

    public Byte getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Byte isEnabled) {
        this.isEnabled = isEnabled;
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