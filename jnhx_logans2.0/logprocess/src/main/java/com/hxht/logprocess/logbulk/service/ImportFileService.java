package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.*;
import com.hxht.logprocess.logbulk.model.*;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ImportFileService {



    @Autowired
    private ImportFileMapper importFileMapper;

    @Autowired
    private  ProjectService projectService;

    @Autowired
    private ProjectAnalysisMapperMapper projectAnalysisMapperMapper;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;

    @Autowired
    private RuleGroupMapper ruleGroupMapper;

    @Autowired
    private ImportFileMapper importFileService;


    @Autowired
    private UsedRuleGroupMapper usedRuleGroupMapper;

    @Autowired
    private ProgressMapper progressMapper;


    @Autowired
    private DirRecordsMapper dirRecordsMapper;


    @Autowired
    private FileRecordsMapper fileRecordsMapper;

    /**
     * 修改导入文件记录
     */
    public int  updateImportFile(ImportFile importFile){
        return  importFileMapper.updateByPrimaryKeySelective(importFile);
    }





    /**
     * 查询导入文件记录
     */
    public List<ImportFile> searchImportFile(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  importFileMapper.selectByConditions(from,size);
    }




    /**
     * 删除导入文件记录
     */
    public int deleteImportFile(String id){

        return  importFileMapper.deleteByPrimaryKey(id);
    }


    /**
     * 查询导入文件的相关信息
     */
    public List<Project>  searchBulkFileMessage(){
        DecimalFormat df = new DecimalFormat("0.00");
        //查询所有的项目
        List<Project> projectList = projectService.searchAllProject();
        for (Project project:projectList){
            List<ProjectAnalysisMapper> objectList =new ArrayList();
            //通过项目对象关系表查询项目相关的对象id
            double objectPrimaryNum = 0;
            double objectTotalNum=0;
            List<ProjectAnalysisMapper> pList =projectAnalysisMapperMapper.selectByProjectId(project.getId());
            for (ProjectAnalysisMapper projectAnalysisMapper:pList){
                List<UsedRuleGroup> ruleGroupList =new ArrayList();
                //通过对象查询规则组
                List<UsedRuleGroup> ruleList = usedRuleGroupMapper.selectByProjectAnalysisId(projectAnalysisMapper.getId());
                double primaryNum = 0;
                double totalNum=0;
                for (UsedRuleGroup usedRuleGroup:ruleList){
//                    Progress progress = progressMapper.selectByUsedRuleGroupId(usedRuleGroup.getId());
//                    if (progress!=null){
//                        usedRuleGroup.setType("ruleGroup");
//                        //usedRuleGroup.setStatusStr(this.getStatusChinese(usedRuleGroup.getStatus()));
//                        double d = (double) progress.getNormalFileHandle()/progress.getFileTotal()*100;
//                        double d1 = (double) progress.getPrimaryFileHandle()/progress.getPrimaryFileTotal()*100;
//                        if (progress.getFileTotal() == 0 ){
//                            d=0;
//                        }
//                        if (progress.getPrimaryFileTotal()==0){
//                            d1=0;
//                        }
//                        if (Double.isNaN(d)){
//                            usedRuleGroup.setTotalPro(0.0);
//                        }else {
//                            usedRuleGroup.setTotalPro(Double.valueOf(df.format(d)));
//
//                        }
//                        if (Double.isNaN(d1)){
//                            usedRuleGroup.setPrimaryPro(0.0);
//                        }else {
//                            usedRuleGroup.setPrimaryPro(Double.valueOf(df.format(d1)));
//
//                        }
//                        ruleGroupList.add(usedRuleGroup);
//                        if (Double.isNaN(d1)){
//                            primaryNum+=0.0;
//                        }else {
//                            primaryNum+=Double.valueOf(df.format(d1));
//
//                        }
//                        if (Double.isNaN(d)){
//                            totalNum+=0.0;
//                        }else {
//                            totalNum+=Double.valueOf(df.format(d));
//
//                        }
//                    }else {
//                        usedRuleGroup.setType("ruleGroup");
//                        usedRuleGroup.setPrimaryPro(0.0);
//                        usedRuleGroup.setTotalPro(0.0);
//                        //usedRuleGroup.setStatusStr(this.getStatusChinese(usedRuleGroup.getStatus()));
//                        ruleGroupList.add(usedRuleGroup);
//                    }
                    List<DirRecords> reDirList =new ArrayList();
                    double reDirNum=0;
                    //查询规则组下的文件夹
                    List<DirRecords> dirList = dirRecordsMapper.selectByUsedGroupId(usedRuleGroup.getId());
                    for (DirRecords dirRecords:dirList){
                        List<FileRecords> refileList =new ArrayList();
                        //查询文件夹下的文件     改到文件详情中显示
//                        List<FileRecords> fileList = fileRecordsMapper.selectByDirId(dirRecords.getId());
//                        for (FileRecords fileRecords:fileList){
//                            fileRecords.setType("fileRecords");
//                            fileRecords.setName(fileRecords.getFilename());
//                            fileRecords.setStatusStr(this.getStatusChinese(fileRecords.getRunStatus()));
//                            refileList.add(fileRecords);
//                        }
                        dirRecords.setName(dirRecords.getDirname());
                        dirRecords.setType("dirRecords");
                        //文件显示    改到文件详情中显示
                        //dirRecords.setChildren(refileList);
                        double d = (double) (dirRecords.getProcessednums()==null?0:dirRecords.getProcessednums())/(dirRecords.getFilenums()-(dirRecords.getJumpnums()==null?0:dirRecords.getJumpnums()))*100;
                        if (dirRecords.getFilenums() == 0 ){
                            d=0;
                        }
                        if (Double.isNaN(d)){
                            dirRecords.setTotalPro(0.0);
                        }else {
                            dirRecords.setTotalPro(Double.valueOf(df.format(d)));
                        }
                        dirRecords.setStatusStr(this.getStatusChinese((dirRecords.getDirStatus()==null?1:dirRecords.getDirStatus())));
//                        dirRecords.setChildren(refileList);
                        reDirList.add(dirRecords);
                        if (Double.isNaN(d)){
                            reDirNum+=0.0;
                        }else {
                            reDirNum+=Double.valueOf(df.format(d));
                        }
                    }
                    if (Double.isNaN(reDirNum/dirList.size())){
                        usedRuleGroup.setTotalPro(0.0);
                    }else {
                        usedRuleGroup.setTotalPro(Double.valueOf(df.format(reDirNum/dirList.size())));
                    }
                    usedRuleGroup.setType("ruleGroup");
                    //usedRuleGroup.setStatusStr(this.getStatusChinese(usedRuleGroup.getStatus()));
                    usedRuleGroup.setChildren(reDirList);
                    ruleGroupList.add(usedRuleGroup);
                    totalNum+=usedRuleGroup.getTotalPro();
                }
                projectAnalysisMapper.setGmtCreate(projectAnalysisMapper.getCreateAt());
                projectAnalysisMapper.setGmtModified(projectAnalysisMapper.getUpdateAt());
                projectAnalysisMapper.setName(projectAnalysisMapper.getObjectName());
                projectAnalysisMapper.setType("object");

                if (Double.isNaN(primaryNum/ruleList.size())){
                    projectAnalysisMapper.setPrimaryPro(0.0);
                }else {
                    projectAnalysisMapper.setPrimaryPro(Double.valueOf(df.format(primaryNum/ruleList.size())));
                }
                if (Double.isNaN(primaryNum/ruleList.size())){
                    projectAnalysisMapper.setTotalPro(0.0);
                }else {
                    projectAnalysisMapper.setTotalPro(Double.valueOf(df.format(totalNum/ruleList.size())));
                }
                //projectAnalysisMapper.setStatusStr(this.getStatusChinese(projectAnalysisMapper.getStatus()));
                projectAnalysisMapper.setChildren(ruleGroupList);
                objectList.add(projectAnalysisMapper);
                objectPrimaryNum+=projectAnalysisMapper.getPrimaryPro();
                objectTotalNum+=projectAnalysisMapper.getTotalPro();
            }
            project.setType("project");
            if (Double.isNaN(objectPrimaryNum/pList.size())){
                project.setPrimaryPro(0.0);
            }else {
                project.setPrimaryPro(Double.valueOf(df.format(objectPrimaryNum/pList.size())));
            }
            if (Double.isNaN(objectTotalNum/pList.size())){
                project.setTotalPro(0.0);
            }else {
                project.setTotalPro(Double.valueOf(df.format(objectTotalNum/pList.size())));
            }
            project.setStatusStr(this.getStatusChinese(project.getStatus()));
            project.setChildren(objectList);
        }
        return projectList;

    }


    /**
     * 修改状态
     */
    public String getStatusChinese(Integer statusCode){

        String statusStr = "";
        switch (statusCode){
            case 0:
                statusStr ="默认状态";
                break;
            case 1:
                statusStr ="等待开始";
                break;
            case 10:
                statusStr ="运行中";
                break;
            case 100:
                statusStr ="完成";
                break;
            case -1:
                statusStr ="失败";
                break;
            case -2:
                statusStr ="暂不执行";
                break;
            case -3:
                statusStr ="暂停运行";
                break;
            case -4:
                statusStr ="跳过";
                break;
            case -10:
                statusStr ="取消";
                break;
            case -11:
                statusStr ="取消成功";
                break;
            default:
                statusStr ="";
                break;
        }

        return statusStr;
    }
}
