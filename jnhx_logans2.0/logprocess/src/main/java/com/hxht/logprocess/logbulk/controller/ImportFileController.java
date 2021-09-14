package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.ImportFile;
import com.hxht.logprocess.logbulk.model.Project;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.service.ImportFileService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/server/importFile")
public class ImportFileController {


    @Autowired
    private ImportFileService importFileService;


//    /**
//     * 查询导入文件记录
//     */
//    @RequestMapping("searchUnit")
//    public ResMessage searchUnit(@RequestParam int page, @RequestParam int size){
//        try {
//            List<ImportFile> list = importFileService.searchImportFile(page,size);
//            if(list.size() > 0){
//                return ResMessage.genSucessMessage("查询成功",list);
//            }else {
//                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
//            }
//        }catch (Exception e){
//            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
//        }
//    }


    /**
     * 查询导入文件的相关信息
     *
     */
    @RequestMapping("/searchBulkFileMessage")
    public ResMessage  searchBulkFileMessage(){
        try {
            List<Project> list = importFileService.searchBulkFileMessage();
            return ResMessage.genSucessMessage("查询成功",list);
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }

    }



}
