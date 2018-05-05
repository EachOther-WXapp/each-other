package com.wutong.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: wutong
 * @date: 2018-5-5
 * 培训的所有评论
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "培训的评论内容")
public class TrainCommentDto {

    @ApiModelProperty(required = true , value = "此条评论的id",position = 10)
    private Long id;
    @ApiModelProperty(required = true , value = "评论用户的id",position = 11)
    private Long userId;
    @ApiModelProperty(required = true , value = "评论的内容",position = 12)
    private String content;
    @ApiModelProperty(required = true , value = "点赞数",position = 13)
    private Integer likeNumber;
    @ApiModelProperty(required = true , value = "踩一下的数目",position = 14)
    private Integer dislikeNumber;


}
