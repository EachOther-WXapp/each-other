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
 * @Author: wutong
 * @date: 2018-4-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "发起培训")
public class AddTrainModel {

    @ApiModelProperty(required = true , value = "培训开始时间",position = 10)
    @NotNull(message = "{startTime.notnull}")
    private Long startTime;

    @ApiModelProperty(required = true , value = "培训结束时间",position = 11)
    @NotNull(message = "{endTime.notnull}")
    private Long endTime;

    @ApiModelProperty(required = true , value = "培训主题",position = 12)
    @NotNull(message = "{theme.notnull}")
    private String theme;

    @ApiModelProperty(required = true , value = "培训讲师名字",position = 13)
    @NotNull(message = "{lecturer.notnull}")
    private String lecturer;

    @ApiModelProperty(required = true , value = "培训地点",position = 14)
    @NotNull(message = "{site.notnull}")
    private String site;

    @ApiModelProperty(value = "培训详情",position = 15)
    private String detail;

    @ApiModelProperty(value = "TeamView房间号",position = 16)
    private String conferenceId;

    @ApiModelProperty(value = "培训资料github地址",position = 17)
    private String githubUrl;









}
