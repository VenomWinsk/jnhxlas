package com.hxht.logprocess.logbulk.model;

import com.hxht.logprocess.LogprocessApplication;
import org.springframework.boot.SpringApplication;

import java.util.UUID;

public class Test {




    public static void main(String[] args) {

        for (int i=0;i<2;i++){
            System.out.println(UUID.randomUUID().toString());
        }
    }

}
