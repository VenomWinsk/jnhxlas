package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.BasicRegex;
import com.hxht.logprocess.logbulk.model.BuiltFunction;
import com.hxht.logprocess.logbulk.model.BuiltFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuiltFunctionMapper {
    long countByExample(BuiltFunctionExample example);

    int deleteByExample(BuiltFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(BuiltFunction record);

    int insertSelective(BuiltFunction record);

    List<BuiltFunction> selectByExample(BuiltFunctionExample example);

    BuiltFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BuiltFunction record, @Param("example") BuiltFunctionExample example);

    int updateByExample(@Param("record") BuiltFunction record, @Param("example") BuiltFunctionExample example);

    int updateByPrimaryKeySelective(BuiltFunction record);

    int updateByPrimaryKey(BuiltFunction record);

    List<BuiltFunction> selectByConditions(BuiltFunction builtFunction);

    Long selectByTotal();
}