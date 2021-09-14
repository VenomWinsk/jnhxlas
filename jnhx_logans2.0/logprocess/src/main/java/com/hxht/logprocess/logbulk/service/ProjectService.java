package com.hxht.logprocess.logbulk.service;


import com.hxht.logprocess.logbulk.dao.*;
import com.hxht.logprocess.logbulk.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class ProjectService {


    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private ProjectAnalysisMapperMapper projectAnalysisMapperMapper;

    @Autowired
    private UsedRuleMapper usedRuleMapper;

    @Autowired
    private UsedRuleGroupMapper usedRuleGroupMapper;

    @Autowired
    private ProgressMapper progressMapper;

    @Value("${deleteDate}")
    private String deleteDate;

    @Autowired
    private DirRecordsMapper dirRecordsMapper;

    @Autowired
    private FileRecordsMapper fileRecordsMapper;
    @Autowired
    private ImportFileService importFileService;


    /**
     * 添加项目
     */
    public int addProject(Project project) {
        project.setStatus(0);
        project.setId(UUID.randomUUID().toString());
        project.setGmtCreate(new Date());
        project.setGmtModified(new Date());
        return projectMapper.insertSelective(project);

    }


    /**
     * 修改项目
     */
    public int updateProject(Project project) {
        if (project.getStatus() != null) {
            project.setStartTime(new Date());
        }
        project.setGmtModified(new Date());
        return projectMapper.updateByPrimaryKeySelective(project);
    }


    /**
     * 查询项目
     */
    public List<Project> searchProject(Project project) {
        if (project.getPage() != null && project.getSize() != null) {
            int from;
            from = (project.getPage() - 1) * project.getSize();
            project.setForm(from);
        }
        return projectMapper.selectByConditions(project);
    }


    /**
     * 查询是否有正在进行中的项目
     */
    public Boolean searchBulkProject() {
        boolean flag = true;
        List<Project> list = projectMapper.selectProject();
        for (Project project : list) {
            if (project.getStatus() == 10) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    /**
     * 查询总量
     *
     * @return
     */
    public Long searchTotal() {
        return projectMapper.selectByTotal();
    }


    /**
     * 根据id查询项目
     */
    public Project searchProjectById(String id) {
        DecimalFormat df = new DecimalFormat("0.00");
        Project project = projectMapper.selectByPrimaryKey(id);
        Unit unit = unitMapper.selectByPrimaryKey(project.getUnitId());
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
                List<DirRecords> reDirList =new ArrayList();
                double reDirNum=0;
                //查询规则组下的文件夹
                List<DirRecords> dirList = dirRecordsMapper.selectByUsedGroupId(usedRuleGroup.getId());
                for (DirRecords dirRecords:dirList){
                    List<FileRecords> refileList =new ArrayList();
                    //查询文件夹下的文件   改到文件详情下面
//                    List<FileRecords> fileList = fileRecordsMapper.selectByDirId(dirRecords.getId());
//                    for (FileRecords fileRecords:fileList){
//                        fileRecords.setType("fileRecords");
//                        fileRecords.setName(fileRecords.getFilename());
//                        fileRecords.setStatusStr(importFileService.getStatusChinese(fileRecords.getRunStatus()));
//                        refileList.add(fileRecords);
//                    }
                    dirRecords.setName(dirRecords.getDirname());
                    dirRecords.setType("dirRecords");
                    //文件显示 改到文件详情
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
                    dirRecords.setStatusStr(importFileService.getStatusChinese((dirRecords.getDirStatus()==null?1:dirRecords.getDirStatus())));
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
        project.setStatusStr(importFileService.getStatusChinese(project.getStatus()));
        project.setChildren(objectList);
        project.setUnitName(unit.getCname());
        return project;
    }


    /**
     * 删除项目
     */
    public void deleteProject(String id) {

        //0.删除文件进度表
        List<Progress> proList = progressMapper.selectByProjectId(id);
        if (proList != null && proList.size() > 0) {
            for (Progress progress : proList) {
                progressMapper.deleteByPrimaryKey(progress.getId());
            }
        }
        log.info("文件进度表删除完毕");
        //1.删除当前项目相关的规则
        List<UsedRule> list = usedRuleMapper.selectByProject(id);
        if (list != null && list.size() > 0) {
            for (UsedRule usedRule : list) {
                usedRuleMapper.deleteByPrimaryKey(usedRule.getId());
            }
        }
        log.info("规则关系删除完毕");
        //2.删除规则组
        List<UsedRuleGroup> list1 = usedRuleGroupMapper.selectByProject(id);
        if (list1 != null && list1.size() > 0) {
            for (UsedRuleGroup usedRuleGroup : list1) {
                usedRuleGroupMapper.deleteByPrimaryKey(usedRuleGroup.getId());
                dirRecordsMapper.deleteFiles(usedRuleGroup.getId());
            }
        }


        log.info("规则组关系删除完毕");

        //3.删除分析对象规则关系
        projectAnalysisMapperMapper.deleteByProjectId(id);
        log.info("分析对象关系删除完毕");
        Unit unit = new Unit();
        try {
            Project project = projectMapper.selectByPrimaryKey(id);
            projectMapper.deleteByPrimaryKey(id);
            unit = unitMapper.selectByPrimaryKey(project.getUnitId());
        } catch (Exception e) {
            log.info("数据库操作异常===", e.fillInStackTrace());
        }


        //将impala里面的数据删除
        log.info("准备执行删除脚本====");
        String[] cmd = new String[]{"/bin/sh", "-c", "sh " + deleteDate + " \"" + unit.getEname() + "\"  \"" + id + "\" "};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            log.info("执行删除脚本失败===", e.fillInStackTrace());
        }
        try {
            process.waitFor(); //等待shell脚本执行完成
        } catch (InterruptedException e) {
            log.info("等待删除脚本执行失败===", e.fillInStackTrace());
        }
        log.info("执行结果: " + process.exitValue());
        log.info("项目删除完毕");

    }


    /**
     * 查询项目
     */
    public List<Project> searchAllProject() {

        return projectMapper.selectProject();
    }

    /**
     * 修改状态
     * @param id
     * @return
     */
//    public int modifyState(String id, Integer status, String type) {
//        if ("project".equals(type)){
//            Project project = new Project();
//            project.setId(id);
//            project.setStatus(status);
//
//            return this.projectMapper.updateByPrimaryKeySelective(project);
//        }else if ("object".equals(type)){
//            ProjectAnalysisMapper record = new ProjectAnalysisMapper();
//            record.setStatus(status);
//            record.setId(id);
//            return this.projectAnalysisMapperMapper.updateByPrimaryKeySelective(record);
//        }else if ("ruleGroup".equals(type)){
//            UsedRuleGroup record = new UsedRuleGroup();
//            record.setStatus(status);
//            record.setId(id);
//            return this.usedRuleGroupMapper.updateByPrimaryKeySelective(record);
//        }else if("dirRecords".equals(type)){
//            DirRecords record = new DirRecords();
//            record.setDirStatus(status);
//            record.setId(id);
//            this.dirRecordsMapper.updateByPrimaryKeySelective(record);
//        }else if ("fileRecords".equals(type)){
//            FileRecords record = new FileRecords();
//            record.setRunStatus(status);
//            record.setId(id);
//            return this.fileRecordsMapper.updateByPrimaryKeySelective(record);
//        }
//        return -1;
//    }
    public boolean modifyState(String id) {
        boolean result = true;
        Project project = projectMapper.selectByPrimaryKey(id);
        if(project != null){
            Project newPro = new Project();
            newPro.setId(id);
            newPro.setStatus(1);
            int a = projectMapper.updateByPrimaryKeySelective(newPro);
            if(a > 0){
                List<ProjectAnalysisMapper> pam = projectAnalysisMapperMapper.selectByProjectId(project.getId());
                if(pam.size() > 0){
                    for(ProjectAnalysisMapper paMapper:pam){
                        ProjectAnalysisMapper newPam = new ProjectAnalysisMapper();
                        newPam.setId(paMapper.getId());
                        newPam.setStatus(1);
                        int b = projectAnalysisMapperMapper.updateByPrimaryKeySelective(newPam);
                        if(b > 0){
                            List<UsedRuleGroup> urg = usedRuleGroupMapper.selectByProjectAnalysisId(paMapper.getId());
                            if(urg.size() > 0){
                                for(UsedRuleGroup urgroup:urg){
                                    UsedRuleGroup newUrg = new UsedRuleGroup();
                                    newUrg.setId(urgroup.getId());
                                    newUrg.setStatus(1);
                                    int c = usedRuleGroupMapper.updateByPrimaryKeySelective(newUrg);
                                    if(c > 0){
                                        List<UsedRule> ur = usedRuleMapper.selectUseRuleByUseRuleGroupId(urgroup.getId());
                                        if(ur.size() > 0){
                                            for(UsedRule usedRule:ur){
                                                UsedRule newRr = new UsedRule();
                                                newRr.setId(usedRule.getId());
                                                newRr.setStatus(1);
                                                int d = usedRuleMapper.updateByPrimaryKeySelective(newRr);
                                            }
                                        }
                                        List<DirRecords> dr = dirRecordsMapper.selectByUsedGroupId(urgroup.getId());
                                        if(dr.size() > 0){
                                            for(DirRecords dirRecords:dr){
                                                DirRecords newDr = new DirRecords();
                                                newDr.setId(dirRecords.getId());
                                                newDr.setDirStatus(1);
                                                int e = dirRecordsMapper.updateByPrimaryKeySelective(newDr);
                                                if(e > 0){
                                                    List<FileRecords> fr = fileRecordsMapper.selectByDirId(dirRecords.getId());
                                                    if(fr.size()>0){
                                                        for(FileRecords fileRecords:fr){
                                                            FileRecords newFr = new FileRecords();
                                                            newFr.setId(fileRecords.getId());
                                                            newFr.setRunStatus(1);
                                                            int f = fileRecordsMapper.updateByPrimaryKeySelective(newFr);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return result;
    }
}
