package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.Node;
import com.hxht.logprocess.logbulk.model.NodeExample;
import com.hxht.logprocess.logbulk.model.NodeKey;
import java.util.List;

import com.hxht.logprocess.logbulk.model.Unit;
import org.apache.ibatis.annotations.Param;

public interface NodeMapper {
    long countByExample(NodeExample example);

    int deleteByExample(NodeExample example);

    int deleteByPrimaryKey(NodeKey key);

    int insert(Node record);

    int insertSelective(Node record);

    List<Node> selectByExample(@Param("from")int from,@Param("size")int size);

    Node selectByPrimaryKey(NodeKey key);

    int updateByExampleSelective(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByExample(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    List<Node> selectByConditions(@Param("from")int from, @Param("size")int size);

}