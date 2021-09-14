package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.core.util.CopyBeansUtils;
import com.hxht.logprocess.logbulk.model.Rule;
import com.hxht.logprocess.logbulk.model.RuleDTO;
import com.hxht.logprocess.logbulk.model.RuleGroup;
import com.hxht.logprocess.logbulk.model.RuleGroupDTO;
import com.hxht.logprocess.logbulk.service.RuleGroupService;
import com.hxht.logprocess.logbulk.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/rule")
public class RuleController {


    @Autowired
    private RuleService ruleService;

    /**
     * 添加规则
     */
    @RequestMapping("addRule")
    public ResMessage addRuleGroup(@RequestBody Rule rule){
        try {
            if (rule.getName()!=null){
                int num = ruleService.selectTotalByName(rule.getName().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"规则重复",null);
                }
            }
            if (rule.getLogFeature()!=null){
                int num = ruleService.selectByLogFeature(rule.getLogFeature().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"日志特征相似或重复",null);
                }
            }
            int n = ruleService.addRule(rule);
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
     * 修改规则
     */
    @RequestMapping("updateRule")
    public ResMessage updateUnit(@RequestBody Rule rule){
        try {
            //通过id查询规则信息
            Rule rule1 = ruleService.selectById(rule.getId());
            if (rule.getName()!=null){
                int num = ruleService.selectTotalByName(rule.getName().trim());
                if (num>0 && !rule1.getName().equals(rule.getName())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"规则重复",null);
                }
            }
            if (rule.getLogFeature()!=null){
                int num = ruleService.selectByLogFeature(rule.getLogFeature().trim());
                if (num>0 && !rule1.getLogFeature().equals(rule.getLogFeature())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"日志特征相似或重复",null);
                }
            }
            int n = ruleService.updateRule(rule);
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
     * 查询规则
     */
    @PostMapping("searchRule")
    public ResMessage searchRule(@RequestBody Rule rule){
        try {
            List<Rule> list = ruleService.searchRule(rule);
            List<RuleDTO> dtoList  = CopyBeansUtils.copyListProperties(list, RuleDTO::new);
            Long total = ruleService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",dtoList,dtoList.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",list);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }



    /**
     * 通过id查询规则
     */
    @GetMapping("searchRuleById")
    public ResMessage searchRuleById(@RequestParam String id){
        try {
            Rule rule = ruleService.searchRuleById(id);
            if(rule!=null){
                return ResMessage.genSucessMessage("查询成功",rule);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }


    /**
     * 删除规则
     */
    @RequestMapping("deleteRule")
    public ResMessage deleteUnit(@RequestParam String id){
        try {
            int n  = ruleService.deleteRule(id);
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
