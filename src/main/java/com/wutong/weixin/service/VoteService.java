package com.wutong.weixin.service;

import com.wutong.weixin.dto.VoteOptionDto;

import java.util.List;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
public interface VoteService {

    /**
     * 新增发起一个投票
     */
    void newVote(String content, String authHeader);

    /**
     * 投票组新增一个选项
     */
    void addOption(String content, String authHeader);

    /**
     * 投票选项id加1赞成
     */
    void approve(Long voteOptionId);

    /**
     * 投票选项id加1 不赞成
     */
    void disapprove(Long voteOptionId);

    /**
     *
     * @return 列出当前的培训投票选项
     */
    List<VoteOptionDto> list(String authHeader);

    /**
     * 删除自己的投票选项
     */
    void delete(Long voteOptionId, String authHeader);

    /**
     *
     * @return 用户发布的投票
     */
    List<VoteOptionDto> publishedVote(String authHeader);
}
