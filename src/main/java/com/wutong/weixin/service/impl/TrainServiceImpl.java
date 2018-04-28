package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.TrainDao;
import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.service.TrainService;
import com.wutong.weixin.service.UserService;
import com.wutong.weixin.utils.date.CalendarUtil;
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
}
