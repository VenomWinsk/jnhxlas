package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Project;
import com.hxht.logprocess.logbulk.model.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(@Param("from")int from,@Param("size")int size);

    Project selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> selectByConditions(Project project);

    Long selectByTotal();

    Long selectByUnitTotal(String unitId);

    List<Project> selectProject();

    List<Project> selectProjectByUnitId(String unitId);




}