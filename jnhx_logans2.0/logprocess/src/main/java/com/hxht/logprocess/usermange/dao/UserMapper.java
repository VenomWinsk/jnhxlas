package com.hxht.logprocess.usermange.dao;

import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.usermange.model.User;
import com.hxht.logprocess.usermange.model.UserExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User seletByUserName(String userName);

    User getUserById(String id);

    long countByExample(UserExample example);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int deleteByPrimaryKey(String id);

    List<User> selectByExample(UserExample example);

}
