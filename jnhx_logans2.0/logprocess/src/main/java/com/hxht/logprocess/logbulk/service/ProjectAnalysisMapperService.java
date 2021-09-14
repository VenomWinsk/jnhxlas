package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.ProjectAnalysisMapperMapper;
import com.hxht.logprocess.logbulk.dao.UnitMapper;
import com.hxht.logprocess.logbulk.model.ProjectAnalysisMapper;
import com.hxht.logprocess.logbulk.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectAnalysisMapperService {


    @Autowired
    private ProjectAnalysisMapperMapper projectAnalysisMapperMapper;



    /**
     * 添加项目对象关系
     */
    public int  addProjectAnalysisMapper(ProjectAnalysisMapper projectAnalysisMapper){
        projectAnalysisMapper.setId(UUID.randomUUID().toString());
        return  projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);

    }


    /**
     * 修改项目对象关系
     */
    public int  updateProjectAnalysisMapper(ProjectAnalysisMapper projectAnalysisMapper){
        return  projectAnalysisMapperMapper.updateByPrimaryKeySelective(projectAnalysisMapper);
    }



    /**
     * 修改状态
     */
    public int  updateStatus(ProjectAnalysisMapper projectAnalysisMapper){
        return  projectAnalysisMapperMapper.updateByStatus(projectAnalysisMapper);
    }



    /**
     * 查询项目对象关系
     */
    public List<ProjectAnalysisMapper> searchProjectAnalysisMapper(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  projectAnalysisMapperMapper.selectByExample(from,size);
    }




    /**
     * 删除项目对象关系
     */
    public int deleteProjectAnalysisMapper(String id){

        return  projectAnalysisMapperMapper.deleteByPrimaryKey(id);
    }
}
