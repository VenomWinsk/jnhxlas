package com.hxht.logprocess.rolemanage.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.menumanage.model.Menu;
import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.rolemanage.model.RoleDto;
import com.hxht.logprocess.rolemanage.service.RoleService;
import com.hxht.logprocess.usermange.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/server/role")
public class RoleController {
    private static Logger logger = LogManager.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;


    @RequestMapping(value = "/addRole", method = RequestMethod.PUT)
    public ResMessage addRole(@RequestBody RoleDto roleDto) {

        Role role = new Role();
        try {
            BeanUtils.copyProperties(roleDto, role);
        } catch (Exception e) {
            logger.error("role实体类转换失败", e.fillInStackTrace());
        }
        //判断角色名不能为空
        if (StringUtils.isNotBlank(roleDto.getRoleName())) {
            long num = roleService.selectRoleCount(role);
            if (num > 0) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "角色已存在", null);
            }
            //保存所要添加的内容
            role.setId(UUID.randomUUID().toString());
            int m = roleService.addRole(role);
            if (m > 0) {
                return ResMessage.genSucessMessage("添加角色成功", m);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加角色失败", m);
            }
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "角色名不能为空", null);
        }

    }


    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResMessage updateRole(@RequestBody RoleDto roleDto) {
        Role role = new Role();
        try {
            BeanUtils.copyProperties(roleDto, role);
        } catch (Exception e) {
            logger.error("role实体类转换失败", e.fillInStackTrace());
        }
        //判断角色id不能为空
        if (StringUtils.isNotBlank(role.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数roleId为空", roleDto);
        }
        int m = roleService.updateRoleByRoleId(role);
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("修改角色成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改角色失败", null);
        }
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public ResMessage deleteRole(@RequestBody RoleDto roleDto) {

        //判断角色id不能为空
        if (StringUtils.isNotBlank(roleDto.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数roleId为空", roleDto);
        }
//        int m = roleService.deleteByRoleId(roleDto.getId());
        int m = roleService.deleteURMByRoleId(roleDto.getId());
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("删除角色成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除角色失败", null);
        }
    }


    @RequestMapping("/searchRole")
    public ResMessage searchRole(@RequestParam Role role, @RequestParam int page, @RequestParam int size) {

        List<Role> list = roleService.getRoleList(role, page, size);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }

    /**
     * 通過角色查看菜單權限
     *
     * @param roleId 用戶id
     * @param page   頁碼
     * @param size   大小
     * @return ResMessage
     */
    @RequestMapping("/searchMenuByRoleId")
    public ResMessage searchRoleByUserId(@RequestParam String roleId, @RequestParam Integer page, @RequestParam Integer size) {
        Role role = new Role();
        role.setId(roleId);
        List<Menu> list = roleService.getMenuByRole(role, page, size);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }
}
