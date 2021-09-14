package com.hxht.logprocess.search.dao;


import com.alibaba.fastjson.JSONObject;
import com.hxht.logprocess.core.util.IPUtil;
import com.hxht.logprocess.core.util.MD5;
import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.dao.RuleGroupMapper;
import com.hxht.logprocess.logbulk.dao.RuleMapper;
import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.logbulk.model.Rule;
import com.hxht.logprocess.logbulk.model.RuleGroup;
import com.hxht.logprocess.search.model.*;
import com.hxht.logprocess.setting.dao.ChannelMapper;
import com.hxht.logprocess.setting.dao.FieldMapper;
import com.hxht.logprocess.setting.model.Channel;
import com.hxht.logprocess.setting.model.Field;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

@Slf4j
@Component
public class SearchLogDao {


    @Autowired
    private ImpalaDao impalaDao;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private RuleGroupMapper ruleGroupMapper;

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private MatchTaskMapper matchTaskMapper;

    /**
     * 查询日志
     */
    public List<Map<String, Object>> searchLog(LogParams logParams) {

        ReData reData = new ReData();
        StringBuffer sql = new StringBuffer();
        sql.append("select")
                .append(" result, ")
                .append(" mdate, ")
                .append(" opt, ")
                .append(" req_ip, ")
                .append(" country , ")
                .append(" city , ")
                .append(" username, ")
                .append(" unit , ")
                .append(" fulltime  , ")
                .append(" ana_obj   , ")
                .append(" rule_group    , ")
                .append(" time ");

        sql.append(" from t_coremail_source   where 1=1");
        if (StringUtils.isNotBlank(logParams.getReq_ip())) {
            sql.append(" and req_ip = '" + logParams.getReq_ip() + "' ");
        }
        if (StringUtils.isNotBlank(logParams.getUsername())) {
            sql.append(" and username = '" + logParams.getUsername() + "' ");
        }
        if (StringUtils.isNotBlank(logParams.getResult()) && !logParams.getResult().equals("all")) {
            sql.append(" and result = '" + logParams.getResult() + "' ");
        }
        if (logParams.getUnitList() != null && logParams.getUnitList().size() > 0) {
            sql.append(" and unit in " + genIns(logParams.getUnitList()));
        }
        if (logParams.getRizhi() != null && logParams.getRizhi().size() > 0) {
            sql.append(" and rule_group in " + genIns(logParams.getRizhi()));
        }
        if (StringUtils.isNotBlank(logParams.getBeginDate())) {
            sql.append(" and fulltime >= '" + logParams.getBeginDate() + "' ");
        }

        if (StringUtils.isNotBlank(logParams.getEndDate())) {
            sql.append(" and fulltime <= '" + logParams.getEndDate() + "' ");
        }

        if (StringUtils.isNotBlank(logParams.getOrNot()) && logParams.getOrNot().equals("yes") && logParams.getCountry() != null && logParams.getCountry().size() > 0) {
            sql.append(" and country in " + genIns(logParams.getCountry()));
        }

        if (StringUtils.isNotBlank(logParams.getOrNot()) && logParams.getOrNot().equals("no") && logParams.getCountry() != null && logParams.getCountry().size() > 0) {
            sql.append(" and country not in " + genIns(logParams.getCountry()));
        }

        if (logParams.getCity() != null && logParams.getCity().size() > 0) {
            sql.append(" and city in " + genIns(logParams.getCity()));
        }
        if (StringUtils.isNotBlank(logParams.getField()) && StringUtils.isNotBlank(logParams.getOrder())) {
            sql.append(" order by " + logParams.getField() + " " + logParams.getOrder() + "");
        }
        sql.append("  limit " + logParams.getPage() * logParams.getSize() + " ");
        log.info("查询出的sql是:" + sql);
        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int k = 1; k <= columnCount; k++) {
                    rowData.put(md.getColumnName(k), rs.getObject(k));
                }
                tmpMapList.add(rowData);
            }
            //reData.setTotal(tmpMapList.size());
        } catch (Exception e) {
            e.getMessage();
        }
        //reData.setData(tmpMapList);
        impalaDao.closeConnec();
        //Map map = dealData(reData,logParams);
        return tmpMapList;
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


    private String genIns1(List<String> strings) {
//        strings.forEach((s) -> {
//            s = "'" + s + "'";
//        });
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, "" + strings.get(i) + "");
        }
        String result = "(" + String.join(",", strings) + ")";
        return result;
    }

    /**
     * 查询日志
     *
     * @return
     */
    public Map<String, Object> searchDataLog(LogParams logParams) {

        ReData reData = new ReData();
        List<Map<String, Object>> list = searchLog(logParams);
        reData.setTotal(list.size());
        reData.setData(list);
        Map map = dealData(reData, logParams);
        return map;
    }


    /**
     * 处理返回的数据，将数据进行分页
     */
    public Map dealData(ReData reData, LogParams logParams) {

        List reList = new ArrayList();
        Map map = new HashMap();
        int limit = logParams.getSize();
        int offset = logParams.getPage();
        int total = reData.getTotal();
        total = total > (offset - 1) * limit ? total : limit;
        int count = 0;
        for (int j = (offset - 1) * limit; j < total; j++) {
            try {
                Map map1 = reData.getData().get(j);
                reList.add(map1);
                count++;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        Long tal = getDataTotal(logParams);
        map.put("count", count);
        map.put("total", tal);
        map.put("data", reList);
        return map;
    }


    /**
     * 获取总量
     */
    public Long getDataTotal(LogParams logParams) {

        StringBuffer sql = new StringBuffer();

        sql.append("select count(*) ");
        sql.append(" from t_coremail_source where 1=1 ");
        if (StringUtils.isNotBlank(logParams.getReq_ip())) {
            sql.append(" and req_ip = '" + logParams.getReq_ip() + "' ");
        }
        if (StringUtils.isNotBlank(logParams.getUsername())) {
            sql.append(" and username = '" + logParams.getUsername() + "' ");
        }
        if (StringUtils.isNotBlank(logParams.getResult()) && !logParams.getResult().equals("all")) {
            sql.append(" and result = '" + logParams.getResult() + "' ");
        }
        if (logParams.getUnitList() != null && logParams.getUnitList().size() > 0) {
            sql.append(" and unit in " + genIns1(logParams.getUnitList()));
        }
        if (logParams.getRizhi() != null && logParams.getRizhi().size() > 0) {
            sql.append(" and rule_group in " + genIns1(logParams.getRizhi()));
        }
        if (StringUtils.isNotBlank(logParams.getBeginDate())) {
            sql.append(" and fulltime >= '" + logParams.getBeginDate() + "' ");
        }

        if (StringUtils.isNotBlank(logParams.getEndDate())) {
            sql.append(" and fulltime <= '" + logParams.getEndDate() + "' ");
        }
        if (StringUtils.isNotBlank(logParams.getOrNot()) && logParams.getOrNot().equals("yes") && logParams.getCountry() != null && logParams.getCountry().size() > 0) {
            sql.append(" and country in " + genIns1(logParams.getCountry()));
        }

        if (StringUtils.isNotBlank(logParams.getOrNot()) && logParams.getOrNot().equals("no") && logParams.getCountry() != null && logParams.getCountry().size() > 0) {
            sql.append(" and country not in " + genIns1(logParams.getCountry()));
        }

        if (logParams.getCity() != null && logParams.getCity().size() > 0) {
            sql.append(" and city in " + genIns1(logParams.getCity()));
        }

        log.info("查询sql: " + sql);
        ResultSet rs = null;
        Long total = 0L;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                total = Long.valueOf(rs.getObject(1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        impalaDao.closeConnec();
        return total;
    }


    /**
     * 导出cvs 2.0
     */
    public ResponseEntity<byte[]> exportLogsCsv(List<Map<String, Object>> logList, Map params) {

        ResponseEntity<byte[]> results = null;
        Map<String, Object> map = logList.get(0);
        String levelId = params.containsKey("levelId") ? (String) params.get("levelId") : null;
        int ownerType = params.containsKey("ownerType") ? (Integer) params.get("ownerType") : -1;

        //通过英文名称查询中文名称
//        AnalysisObject analysisObject = analysisObjectMapper.selectByObjectName(params.get("analysisObject").toString());
//        Channel channel = channelMapper.selectByObjectId(analysisObject.getId());
        List<String> list = new ArrayList<>();
        Field field = null;
        for (String key : map.keySet()) {
            if (ownerType == 0) {
                field = fieldMapper.selectByOnlyEname(key, levelId, "", "");
            } else if (ownerType == 1) {
                field = fieldMapper.selectByOnlyEname(key, "", levelId, "");
            } else if (ownerType == 2) {
                field = fieldMapper.selectByOnlyEname(key, "", "", levelId);
            }
            if (null != field) {
                list.add(field.getCname());
            }
        }
        String[] header = new String[list.size()];
        list.toArray(header);
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(header);
        File tempFile = null;
        FileOutputStream fileOutputStream = null;
        CSVPrinter csvPrinter = null;
        try {
            tempFile = File.createTempFile("vehicle", ".csv");
            fileOutputStream = new FileOutputStream(tempFile);
            csvPrinter = new CSVPrinter(new OutputStreamWriter(fileOutputStream), csvFormat);
            //防止表头乱码
            byte[] bytes = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            fileOutputStream.write(bytes);
            for (int i = 0; i < logList.size(); i++) {
                Map<String, Object> map1 = logList.get(i);
                List list1 = new ArrayList();
                for (String key : map1.keySet()) {
                    list1.add(map1.get(key));
                }
                csvPrinter.printRecord(list1);
            }
            csvPrinter.flush();
            csvPrinter.close();
            fileOutputStream.close();
            FileInputStream fis = new FileInputStream(tempFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            HttpHeaders httpHeaders = new HttpHeaders();
            String name = "日志详细数据.csv";
            String fileName = new String(name.getBytes("UTF-8"), "iso-8859-1");
            httpHeaders.setContentDispositionFormData("attachment", fileName);
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            results = new ResponseEntity<byte[]>(bos.toByteArray(), httpHeaders, HttpStatus.OK);
        } catch (IOException e) {

            results = new ResponseEntity<byte[]>("服务器生成文件错误".getBytes(), new HttpHeaders(), HttpStatus.FAILED_DEPENDENCY);
            e.printStackTrace();
        }
        return results;


    }


    /**
     * 导出cvs
     */
    public ResponseEntity<byte[]> exportLogCsv(List<Map<String, Object>> logList) {

        ResponseEntity<byte[]> results = null;
        String[] header = {"邮箱名", "请求IP", "国家", "地区", "协议类型", "登录状态", "完整时间"};
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(header);
        File tempFile = null;
        FileOutputStream fileOutputStream = null;
        CSVPrinter csvPrinter = null;
        try {
            tempFile = File.createTempFile("vehicle", ".csv");
            fileOutputStream = new FileOutputStream(tempFile);
            csvPrinter = new CSVPrinter(new OutputStreamWriter(fileOutputStream), csvFormat);
            //防止表头乱码
            byte[] bytes = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            fileOutputStream.write(bytes);
            for (int i = 0; i < logList.size(); i++) {
                SearchCsv logBase = JSON.parseObject(JSON.toJSONString(logList.get(i)), SearchCsv.class);
                csvPrinter.printRecord(
                        logBase.getUsername(), //邮箱名
                        logBase.getReq_ip(), //请求ip
                        logBase.getCountry(), // 国家
                        logBase.getCity(), // 地区
                        logBase.getRule_group(), // 协议类型
                        logBase.getResult(), // 登录状态
                        logBase.getFulltime()//完整时间
                );
            }
            csvPrinter.flush();
            csvPrinter.close();
            fileOutputStream.close();
            FileInputStream fis = new FileInputStream(tempFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            HttpHeaders httpHeaders = new HttpHeaders();
            String name = "日志详细数据.csv";
            String fileName = new String(name.getBytes("UTF-8"), "iso-8859-1");
            httpHeaders.setContentDispositionFormData("attachment", fileName);
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            results = new ResponseEntity<byte[]>(bos.toByteArray(), httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            results = new ResponseEntity<byte[]>("服务器生成文件错误".getBytes(), new HttpHeaders(), HttpStatus.FAILED_DEPENDENCY);
            e.printStackTrace();
        }
        return results;


    }


    public String convertArrayToString(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        String res = "";
        for (int i = 0, len = strArr.length; i < len; i++) {
            res += strArr[i];
            if (i < len - 1) {
                res += ",";
            }
        }
        return res;
    }


    /**
     * 获取国家
     */
    public Set getCountryNum(String unit, String anaObject, String ruleGroup, String rule) {
        StringBuffer sql = new StringBuffer();
//        sql.append("select")
//                .append(" country");
//
//        sql.append(" from t_coremail_source  where 1=1");
//        if(unit!=null && unit.size()>0) {
//            sql.append(" and unit in ('" + unit.toString().replaceAll("\\[","").replaceAll("\\]","") +"') ");
//        }
//        sql.append("  group by country "+" ");
        sql.append("select")
                .append(" country");

        sql.append(" from log_analyse_R2U_tmp_country where 1=1");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        if (StringUtils.isNotBlank(anaObject)) {
            sql.append(" and ana_obj = '" + anaObject + "' ");
        }
        if (StringUtils.isNotBlank(ruleGroup)) {
            sql.append(" and rule_group = '" + ruleGroup + "' ");
        }
        if (StringUtils.isNotBlank(rule)) {
            sql.append(" and rule = '" + rule + "' ");
        }

        log.info("查询出的sql是:" + sql);
        List<String> tmpMapList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                for (int k = 1; k <= columnCount; k++) {
                    tmpMapList.add(rs.getObject(k).toString());
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        Set set = duplicate(tmpMapList);
        impalaDao.closeConnec();
        return set;
    }


    //去除重复数据
    public Set duplicate(List<String> tmpMapList) {
        Set set = new HashSet();
        for (String str : tmpMapList) {
            if (!set.contains(str)) {
                set.add(str);
            }
        }
        return set;
    }


    /**
     * 获取地区
     */
    public Set getCity(String unit, String anaObject, List<String> country, String ruleGroup, String rule) {
        StringBuffer sql = new StringBuffer();
//        sql.append("select")
//                .append(" city");
//
//        sql.append(" from t_coremail_source  where 1=1");
//        if(unit!=null && unit.size()>0) {
//            sql.append(" and unit in ('" + unit.toString().replaceAll("\\[","").replaceAll("\\]","") +"') ");
//        }
//        if(country!=null && country.size()>0) {
//            sql.append(" and country in ('" + country.toString().replaceAll("\\[","").replaceAll("\\]","") +"') ");
//        }
//        sql.append("  group by city "+" ");
        sql.append("select")
                .append(" city");
        sql.append(" from log_analyse_R2U_tmp_city where 1=1");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        if (StringUtils.isNotBlank(anaObject)) {
            sql.append(" and ana_obj = '" + anaObject + "' ");
        }
        if (StringUtils.isNotBlank(ruleGroup)) {
            sql.append(" and rule_group = '" + ruleGroup + "' ");
        }
        if (StringUtils.isNotBlank(rule)) {
            sql.append(" and rule = '" + rule + "' ");
        }
        if (country != null && country.size() > 0) {
            sql.append(" and country in " + genIns(country));
        }
        log.info("查询出的sql是:" + sql);
        List<String> tmpMapList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                for (int k = 1; k <= columnCount; k++) {
                    tmpMapList.add(rs.getObject(k).toString());
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        Set set = duplicate(tmpMapList);
        impalaDao.closeConnec();
        return set;
    }


    /**
     * 日志查询2.0
     */
    public ReData searchLogs(Map params, String type) {
        ReData reData = new ReData();
        int page = (int) params.get("page");
        int size = (int) params.get("size");
        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        String where = "";

        String ipPartCond = getPartCond(params, "req_ip", "ip_part", (String input) -> {
            return String.valueOf(IPUtil.ipToLong(input) % 10);
        });
        String usernameCond = getPartCond(params, "username", "username_part", (String input) -> {
            String tmp = input;
            if (input.length() > 4) {
                tmp = input.substring(0, 3);
            }
            int total = 0;
            for (char a : tmp.toCharArray()) {
                total += a;
            }

            return String.valueOf(total % 10);
        });


        try {
            sql = this.getSql(params, type);
            int start = sql.indexOf("where");
            where = sql.substring(start, sql.length());
            System.out.println(where);
        } catch (Exception e) {
            log.info("获取sql失败==", e.fillInStackTrace());
        }
        where += " " + ipPartCond + " " + usernameCond;

        if (StringUtils.isNotBlank(sql.toString())) {
            MD5 md5 = new MD5();
            Map tmpParam = new HashMap();
            tmpParam.putAll(params);
            tmpParam.remove("page");
            tmpParam.remove("size");
            String paramStr = JSONObject.toJSONString(tmpParam);
            String tablename = md5.getMD5ofStr(paramStr);
            int i = matchTaskMapper.isExist(tablename);
            if (i > 0) {
                sql = new StringBuffer();
                sql.append("select * from tmp_" + tablename + " " + where);
            } else {
                if (StringUtils.isNotEmpty(params.get("tableName").toString())) {
                    sql = new StringBuffer();
                    sql.append("select * from " + params.get("tableName").toString() + " " + where);
                }
            }
        }
        sql.append("  limit " + page * size + " ");
        log.info("查询出的sql是:" + sql);
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            if (null != rs) {
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> rowData = new HashMap<>();
                    for (int k = 1; k <= columnCount; k++) {
                        rowData.put(md.getColumnName(k), rs.getObject(k));
                    }
                    tmpMapList.add(rowData);
                }
            }
        } catch (Exception e) {
            log.info("查询异常===", e.fillInStackTrace());
        }

        impalaDao.closeConnec();
        reData.setData(tmpMapList);
        reData.setSql(sql);
        int a = sql.indexOf("from");
        int b = sql.indexOf("where");
        String table = sql.substring(a + 4, b);
        if (table.contains("t_iis_source")) {
            reData.setTablename("");
        } else {
            reData.setTablename(table);
        }
        reData.setTotal(tmpMapList.size());
        return reData;
    }


    /**
     * 获取境内外相交的邮箱
     *
     * @param params
     * @return
     */
    public List getIntersectionEmail(Map params, String anaObject) {
        List<String> reList = new ArrayList<>();
        //境内数据
        List<String> list = this.getWithInLog(params, anaObject);
        //境外数据
        List<String> list1 = this.getOverseasLog(params, anaObject);
        //取境内外交集邮箱
        for (String str : list) {
            if (!str.equals("") && !str.equals("-")) {
                if (list1.contains(str)) {
                    reList.add(str);
                }
            }
        }
        return reList;
    }

    /**
     * 获取查询的sql
     */
    public StringBuffer getSql(Map params, String type) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        String belong = params.containsKey("belong") ? (String) params.get("belong") : null;
        String levelId = params.containsKey("levelId") ? (String) params.get("levelId") : null;
        String project = params.containsKey("project") ? (String) params.get("project") : null;

        int ownerType = params.containsKey("ownerType") ? (Integer) params.get("ownerType") : -1;
        //获取分析对象
        String anaObject = getAnalysisObject(levelId, ownerType);
        List<String> fieldList = params.containsKey("fieldList") ? (List<String>) params.get("fieldList") : null;
        List<Field> list = searchFieldByExample(params);
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        if (type.equals("search")) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sql.append(list.get(i).getEname() + "");
                } else {
                    sql.append(" " + list.get(i).getEname() + "," + " ");
                }
            }
        } else if (type.equals("username")) {
            sql.append(" username ");
        } else if (type.equals("ip")) {
            sql.append(" req_ip,country,city ");
        } else if (type.equals("country")) {
            sql.append(" country ");
        } else if (type.equals("city")) {
            sql.append(" city ");
        } else if (type.equals("mdate")) {
            sql.append(" mdate ");
        } else {
            if (fieldList != null && fieldList.size() > 0) {
                for (int i = 0; i < fieldList.size(); i++) {
                    if (i == fieldList.size() - 1) {
                        sql.append(" " + fieldList.get(i) + " ");
                    } else {
                        sql.append(" " + fieldList.get(i) + "," + " ");
                    }
                }
            }
        }
        sql.append(" from");
        sql.append(" hxht_maillog_db.t_" + anaObject + "_source where 1=1");
        searchExample(params, list, sql, "search");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        if (StringUtils.isNotBlank(belong)) {
            switch (belong) {
                case "inchina":
                    sql.append(" and country = '中国' ");
                    break;
                case "nochina":
                    sql.append(" and country!= '中国' and country!='局域网' ");
                    break;
                case "special":
                    List reList = this.getIntersectionEmail(params, anaObject);
                    if (reList != null && reList.size() > 0) {
                        sql.append(" and username in " + genIns(reList));
                    } else {
                        sql = new StringBuffer();
                        return sql;
                    }
                    break;
            }
        }

        if (ownerType == 1) {
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(levelId);
            sql.append(" and rule_group = '" + ruleGroup.getName() + "' ");
        } else if (ownerType == 2) {
            Rule rule = ruleMapper.selectByPrimaryKey(levelId);
            sql.append(" and rule = '" + rule.getName() + "' ");
        }

        if (StringUtils.isNotBlank(project)) {
            sql.append(" and project = '" + project + "' ");
        }
//        if (params.containsKey("field") && params.containsKey("order")) {
//            if (StringUtils.isNotBlank(params.get("field").toString()) && StringUtils.isNotBlank(params.get("order").toString())) {
//                sql.append(" order by " + params.get("field").toString() + " " + params.get("order").toString() + "");
//            }
//        }
        return sql;
    }


    /**
     * 获取分析对象
     */
    public String getAnalysisObject(String id, int ownerType) {

        String analysisObject = "";
        if (ownerType == 0) {
            AnalysisObject analysis = analysisObjectMapper.selectByPrimaryKey(id);
            analysisObject = analysis.getObjectName();
        } else if (ownerType == 1) {
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(id);
            AnalysisObject analysis = analysisObjectMapper.selectByPrimaryKey(ruleGroup.getObjectId());
            analysisObject = analysis.getObjectName();
        } else if (ownerType == 2) {
            Rule rule = ruleMapper.selectByPrimaryKey(id);
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(rule.getRuleGroupId());
            AnalysisObject analysis = analysisObjectMapper.selectByPrimaryKey(ruleGroup.getObjectId());
            analysisObject = analysis.getObjectName();
        }
        return analysisObject;
    }


    /**
     * 查询分析对象下对应的所有字段2.0
     */
    public List<Field> searchFiledByAnalysis(Map params) {
        AnalysisObject analysisObject = analysisObjectMapper.selectByObjectName(params.get("analysisObject").toString());
        Channel channel = channelMapper.selectByObjectId(analysisObject.getId());
        List<Field> list = fieldMapper.selectAll(channel.getId());
        return list;
    }

    /**
     * 通过条件查询字段
     */
    public List<Field> searchFieldByExample(Map params) {
        String id = params.get("levelId").toString();
        int ownerType = (Integer) params.get("ownerType");
        List<Field> list = new ArrayList<>();
        if (ownerType == 0) {
            Field field = new Field();
            field.setObjectId(id);
            list = fieldMapper.selectByConditions(field);
        } else if (ownerType == 1) {
            Field field = new Field();
            field.setRuleGroupId(id);
            List<Field> ruleGroupList = fieldMapper.selectByConditions(field);
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(id);
            Field field1 = new Field();
            field1.setObjectId(ruleGroup.getObjectId());
            List<Field> objectList = fieldMapper.selectByConditions(field1);
            list.addAll(objectList);
            list.addAll(ruleGroupList);
        } else {
            Field field = new Field();
            field.setRuleId(id);
            List<Field> ruleList = fieldMapper.selectByConditions(field);
            Rule rule = ruleMapper.selectByPrimaryKey(id);
            Field field1 = new Field();
            field1.setRuleGroupId(rule.getRuleGroupId());
            List<Field> ruleGroupList = fieldMapper.selectByConditions(field1);
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(rule.getRuleGroupId());
            Field field2 = new Field();
            field2.setObjectId(ruleGroup.getObjectId());
            List<Field> objectList = fieldMapper.selectByConditions(field2);
            list.addAll(objectList);
            list.addAll(ruleGroupList);
            list.addAll(ruleList);
        }
        return list;
    }


    /**
     * 查询字段的筛选条件
     */
    public void searchExample(Map params, List<Field> list, StringBuffer sql, String type) {
        for (Field field : list) {
            //log.info("字段"+field);
            if (params.containsKey(field.getEname())) {
                if (field.getComponentType().equals("day") || field.getComponentType().equals("daterange") || field.getComponentType().equals("time")) {
                    Map map = (Map) params.get(field.getEname());
                    String types = map.get("type").toString();
                    List list2 = (List) map.get("value");
                    //List list1 = (List)params.get(field.getEname());
                    if (list2 != null && list2.size() > 0) {
                        if (types.equals("include")) {
                            sql.append("  and  " + field.getEname() + " >= '" + list2.get(0) + "' ");
                            sql.append("  and  " + field.getEname() + " <= '" + list2.get(1) + "' ");
                        } else {
                            sql.append("  and  " + field.getEname() + " < '" + list2.get(0) + "' ");
                            sql.append("  or  " + field.getEname() + " > '" + list2.get(1) + "' ");
                        }
                    }
                } else if (field.getIsMoreValue() == 1) {
                    Map map = (Map) params.get(field.getEname());
                    String types = map.get("type").toString();
                    List list2 = (List) map.get("value");
                    if (list2 != null && list2.size() > 0) {
                        if (types.equals("include")) {
                            if (type.equals("total")) {
                                sql.append("  and  " + field.getEname() + "  in " + genIns1(list2));
                            } else {
                                sql.append("  and  " + field.getEname() + "  in " + genIns(list2));
                            }
                        } else {
                            if (type.equals("total")) {
                                sql.append("  and  " + field.getEname() + " not in " + genIns1(list2));
                            } else {
                                sql.append("  and  " + field.getEname() + " not in " + genIns(list2));
                            }
                        }
                    }
                } else {
                    Map map = (Map) params.get(field.getEname());
                    String types = map.get("type").toString();
                    Object value = map.get("value");
                    if (types.equals("include")) {
                        //res_ip 特殊处理  由前端控制精确模糊
                        if (field.getEname().equals("req_ip")) {
                            Map<String, Object> m = (Map<String, Object>) params.get("req_ip");
                            String v = m.get("value").toString();
                            if (StringUtils.isBlank(v)) {
                                continue;
                            }
                            String mode = m.containsKey("smode") ? m.get("smode").toString() : "no";//默认是模糊
                            if ("yes".equals(mode)) {//精确
//                                sql.append("  and  " + field.getEname() + " = '" + value + "' ");
                                sql.append(" and " + field.getEname() + " in ('" + value.toString().replace(",", "','") + "')");
                            } else {//模糊
                                sql.append("  and  " + field.getEname() + " regexp '" + "^" + value + "' ");
                            }
                        } else if (field.getSearchType().equals("term")) {
                            if (value != null && value != "") {
                                sql.append("  and  " + field.getEname() + " = '" + value + "' ");
                            }
                        } else {
                            if (value != null && value != "") {
//                                if (field.getEname().equals("req_ip")) {
//                                    sql.append("  and  " + field.getEname() + " regexp '" + "^" + value + "' ");
//                                }
//                                else {
                                sql.append("  and  " + field.getEname() + " like '" + "%" + value + "%" + "' ");
//                                }
                            }
                        }
                    } else {
                        if (field.getSearchType().equals("term")) {
                            if (value != null && value != "") {
                                sql.append("  and  " + field.getEname() + " != '" + value + "' ");
                            }
                        } else {
                            if (value != null && value != "") {
                                sql.append("  and  " + field.getEname() + " not like '" + "%" + value + "%" + "' ");
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 查询日志2.0
     *
     * @return
     */
    public Map<String, Object> searchDataLogs(Map map, String type) {
        ReData reData = searchLogs(map, type);
        Map map1 = dealDataLog(reData, map);
        return map1;
    }

    public interface ExecCall {

        public String exec(String input);
    }

    private String getPartCond(Map map, String field, String partStr, ExecCall call) {
        if (!map.containsKey(field) || map.get(field) == null) {
            return "";
        }

        String valueStr = ((Map) map.get(field)).get("value").toString();
        if (Strings.isEmpty(valueStr)) return "";
        String[] arr = valueStr.split(",");
        if (arr.length == 0) {
            log.error("params error");
            return "";
        }

        String result = "";

        if (arr.length == 1) {
            String partValue = call.exec(arr[0]);
            result = " and " + partStr + "='" + partValue + "'";
//            result+=" and "+field+"='"+arr[0]+"'";
            return result;
        }
        result = " and " + partStr + " in (";
//        tmp表示ip或用户名的分区
        String tmp = "";
        Set<String> tmpSet = new HashSet<String>();
        for (int i = 0; i < arr.length; i++) {
            String partValue = call.exec(arr[i]);
            if (tmpSet.contains(partValue)) {
                continue;
            }
            if (i != 0) tmp += ",";
            tmpSet.add(partValue);
            tmp += "'" + partValue + "'";
        }
        result += tmp + ") ";

        return result;
    }

    /**
     * 保存结果集
     *
     * @return
     */
    public int saveResult(Map map, String type) {
        int result = 1;
        StringBuffer sql = new StringBuffer();
        String field = "";
        try {
            sql = this.getSql(map, type);
            int from = sql.lastIndexOf("select");
            int end = sql.indexOf("from");
            field = sql.substring(from + 6, end).trim();
            sql.replace(from + 6, end, " * ");
        } catch (Exception e) {
            log.info("获取sql失败==", e.fillInStackTrace());
            return -1;
        }

        if (StringUtils.isNotBlank(sql.toString())) {
            MD5 md5 = new MD5();
            Map tmpParam = new HashMap();
            tmpParam.putAll(map);
            tmpParam.remove("page");
            tmpParam.remove("size");
            String paramStr = JSONObject.toJSONString(tmpParam);
            String tablename = md5.getMD5ofStr(paramStr);
            int a = matchTaskMapper.isExist(tablename);
            if (a > 0) {
                return 0;
            }
            boolean exec = false;
            StringBuffer createSql = new StringBuffer();
            createSql.append("create table tmp_" + tablename + " STORED as parquet as " + sql);
            log.info("查询出的createSql是:" + createSql);
            try {
                exec = impalaDao.execute(createSql.toString());
                if (!exec) {
                    return -1;
                }
            } catch (SQLException e) {
                log.info("创建临时表失败XXXXXXXXXXXXXXXXXXX");
                e.printStackTrace();
                return -1;
            }
            if (exec) {
                MatchTaskEntity matchTask = new MatchTaskEntity();
                matchTask.setId(UUID.randomUUID().toString());
                matchTask.setParameter(paramStr);
                matchTask.setTableName(tablename);
                matchTask.setField(field);
                long total = getDataTotalLog(map, "tmp_" + tablename);
                matchTask.setTotal(total);
                LocalDateTime local = LocalDateTime.now();
                matchTask.setDatetime(local.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                matchTaskMapper.save(matchTask);
            }
        }
        impalaDao.closeConnec();
        return result;
    }

    /**
     * 删除结果集
     */
    public boolean deleteResult() {
        boolean result = true;
        List<MatchTaskEntity> list = matchTaskMapper.getList();
        for (MatchTaskEntity matchTask : list) {
            String tableName = matchTask.getTableName();
            String sql = "drop table if exists hxht_maillog_db.tmp_" + tableName;
            try {
                result = impalaDao.execute(sql);
            } catch (SQLException e) {
                log.info("删除临时表失败XXXXXXXXXXXXXXXXXXX");
                e.printStackTrace();
                return false;
            }
            if (result) {
                matchTaskMapper.deleteById(matchTask.getId());
            }
        }
        return result;
    }

    /**
     * 查询境内去重后的邮箱数据
     */
    public List<String> getWithInLog(Map params, String anaObject) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        StringBuffer sql = new StringBuffer();
        sql.append("select username");
        sql.append(" from");
        sql.append(" t_" + anaObject + "_source where 1=1");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        sql.append(" and country = '中国' ");
        sql.append(" group by username");
        log.info("查询出的sql是:" + sql);
        List<String> tmpMapList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                String username = rs.getString("username");
                tmpMapList.add(username);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        impalaDao.closeConnec();
        return tmpMapList;
    }


    //查询境内外相交的数据
    public Map<String, Object> getIntersectionLog(Map params, List elist, String type, String anaObject, String levelId, int ownerType) {
        int page = (int) params.get("page");
        int size = (int) params.get("size");
        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        if (elist != null && elist.size() > 0) {
            StringBuffer sql = this.getIntersectionSql(params, elist, type, anaObject, levelId, ownerType);
            sql.append("  limit " + page * size + " ");
            log.info("查询出的sql是:" + sql);
            ResultSet rs = null;
            try {
                rs = impalaDao.select(sql.toString());
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> rowData = new HashMap<>();
                    for (int k = 1; k <= columnCount; k++) {
                        rowData.put(md.getColumnName(k), rs.getObject(k));
                    }
                    tmpMapList.add(rowData);
                }
            } catch (Exception e) {
                e.getMessage();
            }
            impalaDao.closeConnec();
        }
        Map<String, Object> map = this.getPgeList(tmpMapList, params, elist);
        return map;
    }


    /**
     * 获取境内外均访问数据sql
     */
    public StringBuffer getIntersectionSql(Map params, List elist, String type, String anaObject, String levelId, int ownerType) {

        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        List<String> fieldList = params.containsKey("fieldList") ? (List<String>) params.get("fieldList") : null;
        String project = params.containsKey("project") ? (String) params.get("project") : null;
        List<Field> list = searchFieldByExample(params);
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        if (type.equals("search")) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sql.append(list.get(i).getEname() + "");
                } else {
                    sql.append(" " + list.get(i).getEname() + "," + " ");
                }
            }
        } else {
            if (fieldList != null && fieldList.size() > 0) {
                for (int i = 0; i < fieldList.size(); i++) {
                    if (i == fieldList.size() - 1) {
                        sql.append(fieldList.get(i) + "");
                    } else {
                        sql.append(" " + fieldList.get(i) + "," + " ");
                    }
                }
            }
        }
        sql.append(" from");
        sql.append(" hxht_maillog_db.t_" + anaObject + "_source where 1=1");
        searchExample(params, list, sql, "search");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        if (ownerType == 1) {
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(levelId);
            sql.append(" and rule_group = '" + ruleGroup.getName() + "' ");
        } else if (ownerType == 2) {
            Rule rule = ruleMapper.selectByPrimaryKey(levelId);
            sql.append(" and rule = '" + rule.getName() + "' ");
        }

        if (StringUtils.isNotBlank(project)) {
            sql.append(" and project = '" + project + "' ");
        }
        sql.append(" and country != '局域网' ");
        //sql.append(" and username in "+"(select emailname from log_temp_special) ");
        if (elist != null && elist.size() > 0) {
            sql.append(" and username in " + genIns(elist));
        }
        return sql;
    }


    /**
     * 获取总量
     */
    public Long getIntersectionLogTotal(Map params, List elist) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        String levelId = params.containsKey("levelId") ? (String) params.get("levelId") : null;

        int ownerType = params.containsKey("ownerType") ? (Integer) params.get("ownerType") : -1;
        //获取分析对象
        String anaObject = getAnalysisObject(levelId, ownerType);
        Long total = 0L;
        List<Field> list = searchFieldByExample(params);
        if (elist != null && elist.size() > 0) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(*) ");
            sql.append(" from");
            sql.append(" t_" + anaObject + "_source where 1=1");
            searchExample(params, list, sql, "total");
            if (StringUtils.isNotBlank(unit)) {
                sql.append(" and unit = '" + unit + "' ");
            }
            sql.append(" and country != '局域网' ");
            //sql.append(" and username in "+"(select emailname from log_temp_special) ");
            if (elist != null && elist.size() > 0) {
                sql.append(" and username in " + genIns1(elist));
            }
            log.info("查询总量sql: " + sql);
            ResultSet rs = null;
            try {
                rs = impalaDao.select(sql.toString());
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();
                while (rs.next()) {
                    total = Long.valueOf(rs.getObject(1).toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            impalaDao.closeConnec();
        }
        return total;
    }


    /**
     * 将数组进行分页
     *
     * @return
     */
    public Map<String, Object> getPgeList(List<Map<String, Object>> list, Map params, List eList) {

        List reList = new ArrayList();
        Map map = new HashMap();
        int limit = params.containsKey("size") ? (int) params.get("size") : 10;
        int offset = params.containsKey("page") ? (int) params.get("page") : 1;
        int count = 0;
        for (int j = (offset - 1) * limit; j < limit * offset; j++) {
            try {
                Map<String, Object> map1 = list.get(j);
                reList.add(map1);
                count++;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        Long tal = getIntersectionLogTotal(params, eList);

        map.put("count", count);
        map.put("total", tal);
        if (eList != null && eList.size() > 0) {
            map.put("data", reList);
        } else {
            map.put("data", new ArrayList());
        }
        return map;

    }


    /**
     * 查询境外去重后的邮箱数据
     */
    public List<String> getOverseasLog(Map params, String anaObject) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        StringBuffer sql = new StringBuffer();
        sql.append("select username");
        sql.append(" from");
        sql.append(" t_" + anaObject + "_source where 1=1");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        sql.append(" and country != '中国' ");
        sql.append(" and country != '局域网' ");
        sql.append(" group by username");
        log.info("查询出的sql是:" + sql);
        List<String> tmpMapList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                String username = rs.getString("username");
                tmpMapList.add(username);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        impalaDao.closeConnec();
        return tmpMapList;
    }


    /**
     * 处理返回的数据，将数据进行分页 2.0
     */
    public Map dealDataLog(ReData reData, Map params) {

        List reList = new ArrayList();
        Map map = new HashMap();
        int limit = (int) params.get("size");
        int offset = (int) params.get("page");
        int total = reData.getTotal();
        String tablename = reData.getTablename();
        total = total > (offset - 1) * limit ? total : limit;
        int count = 0;
        for (int j = (offset - 1) * limit; j < total; j++) {
            try {
                Map map1 = reData.getData().get(j);
                reList.add(map1);
                count++;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        Long tal = getDataTotalLog(params, tablename);
        map.put("count", count);
        map.put("total", tal);
        map.put("data", reList);
        map.put("tableName", tablename);
        return map;
    }


    /**
     * 获取总量2.0
     */
    public Long getDataTotalLog(Map params, String tablename) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        String belong = params.containsKey("belong") ? (String) params.get("belong") : null;
        String levelId = params.containsKey("levelId") ? (String) params.get("levelId") : null;
        String project = params.containsKey("project") ? (String) params.get("project") : null;

        int ownerType = params.containsKey("ownerType") ? (Integer) params.get("ownerType") : -1;
        //获取分析对象
        String anaObject = getAnalysisObject(levelId, ownerType);

        String ipPartCond = getPartCond(params, "req_ip", "ip_part", (String input) -> {
            return String.valueOf(IPUtil.ipToLong(input) % 10);
        });
        String usernameCond = getPartCond(params, "username", "username_part", (String input) -> {
            String tmp = input;
            if (input.length() > 4) {
                tmp = input.substring(0, 3);
            }
            int total = 0;
            for (char a : tmp.toCharArray()) {
                total += a;
            }

            return String.valueOf(total % 10);
        });

        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) ");
        sql.append(" from ");
        if (StringUtils.isNotBlank(tablename)) {
            sql.append(tablename + " where 1=1 ");
        } else {
            sql.append(" hxht_maillog_db.t_iis_source where 1=1 ");
        }
        List<Field> list = searchFieldByExample(params);
        searchExample(params, list, sql, "total");
        if (StringUtils.isNotBlank(unit)) {
            sql.append(" and unit = '" + unit + "' ");
        }
        sql.append(ipPartCond);
        sql.append(usernameCond);

        if (StringUtils.isNotBlank(belong)) {
            switch (belong) {
                case "inchina":
                    sql.append(" and country = '中国' ");
                    break;
                case "nochina":
                    sql.append(" and country!= '中国' and country!='局域网' and country!='本机地址'");
                    break;
            }
        }
        if (ownerType == 1) {
            RuleGroup ruleGroup = ruleGroupMapper.selectByPrimaryKey(levelId);
            sql.append(" and rule_group = '" + ruleGroup.getName() + "' ");
        } else if (ownerType == 2) {
            Rule rule = ruleMapper.selectByPrimaryKey(levelId);
            sql.append(" and rule = '" + rule.getName() + "' ");
        }

        if (StringUtils.isNotBlank(project)) {
            sql.append(" and project = '" + project + "' ");
        }
        log.info("查询总量sql: " + sql);
        ResultSet rs = null;
        Long total = 0L;
        try {
            rs = impalaDao.select(sql.toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                total = Long.valueOf(rs.getObject(1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        impalaDao.closeConnec();
        return total;
    }
}
