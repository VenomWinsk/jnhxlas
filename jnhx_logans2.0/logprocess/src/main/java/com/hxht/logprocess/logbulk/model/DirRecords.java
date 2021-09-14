package com.hxht.logprocess.logbulk.model;

import java.util.List;

public class DirRecords {
    private String id;

    private String usedRuleGroupId;

    private String dirname;

    private Integer wasscanned;

    private Integer dirStatus;

    private Integer filenums;

    private Integer jumpnums;

    private Integer processednums;

    private String name;

    private String statusStr;


    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Double totalPro;

    public Double getTotalPro() {
        return totalPro;
    }

    public void setTotalPro(Double totalPro) {
        this.totalPro = totalPro;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FileRecords> getChildren() {
        return children;
    }

    public void setChildren(List<FileRecords> children) {
        this.children = children;
    }

    private String type;

    private List<FileRecords> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsedRuleGroupId() {
        return usedRuleGroupId;
    }

    public void setUsedRuleGroupId(String usedRuleGroupId) {
        this.usedRuleGroupId = usedRuleGroupId == null ? null : usedRuleGroupId.trim();
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname == null ? null : dirname.trim();
    }

    public Integer getWasscanned() {
        return wasscanned;
    }

    public void setWasscanned(Integer wasscanned) {
        this.wasscanned = wasscanned;
    }

    public Integer getDirStatus() {
        return dirStatus;
    }

    public void setDirStatus(Integer dirStatus) {
        this.dirStatus = dirStatus;
    }

    public Integer getFilenums() {
        return filenums;
    }

    public void setFilenums(Integer filenums) {
        this.filenums = filenums;
    }

    public Integer getJumpnums() {
        return jumpnums;
    }

    public void setJumpnums(Integer jumpnums) {
        this.jumpnums = jumpnums;
    }

    public Integer getProcessednums() {
        return processednums;
    }

    public void setProcessednums(Integer processednums) {
        this.processednums = processednums;
    }
}