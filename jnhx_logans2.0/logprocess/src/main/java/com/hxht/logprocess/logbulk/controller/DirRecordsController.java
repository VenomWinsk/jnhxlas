package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.DirRecords;
import com.hxht.logprocess.logbulk.service.DirRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/dirRecords")
public class DirRecordsController {


    @Autowired
    private DirRecordsService dirRecordsService;

    /**
     * 修改文件夹
     */
    @RequestMapping("/updateDirRecords")
    public ResMessage updateDirRecords(@RequestBody DirRecords dirRecords){

        int n = dirRecordsService.updateDirRecords(dirRecords);
        if (n>0){
           return ResMessage.genSucessMessage("修改成功",n);
        }else {
           return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"修改失败",n);
        }
    }
}
