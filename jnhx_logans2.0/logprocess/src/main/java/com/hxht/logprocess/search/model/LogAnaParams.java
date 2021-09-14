package com.hxht.logprocess.search.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LogAnaParams {

    //开始时间
    private String beginTime = "";

    //结束时间
    private String endTime = "";

    //单位
    private List<String> units = new ArrayList<>();

    //分析对象
    private String ana_obj = "";

    //规则组
    private List<String> rule_groups = new ArrayList<>();

    //境外非局域网 境内非局域网 非局域网 全部
    private String country = "";

    //取前多少条
    private int top = 10000;

    private String username;

    //是否是登录
    private String loginType;
}
