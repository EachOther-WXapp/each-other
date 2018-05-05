package com.wutong.weixin.service;

import com.wutong.weixin.model.AddTrainCommentModel; /**
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
}
