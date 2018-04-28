package com.wutong.weixin.service;

import com.wutong.weixin.dto.LoginResultDto;
import com.wutong.weixin.dto.UserInfoDto;
import com.wutong.weixin.entity.User;
import com.wutong.weixin.model.LoginModel;
import com.wutong.weixin.model.UserInfoModel;
/**
 * @Author: wutong
 * @date: 2018-4-25
 */
public interface UserService {

    /**
     *
     * @return 微信登陆
     */
    String login(LoginModel model);

    /**
     *
     * @param token token
     * @return 验证用户token是否过期
     */
    User verifyToken(String token);

    /**
     * 用户登录后更新用户信息
     */
    void updateUserInfo(UserInfoModel model, String authHeader);

    /**
     *
     * @return token获取用户信息
     */
    UserInfoDto userInfo(String authHeader);
}
