package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.TrainDao;
import com.wutong.weixin.dao.UserTrainDao;
import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.dto.TrainDetailDto;
import com.wutong.weixin.entity.Train;
import com.wutong.weixin.entity.User;
import com.wutong.weixin.entity.UserTrain;
import com.wutong.weixin.model.AddTrainModel;
import com.wutong.weixin.service.TrainService;
import com.wutong.weixin.service.UserService;
import com.wutong.weixin.utils.date.CalendarUtil;
import com.wutong.weixin.utils.exception.BusinessException;
import com.wutong.weixin.utils.exception.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wutong
 * @date 2018/4/27
 */
@Service
public class TrainServiceImpl implements TrainService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private TrainDao trainDao;
    @Autowired
    private UserTrainDao userTrainDao;

    /**
     *
     * @return 获取当天的培训
     */
    @Override
    public List<TodayTrainInfoDto> todayList(String authHeader) {
        userService.verifyToken(authHeader);
        Date now = new Date();
        Date startTime = CalendarUtil.getCoarseDate(now);
        Date endTime = CalendarUtil.dayOrientation(startTime, 1);
        List<TodayTrainInfoDto> list = trainDao.todayList(startTime, endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (TodayTrainInfoDto dto : list) {
            dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
            dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
        }
        return list;
    }

    /**
     *
     * @return 发起培训
     */
    @Override
    public void add(AddTrainModel model, String authHeader) {
        User user = userService.verifyToken(authHeader);
        int result = trainDao.insert(new Train(user.getId(), new Date(model.getStartTime()), new Date(model.getEndTime()), model.getTheme(),
                model.getLecturer(), model.getSite(), model.getConferenceId(), model.getGithubUrl(), model.getDetail()));
        if (result != 1) {
            logger.error("新增培训出错:{}", model);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }

    }

    /**
     * 给培训点赞
     */
    @Override
    public void like(Long trainId) {
        int result = trainDao.updateLikeNumber(trainId);
        if (result != 1) {
            logger.error("培训点赞出错:{}", trainId);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }
    /**
     * 用户加入培训
     */
    @Override
    public void joinTrain(Long trainId, String authHeader) {
        User user = userService.verifyToken(authHeader);
        Train train = trainDao.queryByPk(trainId);
        if (train == null) {
            logger.error("培训id非法:{}", trainId);
            throw new BusinessException(StatusCodeEnum.TRAIN_ID_INVALID);
        }
        int result = userTrainDao.insert(new UserTrain(user.getId(), trainId));
        if (result != 1) {
            logger.error("加入培训出错:{}", trainId);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }

    /**
     *
     * @return 培训详情
     */
    @Override
    public TrainDetailDto detail(Long trainId, String authHeader) {
        userService.verifyToken(authHeader);
        TrainDetailDto dto = trainDao.detail(trainId);
        if (dto == null) {
            logger.error("培训id非法:{}", trainId);
            throw new BusinessException(StatusCodeEnum.TRAIN_ID_INVALID);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
        dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
        return dto;
    }
}
