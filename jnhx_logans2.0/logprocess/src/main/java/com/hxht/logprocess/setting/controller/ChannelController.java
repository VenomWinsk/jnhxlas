package com.hxht.logprocess.setting.controller;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.logbulk.dao.AnalysisObjectMapper;
import com.hxht.logprocess.logbulk.model.AnalysisObject;
import com.hxht.logprocess.logbulk.model.Unit;
import com.hxht.logprocess.logbulk.service.UnitService;
import com.hxht.logprocess.setting.model.Channel;
import com.hxht.logprocess.setting.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/channel")
public class ChannelController {


    @Autowired
    private ChannelService channelService;

    @Autowired
    private AnalysisObjectMapper analysisObjectMapper;
    /**
     * 添加频道
     */
    @RequestMapping("addChannel")
    public ResMessage addChannel(@RequestBody Channel channel){
        try {

            if (channel.getCname()!=null){
                int num = channelService.selectTotalByCname(channel.getCname().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"频道中文名重复",null);
                }
            }
            if (channel.getEname()!=null){
                int num = channelService.selectTotalByEname(channel.getEname().trim());
                if (num>0){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.NameError,"频道英文名重复",null);
                }
            }
            int n = channelService.addChannel(channel);
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
     * 修改频道
     */
    @RequestMapping("updateChannel")
    public ResMessage updateChannel(@RequestBody Channel channel){
        try {
            //通过id查询频道信息
            Channel channel1 = channelService.selectById(channel.getId());
            if (channel.getEname()!=null){
                int num = channelService.selectTotalByEname(channel.getEname().trim());
                if (num>0 && !channel1.getEname().equals(channel.getEname())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"频道中文名重复",null);

                }
            }
            if (channel.getCname()!=null){
                int cnNum = channelService.selectTotalByCname(channel.getCname().trim());
                if (cnNum>0 && !channel1.getCname().equals(channel.getCname())){
                    return ResMessage.genErrorMessage(ResMessage.CodeDict.ParamsError,"频道英文名重复",null);

                }
            }
            //如果修改分析对象为不同的分析对象
            if (!channel1.getObjectId().equals(channel.getObjectId())){
                AnalysisObject analysisObject =new AnalysisObject();
                analysisObject.setId(channel1.getObjectId());
                analysisObject.setIsBind(0);
                analysisObjectMapper.updateByPrimaryKeySelective(analysisObject);
                AnalysisObject analysisObject1 =new AnalysisObject();
                analysisObject1.setId(channel.getObjectId());
                analysisObject1.setIsBind(1);
                analysisObjectMapper.updateByPrimaryKeySelective(analysisObject1);
            }
            int n = channelService.updateChannel(channel);
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
     * 分页查询频道
     */
    @RequestMapping("searchChannel")
    public ResMessage searchChannel(@RequestParam int page, @RequestParam int size){
        try {
            List<Channel> list = channelService.searchChannel(page,size);
            Long total = channelService.searchTotal();
            if(list.size() > 0){
                return ResQueryMessage.genSucessMessage("查询成功",list,list.size(),total);
            }else {
                return ResMessage.genErrorMessage(ResMessage.CodeDict.NoData,"查询为空",null);
            }
        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"修改异常",e.getMessage());
        }
    }



    /**
     * 查询所有频道
     */
    @RequestMapping("searchAllChannel")
    public ResMessage searchChannel(){
        try {
            List<Channel> list = channelService.selectAll();
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
     * 克隆频道
     */
    @RequestMapping("cloningChannel")
    public ResMessage cloningChannel(@RequestBody Channel channel){
        try {
             channelService.cloningChannel(channel);
            return ResMessage.genSucessMessage("克隆成功",null);

        }catch (Exception e){
            return ResMessage.genErrorMessage(ResMessage.CodeDict.exceptionError,"克隆异常",e.getMessage());
        }
    }




    /**
     * 删除频道
     */
    @DeleteMapping("deleteChannel")
    public ResMessage deleteChannel(@RequestParam String id){
        try {
            int n  = channelService.deleteChannel(id);
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
