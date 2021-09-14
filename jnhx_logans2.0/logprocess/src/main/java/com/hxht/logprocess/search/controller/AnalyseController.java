package com.hxht.logprocess.search.controller;


import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.http.model.ResQueryMessage;
import com.hxht.logprocess.search.model.*;
import com.hxht.logprocess.search.service.AnalyseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/server/logana")
public class AnalyseController {
    //
    @Autowired
    AnalyseService analyseService;
//

    @RequestMapping("/forceRefreshCache")
    public ResMessage forceRefreshCache(@RequestParam(required = false) boolean refresh ) {
        List<String> list = analyseService.getEmailAnalyseObject();
        if (refresh)  analyseService.forceRefreshTmpTable(list);
        return ResMessage.genSucessMessage("refresh success", "ok");
    }


    /**
     * 刷新国家城市缓存表
     * @return
     */
    @RequestMapping("/forceRefreshCountry")
    public ResMessage refreshCountry(){
        log.info("开始刷新国家城市缓存");
//        List<String> list = analyseService.getAnalyseObject();
//        analyseService.forceRefreshTmpCountryTable(list);
//        analyseService.forceRefreshTmpCityTable(list);
        return ResMessage.genSucessMessage("refresh success", "ok");
    }


    /**
     * @api {post} /logana/getForceLoginAgg 邮箱IP统计
     * @apiGroup logana
     * @apiName getIPAgg
     * @apiParam {string} beginTime 开始时间
     * @apiParam {string} endTime 结束时间
     * @apiParam {string} ptTag  协议标记
     * @apiParam {string} top  取多少条统计结果
     * @apiSuccessExample ResMessage:
     * {
     * "code":"1000",
     * "message":"查询成功",
     * "data":{}
     * }
     **/
    @RequestMapping("/getForceLoginAgg")
    public ResMessage getIPAgg(@RequestBody ForceLoginParams params) {
        List<ForthLoginEntity> list = analyseService.forceLoginAnalyse(params);

        if (list != null) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询fail", list);
        }
    }
//

    /**
     * 一个IP对应多个邮箱
     *
     * @param params
     * @return
     */
    @RequestMapping("/getUnormalLoginAgg")
    public ResMessage getIPMoreEmails(@RequestBody UnormalLoginParams params) {

        List<UnormalLoginEntity>  list = analyseService.unormalLoginAnalyse(params);
        if (list != null) {
            return ResMessage.genSucessMessage("查询成功",list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询fail", null);
        }
    }
//
//

    /**
     * @api {post} /logana/getMultiRegionAna 邮箱名统计
     * @apiName getMailAgg
     * @apiGroup logana
     * @apiParam {string} beginTime 开始时间
     * @apiParam {string} endTime 结束时间
     * @apiParam {string} ptTag  协议标记
     * @apiParam {string} top  取多少条统计结果
     * @apiSuccessExample ResMessage:
     * {
     * "code":"1000",
     * "message":"查询成功",
     * "data":{}
     * }
     **/
    @RequestMapping("/getMultiRegionAgg")
    public ResMessage getMailAgg(@RequestBody MultiRegionParams multiRegionParams) {

        List<MultiRegionEntity> multiRegionEntities = analyseService.multiRegionAnalyse(multiRegionParams);

        if (multiRegionEntities != null) {
            return ResMessage.genSucessMessage("查询成功", multiRegionEntities);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询fail", multiRegionEntities);
        }
    }


    /**
     * 查询异地详情
     * @return
     */
    @RequestMapping("/getAnotherPlaceDetail")
    public ResMessage getAnotherPlaceDetail(@RequestBody MultiRegionParams multiRegionParams) {

        List<Map<String, Object>> list = analyseService.getAnotherPlaceDetail(multiRegionParams);
        if (list != null && list.size()>0) {
            return ResMessage.genSucessMessage("查询成功", list);
        } else {
            return ResMessage.genErrorMessage(ResMessage.CodeDict.DBError, "查询fail", null);
        }
    }



}
