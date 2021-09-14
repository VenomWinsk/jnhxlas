package com.hxht.logprocess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogprocessApplicationTests {

    @Test
    public void contextLoads() {
        List list =new ArrayList();
        list.add("1");
        System.out.println(list);
    }




    @Test
    public void  test(){

        List list =new ArrayList();
        list.add("1");

        String [] str =new String[list.size()];
        list.toArray(str);

        System.out.println(str.length);
    }
}
