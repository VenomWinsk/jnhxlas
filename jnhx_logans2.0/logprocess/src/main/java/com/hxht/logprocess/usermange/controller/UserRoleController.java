package com.hxht.logprocess.usermange.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.util.MD5;
import com.hxht.logprocess.usermange.model.UserRole;
import com.hxht.logprocess.usermange.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/server/userrole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
    public ResMessage addUserRole(@RequestBody UserRole userRole) {

        //判断用户名和密碼不能为空
        if (StringUtils.isNotBlank(userRole.getRoleId()) && StringUtils.isNotBlank(userRole.getUserId())) {

            //保存所要添加的内容
            userRole.setId(UUID.randomUUID().toString());
            int m = userRoleService.addUserRole(userRole);
            if (m > 0) {
                return ResMessage.genSucessMessage("添加用户角色成功", m);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加用户角色失败", m);
            }
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "用户和角色不能为空", null);
        }

    }


    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public ResMessage updateUserRole(@RequestBody UserRole userRole) {

        //判断id不能为空
        if (StringUtils.isNotBlank(userRole.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数Id为空", userRole);
        }
        int m = userRoleService.updateUserRoleById(userRole);
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("修改用户角色成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改用户角色失败", null);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResMessage deleteUser(@RequestBody UserRole userRole) {

        //判断id不能为空
        if (StringUtils.isNotBlank(userRole.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数Id为空", userRole);
        }
        int m = userRoleService.deleteById(userRole.getId());
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("删除用户角色成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除用户角色失败", null);
        }
    }
}
