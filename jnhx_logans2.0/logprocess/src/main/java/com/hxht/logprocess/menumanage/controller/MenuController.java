package com.hxht.logprocess.menumanage.controller;

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
@RequestMapping(value = "/server/menu")
public class MenuController {
    private static Logger logger = LogManager.getLogger(MenuController.class);
    @Resource
    private MenuService menuService;


    @RequestMapping(value = "/addMenu", method = RequestMethod.PUT)
    public ResMessage addMenu(@RequestBody MenuDto menuDto) {

        Menu menu = new Menu();
        try {
            BeanUtils.copyProperties(menuDto, menu);
        } catch (Exception e) {
            logger.error("Menu实体类转换失败", e.fillInStackTrace());
        }
        //判断菜单名不能为空
        if (StringUtils.isNotBlank(menuDto.getFuncName())) {
            long num = menuService.selectMenuCount(menu);
            if (num > 0) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "菜单已存在", null);
            }
            //保存所要添加的内容
            menu.setId(UUID.randomUUID().toString());
            int m = menuService.addMenu(menu);
            if (m > 0) {
                return ResMessage.genSucessMessage("添加菜单成功", m);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加菜单失败", m);
            }
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "菜单名不能为空", null);
        }

    }


    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public ResMessage updateMenu(@RequestBody MenuDto MenuDto) {
        Menu menu = new Menu();
        try {
            BeanUtils.copyProperties(MenuDto, menu);
        } catch (Exception e) {
            logger.error("Menu实体类转换失败", e.fillInStackTrace());
        }
        //判断菜单id不能为空
        if (StringUtils.isNotBlank(menu.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数MenuId为空", MenuDto);
        }
        int m = menuService.updateMenuByMenuId(menu);
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("修改菜单成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改菜单失败", null);
        }
    }

    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    public ResMessage deleteMenu(@RequestBody MenuDto menuDto) {

        //判断菜单id不能为空
        if (StringUtils.isNotBlank(menuDto.getId())) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "传入的参数MenuId为空", menuDto);
        }
//        int m = menuService.deleteByMenuId(menuDto.getId());
        int m = menuService.deleteRoleMenuByMenuId(menuDto.getId());
        if (m > 0) {//如果修改成功
            return ResMessage.genSucessMessage("删除菜单成功", m);
        } else {//失败
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除菜单失败", null);
        }
    }


    @RequestMapping("/searchMenu")
    public ResMessage searchMenu(@RequestParam Menu menu, @RequestParam int page, @RequestParam int size) {

        List<Menu> list = menuService.getMenuList(menu, page, size);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }

    @RequestMapping("/searchMenuById")
    public ResMessage searchButtonById(@RequestParam String menuId) {

        Menu menu = menuService.getMenuByMenuId(menuId);
        if (null != menu) {
            return ResMessage.genSucessMessage("查询成功", menu);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询数据为空", null);
        }
    }
}
