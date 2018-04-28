package com.wutong.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author wutong
 * @date 2018/4/28
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户信息")
public class UserInfoDto {


    @ApiModelProperty(required = true , value = "头像url地址",position = 10)
    private String avatarUrl;
    @ApiModelProperty(required = true , value = "微信昵称",position = 11)
    private String nickName;
    @ApiModelProperty(required = true , value = "城市",position = 12)
    private String city;
    @ApiModelProperty(required = true , value = "国家",position = 14)
    private String country;
    @ApiModelProperty(required = true , value = "性别",position = 16)
    private Integer gender;
    @ApiModelProperty(required = true , value = "省份",position = 18)
    private String province;
    @ApiModelProperty(required = true , value = "token",position = 19)
    private String token;


}
