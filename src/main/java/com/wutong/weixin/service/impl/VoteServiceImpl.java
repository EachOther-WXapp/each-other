package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.VoteDao;
import com.wutong.weixin.dao.VoteOptionDao;
import com.wutong.weixin.dto.VoteOptionDto;
import com.wutong.weixin.entity.User;
import com.wutong.weixin.entity.Vote;
import com.wutong.weixin.entity.VoteOption;
import com.wutong.weixin.service.UserService;
import com.wutong.weixin.service.VoteService;
import com.wutong.weixin.utils.exception.BusinessException;
import com.wutong.weixin.utils.exception.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: wutong
 * @date: 2018-4-29
 * 培训投票相关服务
 */
@Service
public class VoteServiceImpl implements VoteService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private VoteDao voteDao;
    @Autowired
    private VoteOptionDao voteOptionDao;


    /**
     * 新增发起一个投票
     */
//    @Transactional
    @Override
    public void newVote(String content, String authHeader) {
        User user = userService.verifyToken(authHeader);
        int count = voteDao.queryVoteCount();
        if (count >= 1) {
            logger.warn("已有一个投票,不能添加新的投票");
            throw new BusinessException(StatusCodeEnum.VOTE_ONLY_ONE);
        }
        Vote vote = new Vote(0);
        int result = voteDao.insert(vote);
        if (result != 1) {
            logger.error("插入vote表出错:{}", vote);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
        VoteOption voteOption = new VoteOption(user.getId(), vote.getId(), content);
        result = voteOptionDao.insert(voteOption);
        if (result != 1) {
            logger.error("插入vote表出错:{}", vote);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }

    }
    /**
     * 投票组新增一个选项
     */
    @Override
    public void addOption(String content, Long voteId, String authHeader) {
        User user = userService.verifyToken(authHeader);
        Vote vote = voteDao.queryByPk(voteId);
        if (vote == null) {
            logger.error("非法的voteId:{}", voteId);
            throw new BusinessException(StatusCodeEnum.VOTE_ID_INVALID);
        }
        int count = voteOptionDao.queryVoteCount(voteId);
        if (count >= 8) {
            logger.warn("一个投票组的选项不能超过8个:{}", count);
            throw new BusinessException(StatusCodeEnum.VOTE_ID_COUNT_MORE);
        }
        int result = voteOptionDao.insert(new VoteOption(user.getId(), voteId, content));
        if (result != 1) {
            logger.error("插入vote表出错:{}", vote);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }

    /**
     * 投票选项id加1赞成
     */
    @Override
    public void approve(Long voteOptionId) {
        VoteOption voteOption = voteOptionDao.queryByPk(voteOptionId);
        if (voteOption == null) {
            logger.warn("非法的选项id:{}", voteOptionId);
            throw new BusinessException(StatusCodeEnum.VOTE_OPTION_ID_INVALID);
        }
        int result = voteOptionDao.addApproveNumber(voteOptionId);
        if (result != 1) {
            logger.error("更新投票选项赞成数量出错:{}", voteOptionId);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }
    /**
     * 投票选项id加1 不赞成
     */
    @Override
    public void disapprove(Long voteOptionId) {
        VoteOption voteOption = voteOptionDao.queryByPk(voteOptionId);
        if (voteOption == null) {
            logger.warn("非法的选项id:{}", voteOptionId);
            throw new BusinessException(StatusCodeEnum.VOTE_OPTION_ID_INVALID);
        }
        int result = voteOptionDao.addDisapproveNumber(voteOptionId);
        if (result != 1) {
            logger.error("更新投票选项赞成数量出错:{}", voteOptionId);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }

    /**
     *
     * @return 列出当前的培训投票选项
     */
    @Override
    public List<VoteOptionDto> list(String authHeader) {
        userService.verifyToken(authHeader);
        Vote vote = voteDao.queryPresentVote();
        if (vote == null) {
            return new ArrayList<>();
        }
        return voteOptionDao.queryVoteOption(vote.getId());
    }
}
