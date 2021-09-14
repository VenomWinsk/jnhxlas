package com.hxht.logprocess.usermange.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.util.MD5;
import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.usermange.model.User;
import com.hxht.logprocess.usermange.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/server/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResMessage addUser(@RequestBody User user) {

        //判断用户名和密碼不能为空
        if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
            long num = userService.selectUserCount(user);
            if (num > 0) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "账号已存在", null);
            }
            //保存所要添加的内容
            user.setId(UUID.randomUUID().toString());
            //将密码转为MD5格式
            MD5 md5 = new MD5();
            String passWord = md5.getMD5ofStr(user.getPassword());
            user.setPassWord(passWord);
            int m = userService.addUser(user);
            if (m > 0) {
                return ResMessage.genSucessMessage("添加用户成功", m);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加用户失败", m);
            }
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "用户名不能为空", null);
        }

    }



    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResMessage updateUser(@RequestBody User user) {

        //判断用户id不能为空
        if (StringUtils.isNotBlank(user.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数userId为空", user);
        }
        int m = userService.updateUserByUserId(user);
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("修改用户成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改用户失败", null);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResMessage deleteUser(@RequestBody User user) {

        //判断用户id不能为空
        if (StringUtils.isNotBlank(user.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数userId为空", user);
        }
//        int m = userService.deleteByUserId(user.getId());
        int m = userService.deleteUserAndRoleByUserId(user.getId());
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("删除用户成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除用户失败", null);
        }
    }


    @RequestMapping(value = "/selectUserById")
    public ResMessage selectUserById(@RequestBody Map params) {

        String id = (String) params.get("id");
        if (!params.containsKey("id")) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "参数接收异常", null);
        }
        User user = userService.getUserById(id);
        if (null != user) {
            return ResMessage.genSucessMessage("查询成功", user);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }


    @RequestMapping("/searchUser")
    public ResMessage searchUser(@RequestParam User user, @RequestParam int page, @RequestParam int size) {

        List<User> list = userService.getUserList(user, page, size);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }

    /**
     * 通過用戶查看角色
     * @param userId 用戶id
     * @param page 頁碼
     * @param size 大小
     * @return
     */
    @RequestMapping("/searchRoleByUserId")
    public ResMessage searchRoleByUserId(@RequestParam String userId, @RequestParam Integer page, @RequestParam Integer size) {
        User user = new User();
        user.setId(userId);
        List<Role> list = userService.getRoleByUser(user, page, size);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }


}
