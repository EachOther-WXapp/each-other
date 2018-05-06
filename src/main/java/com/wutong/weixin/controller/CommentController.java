package com.wutong.weixin.controller;

import com.wutong.weixin.dto.TrainCommentDto;
import com.wutong.weixin.model.AddTrainCommentModel;
import com.wutong.weixin.service.CommentService;
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
import java.util.List;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@RestController
@RequestMapping(value = "/comment/")
@Api(tags = {"eachOther - comment"}, description = "评论接口")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommentService service;

    @ApiOperation( nickname = "add_train", value = "给培训添加评论", notes = "给培训添加评论")
    @ApiImplicitParam(paramType = "header", name = HttpHeaders.AUTHORIZATION, value = "token信息", required = true, defaultValue = "Bearer ")
    @PostMapping(value = "add_train")
    public ResponseMessage addTrain(@Valid @RequestBody AddTrainCommentModel model, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("token:{}", authHeader);
        logger.info("add_train 接口参数:{}", model);
        service.addTrain(model, authHeader);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "like", value = "给评论点赞", notes = "给评论点赞")
    @ApiParam(required = true, value = "评论的id")
    @GetMapping(value = "like")
    public ResponseMessage like(@RequestParam(value = "commentId") Long commentId) {
        logger.info("like 接口参数:{}", commentId);
        service.like(commentId);
        return ResponseUtil.ok();
    }


    @ApiOperation( nickname = "dislike", value = "给评论踩一下", notes = "给评论踩一下")
    @ApiParam(required = true, value = "评论的id")
    @GetMapping(value = "dislike")
    public ResponseMessage dislike(@RequestParam(value = "commentId") Long commentId) {
        logger.info("dislike 接口参数:{}", commentId);
        service.dislike(commentId);
        return ResponseUtil.ok();
    }

    @ApiOperation( nickname = "list", value = "查询培训的所有评论", notes = "查询培训的所有评论")
    @GetMapping(value = "list")
    public ResponseMessage<List<TrainCommentDto>> commentList(@ApiParam(required = true, value = "培训的id")
                                       @RequestParam(value = "trainId") Long trainId) {
        logger.info("commentList 接口参数:{}", trainId);
        List<TrainCommentDto> list = service.commentList(trainId);
        logger.debug("list 接口返回:{}", list);
        return ResponseUtil.ok(list);
    }




}
