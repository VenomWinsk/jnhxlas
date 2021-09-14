package com.hxht.logprocess.logbulk.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.model.Project;
import com.hxht.logprocess.logbulk.model.ProjectAnalysisMapper;
import com.hxht.logprocess.logbulk.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/server/project")
public class ProjectController {



    @Autowired
    private ProjectService projectService;

    /**
     * 添加项目
     */
    @RequestMapping("addProject")
    public ResMessage addProject(@RequestBody Project project){
        try {
            int n = projectService.addProject(project);
            String id = project.getId();
            if (n >0){
                return ResMessage.genSucessMessage("添加成功",id);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"添加失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"添加异常",e.getMessage());
        }
    }




    /**
     * 修改项目
     */
    @RequestMapping("updateProject")
    public ResMessage updateProject(@RequestBody Project project){
        try {
            int n = projectService.updateProject(project);
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
    @PostMapping("modifyState")
    public ResMessage modifyState(@RequestBody Map<String,String> param){
        try {
            boolean result = projectService.modifyState(param.get("id"));
            if (result){
                return ResMessage.genSucessMessage("修改成功",null);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"修改失败",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }



    /**
     * 查询项目
     */
    @RequestMapping("searchProject")
    public ResMessage searchProject(@RequestBody Project project){
        try {
            List<Project> list = projectService.searchProject(project);
            Long total = projectService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",list,list.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }


    /**
     * 查询是否有正在导入的项目
     */
    @RequestMapping("searchBulkProject")
    public ResMessage searchBulkProject(){
        try {
            boolean flag = projectService.searchBulkProject();
            if (flag){
                return ResMessage.genSucessMessage("无正在导入的项目",flag);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError,"有正在导入的项目",flag);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }







    /**
     * 根据id查询项目
     */
    @RequestMapping("searchProjectById")
    public ResMessage searchProjectById(@RequestParam String id){
        try {
            Project project = projectService.searchProjectById(id);
            if(project!=null){
                return ResMessage.genSucessMessage("查询成功",project);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"查询异常",e.getMessage());
        }
    }



    /**
     * 删除项目
     */
    @RequestMapping("deleteProject")
    public ResMessage deleteProject(@RequestParam String id){
        try {
            projectService.deleteProject(id);
            return ResMessage.genSucessMessage("删除成功",null);

        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"删除异常",e.getMessage());
        }
    }


}




