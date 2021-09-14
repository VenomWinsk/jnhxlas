package com.hxht.logprocess.logbulk.model;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Date;

@Data
public class AnalysisObjectDTO {

    private String id;

    private String objectName;

    private String objectType;

    private String code;

    private String description;

    private Integer isBind;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;
}
