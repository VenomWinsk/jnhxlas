package com.hxht.logprocess.search.controller;


import com.alibaba.fastjson.JSONArray;
import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.model.OfflineTask;
import com.hxht.logprocess.logbulk.service.OfflineTaskService;
import com.hxht.logprocess.search.model.LogParams;
import com.hxht.logprocess.search.service.SearchLogService;
import com.hxht.logprocess.setting.model.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@Slf4j
@RequestMapping("/server/search")
public class SearchLogController {


    @Autowired
    private SearchLogService searchLogService;

    @Autowired
    private OfflineTaskService offlineTaskService;

    /**
     * 查询日志
     */
    @RequestMapping("/searchLog")
    public ResMessage searchLog(@RequestBody LogParams logParams) {

        try {
            Map map = searchLogService.searchLog(logParams);
            if (map != null) {
                return ResQueryMessage.genSucessMessage("查询成功", map.get("data"), (int) map.get("count"), (Long) map.get("total"));
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }
    }


    /**
     * 导出日志csv
     */
    @RequestMapping("/exportLogCsv")
    public ResponseEntity<byte[]> exportLogCsv(@RequestBody LogParams logParams) {
        //如果不传导出条数，默认size是1千万
        if (logParams.getPage() == null) {
            logParams.setPage(1);
        }
        if (logParams.getSize() == null) {
            logParams.setSize(1000000);
        }
        //查询出数据
        List<Map<String, Object>> list = searchLogService.logData(logParams);
        return searchLogService.exportLogCsv(list);

    }


    /**
     * 导出日志csv 2.0
     */
    @RequestMapping("/exportLogsCsv")
    public ResponseEntity<byte[]> exportLogsCsv(@RequestBody Map params) {
        //查询出数据
        List<Map<String, Object>> list = searchLogService.logsData(params);
        return searchLogService.exportLogsCsv(list, params);

    }


    /**
     * 获取国家
     */
    @RequestMapping("/getCountryNum")
    public ResMessage getCountryNum(@RequestBody Map params) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        String anaObject = params.containsKey("anaObject") ? (String) params.get("anaObject") : null;
        String ruleGroup = params.containsKey("ruleGroup") ? (String) params.get("ruleGroup") : null;
        String rule = params.containsKey("rule") ? (String) params.get("rule") : null;

        try {
            Set<String> set = searchLogService.getCountryNum(unit, anaObject, ruleGroup, rule);
            if (set.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", set);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }

    }


    /**
     * 获取地区
     */
    @RequestMapping("/getCity")
    public ResMessage getCity(@RequestBody Map params) {
        String unit = params.containsKey("unit") ? (String) params.get("unit") : null;
        String anaObject = params.containsKey("anaObject") ? (String) params.get("anaObject") : null;
        String ruleGroup = params.containsKey("ruleGroup") ? (String) params.get("ruleGroup") : null;
        String rule = params.containsKey("rule") ? (String) params.get("rule") : null;
        List country = params.containsKey("country") ? (List) params.get("country") : null;

        try {
            Set<String> set = searchLogService.getCity(unit, anaObject, country, ruleGroup, rule);
            if (set.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", set);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }

    }


    /**
     * 查询日志 2.0
     */
    @RequestMapping("/searchLogs")
    public ResMessage searchLogs(@RequestBody Map params) {

        if (!params.containsKey("levelId") && params.containsKey("ownerType"))
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "缺失必要参数", null);
        try {
            Map<String, Object> map = searchLogService.searchLogs(params);
            if (map != null) {
                return ResQueryMessage.genSucessMessage("查询成功", map.get("data"),map.get("tableName"), (int) map.get("count"), (Long) map.get("total"));
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }

    }


    /**
     * 保存结果集
     */
    @RequestMapping("/saveResult")
    public ResMessage saveResult(@RequestBody Map params) {

        if (!params.containsKey("levelId") && params.containsKey("ownerType"))
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "缺失必要参数", null);
        try {
            int result = searchLogService.saveResult(params);
            if (result == 1) {
                return ResQueryMessage.genSucessMessage("保存成功", result);
            } else if(result == 0){
                return ResMessage.genSucessMessage("已存在缓存表", result);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "保存失败", result);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "保存异常", e.getMessage());
        }

    }

    /**
     * 删除结果集
     */
    @RequestMapping("/deleteResult")
    public ResMessage deleteResult() {

        try {
            boolean result = searchLogService.deleteResult();
            if (result) {
                return ResQueryMessage.genSucessMessage("清除成功", result);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "清除失败", result);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "清除异常", e.getMessage());
        }

    }

    /**
     * 境内外登录分析
     */
    @RequestMapping("specialSearch")
    public ResMessage specialSearch(@RequestBody Map params) {

        Map<String, Object> map = searchLogService.specialSearch(params, "search");
        if (map != null) {
            return ResQueryMessage.genSucessMessage("查询成功", map.get("data"), (int) map.get("count"), (Long) map.get("total"));
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询fail", null);
        }

    }


    @RequestMapping("testFile")
    public void testFile() {
//        Process process1 = null;
//        Process process2 = null;
//        StringBuffer stringBuffer = new StringBuffer();
//        try {
//            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "777", "/file/split_csv.sh");
//            process1 = builder.start();
//            process1.waitFor();
//            String[] c = new String[]{"/bin/bash", "-c", "/file/split_csv.sh"};
//            process2 = Runtime.getRuntime().exec(c,null,null);
//            InputStream errorStream = process2.getErrorStream();
//            BufferedReader err = new BufferedReader(new InputStreamReader(errorStream));
//
//            String line = null;
//            try {
//                while ((line = err.readLine()) != null && !stringBuffer.toString().contains("ERROR")) {
//                    if (line != null) {
//                        stringBuffer.append(line);
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException("[shell exec error]:" + err, e);
//            } finally {
//                try {
//                    errorStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            process2.waitFor(); //等待shell脚本执行完成
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(stringBuffer.toString());
        searchLogService.test();
        System.out.println("接口完毕");
    }
    private static void toZip(List<File> files,OutputStream out){

    }

    private static void compress(File sourceFile, ZipOutputStream zos,String name){
        byte[] bytes = new byte[2*1024];
        if(sourceFile.exists()){
            try {
                zos.putNextEntry(new ZipEntry(name));
                int len;
                FileInputStream in = new FileInputStream(sourceFile);
                while ((len = in.read(bytes)) != -1){
                    zos.write(bytes,0,len);
                }
                zos.closeEntry();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 境内外登录分析导出csv
     */
    @RequestMapping("/specialExportLogsCsv")
    public ResponseEntity<byte[]> specialExportLogsCsv(@RequestBody Map params) {
        //查询出数据
        Map<String, Object> map = searchLogService.specialSearch(params, "export");
        return searchLogService.exportLogsCsv((List<Map<String, Object>>) map.get("data"), params);
    }


    /**
     * 离线下载
     */
    @RequestMapping("/logOfflineDownload")
    public ResMessage logOfflineDownload(@RequestBody Map params) {
        OfflineTask offlineTask = new OfflineTask();
        offlineTask.setId(UUID.randomUUID().toString());
        offlineTask.setStatus("0");//进行中状态
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = sf.format(new Date());
        offlineTask.setName(current + "离线任务");
        offlineTask.setCreateTime(new Date());
        offlineTask.setStartTime(new Date());
        offlineTaskService.addOfflineTask(offlineTask);
        searchLogService.logOfflineDownload(params, offlineTask);
        return ResMessage.genSucessMessage("开始离线下载", null);
    }


    /**
     * 通过分析对象查询字段
     */
    @RequestMapping("/searchFieldByAnalyseObject")
    public ResMessage searchFieldByAnalyseObject(@RequestBody Map params) {

        if (!params.containsKey("analysisObject"))
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "缺失必要参数", null);
        try {
            List<Field> list = searchLogService.searchFieldByAnalyseObject(params);
            if (list != null && list.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", list);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
            }

        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }

    }


    /**
     * 通过条件查询字段
     *
     * @param params levelId 为层级id  ownerType为哪个层级
     * @return
     */
    @RequestMapping("/searchFieldByExample")
    public ResMessage searchFieldByExample(@RequestBody Map params) {

        if (!params.containsKey("levelId") && params.containsKey("ownerType"))
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "缺失必要参数", null);
        try {
            List<Field> list = searchLogService.searchFieldByExample(params);
            if (list != null && list.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", list);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
            }

        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }

    }


    /**
     * 查询总览
     */
    @RequestMapping("/searchOverView")
    public ResMessage getOverView(@RequestBody Map params) {

        Map map = searchLogService.getOverView(params);

        return ResMessage.genSucessMessage("查询成功", map);

    }


    /**
     * 查询邮箱详情
     */
    @RequestMapping("/getEmailDetail")
    public ResMessage getEmailDetail(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getEmailDetail(params);
        Map<String, Object> map = searchLogService.getPgeList(list, params, "username");
        return ResMessage.genSucessMessage("查询成功", map);
    }


    /**
     * 快速导出导出详情
     */
    @RequestMapping("/exportDetail")
    public ResponseEntity<byte[]> exportDetail(@RequestBody Map params) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (params.get("type").equals("email")) {
            list = searchLogService.getEmailDetail(params);
        } else if (params.get("type").equals("ip")) {
            list = searchLogService.getIpDetail(params);

        } else if (params.get("type").equals("country")) {
            list = searchLogService.getCountryDetail(params);

        } else if (params.get("type").equals("city")) {
            list = searchLogService.getCityDetail(params);
        }
        return searchLogService.exportLogsCsv(list, params);
    }


    /**
     * 查询ip详情
     */
    @RequestMapping("/getIpDetail")
    public ResMessage getIpDetail(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getIpDetail(params);
        Map<String, Object> map = searchLogService.getPgeList(list, params, "ip");
        return ResMessage.genSucessMessage("查询成功", map);
    }


    /**
     * 归并ip导出ip详情
     */
    @RequestMapping("/exportIpTotal")
    public ResponseEntity<byte[]> exportIpTotal(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getIpDetail(params);
        return searchLogService.exportLogsCsv(list, params);
    }


    /**
     * 查询国家详情
     */
    @RequestMapping("/getCountryDetail")
    public ResMessage getCountryDetail(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getCountryDetail(params);
        Map<String, Object> map = searchLogService.getPgeList(list, params, "country");
        return ResMessage.genSucessMessage("查询成功", map);
    }


    /**
     * 归并国家导出国家详情
     */
    @RequestMapping("/exportCountryTotal")
    public ResponseEntity<byte[]> exportCountryTotal(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getCountryDetail(params);
        return searchLogService.exportLogsCsv(list, params);
    }


    /**
     * 查询城市详情
     */
    @RequestMapping("/getCityDetail")
    public ResMessage getCityDetail(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getCityDetail(params);
        Map<String, Object> map = searchLogService.getPgeList(list, params, "city");
        return ResMessage.genSucessMessage("查询成功", map);
    }


    /**
     * 归并城市导出城市详情
     */
    @RequestMapping("/exportCityTotal")
    public ResponseEntity<byte[]> exportCityTotal(@RequestBody Map params) {
        List<Map<String, Object>> list = searchLogService.getCityDetail(params);
        return searchLogService.exportLogsCsv(list, params);
    }


}
