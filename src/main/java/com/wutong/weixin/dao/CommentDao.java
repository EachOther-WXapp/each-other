package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.dto.TrainCommentDto;
import com.wutong.weixin.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wutong
 * @date: 2018-4-29
 * 评论表
 */
@Repository
public interface CommentDao extends CrudDao<Comment, Long> {

    /**
     *
     * @return 增加一个点赞
     */
    int updateLikeNumber(Long commentId);

    /**
     *
     * @return 给评论踩一下
     */
    int updateDisLikeNumber(Long commentId);

    /**
     *
     * @return 培训的评论列表
     */
    List<TrainCommentDto> commentList(Long trainId);
}
