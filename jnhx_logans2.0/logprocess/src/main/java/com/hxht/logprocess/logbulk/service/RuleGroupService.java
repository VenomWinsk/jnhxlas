package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.dao.RuleGroupMapper;
import com.hxht.logprocess.logbulk.dao.RuleMapper;
import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.logbulk.model.RuleGroup;
import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RuleGroupService {


    @Autowired
    private RuleGroupMapper ruleGroupMapper;

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;


    /**
     * 添加规则组
     */
    public int  addRuleGroup(RuleGroup ruleGroup){
        ruleGroup.setGmtCreate(new Date());
        ruleGroup.setId(UUID.randomUUID().toString());
        return  ruleGroupMapper.insertSelective(ruleGroup);

    }


    /**
     * 修改规则组
     */
    public int  updateRuleGroup(RuleGroup ruleGroup){
        ruleGroup.setGmtModified(new Date());
        return  ruleGroupMapper.updateByPrimaryKeySelective(ruleGroup);
    }


    /**
     * 根据id查询规则组
     */
    public RuleGroup selectById(String id){

        return  ruleGroupMapper.selectByPrimaryKey(id);
    }




    /**
     * 根据分析对象查询规则组
     */
    public List<String> searchRuleGroupByAna(String name){

        List<String>list =new ArrayList<>();
        //通过分析对象名称查询分析对象id
        AnalysisObject analysisObject = analysisObjectMapper.selectByObjectName(name);
        List<RuleGroup> ruleGroupList = ruleGroupMapper.selectByObjetId(analysisObject.getId());
        for (RuleGroup ruleGroup:ruleGroupList){
            list.add(ruleGroup.getCode());
        }
        return  list;
    }




    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
        return ruleGroupMapper.selectByTotal();
    }


    /**
     * 根据规则组名称查询
     */
    public Integer selectTotalByName(String name,String objectId){

        return  ruleGroupMapper.selectTotalByName(name,objectId);
    }


    /**
     * 根据规则组code查询
     */
    public Integer selectTotalByCode(String code,String objectId){

        return  ruleGroupMapper.selectTotalByCode(code,objectId);
    }


    /**
     * 查询规则组
     */
    public List<RuleGroup> searchRuleGroup(RuleGroup ruleGroup){
        if (ruleGroup.getPage()!=null && ruleGroup.getSize()!=null){
            int from;
            from = (ruleGroup.getPage() - 1) * ruleGroup.getSize();
            ruleGroup.setForm(from);
        }
        String order;
        if (StringUtils.isNotBlank(ruleGroup.getField()) && StringUtils.isNotBlank(ruleGroup.getOrder())){
            order = ruleGroup.getField()+" "+ruleGroup.getOrder();
        }else {
            order ="gmt_create desc";
        }
        return  ruleGroupMapper.selectByConditions(ruleGroup.getForm(),ruleGroup.getSize(),order,ruleGroup.getName(),ruleGroup.getObjectId());
    }




    /**
     * 删除规则组
     */
    public int deleteRuleGroup(String id){
        try {
            //删除规则组的时候将规则组下的规则一起删除
            ruleMapper.deleteByGroupRuleId(id);
        }catch (Exception e){

        }
        return  ruleGroupMapper.deleteByPrimaryKey(id);
    }
}
