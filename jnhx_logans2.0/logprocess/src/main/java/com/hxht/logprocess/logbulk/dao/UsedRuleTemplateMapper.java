package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.RulesTemplate;
import com.hxht.logprocess.logbulk.model.UsedRuleTemplate;
import com.hxht.logprocess.logbulk.model.UsedRuleTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsedRuleTemplateMapper {
    long countByExample(UsedRuleTemplateExample example);

    int deleteByExample(UsedRuleTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(UsedRuleTemplate record);

    int insertSelective(UsedRuleTemplate record);

    List<UsedRuleTemplate> selectByExample(UsedRuleTemplateExample example);

    UsedRuleTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UsedRuleTemplate record, @Param("example") UsedRuleTemplateExample example);

    int updateByExample(@Param("record") UsedRuleTemplate record, @Param("example") UsedRuleTemplateExample example);

    int updateByPrimaryKeySelective(UsedRuleTemplate record);

    int updateByPrimaryKey(UsedRuleTemplate record);

    List<UsedRuleTemplate> selectByConditions(@Param("from")int from, @Param("size")int size);
}