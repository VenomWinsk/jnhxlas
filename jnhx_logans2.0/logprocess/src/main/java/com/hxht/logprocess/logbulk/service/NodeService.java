package com.hxht.logprocess.logbulk.service;

import com.hxht.logprocess.logbulk.dao.NodeMapper;
import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.Node;
import com.hxht.logprocess.logbulk.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NodeService {


    @Autowired
    private NodeMapper nodeMapper;


    /**
     * 查询节点
     */
    public List<Node> searchNode(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  nodeMapper.selectByConditions(from,size);
    }





}
