package com.hxht.logprocess.buttonmanage.controller;

import com.hxht.logprocess.buttonmanage.model.Button;
import com.hxht.logprocess.buttonmanage.service.ButtonService;
import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.menumanage.model.Menu;
import com.hxht.logprocess.menumanage.model.MenuDto;
import com.hxht.logprocess.menumanage.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/server/button")
public class ButtonController {
    private static Logger logger = LogManager.getLogger(ButtonController.class);
    @Resource
    private ButtonService buttonService;


    @RequestMapping(value = "/addButton", method = RequestMethod.PUT)
    public ResMessage addButton(@RequestBody Button button) {


        //判断按钮名不能为空
        if (StringUtils.isNotBlank(button.getBtnName())) {
            long num = buttonService.selectButtonCount(button);
            if (num > 0) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "按钮已存在", null);
            }
            //保存所要添加的内容
            button.setId(UUID.randomUUID().toString());
            int m = buttonService.addButton(button);
            if (m > 0) {
                return ResMessage.genSucessMessage("添加按钮成功", m);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加按钮失败", m);
            }
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "按钮名不能为空", null);
        }

    }


    @RequestMapping(value = "/updateButton", method = RequestMethod.POST)
    public ResMessage updateButton(@RequestBody Button button) {

        //判断按钮id不能为空
        if (StringUtils.isNotBlank(button.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数Id为空", button);
        }
        int m = buttonService.updateButtonByButtonId(button);
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("修改按钮成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改按钮失败", null);
        }
    }

    @RequestMapping(value = "/deleteButton", method = RequestMethod.POST)
    public ResMessage deleteButton(@RequestBody Button button) {

        //判断菜单id不能为空
        if (StringUtils.isNotBlank(button.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数Id为空", button);
        }
        int m = buttonService.deleteByButtonId(button.getId());
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("删除按钮成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除按钮失败", null);
        }
    }


    @RequestMapping("/searchButton")
    public ResMessage searchButton(@RequestParam Button button, @RequestParam int page, @RequestParam int size) {

        List<Button> list = buttonService.getButtonList(button, page, size);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }

    @RequestMapping("/searchButtonById")
    public ResMessage searchButtonById(@RequestParam String buttonId) {

        Button button = buttonService.getButtonByButtonId(buttonId);
        if (null != button) {
            return ResMessage.genSucessMessage("查询成功", button);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }
}
