package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsedRuleGroup {
    private String id;

    private String projectAnalysisId;

    private String ruleGroupId;

    private String objectId;

    private String name;

    private String code;

    private String inputPath;

    private String description;

    private String fileRegex;

    private String fileEncode;

    private String outputPath;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private Double primaryPro;

    private Double totalPro;

    private String statusStr;

    private String type;

    private List<DirRecords> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectAnalysisId() {
        return projectAnalysisId;
    }

    public void setProjectAnalysisId(String projectAnalysisId) {
        this.projectAnalysisId = projectAnalysisId == null ? null : projectAnalysisId.trim();
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId == null ? null : ruleGroupId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath == null ? null : inputPath.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFileRegex() {
        return fileRegex;
    }

    public void setFileRegex(String fileRegex) {
        this.fileRegex = fileRegex == null ? null : fileRegex.trim();
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath == null ? null : outputPath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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