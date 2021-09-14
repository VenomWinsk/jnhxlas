package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;


@Data
public class ImportFile {
    private String id;

    private String projectAnalysisId;

    private String nodeIp;

    private String fileName;

    private String filePath;

    private Integer isAllFile;

    private Integer offset;

    private Date gmtCreate;

    private Integer form;

    private Integer size;

    private String roleGroupId;

    private Integer priorityFileTotal;
    private Integer priorityFileRead;

    private Integer RemainFileTotal;
    private Integer RemainFileRead;


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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getIsAllFile() {
        return isAllFile;
    }

    public void setIsAllFile(Integer isAllFile) {
        this.isAllFile = isAllFile;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}