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
     * ??????????????????
     */
    public int  addAnalysisObject(AnalysisObject analysisObject){
        analysisObject.setGmtCreate(new Date());
        analysisObject.setIsBind(0);
        analysisObject.setId(UUID.randomUUID().toString());
        return  analysisObjectMapper.insertSelective(analysisObject);

    }


    /**
     * ??????????????????
     */
    public int  updateAnalysisObject(AnalysisObject analysisObject){
        analysisObject.setGmtModified(new Date());
        return  analysisObjectMapper.updateByPrimaryKeySelective(analysisObject);
    }



    /**
     * ??????id????????????
     */
    public AnalysisObject selectById(String id){

        return  analysisObjectMapper.selectByPrimaryKey(id);
    }




    /**
     * ??????????????????????????????
     */
    public Integer selectTotalByCname(String objectName){

        return  analysisObjectMapper.selectTotalByObjectName(objectName);
    }




    /**
     * ??????????????????????????????
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
     * ??????????????????code??????
     */
    public Integer selectTotalByCode(String code){

        return  analysisObjectMapper.selectTotalByCode(code);
    }

    /**
     * ??????????????????
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
     * ??????????????????????????????
     */
    public List<AnalysisObject>  searchNoBindAnalysisObject(){
        return  analysisObjectMapper.selectNoBindByAnalyseObject();

    }

    /**
     * ????????????
     * @return
     */
    public Long searchTotal(){
        return analysisObjectMapper.selectByTotal();
    }



    /**
     * ??????????????????
     */
    public int deleteAnalysisObject(String id){

        //???????????????????????????????????????????????????????????????????????????????????????????????????
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
        //?????????????????????????????????
        channelMapper.deleteByObjectId(id);
        //???????????????????????????????????????????????????????????????????????????
        unitAnalyObjectMapper.deleteByObjectId(id);
        return  analysisObjectMapper.deleteByPrimaryKey(id);
    }


    /**
     * ??????????????????
     * @param objectList
     * @param ruleGroupList
     * @param ruleList
     */
    public void addLogMapper(List<ProjectAnalysisMapper> objectList,List<UsedRuleGroup> ruleGroupList,List<UsedRule> ruleList){

        //??????????????????????????????????????????
        for (ProjectAnalysisMapper projectAnalysisMapper:objectList){
            int n =0;
            if (projectAnalysisMapper.getId()!=null && projectAnalysisMapper.getId()!=""){
                projectAnalysisMapper.setUpdateAt(new Date());
                projectAnalysisMapperMapper.updateByPrimaryKeySelective(projectAnalysisMapper);
            }else {
                projectAnalysisMapper.setId(UUID.randomUUID().toString());
                projectAnalysisMapper.setCreateAt(new Date());
                projectAnalysisMapper.setUpdateAt(new Date());
                //??????objectid??????objectCode
                AnalysisObject analysisObject = analysisObjectMapper.selectByPrimaryKey(projectAnalysisMapper.getObjectId());
                projectAnalysisMapper.setCode(analysisObject.getCode());
                //??????????????????id?????????id??????????????????
                n = projectAnalysisMapperMapper.selectByProjectIdObjectId(projectAnalysisMapper.getProjectId(),projectAnalysisMapper.getObjectId());
                if (n<=0){
                    projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);
                }
                //??????????????????????????????????????????
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
                        //??????python????????????????????????????????????
                        //logger.info("------------------????????????-----------------------------");
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
     * ???python?????????????????????????????????java???
     */

    public void insertLogAndFileMessage(String useGroupRuleId,String inputPath,String regex){
        String fileRegex = query_file_re(useGroupRuleId);
        queryDirs(useGroupRuleId,inputPath,fileRegex);
    }

    /**
     * ??????????????????????????????
     * @param useGroupRuleId
     * @return
     */
    String query_file_re(String useGroupRuleId){
        UsedRuleGroup usedRuleGroup = usedRuleGroupMapper.selectByPrimaryKey(useGroupRuleId);
        String fileRegex = usedRuleGroup.getFileRegex();
        return fileRegex;
    }

    /**
     * ???????????????
     * @param useGroupRuleId
     * @param inputPath
     * @param fileRegex
     * used_rule_group_status: ???????????????????????????????????????????????????????????????????????????????????????????????????
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
     * ??????????????????
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
                //?????????????????????????????????????????????
                if (filePath.contains(" ")){
                    filePath.replace(" ",UUID.randomUUID().toString().substring(1,3));
                    file.renameTo(new File(filePath));
                }
                if (file.isDirectory()){
                    logger.info("??????????????????{}??????-------",filePath);
                    lfilelist = dirPath(filePath,lfilelist,fileRe);
                }else {
                    if (fileRe!=null){
                        if (fileRe.matcher(filePath).find()){
                            logger.info("??????????????????{}??????-------",filePath);
                            lfilelist.add(filePath);
                        }
                    }else {
                        lfilelist.add(filePath);
                    }
                }
            }
        }
                 logger.info("????????????{}",lfilelist);
        return lfilelist;
    }

    /**
     * ??????????????????????????????
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
                //???????????????
                String res = reader.readLine();
                //logger.info("linux????????????checkCode----->{}",res.split(" ")[0]);
                oneFile.put("filecode",res.split(" ")[0]);
                //??????????????????
                String checkSize="du -s "+checkFile;
                reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(checkSize).getInputStream()));
                Pattern rex = Pattern.compile("^\\d{0,15}");
                String size = reader.readLine();
                Matcher matcher = rex.matcher(size);
                if(matcher.find()){
                    //logger.info("linux????????????checkSize----->{}",Integer.parseInt(matcher.group(0)));
                    oneFile.put("file_size",Integer.parseInt(matcher.group(0)));
                    //logger.info("?????????????????????----");
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
     * ??????
     * @param useGroupRuleId
     * @param dirName
     * @param fileNums
     * @param files
     */
    void insertDirRecords(String useGroupRuleId,String dirName,int fileNums,List<JSONObject> files){
        String id = usedRuleGroupMapper.selectByUsedRuleGroupIdAndDirName(useGroupRuleId, dirName);
        if (StringUtils.isNotBlank(id)){
            logger.info("{}???????????????-rulegroupid{}",dirName,useGroupRuleId);
            //?????????????????????????????????????????????files????????????????????????????????????
            FileRecordsExample fileRecordsExample = new FileRecordsExample();
            fileRecordsExample.createCriteria().andFiledirIdEqualTo(id);
            long len = fileRecordsMapper.countByExample(fileRecordsExample);
            if (len!=files.size()){
                fileRecordsMapper.deleteByExample(fileRecordsExample);
                logger.info("{}?????????????????????????????????????????????????????????????????????????????????",dirName);
                insertFileRecords(files, id);
                logger.info("{}??????????????????{}???????????????",dirName,files.size());
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
                    logger.info("??????{}????????????---rulegroupid---{}",dirName,useGroupRuleId);
                    insertFileRecords(files,dirId);
                    logger.info("????????????{}??? ----rulegroupid---{}",files.size(),useGroupRuleId);
                }catch (Exception e){
                    logger.info("??????????????????");
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
     * ??????python??????????????????????????????????????????
     */
    public void  callScript(String useGroupRule,String inputPath,String regex){
        log.info("???????????????");
        String mysql = host+"+"+port+"+"+user+"+"+password+"+"+db;
        String []cmd = new String[]{"/bin/sh", "-c", "sh "+ dirFilePath+" "+mysql+" "+useGroupRule+" "+inputPath+" "+regex};
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            log.info("????????????");
            log.info("????????????: " + process.exitValue());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }





    /**
     * ??????????????????
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
                //??????objectid??????objectCode
                AnalysisObject analysisObject = analysisObjectMapper.selectByPrimaryKey(projectAnalysisMapper.getObjectId());
                projectAnalysisMapper.setCode(analysisObject.getCode());
                projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);

                //??????????????????id?????????id??????????????????
                n = projectAnalysisMapperMapper.selectByProjectIdObjectId(projectAnalysisMapper.getProjectId(),projectAnalysisMapper.getObjectId());
                if (n<=0){
                    projectAnalysisMapperMapper.insertSelective(projectAnalysisMapper);
                }
                //??????????????????????????????????????????
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
                        //??????rule_group???
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
                                //??????rule???
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
     * ????????????id??????????????????
     */
    public List  searchLogMapperByProjectId(String projectId){

        Map map =new HashMap();
        List reList =new ArrayList();
        List<ProjectAnalysisMapper> objectList =new ArrayList();
        List<UsedRuleGroup> useRuleGroupList =new ArrayList();
        List<UsedRule> useRuleList =new ArrayList();
        //????????????id???????????????????????????
        List<ProjectAnalysisMapper> projectAnalysisList = projectAnalysisMapperMapper.selectByProjectId(projectId);
        //??????????????????????????????????????????????????????
        for (ProjectAnalysisMapper projectAnalysisMapper:projectAnalysisList){
            //AnalysisObject analysisObject =  analysisObjectMapper.selectByPrimaryKey(projectAnalysisMapper.getObjectId());
            //?????????????????????????????????
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
