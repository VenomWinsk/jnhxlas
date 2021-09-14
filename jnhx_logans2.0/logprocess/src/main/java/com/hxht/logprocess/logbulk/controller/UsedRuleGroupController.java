package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.Project;
import com.hxht.logprocess.logbulk.model.UsedRuleGroup;
import com.hxht.logprocess.logbulk.service.UsedRuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/usedRuleGroup")
public class UsedRuleGroupController {



    @Autowired
    private UsedRuleGroupService usedRuleGroupService;

    /**
     * 修改状态
     */
    @RequestMapping("updateUsedRuleGroup")
    public ResMessage updateUsedRuleGroup(@RequestBody UsedRuleGroup usedRuleGroup){
        try {
            int n = usedRuleGroupService.updateUsedRuleGroup(usedRuleGroup);
            if (n >0){
                return ResMessage.genSucessMessage("修改成功",null);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"修改失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }
}
