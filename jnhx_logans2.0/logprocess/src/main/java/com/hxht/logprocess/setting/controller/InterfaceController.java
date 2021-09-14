package com.hxht.logprocess.setting.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.setting.model.Interface;
import com.hxht.logprocess.setting.service.InterfaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/interface")
public class InterfaceController {

    @Autowired
    private InterfaceService interfaceService;

    /**
     * 添加接口
     */
    @RequestMapping("addInterface")
    public ResMessage addInterface(@RequestBody Interface interfaces) {
        try {

            if (isContains(interfaces)) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字典KEY重复", interfaces.getName());
            }
            int n = interfaceService.addInterface(interfaces);
            if (n > 0) {
                return ResMessage.genSucessMessage("添加成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加失败", n);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "添加异常", e.getMessage());
        }
    }


    /**
     * 修改接口
     */
    @RequestMapping("updateInterface")
    public ResMessage updateInterface(@RequestBody Interface interfaces) {
        try {
            if (StringUtils.isBlank(interfaces.getId())) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "字典ID为空", interfaces);
            }
            //判断是否有重复值
//            if (isContains(interfaces)) {
//                return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字典KEY重复", interfaces.getName());
//            }
            //通过id查询单位信息
            Interface anInterface1 = interfaceService.selectById(interfaces.getId());
            if (interfaces.getName()!=null){
                Long num = interfaceService.countByExample(interfaces);
                if (num>0 && !anInterface1.getName().equals(interfaces.getName())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"字典KEY重复",null);

                }
            }
            int n = interfaceService.updateInterface(interfaces);
            if (n > 0) {
                return ResMessage.genSucessMessage("修改成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", n);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "修改异常", e.getMessage());
        }
    }

    /**
     * 删除接口
     */
    @DeleteMapping("deleteInterface")
    public ResMessage deleteInterface(@RequestParam String id) {
        try {
            Interface anInterface = interfaceService.selectById(id);
            if (anInterface.getIsEnabled() == 1) {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "启用状态的不可删除", null);
            }
            int n = interfaceService.deleteInterface(id);
            if (n > 0) {
                return ResMessage.genSucessMessage("删除成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "删除失败", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "删除异常", e.getMessage());
        }
    }

    /**
     * 查询接口
     */
    @GetMapping("getInterface")
    public ResMessage getInterface(@RequestParam int from, @RequestParam int size) {
        try {
            long count = interfaceService.countByExample(new Interface());
            if (from <= 0) {
                from = 1;
            }
            List list = interfaceService.selectInterfaceByExample(new Interface(), true, from - 1, size);
            if (list.size() > 0) {
                return ResQueryMessage.genSucessMessage("查询成功", list, list, size, count);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询数据为空", list);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }
    }

    /**
     * 查询接口
     */
    @GetMapping("getInterfaceByParam")
    public ResMessage getInterfaceByParam(@RequestBody Interface interfaces, @RequestParam int from, @RequestParam int size) {
        try {
            List list = interfaceService.selectInterfaceByExample(interfaces, true, from - 1, size);
            if (list.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", list);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询数据为空", list);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "删除异常", e.getMessage());
        }
    }



    /**
     * 通过名称查询
     */
    @GetMapping("getInterfaceByName")
    public ResMessage getInterfaceByName(@RequestParam String name) {
        try {
            Interface anInterface = interfaceService.selectByName(name);
            if (anInterface!=null){
                return ResMessage.genSucessMessage("查询成功", anInterface);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询数据为空", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "删除异常", e.getMessage());
        }
    }





    private boolean isContains(Interface interfaces) {
        if (StringUtils.isNotBlank(interfaces.getName())) {
            long num = interfaceService.countByExample(interfaces);
            return num > 0;
        } else {
            return false;
        }
    }

}
