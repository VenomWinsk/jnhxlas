package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.UsedRuleGroupMapper;
import com.hxht.logprocess.logbulk.model.UsedRuleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsedRuleGroupService {


    @Autowired
    private UsedRuleGroupMapper usedRuleGroupMapper;


    /**
     * 修改状态
     */
    public int updateUsedRuleGroup(UsedRuleGroup usedRuleGroup){

        return usedRuleGroupMapper.updateByPrimaryKeySelective(usedRuleGroup);
    }
}
