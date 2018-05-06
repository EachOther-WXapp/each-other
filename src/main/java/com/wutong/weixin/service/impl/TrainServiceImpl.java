package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.FileDao;
import com.wutong.weixin.dao.TrainDao;
import com.wutong.weixin.dao.UserTrainDao;
import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.dto.TrainDetailDto;
import com.wutong.weixin.entity.File;
import com.wutong.weixin.entity.Train;
import com.wutong.weixin.entity.User;
import com.wutong.weixin.entity.UserTrain;
import com.wutong.weixin.model.AddTrainModel;
import com.wutong.weixin.service.FileService;
import com.wutong.weixin.service.TrainService;
import com.wutong.weixin.service.UserService;
import com.wutong.weixin.utils.date.CalendarUtil;
import com.wutong.weixin.utils.enums.FileSaveType;
import com.wutong.weixin.utils.enums.FileType;
import com.wutong.weixin.utils.exception.BusinessException;
import com.wutong.weixin.utils.exception.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private FileService fileService;
    @Autowired
    private FileDao fileDao;

    @Value("${file.imageUrl:https://www.xiciwutong.xin/each-othe/file/images/}")
    private String imageUrl;
    /**
     * @return 获取当天的培训
     */
    @Override
    public List<TodayTrainInfoDto> todayList(String authHeader) {
        User user = userService.verifyToken(authHeader);
        Date now = new Date();
        Date startTime = CalendarUtil.getCoarseDate(now);
        Date endTime = CalendarUtil.dayOrientation(startTime, 1);
        List<TodayTrainInfoDto> list = trainDao.todayList(startTime, endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        formatTrainInfo(user, list, sdf);
        return list;
    }

    /**
     * @return 发起培训
     */
    @Override
    public void add(AddTrainModel model, String authHeader) {
        User user = userService.verifyToken(authHeader);
        logger.debug("图片base64的字符串:{}", model.getImageBase64());
        Long id;
        if (model.getImageBase64() != null) {
            id = fileService.fileUpload(FileType.IMAGE, model.getImageBase64(), "jpg", FileSaveType.TRAIN.getCode());
        } else {
            File file = fileDao.queryByPk(model.getImageId());
            if (file == null) {
                logger.error("图片id不存在,请重新上传:{}", model.getImageId());
                throw new BusinessException(StatusCodeEnum.IMAGE_NOT_EXIST);
            }
            id = model.getImageId();
        }
        int result = trainDao.insert(new Train(user.getId(), new Date(model.getStartTime()), new Date(model.getEndTime()), model.getTheme(),
                model.getLecturer(), model.getSite(), model.getConferenceId(), model.getGithubUrl(), model.getDetail(), id));
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
     * @return 培训详情
     */
    @Override
    public TrainDetailDto detail(Long trainId, String authHeader) {
        User user = userService.verifyToken(authHeader);
        TrainDetailDto dto = trainDao.detail(trainId);
        if (dto == null) {
            logger.error("培训id非法:{}", trainId);
            throw new BusinessException(StatusCodeEnum.TRAIN_ID_INVALID);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
        dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
        UserTrain userTrain = userTrainDao.userJoinFlag(user.getId(), dto.getId());
        dto.setJoinFlag(userTrain == null);
        dto.setImageUrl(imageUrl + dto.getImageId());
        return dto;
    }

    @Override
    public List<TodayTrainInfoDto> monthList(String authHeader) {
        User user = userService.verifyToken(authHeader);
        Date now = new Date();
        Date startTime = CalendarUtil.getCoarseDate(now);
        Date endTime = CalendarUtil.monthOrientation(startTime, 1);
        List<TodayTrainInfoDto> list = trainDao.monthList(startTime, endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        formatTrainInfo(user, list, sdf);
        return list;
    }

    /**
     * 格式化培训列表
     */
    private void formatTrainInfo(User user, List<TodayTrainInfoDto> list, SimpleDateFormat sdf) {
        for (TodayTrainInfoDto dto : list) {
            dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
            dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
            UserTrain userTrain = userTrainDao.userJoinFlag(user.getId(), dto.getId());
            dto.setJoinFlag(userTrain == null);
            dto.setImageUrl(imageUrl + dto.getImageId());
        }
    }

    /**
     * @return 参加过的培训
     */
    @Override
    public List<TodayTrainInfoDto> joinedTrain(String authHeader) {
        User user = userService.verifyToken(authHeader);
        List<TodayTrainInfoDto> list = trainDao.joinedTrain(user.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (TodayTrainInfoDto dto : list) {
            dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
            dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
        }
        return list;
    }

    /**
     * @return 公司历史的培训
     */
    @Override
    public List<TodayTrainInfoDto> historyTrain(String authHeader) {
        userService.verifyToken(authHeader);
        Date time = CalendarUtil.getCoarseDate(new Date());
        List<TodayTrainInfoDto> list = trainDao.historyTrain(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (TodayTrainInfoDto dto : list) {
            dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
            dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
            dto.setImageUrl(imageUrl + dto.getImageId());
        }
        return list;
    }
    /**
     * 删除自己发起的培训
     */
    @Override
    public void delete(Long trainId, String authHeader) {
        User user = userService.verifyToken(authHeader);
        int result = trainDao.deleteTrain(trainId, user.getId());
        if (result != 1) {
            logger.warn("非法的培训id:{}", trainId);
            throw new BusinessException(StatusCodeEnum.TRAIN_ID_INVALID);
        }
    }
    /**
     *
     * @return 用户自己发布的培训
     */
    @Override
    public List<TodayTrainInfoDto> publishedTrain(String authHeader) {
        User user = userService.verifyToken(authHeader);
        List<TodayTrainInfoDto> list = trainDao.publishedTrain(user.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (TodayTrainInfoDto dto : list) {
            dto.setTrainStartTimeStr(sdf.format(dto.getTrainStartTime()));
            dto.setTrainEndTimeStr(sdf.format(dto.getTrainEndTime()));
            dto.setImageUrl(imageUrl + dto.getImageId());
        }
        return list;
    }
}
