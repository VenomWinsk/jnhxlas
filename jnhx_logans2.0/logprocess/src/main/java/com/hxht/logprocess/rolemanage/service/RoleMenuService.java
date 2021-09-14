package com.hxht.logprocess.rolemanage.service;

import com.hxht.logprocess.rolemanage.dao.RoleMenuMapper;
import com.hxht.logprocess.rolemanage.model.RoleMenu;
import com.hxht.logprocess.rolemanage.model.RoleMenuExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleMenuService {

    @Resource
    private RoleMenuMapper RoleMapper;


    /**
     * 添加角色菜单关系
     *
     * @param roleMenu 角色菜单表
     * @return int
     */
    public int addRoleMenu(RoleMenu roleMenu) {
        return RoleMapper.insertSelective(roleMenu);
    }

    /**
     * 根据角色id更新角色信息
     *
     * @param roleMenu 角色菜单表
     * @return int
     */
    public int updateRoleById(RoleMenu roleMenu) {
        return RoleMapper.updateByPrimaryKeySelective(roleMenu);
    }

    /**
     * 删除角色菜单关系
     * @param roleMenuId id
     * @return int
     */
    public int deleteById(String roleMenuId) {
        return RoleMapper.deleteByPrimaryKey(roleMenuId);
    }


    /**
     * 根据角色菜单
     *
     * @return int
     */
    public long selectRoleCount(RoleMenu roleMenu) {
        RoleMenuExample roleMenuExample = new RoleMenuExample();
        RoleMenuExample.Criteria criteria = roleMenuExample.createCriteria();
        if (StringUtils.isNotBlank(roleMenu.getRoleId())) {
            criteria.andRoleIdEqualTo(roleMenu.getRoleId());
        }
        if (StringUtils.isNotBlank(roleMenu.getMenuId())) {
            criteria.andMenuIdEqualTo(roleMenu.getMenuId());
        }
        return RoleMapper.countByExample(roleMenuExample);
    }

    /**
     * 获取角色菜单列表
     * @param roleMenu 角色菜单信息
     * @param page 起始页
     * @param size 分页大小
     * @return List<RoleMenu>
     */
    public List<RoleMenu> getRoleMenuList(RoleMenu roleMenu,int page,int size){
        RoleMenuExample roleMenuExample = new RoleMenuExample();
        RoleMenuExample.Criteria criteria = roleMenuExample.createCriteria();
        if(StringUtils.isNotBlank(roleMenu.getId())){
            criteria.andIdEqualTo(roleMenu.getId());
        }
        int from = page-1;
        int pageSize = from*size;
        roleMenuExample.setOrderByClause("   create_time desc   limit "+from+" , +"+pageSize+" ");//根据某一字段进行排序,并分页
        return RoleMapper.selectByExample(roleMenuExample);
    }
}
