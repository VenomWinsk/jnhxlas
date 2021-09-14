package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.RuleMapper;
import com.hxht.logprocess.logbulk.model.Rule;
import com.hxht.logprocess.logbulk.model.RuleGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RuleService {


    @Autowired
    private RuleMapper ruleMapper;



    /**
     * 添加规则
     */
    public int  addRule(Rule rule){
        rule.setGmtCreate(new Date());
        rule.setId(UUID.randomUUID().toString());
        return  ruleMapper.insertSelective(rule);

    }


    /**
     * 修改规则
     */
    public int  updateRule(Rule rule){
        rule.setGmtModified(new Date());
        return  ruleMapper.updateByPrimaryKeySelective(rule);
    }


    /**
     * 根据规则名称查询
     */
    public Integer selectTotalByName(String name){

        return  ruleMapper.selectTotalByName(name);
    }




    /**
     * 根据日志特征查询
     */
    public Integer selectByLogFeature(String selectByLogFeature){

        return  ruleMapper.selectTotalByName(selectByLogFeature);
    }


    /**
     * 查询规则
     */
    public List<Rule> searchRule(Rule rule){
        if (rule.getPage()!=null && rule.getSize()!=null){
            int from;
            from = (rule.getPage() - 1) * rule.getSize();
            rule.setForm(from);
        }
        String order;
        if (StringUtils.isNotBlank(rule.getField()) && StringUtils.isNotBlank(rule.getOrder())){
            order = rule.getField()+" "+rule.getOrder();
        }else {
            order ="gmt_create desc";
        }
        return  ruleMapper.selectByConditions(rule.getForm(),rule.getSize(),order);
    }



    /**
     * 查询规则
     */
    public Rule searchRuleById(String id){

        return  ruleMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
        return ruleMapper.selectByTotal();
    }


    /**
     * 删除规则
     */
    public int deleteRule(String id){

        return  ruleMapper.deleteByPrimaryKey(id);
    }



    /**
     * 根据id查询规则组
     */
    public Rule selectById(String id){

        return  ruleMapper.selectByPrimaryKey(id);
    }




}
