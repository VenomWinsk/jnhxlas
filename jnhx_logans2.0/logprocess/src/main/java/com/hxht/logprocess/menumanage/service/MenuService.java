package com.hxht.logprocess.menumanage.service;

import com.hxht.logprocess.buttonmanage.dao.ButtonMapper;
import com.hxht.logprocess.menumanage.dao.MenuMapper;
import com.hxht.logprocess.menumanage.model.Menu;
import com.hxht.logprocess.menumanage.model.MenuExample;
import com.hxht.logprocess.rolemanage.dao.RoleMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private ButtonMapper buttonMapper;


    /**
     * 添加菜单
     *
     * @param menu 菜单表
     * @return int
     */
    public int addMenu(Menu menu) {
        return menuMapper.insertSelective(menu);
    }

    /**
     * 根据菜单d更新菜单信息
     *
     * @param menu 菜单表
     * @return int
     */
    public int updateMenuByMenuId(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 根据菜单id查询菜单信息
     *
     * @param id 菜单id
     * @return int
     */
    public Menu getMenuByMenuId(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }


    /**
     * 删除菜单
     *
     * @param menuId id
     * @return int
     */
    public int deleteByMenuId(String menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    /**
     * 删除菜单并清除角色菜单关系清除相关按钮
     *
     * @param menuId id
     * @return int
     */
    public int deleteRoleMenuByMenuId(String menuId) {
        roleMenuMapper.deleteByMenuId(menuId);
        buttonMapper.deleteByMenuId(menuId);
        return menuMapper.deleteByPrimaryKey(menuId);
    }


    /**
     * 根据菜单
     *
     * @return int
     */
    public long selectMenuCount(Menu menu) {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        if (StringUtils.isNotBlank(menu.getFuncName())) {
            criteria.andFuncNameEqualTo(menu.getFuncName());
        }
        return menuMapper.countByExample(menuExample);
    }

    /**
     * 获取菜单列表
     *
     * @param menu 菜单信息
     * @param page 起始页
     * @param size 分页大小
     * @return List<menu>
     */
    public List<Menu> getMenuList(Menu menu, int page, int size) {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        if (StringUtils.isNotBlank(menu.getId())) {
            criteria.andIdEqualTo(menu.getId());
        }
        if (StringUtils.isNotBlank(menu.getFuncName())) {
            criteria.andFuncNameEqualTo(menu.getFuncName());
        }
        int from = page - 1;
        int pageSize = from * size;
        menuExample.setOrderByClause(" create_time desc   limit " + from + " , +" + pageSize + " ");//根据某一字段进行排序,并分页
        return menuMapper.selectByExample(menuExample);
    }
}
