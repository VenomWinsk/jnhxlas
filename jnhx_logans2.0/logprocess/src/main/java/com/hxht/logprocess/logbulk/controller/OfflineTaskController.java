package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.dao.OfflineTaskMapper;
import com.hxht.logprocess.logbulk.model.OfflineTask;
import com.hxht.logprocess.logbulk.service.OfflineTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/server/offlineTask")
public class OfflineTaskController {



    @Autowired
    private OfflineTaskService offlineTaskService;
    @Autowired
    private OfflineTaskMapper offlineTaskMapper;

    /**
     * 查询离线任务
     */
    @RequestMapping("searchOfflineTask")
    public ResMessage searchOfflineTask(@RequestBody OfflineTask offlineTask){
        try {
            List<OfflineTask> list = offlineTaskService.searchOfflineTask(offlineTask);
            Long total = offlineTaskService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",list,list.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }

    /**
     * 删除离线任务
     * @param id
     * @return
     */
    @DeleteMapping(value = "deleteOfflineTaskById")
    public ResMessage deleteOfflineTaskById(String id) {
        int num = offlineTaskService.deleteOfflineTaskById(id);
        if (num>0){
            return ResMessage.genSucessMessage("删除成功",num);
        }else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"删除失败",null);

        }
    }

    /**
     * 下载离线文件
     * @param id
     */
    @GetMapping(value = "downloadOfflineFile")
    public ResMessage downloadOfflineFile(String id) {
        Map result = offlineTaskService.getFileAddress(id);

        if (result != null){
            return ResMessage.genSucessMessage("查询成功",result);
        }else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"未查询到文件地址",null);
        }

    }

    /**
     * 下载离线文件
     * @param id
     */
    @GetMapping(value = "downloadOfflineFile1")
    public ResMessage downloadOfflineFile1(String id,int downType) {
        Map result = offlineTaskService.getFileAddress(id,downType);

        if (result != null){
            return ResMessage.genSucessMessage("查询成功",result);
        }else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"未查询到文件地址",null);
        }

    }
}
