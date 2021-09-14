package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.core.util.CopyBeansUtils;
import com.hxht.logprocess.logbulk.model.RuleGroup;
import com.hxht.logprocess.logbulk.model.RuleGroupDTO;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.model.UnitDTO;
import com.hxht.logprocess.logbulk.service.RuleGroupService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/ruleGroup")
public class RuleGroupController {


    @Autowired
    private RuleGroupService ruleGroupService;

    /**
     * 添加规则组
     */
    @RequestMapping("addRuleGroup")
    public ResMessage addRuleGroup(@RequestBody RuleGroup ruleGroup){
        try {
            if (ruleGroup.getName()!=null){
                int num = ruleGroupService.selectTotalByName(ruleGroup.getName().trim(),ruleGroup.getObjectId());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"规则组名称重复",null);
                }
            }
            if (ruleGroup.getCode()!=null){
                int num = ruleGroupService.selectTotalByCode(ruleGroup.getCode().trim(),ruleGroup.getObjectId());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"规则组名称重复",null);
                }
            }
            int n = ruleGroupService.addRuleGroup(ruleGroup);
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
     * 修改规则组
     */
    @RequestMapping("updateRuleGroup")
    public ResMessage updateRuleGroup(@RequestBody RuleGroup ruleGroup){
        try {
            //通过id查询规则组信息
            RuleGroup ruleGroup1 = ruleGroupService.selectById(ruleGroup.getId());
            if (ruleGroup.getName()!=null){
                int num = ruleGroupService.selectTotalByName(ruleGroup.getName().trim(),ruleGroup.getObjectId());
                if (num>0 && !ruleGroup1.getName().equals(ruleGroup.getName())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"规则组名称重复",null);
                }
                if (num>0 && !ruleGroup1.getObjectId().equals(ruleGroup.getObjectId())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"规则组名称重复",null);
                }
            }
            if (ruleGroup.getCode()!=null){
                int num = ruleGroupService.selectTotalByCode(ruleGroup.getCode().trim(),ruleGroup.getObjectId());
                if (num>0 && !ruleGroup1.getCode().equals(ruleGroup.getCode())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"规则组code重复",null);
                }
                if (num>0 && !ruleGroup1.getObjectId().equals(ruleGroup.getObjectId())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"规则组code重复",null);
                }
            }
            int n = ruleGroupService.updateRuleGroup(ruleGroup);
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
     * 查询规则组
     */
    @RequestMapping("searchRuleGroup")
    public ResMessage searchRuleGroup(@RequestBody RuleGroup ruleGroup){
        try {
            List<RuleGroup> list = ruleGroupService.searchRuleGroup(ruleGroup);
            List<RuleGroupDTO> dtoList  = CopyBeansUtils.copyListProperties(list, RuleGroupDTO::new);
            Long total = ruleGroupService.searchTotal();
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
     * 根据id查询规则组
     */
    @RequestMapping("searchRuleGroupById")
    public ResMessage searchRuleGroupById(@RequestParam String id){
        try {
            RuleGroup ruleGroup = ruleGroupService.selectById(id);
            if(ruleGroup!=null){
                return ResMessage.genSucessMessage("查询成功",ruleGroup);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }



    /**
     * 根据分析对象查询规则组
     */
    @RequestMapping("searchRuleGroupByAna")
    public ResMessage searchRuleGroupByAna(@RequestParam String name){
        try {
            List<String> list = ruleGroupService.searchRuleGroupByAna(name);
            if(list!=null && list.size()>0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }





    /**
     * 删除规则组
     */
    @DeleteMapping("deleteRuleGroup")
    public ResMessage deleteRuleGroup(@RequestParam String id){
        try {
            int n  = ruleGroupService.deleteRuleGroup(id);
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
