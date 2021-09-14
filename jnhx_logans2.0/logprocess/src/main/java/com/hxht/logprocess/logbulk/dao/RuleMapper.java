package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Rule;
import com.hxht.logprocess.logbulk.model.RuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleMapper {
    long countByExample(RuleExample example);

    int deleteByExample(RuleExample example);

    int deleteByPrimaryKey(String id);

    int deleteByGroupRuleId(String ruleGroupId);

    int insert(Rule record);

    int insertSelective(Rule record);

    List<Rule> selectByExample(RuleExample example);

    Rule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Rule record, @Param("example") RuleExample example);

    int updateByExample(@Param("record") Rule record, @Param("example") RuleExample example);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);

    List<Rule> selectByConditions(@Param("form") Integer form, @Param("size") Integer size,@Param("order") String order);

    Long selectByTotal();

    int selectTotalByName(@Param("name")String name);

    int selectByLogFeature(@Param("logFeature")String logFeature);


}
