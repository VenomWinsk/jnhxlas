package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Progress;
import com.hxht.logprocess.logbulk.model.ProgressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressMapper {
    long countByExample(ProgressExample example);

    int deleteByExample(ProgressExample example);

    int deleteByPrimaryKey(String id);

    int insert(Progress record);

    int insertSelective(Progress record);

    List<Progress> selectByExample(ProgressExample example);

    Progress selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Progress record, @Param("example") ProgressExample example);

    int updateByExample(@Param("record") Progress record, @Param("example") ProgressExample example);

    int updateByPrimaryKeySelective(Progress record);

    int updateByPrimaryKey(Progress record);

    Progress selectByUsedRuleGroupId(String usedRuleGroupId);

    List<Progress> selectByProjectId(String projectId);



}
