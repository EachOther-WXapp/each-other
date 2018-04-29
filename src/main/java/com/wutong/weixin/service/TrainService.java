package com.wutong.weixin.service;

import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.dto.TrainDetailDto;
import com.wutong.weixin.model.AddTrainModel;

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

    /**
     *
     * @return 发起培训
     */
    void add(AddTrainModel model, String authHeader);

    /**
     * 给培训点赞
     */
    void like(Long trainId);

    /**
     * 用户加入培训
     */
    void joinTrain(Long trainId, String authHeader);

    /**
     *
     * @return 培训详情
     */
    TrainDetailDto detail(Long trainId, String authHeader);
}
