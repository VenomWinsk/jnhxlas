package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.OfflineTaskMapper;
import com.hxht.logprocess.logbulk.model.OfflineTask;
import com.hxht.logprocess.logbulk.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfflineTaskService {


    @Autowired
    private OfflineTaskMapper offlineTaskMapper;

    @Value("${offlineFile}")
    private String offlineFile;

    /**
     * 添加离线任务
     */
    public int addOfflineTask(OfflineTask offlineTask) {

        return offlineTaskMapper.insertSelective(offlineTask);

    }


    /**
     * 添加离线任务
     */
    public int updateOfflineTask(OfflineTask offlineTask) {

        return offlineTaskMapper.updateByPrimaryKeySelective(offlineTask);

    }


    /**
     * 查询离线任务
     */
    public List<OfflineTask> searchOfflineTask(OfflineTask offlineTask) {
        if (offlineTask.getPage() != null && offlineTask.getSize() != null) {
            int from;
            from = (offlineTask.getPage() - 1) * offlineTask.getSize();
            offlineTask.setForm(from);
        }

        //获取文件的大小
        List<OfflineTask> list = offlineTaskMapper.selectByConditions(offlineTask);
        for (OfflineTask offlineTask1 : list) {
            File file = new File(offlineFile + File.separator + offlineTask1.getFile());
            if (file.exists() && file.isFile()) {
                if (offlineTask1.getSizes() == null) {
                    offlineTask1.setSizes(file.length() / 1024);
                    offlineTaskMapper.updateByPrimaryKeySelective(offlineTask1);
                }
                if (offlineTask1.getSizes() != null && offlineTask1.getSizes() != (file.length() / 1024)) {
                    offlineTask1.setSizes(file.length() / 1024);
                    offlineTaskMapper.updateByPrimaryKeySelective(offlineTask1);
                }
            }
        }
        return list;
    }


    /**
     * 查询总量
     *
     * @return
     */
    public Long searchTotal() {
        return offlineTaskMapper.selectByTotal();
    }


    /**
     * 将文件地址返回给前台
     */
    public Map getFileAddress(String id) {

        Map<String, Object> result = new HashMap<>();
        String fileAddress = "";
        //通过id查询文件地址
        OfflineTask matchTask = offlineTaskMapper.selectByPrimaryKey(id);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = sf.format(new Date());
//        String filename = "日志数据" + current + ".csv";
        String filename = matchTask.getFile();
        if (matchTask != null) {
            fileAddress = offlineFile + File.separator + matchTask.getFile();
        }

        result.put("filepath", fileAddress);
        result.put("filename", filename);
        return result;
    }


    /**
     * 将文件地址返回给前台
     */
    public Map getFileAddress(String id, int downType) {

        Map<String, Object> result = new HashMap<>();
        String fileAddress = "";
        //通过id查询文件地址
        OfflineTask matchTask = offlineTaskMapper.selectByPrimaryKey(id);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = sf.format(new Date());
        String filename = "日志数据" + current;
        if (downType == 0) {//拆分后压缩
            filename = filename + "-csplit.done.zip";
        } else {//直接压缩
            filename = filename + ".zip";
        }
        if (matchTask != null) {
            fileAddress = offlineFile + File.separator + matchTask.getFile();
        }

        result.put("filepath", fileAddress);
        result.put("filename", filename);
        return result;
    }


    /**
     * 删除离线任务
     */
    public int deleteOfflineTaskById(String id) {
        OfflineTask offlineTask = offlineTaskMapper.selectByPrimaryKey(id);
        //删除服务器上对应的csv文件
//        File file1 = new File(offlineFile + File.separator + offlineTask.getFile().substring(0, offlineTask.getFile().length()-4)+"-csplit.done.zip");
//        File file2 = new File(offlineFile + File.separator + offlineTask.getFile().substring(0, offlineTask.getFile().length()-4)+".zip");
        File file = new File(offlineFile + File.separator + offlineTask.getFile());
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        //删除mysql离线任务表里的记录
        return offlineTaskMapper.deleteByPrimaryKey(id);
    }

    public void shell() {
        String[] cmd = new String[]{""};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor(); //等待shell脚本执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
