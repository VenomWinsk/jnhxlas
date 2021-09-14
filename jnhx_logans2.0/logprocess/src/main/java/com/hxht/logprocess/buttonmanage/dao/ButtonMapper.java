package com.hxht.logprocess.buttonmanage.dao;

import com.hxht.logprocess.buttonmanage.model.Button;
import com.hxht.logprocess.buttonmanage.model.ButtonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonMapper {
    long countByExample(ButtonExample example);

    int deleteByExample(ButtonExample example);

    int deleteByPrimaryKey(String id);

    int insert(Button record);

    int insertSelective(Button record);

    List<Button> selectByExample(ButtonExample example);

    Button selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Button record, @Param("example") ButtonExample example);

    int updateByExample(@Param("record") Button record, @Param("example") ButtonExample example);

    int updateByPrimaryKeySelective(Button record);

    int updateByPrimaryKey(Button record);

    int deleteByMenuId(String menuId);
}
