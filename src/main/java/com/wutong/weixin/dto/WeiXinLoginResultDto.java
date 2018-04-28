package com.wutong.weixin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: wutong
 * @date: 2018-4-25
 * 微信登陆接口返回的结果
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class WeiXinLoginResultDto {

    private String openid;

    private String session_key;

    private String unionid;
}
