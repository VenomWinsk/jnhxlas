package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Rule;
import com.hxht.logprocess.logbulk.model.RuleGroup;
import com.hxht.logprocess.logbulk.model.RuleGroupExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleGroupMapper {
    long countByExample(RuleGroupExample example);

    int deleteByExample(RuleGroupExample example);

    int deleteByPrimaryKey(String id);

    int deleteByObjectId(String objectId);

    int insert(RuleGroup record);

    int insertSelective(RuleGroup record);

    List<RuleGroup> selectByExample(@Param("from")int from,@Param("size")int size);

    RuleGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    int updateByExample(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    int updateByPrimaryKeySelective(RuleGroup record);

    int updateByPrimaryKey(RuleGroup record);

    List<RuleGroup> selectByConditions(@Param("form") Integer form, @Param("size") Integer size,@Param("order") String order,@Param("name") String name,@Param("objectId") String objectId);

    Long selectByTotal();

    int selectTotalByName(@Param("name")String name,@Param("objectId")String objectId);

    int selectTotalByCode(@Param("code")String name,@Param("objectId")String objectId);


    List<RuleGroup> selectByObjetId(String objectId);


}