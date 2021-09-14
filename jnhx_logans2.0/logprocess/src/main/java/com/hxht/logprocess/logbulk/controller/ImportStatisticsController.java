package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.ImportFile;
import com.hxht.logprocess.logbulk.model.ImportStatistics;
import com.hxht.logprocess.logbulk.service.ImportFileService;
import com.hxht.logprocess.logbulk.service.ImportStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server/")
public class ImportStatisticsController {


    @Autowired
    private ImportStatisticsService importStatisticsService;


    /**
     * 查询文件统计
     */
    @RequestMapping("searchImportStatistics")
    public ResMessage searchImportStatistics(@RequestParam int page, @RequestParam int size){
        try {
            List<ImportStatistics> list = importStatisticsService.searchImportStatistics(page,size);
            if(list.size() > 0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }


}
