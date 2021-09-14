package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.RulesTemplateMapper;
import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.RulesTemplate;
import com.hxht.logprocess.logbulk.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RulesTemplateService {


    @Autowired
    private RulesTemplateMapper rulesTemplateMapper;



    /**
     * 添加规则模板
     */
    public int  addRulesTemplate(RulesTemplate rulesTemplate){
        rulesTemplate.setId(UUID.randomUUID().toString());
        return  rulesTemplateMapper.insertSelective(rulesTemplate);

    }


    /**
     * 修改规则模板
     */
    public int  updateRulesTemplate(RulesTemplate rulesTemplate){
        return  rulesTemplateMapper.updateByPrimaryKeySelective(rulesTemplate);
    }





    /**
     * 查询规则模板
     */
    public List<RulesTemplate> searchRulesTemplate(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  rulesTemplateMapper.selectByConditions(from,size);
    }




    /**
     * 删除规则模板
     */
    public int deleteRulesTemplate(String id){

        return  rulesTemplateMapper.deleteByPrimaryKey(id);
    }
}
