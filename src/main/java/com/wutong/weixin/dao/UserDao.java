package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author: wutong
 * @date: 2018-4-25
 */
@Repository
public interface UserDao extends CrudDao<User, Long> {

    /**
     *
     * @return 验证token是否过期
     */
    User queryToken(@Param("token") String token, @Param("expireTime") Date expireTime);

    /**
     *
     * @return
     */
    User queryOpenId(@Param("openId") String openId);

    /**
     *
     * @return 更新token过期时间
     */
    int updateToken(@Param("openId") String openId,
                    @Param("sessionKey") String sessionKey,
                    @Param("expireTime") Date expireTime);

    /**
     *
     * @return 更新用户信息
     */
    int updateUserInfo(User user);
}
