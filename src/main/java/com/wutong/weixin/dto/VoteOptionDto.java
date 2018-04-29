package com.wutong.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "投票的选项")
public class VoteOptionDto {

    @ApiModelProperty(required = true , value = "选项的id",position = 10)
    private Long id;
    @ApiModelProperty(required = true , value = "选项的内容",position = 11)
    private String content;
    @ApiModelProperty(required = true , value = "选项赞成数量",position = 12)
    private String approveAmount;
    @ApiModelProperty(required = true , value = "选项不赞成数量",position = 13)
    private String disapproveAmount;

}
