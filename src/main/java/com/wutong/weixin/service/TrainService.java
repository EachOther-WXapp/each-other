package com.wutong.weixin.service;

import com.wutong.weixin.dto.TodayTrainInfoDto;

import java.util.List;

/**
 * @author wutong
 * @date 2018/4/27
 */
public interface TrainService {

    /**
     *
     * @return 获取当天的培训
     */
    List<TodayTrainInfoDto> todayList(String authHeader);
}
