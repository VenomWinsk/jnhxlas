package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.DirRecords;
import com.hxht.logprocess.logbulk.model.DirRecordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DirRecordsMapper {
    long countByExample(DirRecordsExample example);

    int deleteByExample(DirRecordsExample example);

    int deleteByPrimaryKey(String id);

    int insert(DirRecords record);

    int insertSelective(DirRecords record);

    List<DirRecords> selectByExample(DirRecordsExample example);

    DirRecords selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DirRecords record, @Param("example") DirRecordsExample example);

    int updateByExample(@Param("record") DirRecords record, @Param("example") DirRecordsExample example);

    int updateByPrimaryKeySelective(DirRecords record);

    int updateByPrimaryKey(DirRecords record);

    List<DirRecords> selectByUsedGroupId(String ruleGroupId);

    boolean deleteFiles(String used_rule_group_id);


}