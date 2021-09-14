package com.hxht.logprocess.search.model;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReData {


    private int count;

    private int total;

    private StringBuffer sql;

    private String tablename;

    private List<Map<String,Object>>data;
}
