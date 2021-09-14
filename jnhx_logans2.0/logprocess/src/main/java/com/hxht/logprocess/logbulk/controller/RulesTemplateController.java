package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.RulesTemplate;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.service.RulesTemplateService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server/rulesTemplate")
public class RulesTemplateController {


    @Autowired
    private RulesTemplateService rulesTemplateService;

    /**
     * 添加规则模板
     */
    @RequestMapping("addRulesTemplate")
    public ResMessage addRulesTemplate(@RequestBody RulesTemplate rulesTemplate){
        try {
            int n = rulesTemplateService.addRulesTemplate(rulesTemplate);
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
     * 修改规则模板
     */
    @RequestMapping("updateRulesTemplate")
    public ResMessage updateUnit(@RequestBody RulesTemplate rulesTemplate){
        try {
            int n = rulesTemplateService.updateRulesTemplate(rulesTemplate);
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
     * 查询规则模板
     */
    @RequestMapping("searchRulesTemplate")
    public ResMessage searchRulesTemplate(@RequestParam int page, @RequestParam int size){
        try {
            List<RulesTemplate> list = rulesTemplateService.searchRulesTemplate(page,size);
            if(list.size() > 0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }



    /**
     * 删除规则模板
     */
    @RequestMapping("deleteRulesTemplate")
    public ResMessage deleteUnit(@RequestParam String id){
        try {
            int n  = rulesTemplateService.deleteRulesTemplate(id);
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
