package com.hxht.logprocess.search.service;


import com.hxht.logprocess.logbulk.dao.OfflineTaskMapper;
import com.hxht.logprocess.logbulk.model.OfflineTask;
import com.hxht.logprocess.search.dao.ImpalaDao;
import com.hxht.logprocess.search.dao.SearchLogDao;
import com.hxht.logprocess.search.model.LogParams;
import com.hxht.logprocess.search.model.ReData;
import com.hxht.logprocess.setting.model.Field;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class SearchLogService {


    @Autowired
    private ImpalaDao impalaDao;

    @Autowired
    private SearchLogDao searchLogDao;

    @Autowired
    private OfflineTaskMapper offlineTaskMapper;


    @Value("${offlineFile}")
    private String offlineFile;
    @Value("${offlineAddress}")
    private String offlineAddress;
    @Value("${shellPath}")
    private String shellPath;
    @Value("${dirFilePath}")
    private String dirFilePath;

    /**
     * 查询日志
     */
    public Map<String, Object> searchLog(LogParams logParams) {
        if (logParams.getPage() == null || logParams.getSize() == null) {
            logParams.setPage(1);
            logParams.setSize(10);
        }
        int from = (logParams.getPage() - 1) * logParams.getSize();
        logParams.setFrom(from);
        Map map = searchLogDao.searchDataLog(logParams);
        return map;
    }


    /**
     * 不进行分页的日志数据
     */
    public List<Map<String, Object>> logData(LogParams logParams) {
        List<Map<String, Object>> list = searchLogDao.searchLog(logParams);
        return list;
    }


    /**
     * 日志数据
     */
    public List<Map<String, Object>> logsData(Map params) {
        if (!params.containsKey("page")) {
            params.put("page", 1);
        }
        if (!params.containsKey("size")) {
            params.put("size", 100000);
        }
        ReData reData = searchLogDao.searchLogs(params, "export");
        return reData.getData();
    }


    /**
     * 导出服务层
     */
    public ResponseEntity<byte[]> exportLogCsv(List<Map<String, Object>> list) {

        return searchLogDao.exportLogCsv(list);
    }


    /**
     * 导出服务层2.0
     */
    public ResponseEntity<byte[]> exportLogsCsv(List<Map<String, Object>> list, Map params) {

        return searchLogDao.exportLogsCsv(list, params);
    }


    /**
     * 获取国家
     */
    public Set getCountryNum(String unit, String anaObject, String ruleGroup, String rule) {
        return searchLogDao.getCountryNum(unit, anaObject, ruleGroup, rule);
    }


    /**
     * 获取地区
     */
    public Set getCity(String unit, String anaObject, List<String> country, String ruleGroup, String rule) {
        return searchLogDao.getCity(unit, anaObject, country, ruleGroup, rule);
    }


    /**
     * 日志查询2.0
     *
     * @param params
     */
    public Map<String, Object> searchLogs(Map params) {
        if (!params.containsKey("page")) {
            params.put("page", 1);
        }
        if (!params.containsKey("size")) {
            params.put("size", 10);

        }
        log.info("page===" + params.get("page") + "size===" + params.get("size"));
        return searchLogDao.searchDataLogs(params, "search");

    }


    /**
     * 保存结果集
     * @param params
     * @return
     */
    public int saveResult(Map params) {
        return searchLogDao.saveResult(params, "search");
    }

    /**
     * 清除结果集
     * @return
     */
    public boolean deleteResult() {
        return searchLogDao.deleteResult();
    }


    /**
     * 特殊行为查询
     */
    public Map<String, Object> specialSearch(Map params, String type) {
        String levelId = params.containsKey("levelId") ? (String) params.get("levelId") : null;

        int ownerType = params.containsKey("ownerType") ? (Integer) params.get("ownerType") : -1;
        //获取分析对象
        String anaObject = searchLogDao.getAnalysisObject(levelId, ownerType);
        List reList = this.getIntersectionEmail(params, anaObject);

//          //将list数组进行拼接
//          String str = genStringList(reList);
//          log.info("拼接完毕: "+str);
//          log.info("创建境内外临时表");
//          //创建境内外相交邮箱的临时表
//          forceSpecialTempTable(str);
//         log.info("创建境内外临时表完毕");
        if (!params.containsKey("page")) {
            params.put("page", 1);
        }
        if (!params.containsKey("size")) {
            params.put("size", 10);

        }
        //查询境内外相交数据
        Map<String, Object> map = searchLogDao.getIntersectionLog(params, reList, type, anaObject, levelId, ownerType);

        return map;
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
        List<String> list = searchLogDao.getWithInLog(params, anaObject);
        //境外数据
        List<String> list1 = searchLogDao.getOverseasLog(params, anaObject);
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


    public String genStringList(List<String> list) {

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                buffer.append("('" + list.get(i) + "')");
            } else {
                buffer.append("('" + list.get(i) + "')" + ",");

            }
        }
        return buffer.toString();

    }


    //境内外交集邮箱临时表
    public void forceSpecialTempTable(String str) {

        String dropTableSql = "drop table log_temp_special";
        try {
            impalaDao.execute(dropTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        genSpecialTemp(str);
    }


    public void test() {

//        String dropTableSql = "impala-shell -q select * from t_weichai_ana_access_1119_source limit 10 -B --output_delimiter=\"\\t\" -o C:/fileDes/test.csv";
//        try {
//            impalaDao.execute(dropTableSql);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        // String filename = UUID.randomUUID().toString();
        //将生成的sql放入指定的路径中
        //String sql = "select * from hxht_maillog_db.t_weichai_ana_access_1119_source limit 10;";
        //String outRePath = "/file/"  + "test.sql";
        //createFile(sql,outRePath);

//        log.info("开始执行命令");
        //Process process =null;
//        InputStream inputStream;
//         String str ="/usr/bin/impala-shell -f " + "/file/test.sql " + "-B --output_delimiter=',' --print_header -o /file/test.csv";
        //String str = "sh /opt/hxht/logimporter/hx-log/test.sh 'select * from hxht_maillog_db.t_weichai_ana_access_1119_source limit 10' ";
//         try {
//             log.info("传入的sql: "+str);
//             process = Runtime.getRuntime().exec(str);
//             log.info("命令执行完毕");
//             inputStream = process.getInputStream();
//             InputStreamReader inputStreamReader =new InputStreamReader(inputStream);
//             BufferedReader br = new BufferedReader(inputStreamReader);
//             String line =null;
//             while ((line =br.readLine())!=null){
//                 log.info("返回内容"+line);
//             }
//             inputStream.close();
//             br.close();
//         }catch (IOException e){
//            log.error(e.getMessage());
//         }
//        if(process != null)
//            process.destroy();
        String fName = "sql.sql";
        String pathName = offlineFile + "/" + fName;
        String fileName = UUID.randomUUID().toString() + ".csv";
        String allFileName = offlineFile + "/" + fileName;
        //String sql = "select mdate,  time,  username,  req_ip,  country,  city,  opt, source_log from hxht_maillog_db.t_lin_ana_1124_5_source where 1=1 and unit = 'test_pro_5_11124'";
        try {
            log.info("开始执行");
            //String[] cmd = new String[]{"/bin/sh", "-c", "/usr/bin/impala-shell -f " + "/file/test1.sql" + " -B --output_delimiter=',' --print_header -o " + allFileName};

            String [] cmd = {"/bin/sh","-c","sh "+dirFilePath};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            log.info("测试完毕");
            log.info("执行结果: " + process.exitValue());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }


    public void testFile() {
//        String fName = "sql.sql";
//        String pathName= offlineFile+"/"+fName;
//        String fileName =UUID.randomUUID().toString()+".csv";
//        String allFileName= offlineFile+"/"+fileName;
//        log.info("开始执行");
//        String []cmd = new String[]{"/bin/sh", "-c","/usr/bin/impala-shell -f "+"/file/test1.sql"+" -B --output_delimiter=',' --print_header -o "+ allFileName};
//        Process process=null;
//            synchronized (this) {
//                try {
//                    process = Runtime.getRuntime().exec(cmd);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            //记录进程缓存错误信息
//            final StringBuffer errorLog = new StringBuffer();
//            //获取执行进程的错误流
//            final InputStream errorStream = process.getErrorStream();
//            final InputStream inputStream = process.getInputStream();
//            //处理InputStream的线程
//            new Thread() {
//                public void run() {
//                    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//                    String line = null;
//                    try {
//                        while ((line = in.readLine()) != null && !errorLog.toString().contains("ERROR")) {
//                            if (line != null) {
//                                errorLog.append(line);
//                            }
//                        }
//                    } catch (IOException e) {
//                        throw new RuntimeException("[shell exec error]:" + errorLog, e);
//                    } finally {
//                        try {
//                            inputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//            //处理errorStream的线程
//            new Thread() {
//                @Override
//                public void run() {
//                    BufferedReader err = new BufferedReader(new InputStreamReader(errorStream));
//                    String line = null;
//                    try {
//                        while ((line = err.readLine()) != null && !errorLog.toString().contains("ERROR")) {
//                            if (line != null) {
//                                errorLog.append(line);
//                            }
//                        }
//                    } catch (IOException e) {
//                        throw new RuntimeException("[shell exec error]:" + errorLog, e);
//                    } finally {
//                        try {
//                            errorStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//
//            log.info("等待shell脚本执行完成");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //异常终止
//            if (errorLog != null && errorLog.length() > 0 && errorLog.toString().contains("ERROR")) {
//                log.error("[shell exec error]:" + errorLog);
//                throw new RuntimeException("[shell exec error]:" + errorLog);
//            }
//        try {
//            process.waitFor(); //等待shell脚本执行完成
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("测试完毕");
//        log.info("执行结果: "+process.exitValue());
    }


    /**
     * 写文件
     *
     * @param outRePath
     */
    public void createFile(String value, String outRePath) {
        //String outPutPath = "C:/snort";
        //
        File csvFile;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outRePath);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // 使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件内容
            csvWtriter.write(value);
            csvWtriter.newLine();
            log.info("写入完毕");
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 离线导出日志csv
     */
    @Async
    public void logOfflineDownload(Map params, OfflineTask offlineTask) {

        log.info("开始离线导入");
        StringBuffer sql = null;
        String[] cmd = new String[0];
        String fileName;
        fileName = params.containsKey("fileName") ? params.get("fileName").toString() : UUID.randomUUID().toString();
//        String fileName = UUID.randomUUID().toString() + ".csv";
        String allFileName = offlineFile + "/" + fileName;
        File tempFile = new File(allFileName);
        if (tempFile.exists()) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sf.format(new Date());
            fileName = fileName + date;
        }
//        try {
//            String path = offlineFile + File.separator + fileName.substring(0, fileName.length() - 4);
//            File file = new File(path);
//            if (!file.exists()) {
//                file.mkdir();
//            }
//        } catch (Exception e) {
//            log.info("创建文件失败===", e.fillInStackTrace());
//        }

        offlineTask.setFile(fileName + ".zip");
        try {
            offlineTaskMapper.updateByPrimaryKeySelective(offlineTask);
        } catch (Exception e) {
            log.info("更新离线任务失败===", e.fillInStackTrace());
        }
//        String allFileName = offlineFile + "/" + fileName;
        allFileName = offlineFile + "/" + fileName;
        if (params.containsKey("logs_types_offline")) {
            log.info("准备获取sql===");
            String type = params.get("logs_types_offline").toString();
            if (type.equals("ip")) {//境内外均访问离线
//                 String levelId = params.containsKey("levelId") ? (String) params.get("levelId") : null;
//
//                 int ownerType = params.containsKey("ownerType") ? (Integer) params.get("ownerType") : -1;
//                 //获取分析对象
//                 String anaObject = searchLogDao.getAnalysisObject(levelId,ownerType);
//                 List list = this.getIntersectionEmail(params,anaObject);
//                 if (list != null && list.size() > 0) {
//                     sql = searchLogDao.getIntersectionSql(params, list, "export",anaObject,levelId,ownerType);
//
//                 } else {
//                     sql = new StringBuffer();
//                 }
                sql = this.getIpDetailSql(params);

            } else if (type.equals("email")) {
                sql = this.getEmailDetailSql(params);
//                 try {
//                     cmd = new String[]{"/bin/sh", "-c", "sh " + offlineAddress + " \"" + sql.toString() + "\"  \"" + allFileName + "\" "};
//                 } catch (Exception e) {
//                     log.error(e.getMessage());
//                 }
            } else if (type.equals("country")) {
                sql = this.getCountryDetailSql(params);
//                 try {
//                     cmd = new String[]{"/bin/sh", "-c", "sh " + offlineAddress + " \"" + sql.toString() + "\"  \"" + allFileName + "\" "};
//                 } catch (Exception e) {
//                     log.error(e.getMessage());
//                 }
            } else if (type.equals("city")) {
                sql = this.getCityDetailSql(params);
//                 try {
//                     cmd = new String[]{"/bin/sh", "-c", "sh " + offlineAddress + " \"" + sql.toString() + "\"  \"" + allFileName + "\" "};
//                 } catch (Exception e) {
//                     log.error(e.getMessage());
//                 }
            } else {
                sql = searchLogDao.getSql(params, "export");
//                 try {
//                     cmd = new String[]{"/bin/sh", "-c", "sh " + offlineAddress + " \"" + sql.toString() + "\"  \"" + allFileName + "\" "};
//                 } catch (Exception e) {
//                     log.error(e.getMessage());
//                 }
            }
            log.info("获取sql完毕===准备写入sql文件");
            //将sql写入文件中
            String fName = "sql.sql";
            String pathName = offlineFile + "/" + fName;
            log.info("自定义文件名称===" + allFileName);

            try {
                Writer writer = new FileWriter(offlineFile + "/" + fName, false);
                writer.write(sql.toString());
                log.info("写入.sql文件完毕");
                writer.flush();
                writer.close();
//                cmd = new String[]{"/bin/sh", "-c", "/usr/bin/impala-shell -f " + pathName + " -B --output_delimiter=',' --print_header -o " + allFileName};
                cmd = new String[]{"/bin/sh", "-c", "/usr/bin/impala-shell -f " + pathName + " -B --output_delimiter=',' --print_header | $(/usr/bin/python3   " + shellPath + " " + allFileName + ")" };
//                cmd = new String[]{"/bin/sh", "-c", "/usr/bin/impala-shell -f " + pathName + " -B --output_delimiter=',' --print_header | $(/usr/bin/python3 /opt/hxht/logimporter/hx-log/pip_data.py "+allFileName +")"};
            } catch (IOException e) {
                log.info("调用脚本异常===", e.fillInStackTrace());
            }
            log.info("离线下载的sql: " + sql.toString());
            Process process = null;
            synchronized (this) {
                try {
                    process = Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //记录进程缓存错误信息
            final StringBuffer errorLog = new StringBuffer();
            //获取执行进程的错误流
            final InputStream errorStream = process.getErrorStream();
            final InputStream inputStream = process.getInputStream();
            //处理InputStream的线程
            new Thread() {
                public void run() {
                    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    try {
                        while ((line = in.readLine()) != null && !errorLog.toString().contains("ERROR")) {
                            if (line != null) {
                                errorLog.append(line);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("[shell exec error]:" + errorLog, e);
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            //处理errorStream的线程
            new Thread() {
                @Override
                public void run() {
                    BufferedReader err = new BufferedReader(new InputStreamReader(errorStream));
                    String line = null;
                    try {
                        while ((line = err.readLine()) != null && !errorLog.toString().contains("ERROR")) {
                            if (line != null) {
                                errorLog.append(line);
                            }
                        }
                    } catch (IOException e) {
                        log.info("执行脚本异常===", e.fillInStackTrace());
                        throw new RuntimeException("[shell exec error]:" + errorLog, e);
                    } finally {
                        try {
                            errorStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            log.info("等待shell脚本执行完成");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //异常终止
            if (errorLog != null && errorLog.length() > 0 && errorLog.toString().contains("ERROR")) {
                log.error("[shell exec error]:" + errorLog);
                throw new RuntimeException("[shell exec error]:" + errorLog);
            }
            try {
                process.waitFor(); //等待shell脚本执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("执行结果: " + process.exitValue());
            //todo 待优化 exitValue 127
//            if (process.exitValue() == 0) {
                offlineTask.setStatus("1");//完成状态
                offlineTask.setEndTime(new Date());
                offlineTask.setUpdateTime(new Date());
                offlineTaskMapper.updateByPrimaryKeySelective(offlineTask);
//            } else {
//                offlineTask.setStatus("2");//失败状态
//                offlineTask.setEndTime(new Date());
//                offlineTask.setUpdateTime(new Date());
//                offlineTaskMapper.updateByPrimaryKeySelective(offlineTask);
////            }
        }
        log.info("离线导入完毕");
    }


    public void genSpecialTemp(String str) {
        String sql = "create table log_temp_special " +
                "(emailname string)" +
                " stored as parquet;";
        try {
            impalaDao.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql1 = "insert into log_temp_special values " + str;
        try {
            impalaDao.execute(sql1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //impalaDao.closeConnec();

    }


    /**
     * 通过分析对象查询字段
     *
     * @param params
     */
    public List<Field> searchFieldByAnalyseObject(Map params) {
        return searchLogDao.searchFiledByAnalysis(params);
    }

    /**
     * 通过条件查询字段
     *
     * @param params
     */
    public List<Field> searchFieldByExample(Map params) {
        return searchLogDao.searchFieldByExample(params);
    }


    /**
     * 查询总览服务
     */
    public Map getOverView(Map params) {
        Connection con = null;
        try {
            con = impalaDao.connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        long emailTotal = getEmailTotal(params, con);
        long ipTotal = getIpTotal(params, con);
        long countryTotal = getCountryTotal(params, con);
        long cityTotal = getCityTotal(params, con);
        List<String> dateList = new ArrayList<>();
        String start = getStartDateRange(params, con);
        String end = getEndDateRange(params, con);
        dateList.add(start);
        dateList.add(end);
        map.put("emailTotal", emailTotal);
        map.put("ipTotal", ipTotal);
        map.put("countryTotal", countryTotal);
        map.put("cityTotal", cityTotal);
        map.put("dateList", dateList);
        impalaDao.closeConnec();
        return map;
    }


    /**
     * 获取邮箱个数
     */
    public long getEmailTotal(Map params, Connection con) {
        //构成邮箱个数统计sql
        long emailTotal = 0;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer buffer = searchLogDao.getSql(params, "username");
        if (StringUtils.isNotBlank(buffer.toString())) {
            stringBuffer.append("select count(*) as emailTotal from ");
            stringBuffer.append("(" + buffer + "group by username ) t1");
            log.info("邮箱个数统计sql : " + stringBuffer.toString());
            ResultSet rs = null;
            try {
                rs = impalaDao.doSelectSql(con, stringBuffer.toString());
                if (rs != null) {
                    while (rs.next()) {
                        emailTotal = rs.getLong("emailTotal");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info("邮箱总个数 : " + emailTotal);
        return emailTotal;
    }


    /**
     * 获取ip个数
     */
    public long getIpTotal(Map params, Connection con) {

        //构成ip个数统计sql
        long ipTotal = 0;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer buffer = searchLogDao.getSql(params, "ip");
        if (StringUtils.isNotBlank(buffer)) {
            stringBuffer.append("select count(*) as ipTotal from ");
            stringBuffer.append("(" + buffer + "group by req_ip,country,city ) t1");
            log.info("ip个数统计sql : " + stringBuffer.toString());
            ResultSet rs = null;
            try {
                rs = impalaDao.doSelectSql(con, stringBuffer.toString());
                if (rs != null) {
                    while (rs.next()) {
                        ipTotal = rs.getLong("ipTotal");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        log.info("ip总个数 : " + ipTotal);
        return ipTotal;
    }

    /**
     * 获取国家个数
     */
    public long getCountryTotal(Map params, Connection con) {

        //构成ip个数统计sql
        long countryTotal = 0;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer buffer = searchLogDao.getSql(params, "country");
        if (StringUtils.isNotBlank(buffer)) {
            stringBuffer.append("select count(*) as countryTotal from ");
            stringBuffer.append("(" + buffer + "group by country ) t1");
            log.info("国家个数统计sql : " + stringBuffer.toString());
            ResultSet rs = null;
            try {
                rs = impalaDao.doSelectSql(con, stringBuffer.toString());
                if (rs != null) {
                    while (rs.next()) {
                        countryTotal = rs.getLong("countryTotal");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info("国家总个数 : " + countryTotal);
        return countryTotal;
    }


    /**
     * 获取城市个数
     */
    public long getCityTotal(Map params, Connection con) {

        //构成ip个数统计sql
        long cityTotal = 0;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer buffer = searchLogDao.getSql(params, "city");
        if (StringUtils.isNotBlank(buffer)) {
            stringBuffer.append("select count(*) as cityTotal from ");
            stringBuffer.append("(" + buffer + "group by city ) t1");
            log.info("城市个数统计sql : " + stringBuffer.toString());
            ResultSet rs = null;
            try {
                rs = impalaDao.doSelectSql(con, stringBuffer.toString());
                if (rs != null) {
                    while (rs.next()) {
                        cityTotal = rs.getLong("cityTotal");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info("城市总个数 : " + cityTotal);
        return cityTotal;
    }


    /**
     * 获取正序时间范围
     */
    public String getStartDateRange(Map params, Connection con) {

        //构成时间范围统计sql
        StringBuffer buffer = searchLogDao.getSql(params, "mdate");
        List<String> list = new ArrayList<>();
        String startDate = "";
        if (StringUtils.isNotBlank(buffer)) {
            buffer.append("order by mdate asc limit 1");
            log.info("正序时间范围统计sql : " + buffer.toString());
            ResultSet rs = null;
            try {
                rs = impalaDao.doSelectSql(con, buffer.toString());
                if (rs != null) {
                    while (rs.next()) {
                        startDate = rs.getString("mdate");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info("开始时间 : " + startDate);
        return startDate;

    }


    /**
     * 获取倒叙时间范围
     */
    public String getEndDateRange(Map params, Connection con) {

        //构成时间范围统计sql
        StringBuffer buffer = searchLogDao.getSql(params, "mdate");
        List<String> list = new ArrayList<>();
        String endDate = "";
        if (StringUtils.isNotBlank(buffer)) {
            buffer.append("order by mdate desc limit 1");
            log.info("倒叙时间范围统计sql : " + buffer.toString());
            ResultSet rs = null;
            try {
                rs = impalaDao.doSelectSql(con, buffer.toString());
                if (rs != null) {
                    while (rs.next()) {
                        endDate = rs.getString("mdate");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info("结束时间 : " + endDate);
        return endDate;

    }


    /**
     * 归并邮箱查询邮箱详情
     */
    public List<Map<String, Object>> getEmailDetail(Map params) {

        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        ResultSet rs;
        try {
            rs = impalaDao.select(getEmailDetailSql(params).toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> rowData = new HashMap<>();
                    for (int k = 1; k <= columnCount; k++) {
                        rowData.put(md.getColumnName(k), rs.getObject(k));
                    }
                    tmpMapList.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("邮箱个数 : " + tmpMapList.size());
        impalaDao.closeConnec();
        return tmpMapList;
    }


    /**
     * 归并ip查询ip详情
     */
    public List<Map<String, Object>> getIpDetail(Map params) {
        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        ResultSet rs;
        try {
            rs = impalaDao.select(getIpDetailSql(params).toString());
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
        } catch (SQLException e) {
            log.info("查询ip详情异常==={}", e.fillInStackTrace());
        }
        log.info("ip个数 : " + tmpMapList.size());
        impalaDao.closeConnec();
        return tmpMapList;
    }


    /**
     * ip查询详情sql
     */
    public StringBuffer getIpDetailSql(Map params) {
        int page = params.containsKey("page") ? (int) params.get("page") : 1;
        int size = params.containsKey("size") ? (int) params.get("size") : 10;
        StringBuffer buffer = searchLogDao.getSql(params, "ip");
        buffer.append(" group by req_ip,country,city ");
//        buffer.append(" group by req_ip ");
        buffer.append("  limit " + page * size + " ");
        log.info("查询ip详情sql : " + buffer);
        return buffer;
    }


    /**
     * 邮箱查询详情sql
     */
    public StringBuffer getEmailDetailSql(Map params) {
        int page = params.containsKey("page") ? (int) params.get("page") : 1;
        int size = params.containsKey("size") ? (int) params.get("size") : 10;
        StringBuffer buffer = searchLogDao.getSql(params, "username");
        buffer.append(" group by username ");
        buffer.append("  limit " + page * size + " ");
        log.info("查询邮箱详情sql : " + buffer);
        return buffer;
    }


    /**
     * 国家查询详情sql
     */
    public StringBuffer getCountryDetailSql(Map params) {
        int page = params.containsKey("page") ? (int) params.get("page") : 1;
        int size = params.containsKey("size") ? (int) params.get("size") : 10;
        StringBuffer buffer = searchLogDao.getSql(params, "country");
        buffer.append(" group by country ");
        buffer.append("  limit " + page * size + " ");
        log.info("查询国家详情sql : " + buffer);
        return buffer;
    }


    /**
     * 城市查询详情sql
     */
    public StringBuffer getCityDetailSql(Map params) {
        int page = params.containsKey("page") ? (int) params.get("page") : 1;
        int size = params.containsKey("size") ? (int) params.get("size") : 10;
        StringBuffer buffer = searchLogDao.getSql(params, "city");
        buffer.append(" group by city ");
        buffer.append("  limit " + page * size + " ");
        log.info("查询城市详情sql : " + buffer);
        return buffer;
    }

    /**
     * 归并国家查询国家详情
     */
    public List<Map<String, Object>> getCountryDetail(Map params) {

        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        ResultSet rs;
        try {
            rs = impalaDao.select(getCountryDetailSql(params).toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> rowData = new HashMap<>();
                    for (int k = 1; k <= columnCount; k++) {
                        rowData.put(md.getColumnName(k), rs.getObject(k));
                    }
                    tmpMapList.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("国家个数 : " + tmpMapList.size());
        impalaDao.closeConnec();
        return tmpMapList;
    }


    /**
     * 归并城市查询城市详情
     */
    public List<Map<String, Object>> getCityDetail(Map params) {

        List<Map<String, Object>> tmpMapList = new ArrayList<>();
        ResultSet rs;
        try {
            rs = impalaDao.select(getCityDetailSql(params).toString());
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> rowData = new HashMap<>();
                    for (int k = 1; k <= columnCount; k++) {
                        rowData.put(md.getColumnName(k), rs.getObject(k));
                    }
                    tmpMapList.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("城市个数 : " + tmpMapList.size());
        impalaDao.closeConnec();
        return tmpMapList;
    }


    /**
     * 将数组进行分页
     *
     * @return
     */
    public Map<String, Object> getPgeList(List<Map<String, Object>> list, Map params, String type) {

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
        Connection con = null;
        try {
            con = impalaDao.connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Long tal = 0L;
        if (type.equals("ip")) {
            tal = getIpTotal(params, con);
        } else if (type.equals("username")) {
            tal = getEmailTotal(params, con);
        } else if (type.equals("country")) {
            tal = getCountryTotal(params, con);
        } else if (type.equals("city")) {
            tal = getCityTotal(params, con);
        }
        map.put("count", count);
        map.put("total", tal);
        map.put("data", reList);
        impalaDao.closeConnec();
        return map;

    }


}
