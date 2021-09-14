package com.hxht.logprocess.logbulk.model;


import lombok.Data;

import java.util.Date;

@Data
public class UnitDTO {

    private String id;

    private String cname;

    private String ename;

    private String city;

    private String contact;

    private String telephone;

    private String description;

    private String creator;

    private Date gmtCreate;

    private Date gmtModified;


}
