package com.hxht.logprocess.search.model;

import lombok.Data;

@Data
public class UnormalLoginParams extends LogAnaParams {

    private int successNumber=5;

    private int page;

    private int size;
}
