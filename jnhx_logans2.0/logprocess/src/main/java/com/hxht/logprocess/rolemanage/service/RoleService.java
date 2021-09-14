package com.hxht.logprocess.rolemanage.service;

import com.hxht.logprocess.menumanage.model.Menu;
import com.hxht.logprocess.rolemanage.dao.RoleMenuMapper;
import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.rolemanage.dao.RoleMapper;
import com.hxht.logprocess.rolemanage.model.RoleExample;
import com.hxht.logprocess.usermange.dao.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Resource
    private RoleMapper RoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 添加角色
     *
     * @param role 角色表
     * @return int
     */
    public int addRole(Role role) {
        return RoleMapper.insertSelective(role);
    }

    /**
     * 根据角色id更新角色信息
     *
     * @param role 角色表
     * @return int
     */
    public int updateRoleByRoleId(Role role) {
        return RoleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 删除角色
     *
     * @param roleId id
     * @return int
     */
    public int deleteByRoleId(String roleId) {
        return RoleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * 删除角色并清除用户角色菜单关系
     *
     * @param roleId id
     * @return int
     */
    @Transactional
    public int deleteURMByRoleId(String roleId) {
        userRoleMapper.deleteByRoleId(roleId);
        roleMenuMapper.deleteByRoleId(roleId);
        return RoleMapper.deleteByPrimaryKey(roleId);
    }


    /**
     * 根据角色
     *
     * @return int
     */
    public long selectRoleCount(Role role) {
        RoleExample RoleExample = new RoleExample();
        RoleExample.Criteria criteria = RoleExample.createCriteria();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            criteria.andRoleNameEqualTo(role.getRoleName());
        }
        return RoleMapper.countByExample(RoleExample);
    }

    /**
     * 获取角色列表
     *
     * @param role 角色信息
     * @param page 起始页
     * @param size 分页大小
     * @return List<Role>
     */
    public List<Role> getRoleList(Role role, int page, int size) {
        RoleExample RoleExample = new RoleExample();
        RoleExample.Criteria criteria = RoleExample.createCriteria();
        if (StringUtils.isNotBlank(role.getId())) {
            criteria.andIdEqualTo(role.getId());
        }
        if (StringUtils.isNotBlank(role.getRoleName())) {
            criteria.andRoleNameEqualTo(role.getRoleName());
        }
        int from = page - 1;
        int pageSize = from * size;
        RoleExample.setOrderByClause(" create_time desc   limit " + from + " , +" + pageSize + " ");//根据某一字段进行排序,并分页
        return RoleMapper.selectByExample(RoleExample);
    }

    public List<Menu> getMenuByRole(Role role, Integer page, Integer size) {
        int from = (page - 1) * size;
        return roleMenuMapper.getMenuByRole(role, from, size);
    }
}
