package com.hxht.logprocess.search.model;


import lombok.Data;

import java.util.List;

@Data
public class LogParams {

    //开始日期
    private String beginDate;


    //结束日期
    private String endDate;

    //单位
    private List<String> unitList;

    //请求ip
    private String req_ip;

    //邮箱
    private String username;


    //操作
    private String result;

    //国家
    private List<String> country;

    //地区
    private List<String> city;

    //协议
    private List<String> rizhi;


    //是否包含
    private String orNot;

    //页码
    private Integer page;

    //条数
    private Integer size;


    private Integer from;



    private String field;


    private String order;

}
