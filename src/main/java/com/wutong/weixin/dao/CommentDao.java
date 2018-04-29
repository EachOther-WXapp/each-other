package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.entity.Comment;
import org.springframework.stereotype.Repository;

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
}
