package com.wutong.weixin.controller;

import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.dto.TrainDetailDto;
import com.wutong.weixin.model.AddTrainModel;
import com.wutong.weixin.service.TrainService;
import com.wutong.weixin.utils.response.ResponseMessage;
import com.wutong.weixin.utils.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author wutong
 * @date 2018/4/27
 * 培训信息表
 */
@RestController
@RequestMapping(value = "/train/")
@Api(tags = {"eachOther - train"}, description = "培训信息接口")
public class TrainController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrainService service;

    @ApiOperation( nickname = "today_list", value = "获取当天的培训", notes = "获取当天的培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "today_list")
    public ResponseMessage<List<TodayTrainInfoDto>> todayList(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        List<TodayTrainInfoDto> list = service.todayList(authHeader);
        logger.debug("today_list 接口返回:{}", list);
        return ResponseUtil.ok(list);
    }

    @ApiOperation( nickname = "add", value = "发起培训", notes = "发起培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "add")
    public ResponseMessage add(@Valid @RequestBody AddTrainModel model, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        service.add(model, authHeader);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "like", value = "给培训点赞", notes = "给培训点赞")
    @ApiParam(required = true, value = "培训的id")
    @GetMapping(value = "like")
    public ResponseMessage like(@RequestParam(value = "trainId") Long trainId) {
        logger.info("like 接口参数:{}", trainId);
        service.like(trainId);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "join_train", value = "加入培训", notes = "加入培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @ApiParam(required = true, value = "培训的id")
    @GetMapping(value = "join_train")
    public ResponseMessage joinTrain(@RequestParam(value = "trainId") Long trainId, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        logger.info("joinTrain 接口参数:{}", trainId);
        service.joinTrain(trainId, authHeader);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "detail", value = "培训详情", notes = "培训详情")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @ApiParam(required = true, value = "培训的id")
    @GetMapping(value = "detail")
    public ResponseMessage<TrainDetailDto> detail(@RequestParam(value = "trainId") Long trainId, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        logger.info("joinTrain 接口参数:{}", trainId);
        TrainDetailDto result = service.detail(trainId, authHeader);
        logger.debug("joinTrain 接口返回结果:{}", result);
        return ResponseUtil.ok(result);
    }

    @ApiOperation( nickname = "month_list", value = "最近一个月以后的培训", notes = "最近一个月以后的培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "month_list")
    public ResponseMessage<List<TodayTrainInfoDto>> monthList(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        List<TodayTrainInfoDto> list = service.monthList(authHeader);
        logger.debug("month_list 接口返回:{}", list);
        return ResponseUtil.ok(list);
    }

    @ApiOperation( nickname = "joined_train", value = "用户参加过的培训", notes = "用户参加过的培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "joined_train")
    public ResponseMessage<List<TodayTrainInfoDto>> joinedTrain(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        List<TodayTrainInfoDto> list = service.joinedTrain(authHeader);
        logger.debug("joined_train 接口返回:{}", list);
        return ResponseUtil.ok(list);
    }

    @ApiOperation( nickname = "history_train", value = "公司历史的培训", notes = "公司历史的培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "history_train")
    public ResponseMessage<List<TodayTrainInfoDto>> historyTrain(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        List<TodayTrainInfoDto> list = service.historyTrain(authHeader);
        logger.debug("history_train 接口返回:{}", list);
        return ResponseUtil.ok(list);
    }

    @ApiOperation( nickname = "delete", value = "删除自己发起的培训", notes = "删除自己发起的培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @GetMapping(value = "delete")
    public ResponseMessage delete(@ApiParam(required = true, name = "trainId", value = "培训的id")
                                  @RequestParam(value = "trainId") Long trainId, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("delete:{},{}", trainId, authHeader);
        service.delete(trainId, authHeader);
        return ResponseUtil.ok();
    }
    @ApiOperation( nickname = "published_train", value = "用户自己发布的培训", notes = "用户自己发布的培训")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "published_train")
    public ResponseMessage<List<TodayTrainInfoDto>> publishedTrain(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        List<TodayTrainInfoDto> list = service.publishedTrain(authHeader);
        logger.debug("publishedTrain 接口返回:{}", list);
        return ResponseUtil.ok(list);
    }





}
