package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.model.BasicRegex;
import com.hxht.logprocess.logbulk.model.PrgramConfig;
import com.hxht.logprocess.logbulk.service.BasicRegexService;
import com.hxht.logprocess.logbulk.service.PrgramConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/prgramConfig")
public class PrgramConfigController {


    @Autowired
    private PrgramConfigService prgramConfigService;

    /**
     * 添加程序配置
     */
    @RequestMapping("addPrgramConfig")
    public ResMessage addPrgramConfig(@RequestBody PrgramConfig prgramConfig){
        try {
            int n = prgramConfigService.addPrgramConfig(prgramConfig);
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
     * 修改程序配置
     */
    @RequestMapping("updatePrgramConfig")
    public ResMessage updatePrgramConfig(@RequestBody PrgramConfig prgramConfig){
        try {
            int n = prgramConfigService.updatePrgramConfig(prgramConfig);
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
     * 查询程序配置
     */
    @RequestMapping("searchPrgramConfig")
    public ResMessage searchBasicRegex(@RequestBody PrgramConfig prgramConfig){
        try {
            List<PrgramConfig> list = prgramConfigService.searchPrgramConfig(prgramConfig);
            Long total = prgramConfigService.searchTotal();
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
     * 删除程序配置
     */
    @DeleteMapping("deleteBasicRegex")
    public ResMessage deleteBasicRegex(@RequestParam String id){
        try {
            int n  = prgramConfigService.deletePrgramConfig(id);
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
