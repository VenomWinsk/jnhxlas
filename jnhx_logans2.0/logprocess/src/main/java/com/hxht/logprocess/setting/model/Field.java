package com.hxht.logprocess.setting.model;

import java.util.Date;

public class Field {
    private String id;

    private String ename;

    private String cname;

    private String componentType;

    private Integer findex;

    private String type;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isEnabled;

    private Integer category;

    private Integer isSearch;

    private String searchType;

    private String defaultValue;

    private Integer isMoreValue;

    private Integer isFrontComponent;

    private String rules;

    private String options;

    private Integer selectComponentDataType;

    private Integer ownerType;

    private String objectId;

    private String ruleGroupId;

    private String ruleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

//    public String getChannelId() {
//        return channelId;
//    }

//    public void setChannelId(String channelId) {
//        this.channelId = channelId == null ? null : channelId.trim();
//    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType == null ? null : componentType.trim();
    }

    public Integer getFindex() {
        return findex;
    }

    public void setFindex(Integer findex) {
        this.findex = findex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(Integer isSearch) {
        this.isSearch = isSearch;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType == null ? null : searchType.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public Integer getIsMoreValue() {
        return isMoreValue;
    }

    public void setIsMoreValue(Integer isMoreValue) {
        this.isMoreValue = isMoreValue;
    }

    public Integer getIsFrontComponent() {
        return isFrontComponent;
    }

    public void setIsFrontComponent(Integer isFrontComponent) {
        this.isFrontComponent = isFrontComponent;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    public Integer getSelectComponentDataType() {
        return selectComponentDataType;
    }

    public void setSelectComponentDataType(Integer selectComponentDataType) {
        this.selectComponentDataType = selectComponentDataType;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId == null ? null : ruleGroupId.trim();
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }
}
