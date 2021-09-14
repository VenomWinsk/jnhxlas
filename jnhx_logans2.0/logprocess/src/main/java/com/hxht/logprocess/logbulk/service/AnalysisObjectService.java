package com.hxht.logprocess.logbulk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxht.logprocess.logbulk.dao.*;
import com.hxht.logprocess.logbulk.model.*;
import com.hxht.logprocess.setting.dao.ChannelMapper;
import com.hxht.logprocess.setting.model.Channel;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AnalysisObjectService {

    private transient static Logger logger= LoggerFactory.getLogger(AnalysisObjectService.class);
    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;


    @Autowired
    private ProjectAnalysisMapperMapper projectAnalysisMapperMapper;


    @Autowired
    private UsedRuleGroupMapper usedRuleGroupMapper;

    @Autowired
    private UsedRuleMapper usedRuleMapper;


    @Autowired
    private RuleGroupMapper ruleGroupMapper;

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private DirRecordsMapper   dirRecordsMapper;

    @Autowired
    private UnitAnalyObjectMapper unitAnalyObjectMapper;
    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private FileRecordsMapper fileRecordsMapper;

    @Value("${dirFilePath}")
    private String dirFilePath;
    @Value("${host}")
    private String host;
    @Value("${port}")
    private String port;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${db}")
    private String db;


    /**
     * 添加分析对象
     */
    public int  addAnalysisObject(AnalysisObject analysisObject){
        analysisObject.setGmtCreate(new Date());
        analysisObject.setIsBind(0);
        analysisObject.setId(UUID.randomUUID().toString());
        return  analysisObjectMapper.insertSelective(analysisObject);

    }


    /**
     * 修改分析对象
     */
    public int  updateAnalysisObject(AnalysisObject analysisObject){
        analysisObject.setGmtModified(new Date());
        return  analysisObjectMapper.updateByPrimaryKeySelective(analysisObject);
    }



    /**
     * 根据id查询单位
     */
    public AnalysisObject selectById(String id){

        return  analysisObjectMapper.selectByPrimaryKey(id);
    }




    /**
     * 根据分析对象名称查询
     */
    public Integer selectTotalByCname(String objectName){

        return  analysisObjectMapper.selectTotalByObjectName(objectName);
    }




    /**
     * 通过单位查询分析对象
     */
    public List<String> selectAnalyObjectByUnit(String name){

        List<String> reList =new ArrayList();
        Unit unit = unitMapper.selectByEname(name);
        List<UnitAnalyObject> list = unitAnalyObjectMapper.selectAnaysisByUnitId(unit.getId());
        for (UnitAnalyObject unitAnalyObject:list){
            AnalysisObject analysisObject = analysisObjectMapper.selectByPrimaryKey(unitAnalyObject.getObjectId());
            if (analysisObject!=null){
                reList.add(analysisObject.getObjectName());
            }
        }
        return  reList;
    }






    /**
     * 根据分析对象code查询
     */
    public Integer selectTotalByCode(String code){

        return  analysisObjectMapper.selectTotalByCode(code);
    }

    /**
     * 查询分析对象
     */
    public List<AnalysisObject> searchAnalysisObject(AnalysisObject analysisObject){
        if (analysisObject.getPage()!=null && analysisObject.getSize()!=null){
            int from;
            from = (analysisObject.getPage() - 1) * analysisObject.getSize();
            analysisObject.setForm(from);
        }
        return  analysisObjectMapper.selectByConditions(analysisObject);
    }


    /**
     * 查询未绑定的分析对象
     */
    public List<AnalysisObject>  searchNoBindAnalysisObject(){
        return  analysisObjectMapper.selectNoBindByAnalyseObject();

    }

    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
        return analysisObjectMapper.selectByTotal();
    }



    /**
     * 删除分析对象
     */
    public int deleteAnalysisObject(String id){

        //通过分析对象查询规则组再查询规则组下的规则，先删除规则再删除规则组
        List<RuleGroup> ruleGroups = ruleGroupMapper.selectByObjetId(id);
        if (ruleGroups!=null && ruleGroups.size()>0){
            try {
                for (RuleGroup ruleGroup :ruleGroups){
                    ruleMapper.deleteByGroupRuleId(ruleGroup.getId());
                }
                ruleGroupMapper.deleteByObjectId(id);
            }catch (Exception e){

            }
        }
        //删除分析对象关联的频道
        channelMapper.deleteByObjectId(id);
        //删除分析对象时将单位分析对象关系表中的分析对象删除
        unitAnalyObjectMapper.deleteByObjectId(id);
        return  analysisObjectMapper.deleteByPrimaryKey(id);
    }


    /**
     * 添加日志关系
     * @param objectList
     * @param ruleGroupList
     * @param ruleList
     */
    public void addLogMapper(List<ProjectAnalysisMapper> objectList,List<UsedRuleGroup> ruleGroupList,List<UsedRule> ruleList){

        //向项目分析对象关系表添加数据
        for (ProjectAnalysisMapper projectAnalysisMapper:objectList){
            int n =0;
            if (projectAnalysisMapper.getId()!=null && projectAnalysisMapper.getId()!=""){
                projectAnalysisMapper.setUpdateAt(new Date());
                projectAnalysisMapperMapper.updateByPrimaryKeySelective(projectAnalysisMapper);
            }else {
                projectAnalysisMapper.setId(UUID.randomUUID().toString());
                projectAnalysisMapper.setCreateAt(new Date());
                projectAnalysisMapper.setUpdateAt(new Date());
                //通过objectid查询objectCode
                AnalysisObject analysisObject = analysisObjectMapper.selectByPrimaryKey(projectAnalysisMapper.getObjectId());
                projectAnalysisMapper.setCode(analysisObject.getCode());
                //通过分析对象id和项目id去关系表查重
                n = projectAnalysisMapperMapper.selectByProjectIdObjectId(projectAnalysisMapper.getProjectId(),projectAnalysisMapper.getObjectId());
                if (n<=0){
                    projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);
                }
                //向单位分析对象关系表添加数据
                Project project = projectMapper.selectByPrimaryKey(projectAnalysisMapper.getProjectId());
                int num = unitAnalyObjectMapper.selectCountByUnit(project.getUnitId(),projectAnalysisMapper.getObjectId());
                if (num <=0){
                    UnitAnalyObject unitAnalyObject =new UnitAnalyObject();
                    unitAnalyObject.setId(UUID.randomUUID().toString());
                    unitAnalyObject.setUnitId(project.getUnitId());
                    unitAnalyObject.setObjectId(projectAnalysisMapper.getObjectId());
                    unitAnalyObject.setGmtCreate(new Date());
                    unitAnalyObject.setGmtModified(new Date());
                    unitAnalyObjectMapper.insertSelective(unitAnalyObject);
                }
            }
            this.addMapper(projectAnalysisMapper,ruleGroupList,ruleList,n);
        }
    }




    public void addMapper(ProjectAnalysisMapper projectAnalysisMapper,List<UsedRuleGroup> ruleGroupList,List<UsedRule> ruleList,int n){

        for (UsedRuleGroup usedRuleGroup:ruleGroupList){
            if (projectAnalysisMapper.getObjectId().equals(usedRuleGroup.getObjectId())){
                if (usedRuleGroup.getId()!=null  && usedRuleGroup.getId()!=""){
                    usedRuleGroupMapper.updateByPrimaryKeySelective(usedRuleGroup);
                }else {
                    String id =UUID.randomUUID().toString();
                    usedRuleGroup.setId(id);
                    usedRuleGroup.setGmtCreate(new Date());
                    usedRuleGroup.setGmtModified(new Date());
                    usedRuleGroup.setProjectAnalysisId(projectAnalysisMapper.getId());
                    if (n<=0){
                       usedRuleGroupMapper.insertSelective(usedRuleGroup);
                       insertLogAndFileMessage(id,usedRuleGroup.getInputPath(),usedRuleGroup.getFileRegex());
                        //调用python脚本插入文件夹和文件信息
                        //logger.info("------------------原来逻辑-----------------------------");
                        //callScript(id,usedRuleGroup.getInputPath(),usedRuleGroup.getFileRegex());
                    }
                }
                for (UsedRule usedRule:ruleList){
                    if (usedRuleGroup.getRuleGroupId().equals(usedRule.getRuleGroupId())){
                        if (usedRule.getId()!=null  && usedRule.getId()!=""){
                            usedRuleMapper.updateByPrimaryKeySelective(usedRule);
                        }else {
                            usedRule.setId(UUID.randomUUID().toString());
                            usedRule.setGmtCreate(new Date());
                            usedRule.setGmtModified(new Date());
                            usedRule.setUseRuleGroupId(usedRuleGroup.getId());
                            if (n<=0){
                                usedRuleMapper.insertSelective(usedRule);
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * 将python脚本中入库部分，嵌入到java中
     */

    public void insertLogAndFileMessage(String useGroupRuleId,String inputPath,String regex){
        String fileRegex = query_file_re(useGroupRuleId);
        queryDirs(useGroupRuleId,inputPath,fileRegex);
    }

    /**
     * 数据库中查询文件正则
     * @param useGroupRuleId
     * @return
     */
    String query_file_re(String useGroupRuleId){
        UsedRuleGroup usedRuleGroup = usedRuleGroupMapper.selectByPrimaryKey(useGroupRuleId);
        String fileRegex = usedRuleGroup.getFileRegex();
        return fileRegex;
    }

    /**
     * 查询文件夹
     * @param useGroupRuleId
     * @param inputPath
     * @param fileRegex
     * used_rule_group_status: 应该是规则状态，但是一个规则组绑定了一个规则，所以跟规则组状态一样
     */
    void queryDirs(String useGroupRuleId,String inputPath,String fileRegex){
        Pattern fileRe = Pattern.compile(fileRegex);
        List<JSONObject> fileDirs=new ArrayList<>();
        String[] dirs = inputPath.split(";");
        for (String dirName:dirs){
            File file = new File(dirName);
            if (file.exists()){
                JSONObject fileDir = new JSONObject();
                fileDir.put("dir_name",dirName);
                fileDir.put("dir_status",1);
                fileDir.put("wasscanned",1);
                List<JSONObject> files = queryFilesByDir(dirName, fileRe);
                fileDir.put("files", files);
                fileDir.put("processednums",0);
                fileDir.put("filenums",files.size());
                insertDirRecords(useGroupRuleId,dirName,files.size(),files);
            }
        }
    }

    List<JSONObject> queryFilesByDir(String dirPath,Pattern fileRe){
        List<String> fileList = dirPath(dirPath, null, fileRe);
        List<JSONObject> fileInfos = getFileInfos(fileList);
        return fileInfos;
    }

    /**
     * 获取文件列表
     * @param dirPath
     * @param lfilelist
     * @param fileRe
     * @return
     */
    List<String> dirPath(String dirPath,List<String> lfilelist ,Pattern fileRe){
        if (lfilelist==null||lfilelist.size()<=0){
            lfilelist=new ArrayList();
        }
        File[] files = new File(dirPath).listFiles();
        if (files!=null){
            for (File file:files){
                String filePath = file.getAbsolutePath();
                //如果文件中存在空格，把空格替换
                if (filePath.contains(" ")){
                    filePath.replace(" ",UUID.randomUUID().toString().substring(1,3));
                    file.renameTo(new File(filePath));
                }
                if (file.isDirectory()){
                    logger.info("打印出来目录{}路径-------",filePath);
                    lfilelist = dirPath(filePath,lfilelist,fileRe);
                }else {
                    if (fileRe!=null){
                        if (fileRe.matcher(filePath).find()){
                            logger.info("打印出来文件{}名称-------",filePath);
                            lfilelist.add(filePath);
                        }
                    }else {
                        lfilelist.add(filePath);
                    }
                }
            }
        }
                 logger.info("所有文件{}",lfilelist);
        return lfilelist;
    }

    /**
     * 输出文件的编码及大小
     * @param lfilelist
     * @return
     */
    List<JSONObject> getFileInfos(List<String> lfilelist){
        List<JSONObject> fileInfos = lfilelist.stream().map(fileName -> {
            JSONObject oneFile = new JSONObject();
            oneFile.put("filename", fileName);
            oneFile.put("status", 0);
            oneFile.put("data_length", 0);
            try {
                String checkFile=fileName;
                String checkCode="/usr/bin/file -b "+checkFile;
                BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(checkCode).getInputStream()));
                //读取第一行
                String res = reader.readLine();
                //logger.info("linux文件编码checkCode----->{}",res.split(" ")[0]);
                oneFile.put("filecode",res.split(" ")[0]);
                //回去文件大小
                String checkSize="du -s "+checkFile;
                reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(checkSize).getInputStream()));
                Pattern rex = Pattern.compile("^\\d{0,15}");
                String size = reader.readLine();
                Matcher matcher = rex.matcher(size);
                if(matcher.find()){
                    //logger.info("linux文件大小checkSize----->{}",Integer.parseInt(matcher.group(0)));
                    oneFile.put("file_size",Integer.parseInt(matcher.group(0)));
                    //logger.info("正则匹配后正常----");
                }
            } catch (Exception e) {
                oneFile.put("file_size", -1);
                oneFile.put("filecode", -1);
            }finally {
                return oneFile;
            }
        }).collect(Collectors.toList());
        return fileInfos;
    }

    /**
     * 入库
     * @param useGroupRuleId
     * @param dirName
     * @param fileNums
     * @param files
     */
    void insertDirRecords(String useGroupRuleId,String dirName,int fileNums,List<JSONObject> files){
        String id = usedRuleGroupMapper.selectByUsedRuleGroupIdAndDirName(useGroupRuleId, dirName);
        if (StringUtils.isNotBlank(id)){
            logger.info("{}文件已存在-rulegroupid{}",dirName,useGroupRuleId);
            //如果该文件夹的文件记录数是否和files不相等，则删除后重新插入
            FileRecordsExample fileRecordsExample = new FileRecordsExample();
            fileRecordsExample.createCriteria().andFiledirIdEqualTo(id);
            long len = fileRecordsMapper.countByExample(fileRecordsExample);
            if (len!=files.size()){
                fileRecordsMapper.deleteByExample(fileRecordsExample);
                logger.info("{}数据库记录和现有记录不匹配，删除数据库中已经存在的记录",dirName);
                insertFileRecords(files, id);
                logger.info("{}缺少文件记录{}条，已补全",dirName,files.size());
            }
        }else {
                try {
                    String dirId=UUID.randomUUID().toString();
                    DirRecords dirRecords=new DirRecords();
                    dirRecords.setId(dirId);
                    dirRecords.setUsedRuleGroupId(useGroupRuleId);
                    dirRecords.setDirname(dirName);
                    dirRecords.setDirStatus(0);
                    dirRecords.setFilenums(fileNums);
                    dirRecords.setJumpnums(0);
                    dirRecords.setProcessednums(0);
                    dirRecords.setWasscanned(0);
                    dirRecordsMapper.insert(dirRecords);
                    logger.info("记录{}相关数据---rulegroupid---{}",dirName,useGroupRuleId);
                    insertFileRecords(files,dirId);
                    logger.info("记录文件{}条 ----rulegroupid---{}",files.size(),useGroupRuleId);
                }catch (Exception e){
                    logger.info("文件插入失败");
                    e.printStackTrace();
                }
        }
    }

    private void insertFileRecords(List<JSONObject> files, String fileDirid) {
        files.stream().forEach(file->{
            FileRecords fileRecords = new FileRecords();
            fileRecords.setFiledirId(fileDirid);
            fileRecords.setFileSize(Integer.valueOf(file.getString("file_size")));
            fileRecords.setFilename(file.getString("filename"));
            fileRecords.setFileCode(file.getString("filecode"));
            fileRecords.setRunStatus(0);
            fileRecords.setDataLength(0);
            fileRecordsMapper.insert(fileRecords);
        });
    }

    /**
     * 调用python脚本插入日志文件夹和文件信息
     */
    public void  callScript(String useGroupRule,String inputPath,String regex){
        log.info("开始走脚本");
        String mysql = host+"+"+port+"+"+user+"+"+password+"+"+db;
        String []cmd = new String[]{"/bin/sh", "-c", "sh "+ dirFilePath+" "+mysql+" "+useGroupRule+" "+inputPath+" "+regex};
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            log.info("运行完毕");
            log.info("执行结果: " + process.exitValue());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }





    /**
     * 保存日志关系
     * @param objectList
     * @param ruleGroupList
     * @param ruleList
     */
    public void  saveLogMapper(List<ProjectAnalysisMapper> objectList,List<UsedRuleGroup> ruleGroupList,List<UsedRule> ruleList){
        for (ProjectAnalysisMapper projectAnalysisMapper:objectList){
            int n =0;
            if (projectAnalysisMapper.getId()!=null  && projectAnalysisMapper.getId()!=""){
                projectAnalysisMapper.setUpdateAt(new Date());
                projectAnalysisMapperMapper.updateByPrimaryKeySelective(projectAnalysisMapper);
            }else {
                projectAnalysisMapper.setId(UUID.randomUUID().toString());
                projectAnalysisMapper.setCreateAt(new Date());
                projectAnalysisMapper.setUpdateAt(new Date());
                //通过objectid查询objectCode
                AnalysisObject analysisObject = analysisObjectMapper.selectByPrimaryKey(projectAnalysisMapper.getObjectId());
                projectAnalysisMapper.setCode(analysisObject.getCode());
                projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);

                //通过分析对象id和项目id去关系表查重
                n = projectAnalysisMapperMapper.selectByProjectIdObjectId(projectAnalysisMapper.getProjectId(),projectAnalysisMapper.getObjectId());
                if (n<=0){
                    projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);
                }
                //向单位分析对象关系表添加数据
                Project project = projectMapper.selectByPrimaryKey(projectAnalysisMapper.getProjectId());
                int num = unitAnalyObjectMapper.selectCountByUnit(project.getUnitId(),projectAnalysisMapper.getObjectId());
                if (num <=0){
                    UnitAnalyObject unitAnalyObject =new UnitAnalyObject();
                    unitAnalyObject.setId(UUID.randomUUID().toString());
                    unitAnalyObject.setUnitId(project.getUnitId());
                    unitAnalyObject.setObjectId(projectAnalysisMapper.getObjectId());
                    unitAnalyObject.setGmtCreate(new Date());
                    unitAnalyObject.setGmtModified(new Date());
                    unitAnalyObjectMapper.insertSelective(unitAnalyObject);
                }
            }
            for (UsedRuleGroup usedRuleGroup:ruleGroupList){
                if (projectAnalysisMapper.getObjectId().equals(usedRuleGroup.getObjectId())){
                        if (usedRuleGroup.getId()!=null  && usedRuleGroup.getId()!=""){
                            usedRuleGroupMapper.updateByPrimaryKeySelective(usedRuleGroup);
                        }else {
                            usedRuleGroup.setId(UUID.randomUUID().toString());
                            usedRuleGroup.setGmtCreate(new Date());
                            usedRuleGroup.setGmtModified(new Date());
                            usedRuleGroup.setProjectAnalysisId(projectAnalysisMapper.getId());
                            if (n<=0){
                                usedRuleGroupMapper.insertSelective(usedRuleGroup);
                            }
                        }
                        RuleGroup ruleGroup =new RuleGroup();
                        ruleGroup.setCode(usedRuleGroup.getCode());
                        ruleGroup.setDescription(usedRuleGroup.getDescription());
                        ruleGroup.setName(usedRuleGroup.getName());
                        ruleGroup.setObjectId(usedRuleGroup.getObjectId());
                        ruleGroup.setGmtModified(new Date());
                        ruleGroup.setFileRegex(usedRuleGroup.getFileRegex());
                        ruleGroup.setFileEncode(usedRuleGroup.getFileEncode());
                        ruleGroup.setId(usedRuleGroup.getRuleGroupId());
                        //更新rule_group表
                        ruleGroupMapper.updateByPrimaryKeySelective(ruleGroup);
                        for (UsedRule usedRule:ruleList){
                            if (usedRuleGroup.getRuleGroupId().equals(usedRule.getRuleGroupId())){
                                if (usedRule.getId()!=null  && usedRule.getId()!=""){
                                    usedRuleMapper.updateByPrimaryKeySelective(usedRule);
                                }else {
                                    usedRule.setId(UUID.randomUUID().toString());
                                    usedRule.setGmtCreate(new Date());
                                    usedRule.setGmtModified(new Date());
                                    usedRule.setUseRuleGroupId(usedRuleGroup.getId());
                                    if (n<=0){
                                        usedRuleMapper.insertSelective(usedRule);
                                    }
                                }
                                //更新rule表
                                Rule rule =new Rule();
                                rule.setName(usedRule.getName());
                                rule.setExtractRule(usedRule.getExtractRule());
                                rule.setSwitchRule(usedRule.getSwitchRule());
                                rule.setReplaceRule(usedRule.getReplaceRule());
                                rule.setSupplementRule(usedRule.getSupplementRule());
                                rule.setRuleGroupId(usedRule.getRuleGroupId());
                                rule.setDescription(usedRule.getDescription());
                                rule.setGmtModified(new Date());
                                rule.setLogFeature(usedRule.getLogFeature());
                                rule.setId(usedRule.getRuleId());
                                ruleMapper.updateByPrimaryKeySelective(rule);
                            }
                        }
                    }
                }
        }
    }


    /**
     * 根据项目id查询日志关系
     */
    public List  searchLogMapperByProjectId(String projectId){

        Map map =new HashMap();
        List reList =new ArrayList();
        List<ProjectAnalysisMapper> objectList =new ArrayList();
        List<UsedRuleGroup> useRuleGroupList =new ArrayList();
        List<UsedRule> useRuleList =new ArrayList();
        //通过项目id查询项目对象关系表
        List<ProjectAnalysisMapper> projectAnalysisList = projectAnalysisMapperMapper.selectByProjectId(projectId);
        //通过项目对象关系查询项目下的分析对象
        for (ProjectAnalysisMapper projectAnalysisMapper:projectAnalysisList){
            //AnalysisObject analysisObject =  analysisObjectMapper.selectByPrimaryKey(projectAnalysisMapper.getObjectId());
            //通过分析对象查询规则组
            List<UsedRuleGroup> ruleGroupList =usedRuleGroupMapper.selectByProjectAnalysisId(projectAnalysisMapper.getId());
            for (UsedRuleGroup usedRuleGroup:ruleGroupList){
                List<UsedRule> ruleList = usedRuleMapper.selectUseRuleByUseRuleGroupId(usedRuleGroup.getId());
                for (UsedRule usedRule:ruleList){
                    useRuleList.add(usedRule);
                }
                useRuleGroupList.add(usedRuleGroup);
            }
            objectList.add(projectAnalysisMapper);
        }
        map.put("objectList",objectList);
        map.put("useRuleGroupList",useRuleGroupList);
        map.put("useRuleList",useRuleList);
        reList.add(map);

        return reList;
    }














}
