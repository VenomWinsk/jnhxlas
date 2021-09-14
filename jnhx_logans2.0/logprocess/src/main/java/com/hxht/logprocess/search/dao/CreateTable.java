package com.hxht.logprocess.search.dao;

import com.hxht.logprocess.LogprocessApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CreateTable {



//    @Value("${hive_url}")
//    private String hive_url;
//
//    @Value("${hive_driver}")
//    private String hive_driver;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateTable createTable =new CreateTable();
        createTable.createTable();
    }

    public  void createTable() throws SQLException, ClassNotFoundException, SQLException {

        Connection conn = this.getConnection();

        Map<String,Object> map = new HashMap<>(3);
        map.put("time","string");
        map.put("username","string");
        map.put("req_ip","string");
        map.put("result","string");
        map.put("date_time","string");
        map.put("opt","string");
        map.put("type","string");
        map.put("fulltime","string");
        //Map转String
        String str_field = getMapToString(map);
        //System.out.println(str_field);



        String channel_name = "t_coremail_test";
        String data_path = "/home/hxht_data/mlogs/";
        String table_name = channel_name+"_maillog_source_data";





        String create_table_sql = "create EXTERNAL table if not exists "+table_name+" ( " + str_field +" )"
                + " partitioned by (unit string,ana_obj string,rule_group string) STORED AS PARQUET location '"+data_path+"';";

        PreparedStatement ps_create_table = conn.prepareStatement(create_table_sql);
        ps_create_table.execute();
        log.info("创建表：{}成功！",table_name);

        String refresh_sql = "refresh  hxht_maillog_db."+table_name+";";
        PreparedStatement refresh_table = conn.prepareStatement(refresh_sql);
        refresh_table.execute();
        log.info("刷新表");


    }


    public static String getMapToString(Map<String,Object> map){

        Set<String> keySet = map.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if ((String.valueOf(map.get(keyArray[i]))).trim().length() > 0) {
                sb.append(keyArray[i]).append(" ").append(String.valueOf(map.get(keyArray[i])).trim());
            }
            if(i != keyArray.length-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public  Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.cloudera.impala.jdbc41.Driver");
        return DriverManager.getConnection("jdbc:impala://192.168.100.81:21050/hxht_maillog_db;auth=noSasl",null,null);
    }
}
