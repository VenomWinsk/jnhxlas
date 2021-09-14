package com.hxht.logprocess.setting.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.setting.model.Field;
import com.hxht.logprocess.setting.service.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/server/field")
public class FieldController {


    @Autowired
    private FieldService fieldService;

    /**
     * 添加字段
     */
    @RequestMapping("addField")
    public ResMessage addField(@RequestBody Field field) {
        try {

//            if (field.getCname() != null) {
//                int num = fieldService.selectTotalByCname(field.getCname().trim(), field.getChannelId());
//                if (num > 0) {
//                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字段中文名重复", null);
//                }
//            }
//            if (field.getEname() != null) {
//                int num = fieldService.selectTotalByEname(field.getEname().trim(), field.getChannelId());
//                if (num > 0) {
//                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError, "字段英文名重复", null);
//                }
//            }
            int n = fieldService.addField(field);
            if (n > 0) {
                return ResMessage.genSucessMessage("添加成功", null);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "添加失败", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "添加异常", e.getMessage());
        }
    }


//    /**
//     * 克隆字段
//     */
//    @RequestMapping("cloningField")
//    public ResMessage cloningField(@RequestParam String oldChannelId,@RequestParam String newChannelId){
//        try {
//            fieldService.cloningField(oldChannelId,newChannelId);
//            return ResMessage.genSucessMessage("添加成功",null);
//        }catch (Exception e){
//            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"添加异常",e.getMessage());
//        }
//    }


    /**
     * 修改字段
     */
    @RequestMapping("updateField")
    public ResMessage updateField(@RequestBody Field field) {
        try {
            //通过id查询单位信息
            Field field1 = fieldService.selectById(field.getId());
//            if (field.getEname() != null) {
//                int num = fieldService.selectTotalByEname(field.getEname().trim(), field.getChannelId());
//                if (num > 0 && !field1.getEname().equals(field.getEname())) {
//                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "字段中文名重复", null);
//
//                }
//            }
//            if (field.getCname() != null) {
//                int cnNum = fieldService.selectTotalByCname(field.getCname().trim(), field.getChannelId());
//                if (cnNum > 0 && !field1.getCname().equals(field.getCname())) {
//                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError, "字段英文名重复", null);
//
//                }
//            }
            int n = fieldService.updateField(field);
            if (n > 0) {
                return ResMessage.genSucessMessage("修改成功", null);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "修改异常", e.getMessage());
        }
    }


//    /**
//     * 查询字段
//     */
//    @RequestMapping("searchField")
//    public ResMessage searchField(@RequestParam int page, @RequestParam int size, @RequestParam String channelId) {
//        try {
//            List<Field> list = fieldService.searchField(page, size, channelId);
//            Long total = fieldService.searchTotal(channelId);
//            if (list.size() > 0) {
//                return ResQueryMessage.genSucessMessage("查询成功", list, list.size(), total);
//            } else {
//                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
//            }
//        } catch (Exception e) {
//            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
//        }
//    }

    /**
     * 查询字段
     */
    @RequestMapping("selectByExample")
    public ResMessage selectByExample(@RequestParam int page, @RequestParam int size, @RequestParam int ownerType,
                                      @RequestParam String objectId, @RequestParam String ruleGroupId, @RequestParam String ruleId) {
        Field field = new Field();
        field.setOwnerType(ownerType);
        field.setObjectId(objectId);
        field.setRuleGroupId(ruleGroupId);
        field.setRuleId(ruleId);
        long m = fieldService.countByExample(field);
        List<Field> list = fieldService.selectDictionaryByExample(field, true, page, size);
        if (list.size() > 0) {
            return ResQueryMessage.genSucessMessage("查询成功", list, list.size(), m);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
        }
    }


    /**
     * 根据id和树的层级查询字段
     * @param id
     * @param ownerType 树的层级
     */
    @RequestMapping("searchFileById")
    public ResMessage searchFileById(@RequestParam String id,@RequestParam Integer ownerType){

        List<Field> list = fieldService.searchFileById(id,ownerType);
        if (list.size() > 0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
        }
    }




    /**
     * 查询字段
     */
    @RequestMapping("searchAllFieldByChannelId")
    public ResMessage searchAllFieldByChannelId(@RequestParam String channelId) {
        try {
            List<Field> list = fieldService.searchAll(channelId);
            if (list.size() > 0) {
                return ResMessage.genSucessMessage("查询成功", list);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }
    }


    /**
     * 根据id查询字段
     */
    @RequestMapping("searchFieldById")
    public ResMessage searchFieldById(@RequestParam String id) {
        try {
            Field field = fieldService.selectFieldById(id);
            if (field != null) {
                return ResMessage.genSucessMessage("查询成功", field);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData, "查询为空", null);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "查询异常", e.getMessage());
        }
    }


    /**
     * 删除字段
     */
    @DeleteMapping("deleteField")
    public ResMessage deleteField(@RequestParam String id) {
        try {
            int n = fieldService.deleteField(id);
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
     * 批量更新
     *
     * @param params
     * @return
     */
    @RequestMapping("/bathUpdate")
    @ResponseBody
    public ResMessage bathUpdate(@RequestBody Map params) {

        if (!params.containsKey("list")) return ResMessage.genErrorMessage(201, "传入的参数为空", null);
        List<Field> list = (List) params.get("list");
        try {
            int n = fieldService.bathUpdate(list);
            if (n > 0) {
                return ResMessage.genSucessMessage("修改成功", n);
            } else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "修改失败", null);
            }
        } catch (Exception e) {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError, "修改异常", e.getMessage());
        }

    }

}
