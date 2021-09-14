package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.Node;
import com.hxht.logprocess.logbulk.model.NodeStatus;
import com.hxht.logprocess.logbulk.service.NodeService;
import com.hxht.logprocess.logbulk.service.NodeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server/nodeStatus")
public class NodeStatusController {


    @Autowired
    private NodeStatusService nodeStatusService;


    /**
     * 查询节点状态
     */
    @RequestMapping("searchNodeStatus")
    public ResMessage searchNodeStatus(@RequestParam int page, @RequestParam int size){
        try {
            List<NodeStatus> list = nodeStatusService.searchNodeStatus(page,size);
            if(list.size() > 0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }
}
