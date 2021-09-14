package com.hxht.logprocess.setting.dao;


import com.hxht.logprocess.setting.model.ObjectTree;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Repository
public interface ObjectTreeMapper {

    List<ObjectTree> selectByUnitId(String unit_id);

    List<Map<String,Object>> selectObjectByUnitId(String unit_id);

    List<Map<String,Object>> selectObject();

    List<Map<String,Object>> selectRuleGroupByObjectId(String object_id);

    List<LinkedHashMap<String,Object>> selectRuleByRuleGroupId(String rule_group_id);

}
