package com.hxht.logprocess.logbulk.service;

import com.hxht.logprocess.logbulk.dao.BuiltFunctionMapper;
import com.hxht.logprocess.logbulk.model.BuiltFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BuiltFunctionService {




    @Autowired
    private BuiltFunctionMapper builtFunctionMapper;



    /**
     * 添加内置函数
     */
    public int  addBuiltFunction(BuiltFunction builtFunction){
        builtFunction.setId(UUID.randomUUID().toString());
        builtFunction.setGmtCreate(new Date());
        builtFunction.setGmtModified(new Date());
        return  builtFunctionMapper.insertSelective(builtFunction);

    }


    /**
     * 修改内资函数
     */
    public int  updateBuiltFunction(BuiltFunction builtFunction){
        return  builtFunctionMapper.updateByPrimaryKeySelective(builtFunction);
    }





    /**
     * 查询内置函数
     */
    public List<BuiltFunction> searchBuiltFunction(BuiltFunction builtFunction){
        if (builtFunction.getPage()!=null && builtFunction.getSize()!=null){
            int from;
            from = (builtFunction.getPage() - 1) * builtFunction.getSize();
            builtFunction.setForm(from);
        }
        return  builtFunctionMapper.selectByConditions(builtFunction);
    }


    /**
     * 查询内置函数总数
     * @return
     */
    public Long searchTotal(){
        return builtFunctionMapper.selectByTotal();
    }

    /**
     * 删除内置函数
     */
    public int deleteBuiltFunction(String id){

        return  builtFunctionMapper.deleteByPrimaryKey(id);
    }
}
