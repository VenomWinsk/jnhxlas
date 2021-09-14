package com.hxht.logprocess.setting.service;


import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.setting.dao.FieldMapper;
import com.hxht.logprocess.setting.model.Field;
import com.hxht.logprocess.setting.model.FieldExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class FieldService {


    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;


    /**
     * 添加频道
     */
    public int addField(Field field) {
        field.setGmtCreate(new Date());
        field.setId(UUID.randomUUID().toString());
//        Long num =  fieldMapper.selectByTotal(field.getChannelId());
//        field.setFindex(num.intValue());
        //根据频道id倒叙查询字段序号，如果查询不出结果取0开始
//        List<Field> list = fieldMapper.selectFieldByChannelId(field.getChannelId());
//        if (list != null && list.size() > 0) {
//            field.setFindex(list.get(0).getFindex() + 1);
//        } else {
//            field.setFindex(0);
//        }
        //log.info("添加完毕");
        return fieldMapper.insertSelective(field);

    }


//    /**
//     * 克隆字段
//     */
//    public void  cloningField(String oldChannelId,String newChannelId){
//
//        //通过要复制的channelid查询下面的所有字段
//        List<Field> list = fieldMapper.selectFieldByChannelId(oldChannelId);
//        //将旧的channelId替换成新的channelId
//        for (Field field:list){
//            field.setChannelId(newChannelId);
//            fieldMapper.insertSelective(field);
//        }
//
//    }


    /**
     * 修改频道
     */
    public int updateField(Field field) {
        field.setGmtModified(new Date());
        return fieldMapper.updateByPrimaryKeySelective(field);
    }


    /**
     * 根据中文查询
     */
    public Integer selectTotalByCname(String cname, String channelId) {

        return fieldMapper.selectTotalByCname(cname, channelId);
    }


    /**
     * 根据英文查询
     */
    public Integer selectTotalByEname(String ename, String channelId) {

        return fieldMapper.selectTotalByEname(ename, channelId);
    }


    /**
     * 根据id查询字段
     */
    public Field selectById(String id) {

        return fieldMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询字段
     */
    public List<Field> searchField(int page, int size, String channelId) {
        List list = new ArrayList();
        int from = 0;
        if (page != 0 && size != 0) {
            from = (page - 1) * size;
        }
        return list;
    }


    /**
     * 查询某个频道下所有字段
     */
    public List<Field> searchAll(String channelId) {

        return fieldMapper.selectAll(channelId);
    }


    /**
     * 根据id查询字段
     *
     * @param id
     */
    public Field selectFieldById(String id) {
        return fieldMapper.selectByPrimaryKey(id);

    }


    /**
     * 查询总量
     *
     * @return
     */
    public Long searchTotal(String channelId) {
        return fieldMapper.selectByTotal(channelId);
    }


    /**
     * 删除字段
     */
    public int deleteField(String id) {

        return fieldMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量更新
     */

    public int bathUpdate(List<Field> list) {

        return fieldMapper.bathUpdate(list);
    }


    public List<Field> selectDictionaryByExample(Field field, boolean order, int from, int size) {
        FieldExample fieldExample = new FieldExample();
        if (field != null) {
            FieldExample.Criteria criteria = fieldExample.createCriteria();
            if (StringUtils.isNotBlank(field.getObjectId())) {
                criteria.andObjectIdEqualTo(field.getObjectId().trim());
            }
            if (StringUtils.isNotBlank(field.getRuleGroupId())) {
                criteria.andRuleGroupIdEqualTo(field.getRuleGroupId().trim());
            }
            if (StringUtils.isNotBlank(field.getRuleId())) {
                criteria.andRuleIdEqualTo(field.getRuleId().trim());
            }
            if (field.getOwnerType() != null) {
                criteria.andOwnerTypeLessThanOrEqualTo(field.getOwnerType());
            }
        }
        from = (from - 1) * size;
        if (order) {
            fieldExample.setOrderByClause("gmt_create asc limit    " + from + "," + size);//根据某一字段进行排序
        } else {
            fieldExample.setOrderByClause("gmt_create desc limit    " + from + "," + size);
        }
        return fieldMapper.selectByExample(fieldExample);
    }

    public long countByExample(Field field) {
        FieldExample fieldExample = new FieldExample();

        FieldExample.Criteria criteria = fieldExample.createCriteria();
        if (StringUtils.isNotBlank(field.getRuleGroupId())) {
            criteria.andRuleGroupIdEqualTo(field.getRuleGroupId().trim());
        }
        if (StringUtils.isNotBlank(field.getObjectId())) {
            criteria.andObjectIdEqualTo(field.getObjectId().trim());
        }
        if (field.getOwnerType() != null) {
            criteria.andOwnerTypeEqualTo(field.getOwnerType());
        }
        if (StringUtils.isNotBlank(field.getRuleId())) {
            criteria.andRuleIdEqualTo(field.getRuleId().trim());
        }
        return fieldMapper.countByExample(fieldExample);
    }


    /**
     * 查询字段服务层
     *
     * @param id
     * @param ownerType 树的层级
     */
    public List<Field> searchFileById(String id, Integer ownerType) {
        List<Field> list;
        if (ownerType == 0) {//第一层
            Field field = new Field();
            field.setObjectId(id);
            list = fieldMapper.selectByConditions(field);
        } else if (ownerType == 1) {//第二层
            Field field = new Field();
            field.setRuleGroupId(id);
            list = fieldMapper.selectByConditions(field);
        } else {
            Field field = new Field();
            field.setRuleId(id);
            list = fieldMapper.selectByConditions(field);
        }
        return list;


    }
}
