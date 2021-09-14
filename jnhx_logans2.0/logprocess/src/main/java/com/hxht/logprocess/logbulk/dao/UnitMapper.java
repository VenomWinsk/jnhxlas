package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.model.UnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UnitMapper {
    long countByExample(UnitExample example);

    int deleteByExample(UnitExample example);

    int deleteByPrimaryKey(String id);

    int insert(Unit record);

    int insertSelective(Unit record);

    List<Unit> selectByExample(@Param("from")int from,@Param("size")int size);

    Unit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Unit record, @Param("example") UnitExample example);

    int updateByExample(@Param("record") Unit record, @Param("example") UnitExample example);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);

    List<Unit> selectByConditions(Unit unit);

    Long selectByTotal();

    int selectTotalByCname(@Param("cname")String cname);

    int selectTotalByEname(@Param("ename")String ename);

    Unit selectByEname(@Param("name")String name);


}