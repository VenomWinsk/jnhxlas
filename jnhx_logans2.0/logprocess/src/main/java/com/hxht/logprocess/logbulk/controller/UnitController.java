package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.core.util.CopyBeansUtils;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.model.UnitDTO;
import com.hxht.logprocess.logbulk.service.UnitAnalyObjectService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/unit")
public class UnitController {


    @Autowired
    private UnitService unitService;
    @Autowired
    private UnitAnalyObjectService unitAnalyObjectService;


    /**
     * 添加单位
     */
    @RequestMapping("addUnit")
    public ResMessage addUnit(@RequestBody Unit unit){
        try {

            if (unit.getCname()!=null){
                int num = unitService.selectTotalByCname(unit.getCname().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"单位名称重复",null);
                }
            }
            if (unit.getEname()!=null){
                int num = unitService.selectTotalByEname(unit.getEname().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"单位别名重复",null);
                }
            }
            int n = unitService.addUnit(unit);
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
     * 修改单位
     */
    @RequestMapping("updateUnit")
    public ResMessage updateUnit(@RequestBody Unit unit){
        try {
            //通过id查询单位信息
            Unit unit1 = unitService.selectById(unit.getId());
            if (unit.getEname()!=null){
                int num = unitService.selectTotalByEname(unit.getEname().trim());
                if (num>0 && !unit1.getEname().equals(unit.getEname())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"单位名称重复",null);

                }
            }
            if (unit.getCname()!=null){
                int cnNum = unitService.selectTotalByCname(unit.getCname().trim());
                if (cnNum>0 && !unit1.getCname().equals(unit.getCname())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"单位别名重复",null);

                }
            }
            int n = unitService.updateUnit(unit);
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
     * 查询单位
     */
    @RequestMapping("searchUnit")
    public ResMessage searchUnit(@RequestBody Unit unit){
        try {
            List<Unit> list = unitService.searchUnit(unit);
            List<UnitDTO> dtoList  = CopyBeansUtils.copyListProperties(list, UnitDTO::new);
            Long total = unitService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",dtoList,dtoList.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }



    /**
     * 删除单位
     */
    @DeleteMapping("deleteUnit")
    public ResMessage deleteUnit(@Param("id") String id){
        try {
            int n  = unitService.deleteUnit(id);
            //删除单位的时候将单位分析对象关系删除掉
            unitAnalyObjectService.deleteByUnitId(id);
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
