package com.hxht.logprocess.logbulk.service;

import com.hxht.logprocess.logbulk.dao.ImportFileMapper;
import com.hxht.logprocess.logbulk.dao.ImportStatisticsMapper;
import com.hxht.logprocess.logbulk.model.ImportFile;
import com.hxht.logprocess.logbulk.model.ImportStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImportStatisticsService {


    @Autowired
    private ImportStatisticsMapper importStatisticsMapper;


    /**
     * 修改文件统计
     */
    public int  updateImportStatistics(ImportStatistics importStatistics){
        return  importStatisticsMapper.updateByPrimaryKeySelective(importStatistics);
    }





    /**
     * 查询文件统计
     */
    public List<ImportStatistics> searchImportStatistics(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  importStatisticsMapper.selectByConditions(from,size);
    }




    /**
     * 删除导入文件记录
     */
    public int deleteImportStatistics(String id){

        return  importStatisticsMapper.deleteByPrimaryKey(id);
    }






}
