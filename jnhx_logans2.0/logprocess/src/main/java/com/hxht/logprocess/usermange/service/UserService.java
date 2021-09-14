package com.hxht.logprocess.usermange.service;


import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.usermange.dao.UserMapper;
import com.hxht.logprocess.usermange.dao.UserRoleMapper;
import com.hxht.logprocess.usermange.model.User;
import com.hxht.logprocess.usermange.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 根据用户id获取用户完全信息
     *
     * @return
     */
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.seletByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    /**
     * 根据用户
     *
     * @return int
     */
    public long selectUserCount(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(user.getUserName())) {
            criteria.andUserNameEqualTo(user.getUserName());
        }
        if (StringUtils.isNotBlank(user.getPassWord())) {
            criteria.andUserNameEqualTo(user.getPassWord());
        }
        return userMapper.countByExample(userExample);
    }

    /**
     * 添加用户
     *
     * @param user 用户表
     * @return int
     */
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }


    /**
     * 根据用户id更新用户信息
     *
     * @param user 用户表
     * @return int
     */
    public int updateUserByUserId(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户
     *
     * @param userId id
     * @return int
     */
    public int deleteByUserId(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 删除用户并清除用户角色关系
     *
     * @param userId id
     * @return int
     */
    @Transactional
    public int deleteUserAndRoleByUserId(String userId) {
        int n = userMapper.deleteByPrimaryKey(userId);
        try {
            userRoleMapper.deleteByUserId(userId);
        } catch (Exception e) {
            logger.info("清除用户角色关系异常", e.fillInStackTrace());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            n = 0;
        }
        return n;
    }

    /**
     * 获取用户列表
     *
     * @param user 用户信息
     * @param page 起始页
     * @param size 分页大小
     * @return List<User>
     */
    public List<User> getUserList(User user, int page, int size) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(user.getId())) {
            criteria.andIdEqualTo(user.getId());
        }
        if (StringUtils.isNotBlank(user.getUserName())) {
            criteria.andUserNameEqualTo(user.getUserName());
        }
        int from = page - 1;
        int pageSize = from * size;
        userExample.setOrderByClause("  create_time desc   limit " + from + " , +" + pageSize + " ");//根据某一字段进行排序,并分页
        return userMapper.selectByExample(userExample);
    }


    public List<Role> getRoleByUser(User user, Integer page, Integer size) {
        int from = (page - 1) * size;
        return userRoleMapper.getRoleByUser(user, from, size);
    }
}
