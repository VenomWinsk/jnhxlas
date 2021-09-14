package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.NodeMapper;
import com.hxht.logprocess.logbulk.dao.NodeStatusMapper;
import com.hxht.logprocess.logbulk.model.Node;
import com.hxht.logprocess.logbulk.model.NodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeStatusService {

    @Autowired
    private NodeStatusMapper nodeStatusMapper;


    /**
     * 查询节点状态
     */
    public List<NodeStatus> searchNodeStatus(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  nodeStatusMapper.selectByConditions(from,size);
    }


}
