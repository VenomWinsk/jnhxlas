package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.FileRecords;
import com.hxht.logprocess.logbulk.model.FileRecordsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRecordsMapper {
    long countByExample(FileRecordsExample example);

    int deleteByExample(FileRecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileRecords record);

    int insertSelective(FileRecords record);

    List<FileRecords> selectByExample(FileRecordsExample example);

    FileRecords selectByPrimaryKey(String id);

    List<Map<String,Object>> selectByFileId(String fileId,Integer page,Integer size);

    Long  selectCountByFileId(String fileId,Integer page,Integer size);

    int updateByExampleSelective(@Param("record") FileRecords record, @Param("example") FileRecordsExample example);

    int updateByExample(@Param("record") FileRecords record, @Param("example") FileRecordsExample example);

    int updateByPrimaryKeySelective(FileRecords record);

    int updateByPrimaryKey(FileRecords record);

    List<FileRecords> selectByDirId(String fileDirId);


}
