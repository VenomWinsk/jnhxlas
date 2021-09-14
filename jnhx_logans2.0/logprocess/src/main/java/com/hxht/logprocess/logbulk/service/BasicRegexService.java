package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.BasicRegexMapper;
import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.BasicRegex;
import com.hxht.logprocess.logbulk.model.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BasicRegexService {



    @Autowired
    private BasicRegexMapper basicRegexMapper;



    /**
     * 添加正则
     */
    public int  addBasicRegex(BasicRegex basicRegex){
        basicRegex.setId(UUID.randomUUID().toString());
        basicRegex.setGmtCreate(new Date());
        basicRegex.setGmtModified(new Date());
        return  basicRegexMapper.insertSelective(basicRegex);

    }


    /**
     * 修改正则
     */
    public int  updateBasicRegex(BasicRegex basicRegex){
        return  basicRegexMapper.updateByPrimaryKeySelective(basicRegex);
    }





    /**
     * 查询正则
     */
    public List<BasicRegex> searchBasicRegex(BasicRegex basicRegex){
        if (basicRegex.getPage()!=null && basicRegex.getSize()!=null){
            int from;
            from = (basicRegex.getPage() - 1) * basicRegex.getSize();
            basicRegex.setForm(from);
        }
        return  basicRegexMapper.selectByConditions(basicRegex);
    }


    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
        return basicRegexMapper.selectByTotal();
    }

    /**
     * 删除正则
     */
    public int deleteBasicRegex(String id){

        return  basicRegexMapper.deleteByPrimaryKey(id);
    }

}
