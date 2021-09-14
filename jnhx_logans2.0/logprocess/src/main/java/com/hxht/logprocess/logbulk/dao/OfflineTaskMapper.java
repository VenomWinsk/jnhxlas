package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.OfflineTask;
import com.hxht.logprocess.logbulk.model.OfflineTaskExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfflineTaskMapper {
    long countByExample(OfflineTaskExample example);

    int deleteByExample(OfflineTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(OfflineTask record);

    int insertSelective(OfflineTask record);

    List<OfflineTask> selectByExample(OfflineTaskExample example);

    OfflineTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OfflineTask record, @Param("example") OfflineTaskExample example);

    int updateByExample(@Param("record") OfflineTask record, @Param("example") OfflineTaskExample example);

    int updateByPrimaryKeySelective(OfflineTask record);

    int updateByPrimaryKey(OfflineTask record);

    List<OfflineTask> selectByConditions(OfflineTask offlineTask);

    Long selectByTotal();
}