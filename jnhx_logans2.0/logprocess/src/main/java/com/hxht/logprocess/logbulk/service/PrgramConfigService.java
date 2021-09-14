package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.PrgramConfigMapper;
import com.hxht.logprocess.logbulk.model.PrgramConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PrgramConfigService {


    @Autowired
    private PrgramConfigMapper prgramConfigMapper;



    /**
     * 添加程序配置
     */
    public int  addPrgramConfig(PrgramConfig prgramConfig){
        prgramConfig.setId(UUID.randomUUID().toString());
        prgramConfig.setGwtCreate(new Date());
        return  prgramConfigMapper.insertSelective(prgramConfig);

    }





    /**
     * 根据id查询程序配置
     */
    public PrgramConfig selectById(String id){

        return  prgramConfigMapper.selectByPrimaryKey(id);
    }



    /**
     * 修改程序配置
     */
    public int  updatePrgramConfig(PrgramConfig prgramConfig){
        prgramConfig.setGwtModified(new Date());
        return  prgramConfigMapper.updateByPrimaryKeySelective(prgramConfig);
    }





    /**
     * 查询程序配置
     */
    public List<PrgramConfig> searchPrgramConfig(PrgramConfig prgramConfig){
        if (prgramConfig.getPage()!=null && prgramConfig.getSize()!=null){
            int from;
            from = (prgramConfig.getPage() - 1) * prgramConfig.getSize();
            prgramConfig.setForm(from);
        }
        return  prgramConfigMapper.selectByConditions(prgramConfig);
    }


    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
        return prgramConfigMapper.selectByTotal();
    }


    /**
     * 删除程序配置
     */
    public int deletePrgramConfig(String id){

        return  prgramConfigMapper.deleteByPrimaryKey(id);
    }
}
