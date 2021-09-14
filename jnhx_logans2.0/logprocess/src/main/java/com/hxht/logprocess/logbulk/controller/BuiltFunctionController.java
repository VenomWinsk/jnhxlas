package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.model.BasicRegex;
import com.hxht.logprocess.logbulk.model.BuiltFunction;
import com.hxht.logprocess.logbulk.service.BasicRegexService;
import com.hxht.logprocess.logbulk.service.BuiltFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/builtFunction")
public class BuiltFunctionController {


    @Autowired
    private BuiltFunctionService builtFunctionService;

    /**
     * 添加内置函数
     */
    @RequestMapping("addBuiltFunction")
    public ResMessage addBuiltFunction(@RequestBody BuiltFunction builtFunction){
        try {
            int n = builtFunctionService.addBuiltFunction(builtFunction);
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
     * 修改内置函数
     */
    @RequestMapping("updateBuiltFunction")
    public ResMessage updateBasicRegex(@RequestBody BuiltFunction builtFunction){
        try {
            int n = builtFunctionService.updateBuiltFunction(builtFunction);
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
     * 查询内置函数
     */
    @RequestMapping("searchBuiltFunction")
    public ResMessage searchBuiltFunction(@RequestBody BuiltFunction builtFunction){
        try {
            List<BuiltFunction> list = builtFunctionService.searchBuiltFunction(builtFunction);
            Long total = builtFunctionService.searchTotal();
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
     * 删除内置函数
     */
    @DeleteMapping("deleteBuiltFunction")
    public ResMessage deleteBuiltFunction(@RequestParam String id){
        try {
            int n  = builtFunctionService.deleteBuiltFunction(id);
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
