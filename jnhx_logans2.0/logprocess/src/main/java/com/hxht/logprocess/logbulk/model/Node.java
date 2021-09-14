package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;


@Data
public class Node extends NodeKey {
    private String nodeName;

    private String nodeConfig;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isEnabled;

    private Integer form;

    private Integer size;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(String nodeConfig) {
        this.nodeConfig = nodeConfig == null ? null : nodeConfig.trim();
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
}