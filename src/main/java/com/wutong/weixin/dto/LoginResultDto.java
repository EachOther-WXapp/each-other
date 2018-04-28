package com.wutong.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: wutong
 * @date: 2018-4-25
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "微信登陆返回token")
public class LoginResultDto {

    @ApiModelProperty(required = true , value = "自定义的token",position = 10)
    private String token;
}
