package com.hxht.logprocess.setting.dao;

import com.hxht.logprocess.setting.model.Field;
import com.hxht.logprocess.setting.model.FieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldMapper {
    long countByExample(FieldExample example);

    int deleteByExample(FieldExample example);

    int deleteByChannelId(String channelId);

    int deleteByPrimaryKey(String id);

    int insert(Field record);

    int insertSelective(Field record);

    List<Field> selectByExample(@Param("from")int from,@Param("size")int size);

    List<Field> selectByExample(FieldExample example);

    Field selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Field record, @Param("example") FieldExample example);

    int updateByExample(@Param("record") Field record, @Param("example") FieldExample example);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);

    List<Field> selectByConditions(Field field);

    int selectTotalByCname(@Param("cname")String cname,@Param("channelId")String channelId);

    int selectTotalByEname(@Param("ename")String ename,@Param("channelId")String channelId);

    Long selectByTotal(@Param("channelId")String channelId);

    List<Field> selectAll(@Param("channelId")String channelId);

    int bathUpdate(@Param("list") List<Field> list);

    List<Field> selectFieldByChannelId(String channelId);

    Field selectByEname(@Param("ename")String ename,@Param("channelId")String channelId);

    Field selectByOnlyEname(@Param("ename")String ename,@Param("objectId")String objectId,@Param("ruleGroupId")String ruleGroupId,@Param("ruleId")String ruleId);



}
