package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.entity.UserTrain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: wutong
 * @date: 2018-4-29
 * 培训和用户关联表
 */
@Repository
public interface UserTrainDao extends CrudDao<UserTrain, Long> {

    /**
     *
     * @return 用户是否加入了培训
     */
    UserTrain userJoinFlag(@Param("userId") Long userId, @Param("trainId") Long trainId);
}
