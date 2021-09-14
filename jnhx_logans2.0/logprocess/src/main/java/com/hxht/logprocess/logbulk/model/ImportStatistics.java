package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;


@Data
public class ImportStatistics {
    private String id;

    private String projectAnalysisId;

    private String nodeIp;

    private String nodeName;

    private Integer importBytes;

    private Integer importCount;

    private Date createAt;

    private Integer form;

    private Integer size;

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

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp == null ? null : nodeIp.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Integer getImportBytes() {
        return importBytes;
    }

    public void setImportBytes(Integer importBytes) {
        this.importBytes = importBytes;
    }

    public Integer getImportCount() {
        return importCount;
    }

    public void setImportCount(Integer importCount) {
        this.importCount = importCount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}