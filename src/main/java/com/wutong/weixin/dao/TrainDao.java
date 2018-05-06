package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.dto.TodayTrainInfoDto;
import com.wutong.weixin.dto.TrainDetailDto;
import com.wutong.weixin.entity.Train;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author wutong
 * @date 2018/4/27
 */
@Repository
public interface TrainDao extends CrudDao<Train, Long> {

    /**
     *
     * @return 返回当天的培训内容
     */
    List<TodayTrainInfoDto> todayList(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     *
     * @return 给培训点赞
     */
    int updateLikeNumber(Long trainId);

    /**
     *
     * @return 查询培训详情
     */
    TrainDetailDto detail(Long trainId);

    /**
     *
     * @return 最近一个月以后的培训列表
     */
    List<TodayTrainInfoDto> monthList(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     *
     * @return 用户参加过的培训
     */
    List<TodayTrainInfoDto> joinedTrain(Long userId);

    /**
     *
     * @return 公司历史的培训
     */
    List<TodayTrainInfoDto> historyTrain(Date time);

    /**
     *
     * @return 删除自己的培训id
     */
    int deleteTrain(@Param("id") Long id, @Param("userId") Long userId);

    /**
     *
     * @return 用户自己发布的培训
     */
    List<TodayTrainInfoDto> publishedTrain(Long userId);
}
