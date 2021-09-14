package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.model.BasicRegex;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.service.BasicRegexService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/basicRegex")
public class BasicRegexController {



    @Autowired
    private BasicRegexService basicRegexService;

    /**
     * 添加基本正则
     */
    @RequestMapping("addBasicRegex")
    public ResMessage addBasicRegex(@RequestBody BasicRegex basicRegex){
        try {
            int n = basicRegexService.addBasicRegex(basicRegex);
            if (n >0){
                return ResMessage.genSucessMessage("添加成功",null);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"添加失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"添加异常",e.getMessage());
        }
    }




    /**
     * 修改基本正则
     */
    @RequestMapping("updateBasicRegex")
    public ResMessage updateBasicRegex(@RequestBody BasicRegex basicRegex){
        try {
            int n = basicRegexService.updateBasicRegex(basicRegex);
            if (n >0){
                return ResMessage.genSucessMessage("修改成功",null);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"修改失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }




    /**
     * 查询基本正则
     */
    @RequestMapping("searchBasicRegex")
    public ResMessage searchBasicRegex(@RequestBody BasicRegex basicRegex){
        try {
            List<BasicRegex> list = basicRegexService.searchBasicRegex(basicRegex);
            Long total = basicRegexService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",list,list.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }



    /**
     * 删除基本正则
     */
    @DeleteMapping("deleteBasicRegex")
    public ResMessage deleteBasicRegex(@RequestParam String id){
        try {
            int n  = basicRegexService.deleteBasicRegex(id);
            if(n > 0){
                return ResMessage.genSucessMessage("删除成功",n);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"删除失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"删除异常",e.getMessage());
        }
    }










}
