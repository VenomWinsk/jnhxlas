package com.hxht.logprocess.setting.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.setting.model.ObjectTree;
import com.hxht.logprocess.setting.service.ObjectTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/server/searchTree")
public class SearchController {



    @Autowired
    private ObjectTreeService objectTreeService;



    /**
     * 拼接树形结构 分析对象-规则组-规则 根据单位id
     * @param unitId 单位id
     * @return
     */
    @RequestMapping("/selectTree")
    public ResMessage selectTree(@RequestParam String unitId) {
        List<LinkedHashMap<String,Object>> list = objectTreeService.selectTree(unitId,"part");
        return ResMessage.genSucessMessage("成功", list);
    }


    /**
     * 拼接树形结构 分析对象-规则组-规则 全局
     * @param
     * @return
     */
    @RequestMapping("/selectTreeGlobal")
    public ResMessage selectTreeGlobal() {
        List<LinkedHashMap<String,Object>> list = objectTreeService.selectTree("","global");
        return ResMessage.genSucessMessage("成功", list);
    }



}
