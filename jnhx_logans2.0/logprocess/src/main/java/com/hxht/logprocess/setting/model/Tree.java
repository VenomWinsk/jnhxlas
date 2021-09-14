package com.hxht.logprocess.setting.model;

import lombok.Data;

import java.util.List;

@Data
public class Tree {

    String id;
    String name;
    List<Children> children;

    @Data
    class Children {
        String id;
        String name;
    }
}
