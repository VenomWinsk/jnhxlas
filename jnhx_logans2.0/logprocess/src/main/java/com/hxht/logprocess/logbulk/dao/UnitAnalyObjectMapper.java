package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.model.UnitAnalyObject;
import com.hxht.logprocess.logbulk.model.UnitAnalyObjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnitAnalyObjectMapper {
    long countByExample(UnitAnalyObjectExample example);

    int deleteByExample(UnitAnalyObjectExample example);

    int insert(UnitAnalyObject record);

    int insertSelective(UnitAnalyObject record);

    List<UnitAnalyObject> selectByExample(@Param("from")int from,@Param("size")int size);

    int updateByExampleSelective(@Param("record") UnitAnalyObject record, @Param("example") UnitAnalyObjectExample example);

    int updateByExample(@Param("record") UnitAnalyObject record, @Param("example") UnitAnalyObjectExample example);


    int updateByPrimaryKeySelective(UnitAnalyObject record);

    int deleteByPrimaryKey(String id);

    int deleteByUnitId(String unitId);

    int deleteByObjectId(String objectId);

    int selectCountByUnit(@Param("unitId")String unitId,@Param("objectId")String objectId);

    List<UnitAnalyObject> selectAnaysisByUnitId(String unitId);



}