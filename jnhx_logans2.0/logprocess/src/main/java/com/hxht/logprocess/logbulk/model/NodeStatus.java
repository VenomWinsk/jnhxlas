package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;


@Data
public class NodeStatus {
    private String nodeIp;

    private Integer isAlive;

    private Long cpuRate;

    private Long avalibleDist;

    private Long allDist;

    private Date gmtCreate;

    private Integer form;

    private Integer size;

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp == null ? null : nodeIp.trim();
    }

    public Integer getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(Integer isAlive) {
        this.isAlive = isAlive;
    }

    public Long getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(Long cpuRate) {
        this.cpuRate = cpuRate;
    }

    public Long getAvalibleDist() {
        return avalibleDist;
    }

    public void setAvalibleDist(Long avalibleDist) {
        this.avalibleDist = avalibleDist;
    }

    public Long getAllDist() {
        return allDist;
    }

    public void setAllDist(Long allDist) {
        this.allDist = allDist;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}