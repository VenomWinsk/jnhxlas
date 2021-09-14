package com.hxht.logprocess.setting.service;

import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.setting.dao.ObjectTreeMapper;
import com.hxht.logprocess.setting.model.ObjectTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ObjectTreeService {

    @Autowired
    private ObjectTreeMapper objectTreeMapper;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;

    /**
     * tree
     */
    public List<ObjectTree> selectTreeByUnitId(String unit_id) {
        return objectTreeMapper.selectByUnitId(unit_id);

    }

    public List<Map<String,Object>> selectObjectByUnitId(String unit_id) {
        return objectTreeMapper.selectObjectByUnitId(unit_id);
    }




    public List<Map<String,Object>> selectRuleGroupByObjectId(String object_id) {
        return objectTreeMapper.selectRuleGroupByObjectId(object_id);
    }

    public List<LinkedHashMap<String,Object>> selectRuleByRuleGroupId(String rule_group_id) {
        return objectTreeMapper.selectRuleByRuleGroupId(rule_group_id);
    }


    /**
     * 查询树形结构数据
     */
    public List<LinkedHashMap<String,Object>> selectTree(String unitId,String type){

        List<LinkedHashMap<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> objectList;
        if (type.equals("part")){
            objectList = objectTreeMapper.selectObjectByUnitId(unitId);
        }else {
            objectList = objectTreeMapper.selectObject();
        }
        for(Map<String,Object> o:objectList){
            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
            String id = o.get("id").toString();
            List<Map<String,Object>> ruleGroupList = objectTreeMapper.selectRuleGroupByObjectId(id);
            List<Map<String,Object>> list1 = new ArrayList<>();
            for(Map<String,Object> o1:ruleGroupList){
                LinkedHashMap<String,Object> map1 = new LinkedHashMap<>();
                String id2 = o1.get("id").toString();
                List<LinkedHashMap<String,Object>> ruleList = objectTreeMapper.selectRuleByRuleGroupId(id2);
                map1.put("id",o1.get("id").toString());
                map1.put("name",o1.get("name").toString());
                map1.put("ownerType",1);
                map1.put("children",ruleList);
                list1.add(map1);
            }
            map.put("id",o.get("id").toString());
            map.put("name",o.get("name").toString());
            map.put("ownerType",0);
            map.put("children",list1);
            list.add(map);
        }
        return  list;

    }






}
