package com.wutong.weixin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "给培训评论")
public class AddTrainCommentModel {

    @ApiModelProperty(required = true , value = "培训ID",position = 10)
    @NotNull(message = "{trainId.notnull}")
    private Long trainId;

    @ApiModelProperty(required = true , value = "培训内容",position = 12)
    @NotNull(message = "{content.notnull}")
    private String content;


}
