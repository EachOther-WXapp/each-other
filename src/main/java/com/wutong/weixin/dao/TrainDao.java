package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.dto.TodayTrainInfoDto;
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
}
