package com.hxht.logprocess.setting.service;

import com.hxht.logprocess.setting.dao.InterfaceMapper;
import com.hxht.logprocess.setting.model.Interface;
import com.hxht.logprocess.setting.model.InterfaceExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InterfaceService {

    @Autowired
    private InterfaceMapper interfaceMapper;

    /**
     * 添加字典
     */
    public int addInterface(Interface interfaces) {
        interfaces.setGmtCreate(new Date());
        interfaces.setGmtModified(new Date());
        interfaces.setId(UUID.randomUUID().toString());
        return interfaceMapper.insertSelective(interfaces);

    }


    /**
     * 修改字典
     */
    public int updateInterface(Interface interfaces) {
        interfaces.setGmtModified(new Date());
        return interfaceMapper.updateByPrimaryKeySelective(interfaces);
    }

    /**
     * 删除字典
     */
    public int deleteInterface(String id) {

        return interfaceMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param dictionary 字典
     * @return long 总数
     */
    public long countByExample(Interface interfaces) {
        InterfaceExample interfaceExample = new InterfaceExample();
        InterfaceExample.Criteria criteria = interfaceExample.createCriteria();
        if (StringUtils.isNotBlank(interfaces.getName())) {
            criteria.andNameEqualTo(interfaces.getName().trim());
        }
        return interfaceMapper.countByExample(interfaceExample);
    }

    /**
     * 通過id查詢接口詳情
     *
     * @param id id
     * @return Interface
     */
    public Interface selectById(String id) {
        return interfaceMapper.selectByPrimaryKey(id);
    }

    public List<Interface> selectInterfaceByExample(Interface interfaces, boolean order, int from, int size) {
        InterfaceExample interfaceExample = new InterfaceExample();
        if (order) {
            interfaceExample.setOrderByClause("gmt_create asc limit   " + from + "," + size);//根据某一字段进行排序
        } else {
            interfaceExample.setOrderByClause("gmt_create desc limit   " + from + "," + size);
        }
        InterfaceExample.Criteria criteria = interfaceExample.createCriteria();
        if (StringUtils.isNotBlank(interfaces.getName())) {
            criteria.andNameEqualTo(interfaces.getName().trim());
        }
        return interfaceMapper.selectByExample(interfaceExample);
    }


    /**
     * 根据名称查询
     */
    public  Interface  selectByName(String name){

        return interfaceMapper.selectByName(name);

    }



}
