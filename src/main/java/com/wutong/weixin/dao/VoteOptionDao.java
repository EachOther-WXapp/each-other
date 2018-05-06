package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.dto.VoteOptionDto;
import com.wutong.weixin.entity.VoteOption;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@Repository
public interface VoteOptionDao extends CrudDao<VoteOption, Long> {

    /**
     *
     * @return 查看一个投票已有的选项
     */
    int queryVoteCount();

    /**
     *
     * @return 添加一个赞成数量
     */
    int addApproveNumber(@Param("voteOptionId") Long voteOptionId);

    /**
     *
     * @return 添加一个不赞成数量
     */
    int addDisapproveNumber(@Param("voteOptionId") Long voteOptionId);

    /**
     *
     * @return 投票的选项
     */
    List<VoteOptionDto> queryVoteOption();

    /**
     *
     * @return 选项的总的赞成数量
     */
    Integer queryTotalApproveAmount();
    /**
     *
     * @return 选项的总的不赞成数量
     */
    Integer queryTotalDisapproveAmount();

    /**
     *
     * @return 删除投票选项
     */
    int deleteOption(@Param("userId") Long userId, @Param("id") Long id);

    /**
     *
     * @return 用户发布的投票
     */
    List<VoteOptionDto> publishedVote(@Param("userId") Long userId);
}
