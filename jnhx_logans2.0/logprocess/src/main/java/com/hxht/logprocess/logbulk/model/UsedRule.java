package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;

@Data
public class UsedRule {
    private String id;

    private String useRuleGroupId;

    private String ruleId;

    private String ruleGroupId;

    private String name;

    private String description;

    private String logData;

    private Integer status;

    private String logFeature;

    private String extractRule;

    private String switchRule;

    private String supplementRule;

    private String replaceRule;

    private Byte isEnabled;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUseRuleGroupId() {
        return useRuleGroupId;
    }

    public void setUseRuleGroupId(String useRuleGroupId) {
        this.useRuleGroupId = useRuleGroupId == null ? null : useRuleGroupId.trim();
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getLogFeature() {
        return logFeature;
    }

    public void setLogFeature(String logFeature) {
        this.logFeature = logFeature == null ? null : logFeature.trim();
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

    public String getSupplementRule() {
        return supplementRule;
    }

    public void setSupplementRule(String supplementRule) {
        this.supplementRule = supplementRule == null ? null : supplementRule.trim();
    }

    public String getReplaceRule() {
        return replaceRule;
    }

    public void setReplaceRule(String replaceRule) {
        this.replaceRule = replaceRule == null ? null : replaceRule.trim();
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