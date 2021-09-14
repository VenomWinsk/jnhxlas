package com.hxht.logprocess.setting.service;

import com.hxht.logprocess.setting.dao.DictionaryMapper;
import com.hxht.logprocess.setting.model.Dictionary;
import com.hxht.logprocess.setting.model.DictionaryExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 添加字典
     */
    public int addDictionary(Dictionary dictionary) {
        dictionary.setGmtCreate(new Date());
        dictionary.setGmtModified(new Date());
        dictionary.setId(UUID.randomUUID().toString());
        return dictionaryMapper.insertSelective(dictionary);

    }


    /**
     * 修改字典
     */
    public int updateDictionary(Dictionary dictionary) {
        dictionary.setGmtModified(new Date());
        return dictionaryMapper.updateByPrimaryKeySelective(dictionary);
    }

    /**
     * 删除字典
     */
    public int deleteDictionary(String id) {

        return dictionaryMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param dictionary 字典
     * @return long 总数
     */
    public long countByExample(Dictionary dictionary) {
        DictionaryExample dictionaryExample = new DictionaryExample();
        DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
        if (StringUtils.isNotBlank(dictionary.getDictKey())) {
            criteria.andDictKeyEqualTo(dictionary.getDictKey().trim());
        }
        return dictionaryMapper.countByExample(dictionaryExample);
    }


    /**
     * @param dictionary 字典
     * @param order      查询排序 默认以创建时间倒序
     * @param from       起始页
     * @param size       分页大小
     * @return List<Dictionary>
     */
    public List<Dictionary> selectDictionaryByExample(Dictionary dictionary, boolean order, int from, int size) {
        DictionaryExample dictionaryExample = new DictionaryExample();
        if (order) {
            dictionaryExample.setOrderByClause("gmt_create asc limit    " + from + "," + size);//根据某一字段进行排序
        } else {
            dictionaryExample.setOrderByClause("gmt_create desc limit    " + from + "," + size);
        }
        if (dictionary != null) {
            DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
            if (StringUtils.isNotBlank(dictionary.getDictValue())) {
                criteria.andDictValueEqualTo(dictionary.getDictValue().trim());
            }
            if (StringUtils.isNotBlank(dictionary.getDictKey())) {
                criteria.andDictKeyEqualTo(dictionary.getDictKey().trim());
            }
        }
        return dictionaryMapper.selectByExample(dictionaryExample);
    }

    /**
     * 通過id查詢字典詳情
     *
     * @param id id
     * @return Dictionary
     */
    public Dictionary selectById(String id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }


}
