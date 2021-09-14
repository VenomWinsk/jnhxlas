package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.DirRecordsMapper;
import com.hxht.logprocess.logbulk.model.DirRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirRecordsService {



    @Autowired
    private DirRecordsMapper dirRecordsMapper;


    /**
     * 修改文件夹
     */
    public int updateDirRecords(DirRecords dirRecords){

        return dirRecordsMapper.updateByPrimaryKeySelective(dirRecords);

    }


}
