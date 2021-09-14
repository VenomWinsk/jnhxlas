package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.PrgramConfig;
import com.hxht.logprocess.logbulk.model.PrgramConfigExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;

public interface PrgramConfigMapper {
    long countByExample(PrgramConfigExample example);

    int deleteByExample(PrgramConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(PrgramConfig record);

    int insertSelective(PrgramConfig record);

    List<PrgramConfig> selectByExample(PrgramConfigExample example);

    PrgramConfig selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PrgramConfig record, @Param("example") PrgramConfigExample example);

    int updateByExample(@Param("record") PrgramConfig record, @Param("example") PrgramConfigExample example);

    int updateByPrimaryKeySelective(PrgramConfig record);

    int updateByPrimaryKey(PrgramConfig record);

    List<PrgramConfig> selectByConditions(PrgramConfig prgramConfig);

    Long selectByTotal();
}