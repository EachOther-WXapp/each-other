package com.wutong.weixin.service;

import com.wutong.weixin.dto.TrainCommentDto;
import com.wutong.weixin.model.AddTrainCommentModel;

import java.util.List;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
public interface CommentService {

    /**
     * 给培训添加评论
     */
    void addTrain(AddTrainCommentModel model, String authHeader);

    /**
     * 给评论点赞
     */
    void like(Long commentId);

    /**
     * 给评论踩一下
     */
    void dislike(Long commentId);

    /**
     *
     * @return 培训的评论列表
     */
    List<TrainCommentDto> commentList(Long trainId);
}
