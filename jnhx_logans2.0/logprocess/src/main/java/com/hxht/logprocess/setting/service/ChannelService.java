package com.hxht.logprocess.setting.service;

import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.dao.RuleGroupMapper;
import com.hxht.logprocess.logbulk.dao.RuleMapper;
import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.setting.dao.ChannelMapper;
import com.hxht.logprocess.setting.dao.FieldMapper;
import com.hxht.logprocess.setting.model.Channel;
import com.hxht.logprocess.setting.model.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChannelService {


    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private FieldMapper fieldMapper;


    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;


    @Autowired
    private RuleGroupMapper ruleGroupMapper;

    @Autowired
    private RuleMapper ruleMapper;



    /**
     * 添加频道
     */
    public int  addChannel(Channel channel){
        channel.setGmtCreate(new Date());
        channel.setId(UUID.randomUUID().toString());
        int n = channelMapper.insertSelective(channel);
        //修改分析对象为绑定
        AnalysisObject analysisObject =new AnalysisObject();
        analysisObject.setId(channel.getObjectId());
        analysisObject.setIsBind(1);
        analysisObjectMapper.updateByPrimaryKeySelective(analysisObject);
        return  n;

    }


    /**
     * 修改频道
     */
    public int  updateChannel(Channel channel)
    {
        channel.setGmtModified(new Date());
        return  channelMapper.updateByPrimaryKeySelective(channel);
    }


    /**
     * 查询总量
     * @return
     */
    public Long searchTotal(){
        return channelMapper.selectByTotal();
    }


    /**
     * 根据中文查询
     */
    public Integer selectTotalByCname(String cname){

        return  channelMapper.selectTotalByCname(cname);
    }


    /**
     * 根据英文查询
     */
    public Integer selectTotalByEname(String ename){

        return  channelMapper.selectTotalByEname(ename);
    }


    public List<Channel> selectAll(){
        return  channelMapper.selectAll();

    }


    /**
     * 克隆频道
     * @param channel
     */
    public void cloningChannel(Channel channel){

        channel.setGmtCreate(new Date());
        channel.setId(UUID.randomUUID().toString());
        channelMapper.insertSelective(channel);
        List<Field> list = fieldMapper.selectFieldByChannelId(channel.getChannelId());
        for (Field field:list){
//            field.setChannelId(channel.getId());
            field.setId(UUID.randomUUID().toString());
            fieldMapper.insertSelective(field);
        }
        //将分析对象设置为绑定
        AnalysisObject analysisObject =new AnalysisObject();
        analysisObject.setId(channel.getObjectId());
        analysisObject.setIsBind(1);
        analysisObjectMapper.updateByPrimaryKeySelective(analysisObject);
    }





    /**
     * 根据id查询频道
     */
    public Channel selectById(String id){

        return  channelMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询频道
     */
    public List<Channel> searchChannel(int page, int size){
        int from  = 0;
        if(page!=0 && size!=0){
            from = (page - 1) * size;
        }
        return  channelMapper.selectByConditions(from,size);
    }




    /**
     * 删除频道
     */
    public int deleteChannel(String id){

        try {
            //删除频道时，把频道对应的字段也删除掉
            fieldMapper.deleteByChannelId(id);
//            //通过频道查询分析对象，再查询规则组，再查询规则，删删除规则
//            Channel channel = channelMapper.selectByPrimaryKey(id);
//            AnalysisObject analysisObject = analysisObjectMapper.selectByPrimaryKey(channel.getObjectId());
//            if (analysisObject!=null){
//                List<RuleGroup> ruleGroups = ruleGroupMapper.selectByObjetId(analysisObject.getId());
//                if (ruleGroups!=null && ruleGroups.size()>0){
//                    for (RuleGroup ruleGroup :ruleGroups){
//                        ruleMapper.deleteByGroupRuleId(ruleGroup.getId());
//                    }
//                    ruleGroupMapper.deleteByObjectId(analysisObject.getId());
//                }
//                analysisObjectMapper.deleteByPrimaryKey(analysisObject.getId());
//            }
            Channel channel = channelMapper.selectByPrimaryKey(id);
            AnalysisObject analysisObject =new AnalysisObject();
            analysisObject.setIsBind(0);
            analysisObject.setId(channel.getObjectId());
            analysisObjectMapper.updateByPrimaryKeySelective(analysisObject);
        }catch (Exception e){

        }
        return  channelMapper.deleteByPrimaryKey(id);
    }
}
