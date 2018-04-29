package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.entity.Vote;
import org.springframework.stereotype.Repository;

/**
 * @Author: wutong
 * @date: 2018-4-29
 * 投票组
 */
@Repository
public interface VoteDao extends CrudDao<Vote, Long> {

    /**
     *
     * @return 查询已发起的投票数量
     */
    int queryVoteCount();

    /**
     *
     * @return 查询当前的投票
     */
    Vote queryPresentVote();
}
