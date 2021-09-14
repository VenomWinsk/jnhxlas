package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.logbulk.model.AnalysisObjectExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalysisObjectMapper {
    long countByExample(AnalysisObjectExample example);

    int deleteByExample(AnalysisObjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(AnalysisObject record);

    int insertSelective(AnalysisObject record);

    List<AnalysisObject> selectByExample(@Param("from")int from,@Param("size")int size);

    AnalysisObject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AnalysisObject record, @Param("example") AnalysisObjectExample example);

    int updateByExample(@Param("record") AnalysisObject record, @Param("example") AnalysisObjectExample example);

    int updateByPrimaryKeySelective(AnalysisObject record);

    int updateByPrimaryKey(AnalysisObject record);

    List<AnalysisObject> selectByConditions(AnalysisObject analysisObject);

    Long selectByTotal();

    int selectTotalByObjectName(@Param("objectName")String objectName);

    int selectTotalByCode(@Param("code")String code);

   // AnalysisObject selectAnalysisByObjectId(String objectId);
    AnalysisObject selectByObjectName(String objectName);

    List<AnalysisObject>selectByAnalyseObject();

    List<AnalysisObject>selectNoBindByAnalyseObject();



}