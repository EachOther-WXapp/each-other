package com.wutong.weixin.controller;

import com.wutong.weixin.dto.VoteOptionDto;
import com.wutong.weixin.service.VoteService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@RestController
@RequestMapping(value = "/vote/")
@Api(tags = {"eachOther - vote"}, description = "培训投票相关接口")
public class VoteController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @ApiOperation( nickname = "new_vote", value = "发起一个投票", notes = "发起一个投票")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @ApiParam(required = true, name = "model", value = "微信登陆code")
    @GetMapping(value = "new_vote")
    public ResponseMessage newVote(@RequestParam(value = "content") String content, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        logger.debug("new_vote 接口参数:{}", content);
        service.newVote(content, authHeader);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "add_option", value = "培训投票新增一个选项", notes = "培训投票新增一个选项")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @ApiParam(required = true, name = "model", value = "微信登陆code")
    @GetMapping(value = "add_option")
    public ResponseMessage addOption(@ApiParam(required = true, name = "content", value = "新增的投票选项")
                                     @RequestParam(value = "content") String content,
                                     @ApiParam(required = true, name = "voteId", value = "投票组id")
                                     @RequestParam(value = "voteId") Long voteId, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        logger.debug("addOption 接口参数:{},{}", content, voteId);
        service.addOption(content, voteId, authHeader);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "approve", value = "培训选项赞成", notes = "赞成培训投票的选项")
    @ApiParam(required = true, name = "model", value = "微信登陆code")
    @GetMapping(value = "approve")
    public ResponseMessage approve(@ApiParam(required = true, name = "voteOptionId", value = "投票选项id")
                                   @RequestParam(value = "voteOptionId") Long voteOptionId) {
        logger.debug("approve 接口参数:{}", voteOptionId);
        service.approve(voteOptionId);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "disapprove", value = "培训选项不赞成", notes = "不赞成培训投票的选项")
    @ApiParam(required = true, name = "model", value = "微信登陆code")
    @GetMapping(value = "disapprove")
    public ResponseMessage disapprove(@ApiParam(required = true, name = "voteOptionId", value = "投票选项id")
                                   @RequestParam(value = "voteOptionId") Long voteOptionId) {
        logger.debug("disapprove 接口参数:{}", voteOptionId);
        service.disapprove(voteOptionId);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "list", value = "当前的投票选项", notes = "当前的投票选项")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @GetMapping(value = "list")
    public ResponseMessage<List<VoteOptionDto>> list(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("list:{}", authHeader);
        return ResponseUtil.ok(service.list(authHeader));
    }


}
