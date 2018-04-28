package com.wutong.weixin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @Author: wutong
 * @date: 2018-4-25
 * 微信登陆
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "微信登陆")
public class LoginModel {

    @ApiModelProperty(required = true , value = "微信login返回的code",position = 10)
    @NotBlank(message = "{code.notnull}")
    private String code;
}
