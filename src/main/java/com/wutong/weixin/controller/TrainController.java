package com.wutong.weixin.controller;

import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.service.TrainService;
import com.wutong.weixin.utils.response.ResponseMessage;
import com.wutong.weixin.utils.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        return ResponseUtil.ok(service.todayList(authHeader));
    }
}
