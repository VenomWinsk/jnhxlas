package com.hxht.logprocess.rolemanage.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.rolemanage.model.RoleMenu;
import com.hxht.logprocess.rolemanage.model.RoleMenuDto;
import com.hxht.logprocess.rolemanage.service.RoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/server/rolemenu")
public class RoleMenuController {

    private static Logger logger = LogManager.getLogger(RoleMenuController.class);
    @Autowired
    private RoleMenuService roleMenuService;


    @RequestMapping(value = "/addRoleMenu", method = RequestMethod.PUT)
    public ResMessage addRoleMenu(@RequestBody RoleMenuDto roleMenuDto) {

        RoleMenu roleMenu = new RoleMenu();
        try {
            BeanUtils.copyProperties(roleMenuDto, roleMenu);
        } catch (Exception e) {
            logger.error("roleMenu实体类转换失败", e.fillInStackTrace());
        }
        //判断角色id和菜单id不能为空
        if (StringUtils.isNotBlank(roleMenuDto.getRoleId()) && StringUtils.isNotBlank(roleMenuDto.getMenuId())) {
            //保存所要添加的内容
            roleMenu.setId(UUID.randomUUID().toString());
            int m = roleMenuService.addRoleMenu(roleMenu);
            if (m > 0) {
                return ResMessage.genSucessMessage("添加角色菜单关联成功", m);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加角色菜单失败", m);
            }
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "角色菜单不能为空", null);
        }

    }


    @RequestMapping(value = "/updateRoleMenu", method = RequestMethod.POST)
    public ResMessage updateRoleMenu(@RequestBody RoleMenuDto roleMenuDto) {
        RoleMenu roleMenu = new RoleMenu();
        try {
            BeanUtils.copyProperties(roleMenuDto, roleMenu);
        } catch (Exception e) {
            logger.error("roleMenu实体类转换失败", e.fillInStackTrace());
        }
        //判断角色id和菜单id不能为空
        if (StringUtils.isNotBlank(roleMenuDto.getRoleId()) && StringUtils.isNotBlank(roleMenuDto.getMenuId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "角色菜单不能为空", roleMenuDto);
        }
        int m = roleMenuService.updateRoleById(roleMenu);
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("修改成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", null);
        }
    }


    @RequestMapping(value = "/deleteRoleMenu", method = RequestMethod.POST)
    public ResMessage deleteRoleMenu(@RequestBody RoleMenuDto roleMenuDto) {

        //判断角色id不能为空
        if (StringUtils.isNotBlank(roleMenuDto.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数Id为空", roleMenuDto);
        }
        int m = roleMenuService.deleteById(roleMenuDto.getId());
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("删除成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除失败", null);
        }
    }
}
