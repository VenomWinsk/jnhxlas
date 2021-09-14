package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.core.util.CopyBeansUtils;
import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.dao.RuleGroupMapper;
import com.hxht.logprocess.logbulk.model.*;
import com.hxht.logprocess.logbulk.service.AnalysisObjectService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/server/analysisObject")
public class AnalysisObjectController {




    @Autowired
    private AnalysisObjectService analysisObjectService;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;



    /**
     * 添加分析对象
     */
    @RequestMapping("addAnalysisObject")
    public ResMessage addAnalysisObject(@RequestBody AnalysisObject analysisObject){
        try {
            if (analysisObject.getObjectName()!=null){
                int num = analysisObjectService.selectTotalByCname(analysisObject.getObjectName().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"分析对象重复",null);
                }
            }
            if (analysisObject.getCode()!=null){
                int num = analysisObjectService.selectTotalByCode(analysisObject.getCode().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"分析对象code重复",null);
                }
            }
            int n = analysisObjectService.addAnalysisObject(analysisObject);
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
     * 修改分析对象
     */
    @RequestMapping("updateAnalysisObject")
    public ResMessage updateAnalysisObject(@RequestBody AnalysisObject analysisObject){
        try {

            //通过id查询单位信息
            AnalysisObject object = analysisObjectService.selectById(analysisObject.getId());
            if (analysisObject.getObjectName()!=null){
                int num = analysisObjectService.selectTotalByCname(analysisObject.getObjectName().trim());
                if (num>0 && !object.getObjectName().equals(analysisObject.getObjectName())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"分析对象重复",null);
                }
            }
            if (analysisObject.getCode()!=null){
                int num = analysisObjectService.selectTotalByCode(analysisObject.getCode().trim());
                if (num>0 && !object.getCode().equals(analysisObject.getCode())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"分析对象code重复",null);
                }
            }
            int n = analysisObjectService.updateAnalysisObject(analysisObject);
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
     * 通过单位查询分析对象
     */
    @RequestMapping("selectAnalyObjectByUnit")
    public ResMessage selectAnalyObjectByUnit(@RequestParam String name){
        try {
            List<String>list  = analysisObjectService.selectAnalyObjectByUnit(name);
            if(list.size()>0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"查询失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }



    /**
     * 通过分析对象名称获取整个对象
     */
    @RequestMapping("selectByObjectName")
    public ResMessage selectByObjectName(@RequestParam String name){
        try {
            AnalysisObject analysisObject = analysisObjectMapper.selectByObjectName(name);
            if(analysisObject!=null){
                return ResMessage.genSucessMessage("查询成功",analysisObject);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"查询失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }







    /**
     * 查询分析对象
     */
    @RequestMapping("searchAnalysisObject")
    public ResMessage searchAnalysisObject(@RequestBody AnalysisObject analysisObject){
        try {
            List<AnalysisObject> list = analysisObjectService.searchAnalysisObject(analysisObject);
            List<AnalysisObjectDTO> dtoObjectList  = CopyBeansUtils.copyListProperties(list, AnalysisObjectDTO::new);
            Long total = analysisObjectService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",dtoObjectList,dtoObjectList.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",list);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }

    /**
     * 查询未绑定的分析对象
     */
    @RequestMapping("searchNoBindAnalysisObject")
    public ResMessage searchNoBindAnalysisObject(){
        try {
            List<AnalysisObject> list = analysisObjectService.searchNoBindAnalysisObject();
            if(list.size() > 0){
                return ResMessage.genSucessMessage("查询成功",list);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",list);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }



    /**
     * 根据id查询分析对象
     */
    @RequestMapping("searchAnalysisObjectById")
    public ResMessage searchAnalysisObjectById(@RequestParam String id){
        try {
            AnalysisObject analysisObject = analysisObjectService.selectById(id);
            if(analysisObject!=null){
                return ResMessage.genSucessMessage("查询成功",analysisObject);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }


    /**
     * 删除分析对象
     */
    @DeleteMapping("deleteAnalysisObject")
    public ResMessage deleteAnalysisObject(@RequestParam String id){
        try {
            int n  = analysisObjectService.deleteAnalysisObject(id);
            if(n > 0){
                return ResMessage.genSucessMessage("删除成功",n);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"删除失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"删除异常",e.getMessage());
        }
    }


    /**
     * 添加日志关系
     */
    @PostMapping("addLogMapper")
    public ResMessage addLogMapper(@RequestBody LogMapperParams logMapperParams){

        try {
            analysisObjectService.addLogMapper(logMapperParams.getObjectList(),logMapperParams.getRuleGroupList(),logMapperParams.getRuleList());
            return ResMessage.genSucessMessage("添加成功",null);

        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"添加异常",e.getMessage());
        }
    }



    /**
     * 保存日志关系
     */
    @PostMapping("saveLogMapper")
    public ResMessage saveLogMapper(@RequestBody LogMapperParams logMapperParams){


        try {
            analysisObjectService.saveLogMapper(logMapperParams.getObjectList(),logMapperParams.getRuleGroupList(),logMapperParams.getRuleList());
            return ResMessage.genSucessMessage("添加成功",null);

        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"添加异常",e.getMessage());
        }
    }




    /**
     * 根据项目id查询日志关系
     */
    @GetMapping("searchLogMapperByProjectId")
    public ResMessage searchLogMapperByProjectId(@RequestParam String projectId){

        try {
            List list = analysisObjectService.searchLogMapperByProjectId(projectId);
            return ResMessage.genSucessMessage("查询成功",list);

        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }

}
