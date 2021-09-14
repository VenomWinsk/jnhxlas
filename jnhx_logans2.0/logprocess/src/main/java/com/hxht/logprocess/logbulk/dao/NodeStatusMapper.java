package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.NodeStatus;
import com.hxht.logprocess.logbulk.model.NodeStatusExample;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;

public interface NodeStatusMapper {
    long countByExample(NodeStatusExample example);

    int deleteByExample(NodeStatusExample example);

    int insert(NodeStatus record);

    int insertSelective(NodeStatus record);

    List<NodeStatus> selectByExample(@Param("from")int from,@Param("size")int size);

    int updateByExampleSelective(@Param("record") NodeStatus record, @Param("example") NodeStatusExample example);

    int updateByExample(@Param("record") NodeStatus record, @Param("example") NodeStatusExample example);

    List<NodeStatus> selectByConditions(@Param("from")int from, @Param("size")int size);

}