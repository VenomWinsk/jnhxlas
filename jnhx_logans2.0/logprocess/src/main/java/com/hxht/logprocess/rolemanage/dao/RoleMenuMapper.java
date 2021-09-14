package com.hxht.logprocess.rolemanage.dao;

import com.hxht.logprocess.menumanage.model.Menu;
import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.rolemanage.model.RoleMenu;
import com.hxht.logprocess.rolemanage.model.RoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuMapper {
    long countByExample(RoleMenuExample example);

    int deleteByExample(RoleMenuExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<RoleMenu> selectByExample(RoleMenuExample example);

    RoleMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByExample(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    List<Menu> getMenuByRole(Role role, Integer from, Integer size);

    int deleteByRoleId(String roleId);

    int deleteByMenuId(String menuId);
}
