package com.hxht.logprocess.search.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@Slf4j
public class ImpalaDao {

    private Connection connection = null;

    @Value("${hive_url}")
    private String hive_url;

    @Value("${hive_driver}")
    private String hive_driver;

    /**
     * @param sql
     * @MethodName: select
     * @Description: 通用查询
     * @Param: [sql]
     * @Return: java.sql.ResultSet
     * @Author: hxht-chenhao
     * @Date: 10:30
     */

    synchronized
    public ResultSet select(String sql) throws SQLException {

        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.info("获取impala连接异常！");
        }
//        log.info("lianjie...");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            log.info("impala 查询异常 ！" + e.getMessage());
        }
//        log.info("chaxunjieshu...");
        return rs;
    }

    synchronized
    public boolean execute(String sql) throws SQLException {

        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.info("获取impala连接异常！");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean execute = false;
        try {
            Statement conn = connection.createStatement();
            conn.execute(sql);
            execute = true;
        } catch (SQLException e) {
            log.info("impala 查询异常 ！" + e.getMessage());
        }
        return execute;
    }




    synchronized
    public Connection connection() throws SQLException {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.info("获取impala连接异常！");
        }
        //Statement conn = connection.createStatement();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//
//            conn.execute(sql);
//        } catch (SQLException e) {
//            log.info("impala 查询异常 ！" + e.getMessage());
//        }
        return connection;
    }


    synchronized
    public ResultSet doSql(Connection connection,String sql) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Statement conn = connection.createStatement();
            conn.execute(sql);
        } catch (SQLException e) {
            log.info("impala 查询异常 ！" + e.getMessage());
        }
        return rs;
    }



    synchronized
    public ResultSet doSelectSql(Connection conn,String sql) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            log.info("impala 查询异常 ！" + e.getMessage());
        }
        return rs;
    }


    public boolean closeConnec() {

        try {
            connection.close();
        } catch (SQLException e) {
            log.info("impala 关闭异常 ！");
            return false;
        }

        return true;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(hive_driver);
        return DriverManager.getConnection(hive_url, null, null);
    }


}
