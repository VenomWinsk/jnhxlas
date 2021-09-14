package com.hxht.logprocess.usermange.service;

import com.hxht.logprocess.usermange.dao.UserRoleMapper;
import com.hxht.logprocess.usermange.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 添加用户角色
     *
     * @param userRole 用户j角色表
     * @return int
     */
    public int addUserRole(UserRole userRole) {
        return userRoleMapper.insertSelective(userRole);
    }

    /**
     * 根据id更新用户角色信息
     *
     * @param userRole 用户角色表
     * @return int
     */
    public int updateUserRoleById(UserRole userRole) {
        return userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    /**
     * 删除用户角色
     *
     * @param id id
     * @return int
     */
    public int deleteById(String id) {
        return userRoleMapper.deleteByPrimaryKey(id);
    }


}
