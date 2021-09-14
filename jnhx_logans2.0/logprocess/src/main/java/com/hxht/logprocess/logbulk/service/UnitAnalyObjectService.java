package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.UnitAnalyObjectMapper;
import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.model.UnitAnalyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UnitAnalyObjectService {



    @Autowired
    private UnitAnalyObjectMapper unitAnalyObjectMapper;



    /**
     * 添加单位对象关系
     */
    public int  addUnitAnalyObject(UnitAnalyObject unitAnalyObject){
        unitAnalyObject.setId(UUID.randomUUID().toString());
        return  unitAnalyObjectMapper.insertSelective(unitAnalyObject);

    }


    /**
     * 修改单位对象关系
     */
    public int  updateUnitAnalyObject(UnitAnalyObject unitAnalyObject){
        return  unitAnalyObjectMapper.updateByPrimaryKeySelective(unitAnalyObject);
    }





    /**
     * 查询单位对象关系
     */
    public List<UnitAnalyObject> searchUnitAnalyObject(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  unitAnalyObjectMapper.selectByExample(from,size);
    }




    /**
     * 删除单位对象关系
     */
    public int deleteUnitAnalyObject(String id){

        return  unitAnalyObjectMapper.deleteByPrimaryKey(id);
    }


    /**
     * 通过单位id删除
     * @param unitId
     * @return
     */
    public int deleteByUnitId(String unitId){

        return  unitAnalyObjectMapper.deleteByUnitId(unitId);
    }
}
