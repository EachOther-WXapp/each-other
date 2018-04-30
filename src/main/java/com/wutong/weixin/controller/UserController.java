package com.wutong.weixin.controller;


import com.wutong.weixin.dto.LoginResultDto;
import com.wutong.weixin.dto.UserInfoDto;
import com.wutong.weixin.model.LoginModel;
import com.wutong.weixin.model.UserInfoModel;
import com.wutong.weixin.service.UserService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户相关
 */
@RestController
@RequestMapping(value = "/user/")
@Api(tags = {"eachOther - user"}, description = "用户接口")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @ApiOperation( nickname = "login", value = "微信账号登陆", notes = "微信账号登陆")
    @ApiParam(required = true, name = "model", value = "微信登陆code")
    @PostMapping(value = "login")
    public ResponseMessage<String> login(@Valid @RequestBody LoginModel model) {
        logger.debug("login 接口参数:{}", model);
        return ResponseUtil.ok(service.login(model));
    }


    @ApiOperation( nickname = "update_user_info", value = "更新用户信息", notes = "更新用户信息")
    @ApiParam(required = true, name = "model", value = "微信用户信息")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "update_user_info")
    public ResponseMessage updateUserInfo(@Valid @RequestBody UserInfoModel model, HttpServletRequest request) {
        logger.debug("update_user_info 接口参数:{}", model);
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        service.updateUserInfo(model, authHeader);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "user_info", value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "user_info")
    public ResponseMessage<UserInfoDto> userInfo(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return ResponseUtil.ok(service.userInfo(authHeader));
    }





}
