package com.hxht.logprocess.logbulk.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Rule {
    private String id;

    private String name;

    private String ruleGroupId;

    private String logData;

    private String logFeature;

    private String extractRule;

    private String switchRule;

    private String replaceRule;

    private String supplementRule;

    private String category;

    private String description;

    private Integer isEnabled;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer page;

    private Integer form;

    private Integer size;

    private String order;

    private String field;

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId == null ? null : ruleGroupId.trim();
    }

    public String getExtractRule() {
        return extractRule;
    }

    public void setExtractRule(String extractRule) {
        this.extractRule = extractRule == null ? null : extractRule.trim();
    }

    public String getSwitchRule() {
        return switchRule;
    }

    public void setSwitchRule(String switchRule) {
        this.switchRule = switchRule == null ? null : switchRule.trim();
    }

    public String getReplaceRule() {
        return replaceRule;
    }

    public void setReplaceRule(String replaceRule) {
        this.replaceRule = replaceRule == null ? null : replaceRule.trim();
    }

    public String getSupplementRule() {
        return supplementRule;
    }

    public void setSupplementRule(String supplementRule) {
        this.supplementRule = supplementRule == null ? null : supplementRule.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
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