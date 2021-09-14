package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UnitService {


    @Autowired
    private UnitMapper unitMapper;



    /**
     * 添加单位
     */
    public int  addUnit(Unit unit){
        unit.setId(UUID.randomUUID().toString());
        unit.setGmtCreate(new Date());
        return  unitMapper.insertSelective(unit);

    }


    /**
     * 根据中文查询
     */
     public Integer selectTotalByCname(String cname){

         return  unitMapper.selectTotalByCname(cname);
     }



    /**
     * 根据id查询单位
     */
    public Unit selectById(String id){

        return  unitMapper.selectByPrimaryKey(id);
    }



    /**
     * 根据英文查询
     */
    public Integer selectTotalByEname(String ename){

        return  unitMapper.selectTotalByEname(ename);
    }




    /**
     * 修改单位
     */
    public int  updateUnit(Unit unit){
        unit.setGmtModified(new Date());
        return  unitMapper.updateByPrimaryKeySelective(unit);
    }





    /**
     * 查询单位
     */
    public List<Unit> searchUnit(Unit unit){
        if (unit.getPage()!=null && unit.getSize()!=null){
            int from;
            from = (unit.getPage() - 1) * unit.getSize();
            unit.setForm(from);
        }
        return  unitMapper.selectByConditions(unit);
    }


    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
       return unitMapper.selectByTotal();
    }


    /**
     * 删除单位
     */
    public int deleteUnit(String id){

        return  unitMapper.deleteByPrimaryKey(id);
    }
}
