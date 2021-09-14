package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.UsedRuleTemplate;
import com.hxht.logprocess.logbulk.service.UsedRuleTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server/usedRuleTemplate")
public class UsedRuleTemplateController {



    @Autowired
    private UsedRuleTemplateService usedRuleTemplateService;

    /**
     * 添加用户规则模板
     */
    @RequestMapping("addUsedRuleTemplate")
    public ResMessage addUsedRuleTemplate(@RequestBody UsedRuleTemplate usedRuleTemplate){
        try {
            int n = usedRuleTemplateService.addUsedRuleTemplate(usedRuleTemplate);
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
     * 修改用户规则模板
     */
    @RequestMapping("updateUsedRuleTemplate")
    public ResMessage updateUsedRuleTemplate(@RequestBody UsedRuleTemplate usedRuleTemplate){
        try {
            int n = usedRuleTemplateService.updateUsedRuleTemplate(usedRuleTemplate);
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
     * 查询用户规则模板
     */
    @RequestMapping("searchUsedRuleTemplate")
    public ResMessage searchUsedRuleTemplate(@RequestParam int page, @RequestParam int size){
        try {
            List<UsedRuleTemplate> list = usedRuleTemplateService.searchRulesTemplate(page,size);
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
     * 删除用户规则模板
     */
    @RequestMapping("deleteUsedRuleTemplate")
    public ResMessage deleteUsedRuleTemplate(@RequestParam String id){
        try {
            int n  = usedRuleTemplateService.deleteRulesTemplate(id);
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
