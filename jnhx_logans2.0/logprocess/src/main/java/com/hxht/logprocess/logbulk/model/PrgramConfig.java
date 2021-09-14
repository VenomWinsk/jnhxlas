package com.hxht.logprocess.logbulk.model;

import lombok.Data;

import java.util.Date;

@Data
public class PrgramConfig {
    private String id;

    private String tag;

    private String keyes;

    private String value;

    private String description;

    private String creator;

    private Date gwtCreate;

    private Date gwtModified;

    private Integer page;

    private Integer size;

    private Integer form;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGwtCreate() {
        return gwtCreate;
    }

    public void setGwtCreate(Date gwtCreate) {
        this.gwtCreate = gwtCreate;
    }

    public Date getGwtModified() {
        return gwtModified;
    }

    public void setGwtModified(Date gwtModified) {
        this.gwtModified = gwtModified;
    }
}