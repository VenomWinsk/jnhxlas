package com.hxht.logprocess.search.model;

import lombok.Data;

@Data
public class MatchTaskEntity {

    private String id;

    private String tableName;

    private String field;

    private String parameter;

    private String datetime;

    private long total;
}
