package com.hxht.logprocess.setting.dao;

import com.hxht.logprocess.setting.model.Interface;
import com.hxht.logprocess.setting.model.InterfaceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceMapper {
    long countByExample(InterfaceExample example);

    int deleteByExample(InterfaceExample example);

    int deleteByPrimaryKey(String id);

    int insert(Interface record);

    int insertSelective(Interface record);

    List<Interface> selectByExample(InterfaceExample example);

    Interface selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Interface record, @Param("example") InterfaceExample example);

    int updateByExample(@Param("record") Interface record, @Param("example") InterfaceExample example);

    int updateByPrimaryKeySelective(Interface record);

    int updateByPrimaryKey(Interface record);

    Interface selectByName(String name);
}
