package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.RuleGroup;
import com.hxht.logprocess.logbulk.model.UsedRuleGroup;
import com.hxht.logprocess.logbulk.model.UsedRuleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedRuleGroupMapper {
    long countByExample(UsedRuleGroupExample example);

    int deleteByExample(UsedRuleGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(UsedRuleGroup record);

    int insertSelective(UsedRuleGroup record);

    List<UsedRuleGroup> selectByExample(UsedRuleGroupExample example);

    UsedRuleGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UsedRuleGroup record, @Param("example") UsedRuleGroupExample example);

    int updateByExample(@Param("record") UsedRuleGroup record, @Param("example") UsedRuleGroupExample example);

    int updateByPrimaryKeySelective(UsedRuleGroup record);

    int updateByPrimaryKey(UsedRuleGroup record);

    List<UsedRuleGroup> selectByUsedRuleGroupId(String objectId);


    List<UsedRuleGroup> selectByProjectAnalysisId(String projectAnalysisId);

    List<UsedRuleGroup> selectByProject(String projectId);

    String selectByUsedRuleGroupIdAndDirName(String useGroupRuleId, String dirName);
}
