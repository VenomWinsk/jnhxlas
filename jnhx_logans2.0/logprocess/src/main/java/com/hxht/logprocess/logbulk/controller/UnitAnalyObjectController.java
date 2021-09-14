package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.model.UnitAnalyObject;
import com.hxht.logprocess.logbulk.service.UnitAnalyObjectService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server/unitAnalyObject")
public class UnitAnalyObjectController {



    @Autowired
    private UnitAnalyObjectService unitAnalyObjectService;

    /**
     * 添加单位对象关系
     */
    @RequestMapping("addUnitAnalyObject")
    public ResMessage addUnitAnalyObject(@RequestBody UnitAnalyObject unitAnalyObject){
        try {
            int n = unitAnalyObjectService.addUnitAnalyObject(unitAnalyObject);
            if (n >0){
                return ResMessage.genSucessMessage("添加成功",null);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"添加失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"添加异常",e.getMessage());
        }
    }




    /**
     * 修改单位对象关系
     */
    @RequestMapping("updateUnitAnalyObject")
    public ResMessage updateUnitAnalyObject(@RequestBody UnitAnalyObject unitAnalyObject){
        try {
            int n = unitAnalyObjectService.updateUnitAnalyObject(unitAnalyObject);
            if (n >0){
                return ResMessage.genSucessMessage("修改成功",null);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"修改失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }




    /**
     * 查询单位对象关系
     */
    @RequestMapping("searchUnitAnalyObject")
    public ResMessage searchUnitAnalyObject(@RequestParam int page, @RequestParam int size){
        try {
            List<UnitAnalyObject> list = unitAnalyObjectService.searchUnitAnalyObject(page,size);
            if(list.size() > 0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }



    /**
     * 删除单位对象关系
     */
    @RequestMapping("deleteUnitAnalyObject")
    public ResMessage deleteUnitAnalyObject(@RequestParam String id){
        try {
            int n  = unitAnalyObjectService.deleteUnitAnalyObject(id);
            if(n > 0){
                return ResMessage.genSucessMessage("删除成功",n);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"删除失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"删除异常",e.getMessage());
        }
    }




}
