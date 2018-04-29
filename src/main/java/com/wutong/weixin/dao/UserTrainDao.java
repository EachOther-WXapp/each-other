package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.entity.UserTrain;
import org.springframework.stereotype.Repository;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@Repository
public interface UserTrainDao extends CrudDao<UserTrain, Long> {
}
