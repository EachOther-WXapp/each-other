package com.wutong.weixin.model;

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
 * @date 2018/4/27
 */

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "获取微信用户信息并更新")
public class UserInfoModel {

    @ApiModelProperty(required = true , value = "头像url地址",position = 10)
    @NotBlank(message = "{avatarUrl.notnull}")
    private String avatarUrl;
    @ApiModelProperty(required = true , value = "微信昵称",position = 11)
    @NotBlank(message = "{nickName.notnull}")
    private String nickName;
    @ApiModelProperty(required = true , value = "城市",position = 12)
    @NotBlank(message = "{city.notnull}")
    private String city;
    @ApiModelProperty(required = true , value = "国家",position = 14)
    @NotBlank(message = "{country.notnull}")
    private String country;
    @ApiModelProperty(required = true , value = "性别",position = 16)
    @NotNull(message = "{gender.notnull}")
    private Integer gender;
    @ApiModelProperty(required = true , value = "省份",position = 18)
    @NotBlank(message = "{province.notnull}")
    private String province;



}
