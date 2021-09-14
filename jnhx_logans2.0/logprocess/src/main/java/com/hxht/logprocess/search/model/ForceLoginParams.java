package com.hxht.logprocess.search.model;

import lombok.Data;

@Data
public class ForceLoginParams extends LogAnaParams {

    private int successThreshold;
    private int failThreshold;
}
