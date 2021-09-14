package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.UsedRule;
import com.hxht.logprocess.logbulk.model.UsedRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedRuleMapper {
    long countByExample(UsedRuleExample example);

    int deleteByExample(UsedRuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(UsedRule record);

    int insertSelective(UsedRule record);

    List<UsedRule> selectByExample(UsedRuleExample example);

    UsedRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UsedRule record, @Param("example") UsedRuleExample example);

    int updateByExample(@Param("record") UsedRule record, @Param("example") UsedRuleExample example);

    int updateByPrimaryKeySelective(UsedRule record);

    int updateByPrimaryKey(UsedRule record);

    List<UsedRule> selectUseRuleByUseRuleGroupId(String useRuleGroupId);


    List<UsedRule> selectByProject(String projectId);



}
