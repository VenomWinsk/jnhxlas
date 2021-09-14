package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.ImportStatistics;
import com.hxht.logprocess.logbulk.model.ImportStatisticsExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;

public interface ImportStatisticsMapper {
    long countByExample(ImportStatisticsExample example);

    int deleteByExample(ImportStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(ImportStatistics record);

    int insertSelective(ImportStatistics record);

    List<ImportStatistics> selectByExample(@Param("from")int from,@Param("size")int size);

    ImportStatistics selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ImportStatistics record, @Param("example") ImportStatisticsExample example);

    int updateByExample(@Param("record") ImportStatistics record, @Param("example") ImportStatisticsExample example);

    int updateByPrimaryKeySelective(ImportStatistics record);

    int updateByPrimaryKey(ImportStatistics record);

    List<ImportStatistics> selectByConditions(@Param("from")int from, @Param("size")int size);

}