package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.UserDao;
import com.wutong.weixin.dto.UserInfoDto;
import com.wutong.weixin.dto.WeiXinLoginResultDto;
import com.wutong.weixin.entity.User;
import com.wutong.weixin.model.LoginModel;
import com.wutong.weixin.model.UserInfoModel;
import com.wutong.weixin.service.UserService;
import com.wutong.weixin.utils.date.CalendarUtil;
import com.wutong.weixin.utils.encrypt.HashUtil;
import com.wutong.weixin.utils.exception.BaseException;
import com.wutong.weixin.utils.exception.BusinessException;
import com.wutong.weixin.utils.exception.StatusCodeEnum;
import com.wutong.weixin.utils.json.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;

/**
 * @Author: wutong
 * @date: 2018-4-25
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao dao;

    /**
     * @return 微信登陆
     */
    @Override
    public String login(LoginModel model) {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        // Get请求
        String str = "appid=wx06228a0b899d0e9c&secret=e955a75d8eb8644d14196e274235363f&js_code=" + model.getCode() + "&grant_type=authorization_code";
        HttpGet httpget = new HttpGet(url);
        String body;
        try {
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (body.contains("errcode")) {
                logger.error("微信登陆失败:{}", body);
                throw new BaseException(500, "登陆失败");
            }
            logger.debug("微信登陆返回结果:{}", body);
        } catch (Exception e) {
            logger.error("调用微信登陆接口出错:{}", e.getMessage());
            throw new BaseException(500, "登陆失败");
        }
        WeiXinLoginResultDto dto = JacksonUtil.toObject(body, WeiXinLoginResultDto.class);
        if (dto == null) {
            logger.error("Json转换失败");
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
        User user = dao.queryOpenId(dto.getOpenid());
        // 新用户登陆
        String token = HashUtil.md5(dto.getOpenid());
        Date date = CalendarUtil.dayOrientation(new Date(), 60);
        if (user == null) {
            int result = dao.insert(new User(dto.getOpenid(), dto.getSession_key(), dto.getUnionid(), token, date));
            if (result != 1) {
                logger.error("新增用户失败");
                throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
            }
        } else { //更新token过期时间和session_key
            int result = dao.updateToken(dto.getOpenid(), dto.getSession_key(), date);
            if (result != 1) {
                logger.error("更新用户表失败");
                throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
            }
        }
        return token;
    }

    /**
     * @param token token
     * @return 验证用户token是否过期
     */
    @Override
    public User verifyToken(String token) {
        logger.debug("用户token信息:{}", token);
        if (token == null) {
            logger.error("token 不能为空");
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
        if (!token.startsWith("Bearer ")) {
            logger.error("非法的token");
            throw new BusinessException(StatusCodeEnum.INVALID_AUTHORIZATION);
        }
        final String authToken = token.substring("Bearer ".length());
        User user = dao.queryToken(authToken, new Date());
        if (user == null) {
            logger.error("非法的token");
            throw new BusinessException(StatusCodeEnum.INVALID_AUTHORIZATION);
        }
        return user;
    }

    /**
     * 用户登录后更新用户信息
     */
    @Override
    public void updateUserInfo(UserInfoModel model, String authHeader) {
        User user = verifyToken(authHeader);
        logger.info("根据token查询到的user信息:{}", user);
        user = new User(user.getId(), model.getNickName(), model.getGender(), model.getAvatarUrl(), model.getCity(),
                model.getProvince(), model.getCountry());
        int result = dao.updateUserInfo(user);
        if (result != 1) {
            logger.error("更新用户表失败:{}", user);
            throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
        }
    }

    /**
     *
     * @return token获取用户信息
     */
    @Override
    public UserInfoDto userInfo(String authHeader) {
        User user = verifyToken(authHeader);
        return new UserInfoDto(user.getAvatarUrl(), user.getNickName(), user.getCity(), user.getCountry(), user.getGender(),
                user.getProvince(), user.getToken());
    }
}
