package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;

@Data
public class Progress {
    private String id;

    private String useRuleGroupId;

    private Integer fileTotal;

    private Integer primaryFileTotal;

    private Integer normalFileHandle;

    private Integer primaryFileHandle;

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

    public Integer getFileTotal() {
        return fileTotal;
    }

    public void setFileTotal(Integer fileTotal) {
        this.fileTotal = fileTotal;
    }

    public Integer getPrimaryFileTotal() {
        return primaryFileTotal;
    }

    public void setPrimaryFileTotal(Integer primaryFileTotal) {
        this.primaryFileTotal = primaryFileTotal;
    }

    public Integer getNormalFileHandle() {
        return normalFileHandle;
    }

    public void setNormalFileHandle(Integer normalFileHandle) {
        this.normalFileHandle = normalFileHandle;
    }

    public Integer getPrimaryFileHandle() {
        return primaryFileHandle;
    }

    public void setPrimaryFileHandle(Integer primaryFileHandle) {
        this.primaryFileHandle = primaryFileHandle;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }


}