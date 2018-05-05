package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.CommentDao;
import com.wutong.weixin.entity.Comment;
import com.wutong.weixin.entity.User;
import com.wutong.weixin.model.AddTrainCommentModel;
import com.wutong.weixin.service.CommentService;
import com.wutong.weixin.service.UserService;
import com.wutong.weixin.utils.exception.BusinessException;
import com.wutong.weixin.utils.exception.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@Service
public class CommentServiceImpl implements CommentService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private CommentDao commentDao;

    /**
     * 给培训添加评论
     */
    @Override
    public void addTrain(AddTrainCommentModel model, String authHeader) {
        User user = userService.verifyToken(authHeader);
        int result = commentDao.insert(new Comment(user.getId(), model.getTrainId(), model.getContent()));
        if (result != 1) {
            logger.error("新增评论出错:{}", model);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }

    /**
     * 给评论点赞
     */
    @Override
    public void like(Long commentId) {
        Comment comment = commentDao.queryByPk(commentId);
        if (comment == null) {
            logger.error("无效的评论id");
            throw new BusinessException(StatusCodeEnum.COMMENT_ID_INVALID);
        }
        int result = commentDao.updateLikeNumber(commentId);
        if (result != 1) {
            logger.error("更新点赞数出错:{}", commentId);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }
    /**
     * 给评论踩一下
     */
    @Override
    public void dislike(Long commentId) {

    }
}
