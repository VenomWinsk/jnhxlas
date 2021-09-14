package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.logbulk.model.ProjectAnalysisMapper;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.service.ProjectAnalysisMapperService;
import com.hxht.logprocess.logbulk.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server/projectAnalysisMapper")
public class ProjectAnalysisMapperController {



    @Autowired
    private ProjectAnalysisMapperService projectAnalysisMapperService;

    /**
     * 添加项目对象关系
     */
    @RequestMapping("addProjectAnalysisMapper")
    public ResMessage addProjectAnalysisMapper(@RequestBody ProjectAnalysisMapper projectAnalysisMapper){
        try {
            int n = projectAnalysisMapperService.addProjectAnalysisMapper(projectAnalysisMapper);
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
     * 修改项目对象关系
     */
    @RequestMapping("updateProjectAnalysisMapper")
    public ResMessage updateProjectAnalysisMapper(@RequestBody ProjectAnalysisMapper projectAnalysisMapper){
        try {
            int n = projectAnalysisMapperService.updateProjectAnalysisMapper(projectAnalysisMapper);
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
     * 修改状态
     */
    @RequestMapping("updateStatus")
    public ResMessage updateStatus(@RequestBody ProjectAnalysisMapper projectAnalysisMapper){
        try {
            int n = projectAnalysisMapperService.updateStatus(projectAnalysisMapper);
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
     * 查询项目对象关系
     */
    @RequestMapping("searchProjectAnalysisMapper")
    public ResMessage searchProjectAnalysisMapper(@RequestParam int page, @RequestParam int size){
        try {
            List<ProjectAnalysisMapper> list = projectAnalysisMapperService.searchProjectAnalysisMapper(page,size);
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
     * 删除项目对象关系
     */
    @RequestMapping("deleteProjectAnalysisMapper")
    public ResMessage deleteProjectAnalysisMapper(@RequestParam String id){
        try {
            int n  = projectAnalysisMapperService.deleteProjectAnalysisMapper(id);
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
