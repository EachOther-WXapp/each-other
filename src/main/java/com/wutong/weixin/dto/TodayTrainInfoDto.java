package com.wutong.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wutong
 * @date 2018/4/27
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "当天的培训信息")
public class TodayTrainInfoDto {

    @ApiModelProperty(required = true , value = "id",position = 9)
    private Long id;
    @ApiModelProperty(required = true , value = "开始时间",position = 10)
    private Date trainStartTime;
    @ApiModelProperty(required = true , value = "结束时间",position = 11)
    private Date trainEndTime;
    @ApiModelProperty(required = true , value = "培训主题",position = 12)
    private String theme;
    @ApiModelProperty(required = true , value = "讲师名字",position = 13)
    private String lecturer;
    @ApiModelProperty(required = true , value = "培训地点",position = 14)
    private String site;
    @ApiModelProperty(required = true , value = "开始时间字符串",position = 16)
    private String trainStartTimeStr;
    @ApiModelProperty(required = true , value = "结束时间字符串",position = 18)
    private String trainEndTimeStr;











}
