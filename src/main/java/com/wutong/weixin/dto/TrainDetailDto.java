package com.wutong.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wutong
 * @date: 2018-4-29
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "培训详情")
public class TrainDetailDto {

    @ApiModelProperty(required = true , value = "培训id",position = 9)
    private Long id;
    @ApiModelProperty(required = true , value = "培训开始时间",position = 10)
    private Date trainStartTime;
    @ApiModelProperty(required = true , value = "培训结束时间",position = 11)
    private Date trainEndTime;
    @ApiModelProperty(required = true , value = "培训主题",position = 12)
    private String theme;
    @ApiModelProperty(required = true , value = "培训讲师名字",position = 13)
    private String lecturer;
    @ApiModelProperty(required = true , value = "培训地点",position = 14)
    private String site;
    @ApiModelProperty(required = true, value = "培训详情", position = 15)
    private String detail;
    @ApiModelProperty(required = true, value = "TeamView房间号",position = 16)
    private String conferenceId;
    @ApiModelProperty(required = true, value = "培训资料github地址",position = 17)
    private String githubUrl;
    @ApiModelProperty(required = true , value = "培训开始时间字符串格式",position = 18)
    private String trainStartTimeStr;
    @ApiModelProperty(required = true , value = "培训结束时间字符串格式",position = 19)
    private String trainEndTimeStr;
    @ApiModelProperty(required = true , value = "点赞数量",position = 20)
    private Integer likeNumber;
    @ApiModelProperty(required = true , value = "图片的id",position = 22)
    private Long imageId;

}
