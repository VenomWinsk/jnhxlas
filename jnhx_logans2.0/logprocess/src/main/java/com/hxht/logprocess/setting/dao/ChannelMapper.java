package com.hxht.logprocess.setting.dao;

import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.setting.model.Channel;
import com.hxht.logprocess.setting.model.ChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelMapper {
    long countByExample(ChannelExample example);

    int deleteByExample(ChannelExample example);

    int deleteByPrimaryKey(String id);

    int deleteByObjectId(String objectId);

    int insert(Channel record);

    int insertSelective(Channel record);

    List<Channel> selectByExample(@Param("from")int from,@Param("size")int size);

    Channel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByExample(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

    List<Channel> selectByConditions(@Param("from")int from, @Param("size")int size);

    int selectTotalByCname(@Param("cname")String cname);

    int selectTotalByEname(@Param("ename")String ename);

    Long selectByTotal();

    List<Channel> selectAll();

    Channel selectByObjectId(String objectId);



}
