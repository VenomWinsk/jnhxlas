package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.BasicRegex;
import com.hxht.logprocess.logbulk.model.BasicRegexExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;

public interface BasicRegexMapper {
    long countByExample(BasicRegexExample example);

    int deleteByExample(BasicRegexExample example);

    int deleteByPrimaryKey(String id);

    int insert(BasicRegex record);

    int insertSelective(BasicRegex record);

    List<BasicRegex> selectByExample(@Param("from")int from,@Param("size")int size);

    BasicRegex selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BasicRegex record, @Param("example") BasicRegexExample example);

    int updateByExample(@Param("record") BasicRegex record, @Param("example") BasicRegexExample example);

    int updateByPrimaryKeySelective(BasicRegex record);

    int updateByPrimaryKey(BasicRegex record);

    List<BasicRegex> selectByConditions(BasicRegex basicRegex);

    Long selectByTotal();
}