package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ImportFileMapper {
    long countByExample(ImportFileExample example);

    int deleteByExample(ImportFileExample example);

    int insert(ImportFile record);

    int deleteByPrimaryKey(String id);

    int insertSelective(ImportFile record);

    List<ImportFile> selectByExample(@Param("from")int from,@Param("size")int size);

    int updateByExampleSelective(@Param("record") ImportFile record, @Param("example") ImportFileExample example);

    int updateByExample(@Param("record") ImportFile record, @Param("example") ImportFileExample example);

    int updateByPrimaryKeySelective(ImportFile record);

    List<ImportFile> selectByConditions(@Param("from")int from, @Param("size")int size);


    List<ImportFile> selectByRuleGroupId(String ruleGroupId);

}