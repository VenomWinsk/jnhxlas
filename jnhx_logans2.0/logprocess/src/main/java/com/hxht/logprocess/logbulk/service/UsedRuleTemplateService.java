package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.UsedRuleTemplateMapper;
import com.hxht.logprocess.logbulk.model.UsedRuleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsedRuleTemplateService {



    @Autowired
    private UsedRuleTemplateMapper usedRuleTemplateMapper;



    /**
     * 添加用户规则模板
     */
    public int  addUsedRuleTemplate(UsedRuleTemplate usedRuleTemplate){
        usedRuleTemplate.setId(UUID.randomUUID().toString());
        return  usedRuleTemplateMapper.insertSelective(usedRuleTemplate);

    }


    /**
     * 修改用户规则模板
     */
    public int  updateUsedRuleTemplate(UsedRuleTemplate usedRuleTemplate){
        return  usedRuleTemplateMapper.updateByPrimaryKeySelective(usedRuleTemplate);
    }





    /**
     * 查询用户规则模板
     */
    public List<UsedRuleTemplate> searchRulesTemplate(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  usedRuleTemplateMapper.selectByConditions(from,size);
    }




    /**
     * 删除用户规则模板
     */
    public int deleteRulesTemplate(String id){

        return  usedRuleTemplateMapper.deleteByPrimaryKey(id);
    }
}
