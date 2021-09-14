package com.hxht.logprocess.search.service;

import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.search.dao.ImpalaDao;
import com.hxht.logprocess.search.dao.SearchLogDao;
import com.hxht.logprocess.search.model.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
@Slf4j
public class AnalyseService {

    @Autowired
    private ImpalaDao impalaDao;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;

    /**
     * create a tmp table stored req_ip relation with username
     */
    public void createR2UTmpTable(Connection con) {
        String sql = "create table log_analyse_R2U_tmp " +
                "(mdate string,req_ip string,username string,country string,city string,result string,opt string,num BIGINT)" +
                " partitioned by (unit string,ana_obj string,rule_group string)" +
                " stored as parquet;";
//        "create table log_analyse_R2U_tmp (mdate string,req_ip string,username string,geoip string,result string,num BIGINT) partition by (unit string,ana_obj string,rule_group string) stored as parquet;";
        try {
            impalaDao.doSql(con,sql);
//            ResultSet select = impalaDao.execute(sql);
//            log.info(select.toString());
//            int a = 2;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void forceRefreshTmpTable(List<String>list) {
        String dropTableSql = "drop table log_analyse_R2U_tmp";
        Connection con =null;
        try {
            con = impalaDao.connection();
            impalaDao.doSql(con,dropTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        createR2UTmpTable(con);
        autoGenR2U(list,con);
        List<String> list1 = this.getAnalyseObject();
        this.forceRefreshTmpCountryTable(list1,con);
        this.forceRefreshTmpCityTable(list1,con);
        impalaDao.closeConnec();
    }



    //刷新国家缓存表
    public  void  forceRefreshTmpCountryTable(List<String>list,Connection con){

        log.info("开始删除旧的国家表");
        String dropTableSql = "drop table log_analyse_R2U_tmp_country";
        try {
            impalaDao.doSql(con,dropTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log.info("准备国家缓存");
        //国家缓存表
        genR2UTmpCounrty(list,con);
    }


    /**
     * 获取所有的分析对象
     */
    public List<String>  getAnalyseObject(){

        List<String> list1 =new ArrayList();
        List<AnalysisObject> list = analysisObjectMapper.selectByAnalyseObject();
        for (AnalysisObject analysisObject:list){
            list1.add(analysisObject.getObjectName());
        }
        return list1;
    }


    /**
     * 获取邮箱日志的分析对象
     */
    public List<String>  getEmailAnalyseObject(){

        List<String> list1 =new ArrayList();
        AnalysisObject analysis =new AnalysisObject();
        analysis.setObjectType("email");
        List<AnalysisObject> list = analysisObjectMapper.selectByConditions(analysis);
        for (AnalysisObject analysisObject:list){
            list1.add(analysisObject.getObjectName());
        }
        return list1;
    }




    //刷新城市缓存表
    public  void  forceRefreshTmpCityTable(List<String>list,Connection con){
        log.info("开始删除旧的城市表");
        String dropTableSql = "drop table log_analyse_R2U_tmp_city";
        try {
            impalaDao.doSql(con,dropTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log.info("准备城市缓存");
        //城市缓存表
        genR2UTmpCity(list,con);
    }



    public void autoGenR2U(List<String>list,Connection con) {

        String ana_sql = "show partitions log_analyse_R2U_tmp;";
        Set<String> hashs = new HashSet<>();
        log.info("开始自动创建缓存表");
        for (String str:list){
            String data_sql = "show partitions t_"+str+"_source;";
            try {
                //asdasd
                ResultSet rs = impalaDao.doSelectSql(con,ana_sql);
                if (rs != null) {
                    while (rs.next()) {
                        String unit = rs.getString("unit");
                        String ana_obj = rs.getString("ana_obj");
                        String rule_group = rs.getString("rule_group");
                        hashs.add(unit + ana_obj + rule_group);
                    }
                    rs = impalaDao.doSelectSql(con,data_sql);
                    if (rs != null) {
                        while (rs.next()) {
                            String unit = rs.getString("unit");
                            String ana_obj = rs.getString("ana_obj");
                            String rule_group = rs.getString("rule_group");
                            if (!hashs.contains(unit + ana_obj + rule_group)) {
                                genR2UTmp("t_"+str+"_source", unit, ana_obj, rule_group,con);
                            }
                        }
                    }
                }
                log.info("缓存创建结束");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //国家缓存表
    public void genR2UTmpCounrty(List<String>list,Connection con) {
        String sql = "create table log_analyse_R2U_tmp_country " +
                "(country string,unit string,ana_obj string,rule_group string,rule string)"+
                " stored as parquet;";
        try {
            impalaDao.doSql(con,sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //impalaDao.closeConnec();
        log.info("开始创建国家缓存");

        for (String str:list){
            String sql1 = "insert into log_analyse_R2U_tmp_country" +
                    "  select country,unit,ana_obj,rule_group,rule" +
                    " from " + "t_"+str+"_source" + "  " +
                    " group by country,unit,ana_obj,rule_group,rule " ;
            try {
                impalaDao.doSql(con,sql1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        log.info("创建国家缓存完毕");

    }


    //城市缓存表
    public void genR2UTmpCity(List<String>list,Connection con) {
        String sql = "create table log_analyse_R2U_tmp_city " +
                "(city string,country string,unit string,ana_obj string,rule_group string,rule string )" +
                " stored as parquet;";
        try {
            impalaDao.doSql(con,sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //impalaDao.closeConnec();
        log.info("开始创建城市缓存");
        for (String str:list){
            String sql1 = "insert into log_analyse_R2U_tmp_city" +
                    "  select city,country,unit,ana_obj,rule_group,rule" +
                    " from " + "t_"+str+"_source" + "  " +
                    " group by city,country,unit,ana_obj,rule_group,rule " ;
            try {
                impalaDao.doSql(con,sql1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        log.info("创建城市缓存完毕");
    }


    public void genR2UTmp(String tablename, String unit, String ana_obj, String rule_group,Connection con) {

        String unitStr = Strings.isNotEmpty(unit) ? " and unit='" + unit + "' " : "";
        String ana_objStr = Strings.isNotEmpty(ana_obj) ? " and ana_obj='" + ana_obj + "' " : "";
        String rule_groupStr = Strings.isNotEmpty(rule_group) ? " and rule_group='" + rule_group + "' " : "";
        log.info("开始创建缓存" + unit + "-" + ana_obj + "-" + rule_group + "");
        String sql = "insert into log_analyse_R2U_tmp partition(unit,ana_obj,rule_group)" +
                "  select mdate,req_ip,username,country,city,result,opt,count(result),unit,ana_obj,rule_group" +
                " from " + tablename + "  " +
                "where  username!='' " + unitStr + ana_objStr + rule_groupStr +
                " group by mdate,username,req_ip,country,city,result,opt,unit,ana_obj,rule_group " +
                " order by mdate,username,req_ip,result,opt;";
        try {
            impalaDao.doSql(con,sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //impalaDao.closeConnec();
    }

    public class WhereCond {
        public String unitStr;
        public String ana_objStr;
        public String rule_groupStr;
        public String countryStr;
        public String timeStr;

        public WhereCond(String unitStr, String ana_objStr, String rule_groupStr, String countryStr, String timeStr) {
            this.unitStr = unitStr;
            this.ana_objStr = ana_objStr;
            this.rule_groupStr = rule_groupStr;
            this.countryStr = countryStr;
            this.timeStr = timeStr;
        }
    }

    private String genIns(List<String> strings) {
//        strings.forEach((s) -> {
//            s = "'" + s + "'";
//        });
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, "'" + strings.get(i) + "'");
        }
        String result = "(" + String.join(",", strings) + ")";
        return result;
    }

    public WhereCond preParams(LogAnaParams params) {
        String unitStr = params.getUnits().size() > 0 ? " and unit in " + genIns(params.getUnits()) + " " : "";
        String ana_objStr = Strings.isNotEmpty(params.getAna_obj()) && !"all".equals(params.getAna_obj()) ? " and ana_obj='" + params.getAna_obj() + "' " : "";
        String rule_groupStr = params.getRule_groups().size() > 0 ? " and rule_group in " + genIns(params.getRule_groups()) + " " : "";
        String countryStr = "";
        if (params.getCountry() != null) {
            switch (params.getCountry()) {
                case "inchina":
                    countryStr = " and country= '中国' ";
                    break;
                case "nochina":
                    countryStr = " and country!= '中国' and country!='局域网' ";
                    break;
                case "norn":
                    countryStr = " and country= '局域网'  ";
                    break;
                default:
                    countryStr = "";
            }
        }
        String timeStr = "";
        if (Strings.isNotEmpty(params.getBeginTime())) {
            timeStr += " and mdate>='" + params.getBeginTime() + "'";
        }
        if (Strings.isNotEmpty(params.getEndTime())) {
            timeStr += " and mdate<='" + params.getEndTime() + "'";
        }

        return new WhereCond(unitStr, ana_objStr, rule_groupStr, countryStr, timeStr);
    }

    /**
     * @param forceLoginParams
     * @return
     */
    public List<ForthLoginEntity> forceLoginAnalyse(ForceLoginParams forceLoginParams) {

        WhereCond whereCond = preParams(forceLoginParams);

        String sql = " with t1 as (select username,req_ip,country,city,result,sum(num) as total" +
                " from log_analyse_R2U_tmp " +
                " where result='success' and opt='login' " + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr + whereCond.timeStr +
                " group by username,req_ip,country,city,result " +
                " having  (count(result)>=" + forceLoginParams.getSuccessThreshold() + ")) ,t2 as (select username,req_ip,country,city,result,sum(num) as total" +
                " from log_analyse_R2U_tmp" +
                " where result='fail' and opt='login' " + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr + whereCond.timeStr +
                " group by username,req_ip,country,city,result " +
                " having  (count(result)>=" + forceLoginParams.getFailThreshold() + "))" +
                " select t1.username as username,t1.req_ip as req_ip,t1.country as country,t1.city as city,t1.total as success_num,t2.total as fail_num " +
                " from t1 " +
                "inner join t2 on t1.username=t2.username and t1.req_ip=t2.req_ip limit " + forceLoginParams.getTop() + ";";

        log.info(sql);
        List<ForthLoginEntity> results = new ArrayList<>();
        try {
            log.info("分析查询sql  "+sql);
            log.info("开始查询");
            ResultSet rs = impalaDao.select(sql);
            log.info("查询结束");
            if (rs != null) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String req_ip = rs.getString("req_ip");
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    int success_num = rs.getInt("success_num");
                    int fail_num = rs.getInt("fail_num");

                    results.add(new ForthLoginEntity(req_ip, country, city, username, success_num, fail_num));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            results = null;
        }
        log.info("内存分析完毕");
        impalaDao.closeConnec();
        log.info("result:" + results.size());
        return results;
    }


    public List<UnormalLoginEntity>  unormalLoginAnalyse(UnormalLoginParams params) {
        WhereCond whereCond = preParams(params);
        String sql;
        if (params.getLoginType().equals("login")){
            sql = "select req_ip,username,country,city,count(username) as num" +
                    " from log_analyse_R2U_tmp " +
                    " where result='success' and opt='login' " + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr + whereCond.timeStr +
                    " group by req_ip,username,country,city " +
                    " order by req_ip,username " +
                    " limit " + params.getTop() + ";";
        }else {
            sql = "select req_ip,username,country,city,count(username) as num" +
                    " from log_analyse_R2U_tmp " +
                    " where result='success' " + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr + whereCond.timeStr +
                    " group by req_ip,username,country,city " +
                    " order by req_ip,username " +
                    " limit " + params.getTop() + ";";
        }
        log.info("非法登录查询出的sql"+sql);
        List<UnormalLoginEntity> results = new ArrayList<>();
        try {
            log.info("开始查询");
            ResultSet rs = impalaDao.select(sql);
            log.info("查询结束");

            if (rs != null) {
                String lastReq_ip = "";
                String lastResult = "";
                int lastNum = 0;
                UnormalLoginEntity lastData = null;
                while (rs.next()) {
//                    log.info(rs.getString("req_ip"));
                    String username = rs.getString("username");
                    String req_ip = rs.getString("req_ip");
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    int num = rs.getInt("num");
//                    int total = rs.getInt("total");
//                    log.info(username+req_ip+geoip+num+"||"+total);
                    if (lastReq_ip.equals(req_ip)) {
                        lastData.addEmail(new UnormalLoginEntity.EmailResult(username, num, 0));
//                        if (lastData.getEmailCount() >= params.getSuccessNumber()) {
//                            results.add(lastData);
//                        }
                    } else {
                        lastData = new UnormalLoginEntity(req_ip, country, city);
                        lastData.addEmail(new UnormalLoginEntity.EmailResult(username, num, 0));
                        results.add(lastData);
                        lastReq_ip = req_ip;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            results = null;
        }

        impalaDao.closeConnec();

        List<UnormalLoginEntity> reList =new ArrayList();
        //取出大于所传邮箱个数的
        for(UnormalLoginEntity unormalLoginEntity:results){
             if (unormalLoginEntity.getEmailCount()>=params.getSuccessNumber()){
                 reList.add(unormalLoginEntity);
             }
        }
        log.info("result:" + reList.size());
        //将数组进行分页
        //Map<String,Object> map= getPgeList(reList,params);
        return reList;
    }


    /**
     * 将数组进行分页
     * @return
     */
     public Map<String,Object> getPgeList(List<UnormalLoginEntity> list,UnormalLoginParams params){

         List reList =new ArrayList();
         Map map =new HashMap();
         int limit = params.getSize();
         int offset = params.getPage();
         int count=0;
         for(int j=(offset-1)*limit;j<limit*offset;j++){
             try {
                 UnormalLoginEntity unormalLoginEntity = list.get(j);
                 reList.add(unormalLoginEntity);
                 count++;
             }catch (Exception e){
                 e.getMessage();
             }
         }
         map.put("count",count);
         map.put("total",Long.valueOf(list.size()));
         map.put("data",reList);
         return map;

     }


    public List<MultiRegionEntity> multiRegionAnalyse(MultiRegionParams params) {
        WhereCond whereCond = preParams(params);

//        String sql = "select username,count( DISTINCT req_ip) AS req_num,ndv(country) as country_num,ndv(city) as city_num" +
//                " from log_analyse_R2U_tmp " +
//                " where  result='success' and username!='' and country!='局域网' " + whereCond.timeStr + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr +
//                " group by username " +
//                " having ndv(country)>=" + params.getSuccessCountry() + " and ndv(city)>=" + params.getSuccessCity() +
//                " limit " + params.getTop() + ";";

        String sql;
        if (params.getLoginType().equals("login")){
             sql = "select username,count( DISTINCT req_ip) AS req_num,ndv(country) as country_num,ndv(city) as city_num" +
                    " from log_analyse_R2U_tmp " +
                    " where  result='success' and opt='login' and username!='' and country!='局域网' " + whereCond.timeStr + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr +
                    " group by username " +
                    " having ndv(country)>=" + params.getSuccessCountry() + " and ndv(city)>=" + params.getSuccessCity() +
                    " limit " + params.getTop() + ";";
        }else {
            sql = "select username,count( DISTINCT req_ip) AS req_num,ndv(country) as country_num,ndv(city) as city_num" +
                    " from log_analyse_R2U_tmp " +
                    " where  result='success' and username!='' and country!='局域网' " + whereCond.timeStr + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr +
                    " group by username " +
                    " having ndv(country)>=" + params.getSuccessCountry() + " and ndv(city)>=" + params.getSuccessCity() +
                    " limit " + params.getTop() + ";";
        }
        List<MultiRegionEntity> results = new ArrayList<>();
        try {
            log.info("开始查询");
            ResultSet rs = impalaDao.select(sql);
            log.info("异地登录sql: "+sql);
            log.info("查询结束");
            if (rs != null) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    int req_num = rs.getInt("req_num");
                    int country_num = rs.getInt("country_num");
                    int city_num = rs.getInt("city_num");
                    //String countrys = rs.getString("countrys");

                    results.add(new MultiRegionEntity(username, country_num, req_num, city_num));
                }
            }
//            log.info("查询结束" + rs.getFetchSize());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            results = null;
        }

        impalaDao.closeConnec();
        log.info("result:" + results.size());
        return results;

    }


    /**
     * 获取异地详情
     * @param params
     */
    public List<Map<String, Object>>  getAnotherPlaceDetail(MultiRegionParams params){

        WhereCond whereCond = preParams(params);

        String sql = "select req_ip,country,city" +
                " from log_analyse_R2U_tmp " +
                " where  result='success'"+" and username= '"+params.getUsername()+"' and country!='局域网' " + whereCond.timeStr + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr +
                " group by req_ip,country,city";
//        String sql = "select username,ndv(country) as country_num,ndv(city) as city_num,group_concat( DISTINCT req_ip ) AS req " +
//                " from log_analyse_R2U_tmp " +
//                " where  result='success'"+" and username= '"+params.getUsername()+"' and country!='局域网' " + whereCond.timeStr + whereCond.unitStr + whereCond.ana_objStr + whereCond.rule_groupStr + whereCond.countryStr +
//                " group by username " +
//                " having ndv(country)>=" + params.getSuccessCountry() + " and ndv(city)>=" + params.getSuccessCity();

        log.info("异地登录详情sql: "+sql);
        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            String req ="";
            while (rs.next()){
                 //req = rs.getString("req");

                Map<String, Object> rowData = new HashMap<>();
                for (int k = 1; k <= columnCount; k++) {
                    rowData.put(md.getColumnName(k), rs.getObject(k));
                }
                tmpMapList.add(rowData);
            }
//            String [] reqs= req.split(",");
//            int num = reqs.length;
//            log.info("ip的数量是:"+num);
        }catch (Exception e){
            e.getMessage();
        }
        impalaDao.closeConnec();
        return  tmpMapList;
    }




}
