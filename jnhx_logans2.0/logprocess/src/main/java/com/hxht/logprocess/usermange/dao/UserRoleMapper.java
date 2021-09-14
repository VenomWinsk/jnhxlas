package com.hxht.logprocess.usermange.dao;

import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.usermange.model.User;
import com.hxht.logprocess.usermange.model.UserRole;
import com.hxht.logprocess.usermange.model.UserRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRoleMapper {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<Role> getRoleByUser(User user,Integer from,Integer size);

    int deleteByUserId(String userId);

    int deleteByRoleId(String roleId);

}
