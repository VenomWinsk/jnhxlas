package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.ProjectAnalysisMapper;
import com.hxht.logprocess.logbulk.model.ProjectAnalysisMapperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectAnalysisMapperMapper {
    long countByExample(ProjectAnalysisMapperExample example);

    int deleteByExample(ProjectAnalysisMapperExample example);

    int deleteByPrimaryKey(String id);

    int deleteByProjectId(String projectId);

    int insert(ProjectAnalysisMapper record);

    int insertSelective(ProjectAnalysisMapper record);

    List<ProjectAnalysisMapper> selectByExample(@Param("from")int from,@Param("size")int size);

    ProjectAnalysisMapper selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectAnalysisMapper record, @Param("example") ProjectAnalysisMapperExample example);

    int updateByExample(@Param("record") ProjectAnalysisMapper record, @Param("example") ProjectAnalysisMapperExample example);

    int updateByPrimaryKeySelective(ProjectAnalysisMapper record);

    int updateByPrimaryKey(ProjectAnalysisMapper record);

    List<ProjectAnalysisMapper> selectByProjectId(String projectId);

    int updateByStatus(ProjectAnalysisMapper projectAnalysisMapper);

    int selectByProjectIdObjectId(@Param("projectId")String projectId,@Param("objectId")String objectId);


}