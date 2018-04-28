package com.wutong.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author：wutong
 * @date：2018-04-24
 * @email：919964333@qq.com
 * 用户表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class User extends Entity {
    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifiedTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（md5加密）
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别 值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer gender;

    /**
     * 用户微信头像url
     */
    private String avatarUrl;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 微信的openid
     */
    private String openId;

    /**
     * 微信的session_key
     */
    private String sessionKey;

    /**
     * 微信的UnionID
     */
    private String unionId;

    /**
     * 用户登录的token
     */
    private String token;

    /**
     * 用户登录token的过期时间
     */
    private Date expireTime;

    /**
     * 账号状态（-1：禁用，0：正常，1：撤销）
     */
    private Byte status;

    public User(String openId, String sessionKey, String unionId, String token, Date expireTime) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.unionId = unionId;
        this.token = token;
        this.expireTime = expireTime;
    }

    public User(Long id, String nickName, Integer gender, String avatarUrl, String city, String province, String country) {
        this.id = id;
        this.nickName = nickName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.city = city;
        this.province = province;
        this.country = country;
    }
}