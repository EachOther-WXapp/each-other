package com.wutong.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author：wutong
 * @date：2018-04-27
 * @email：919964333@qq.com
 * 培训信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Train extends Entity {
    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifiedTime;

    /**
     * 创建者id
     */
    private Long userId;

    /**
     * 培训开始时间
     */
    private Date trainStartTime;

    /**
     * 培训结束时间
     */
    private Date trainEndTime;

    /**
     * 主题
     */
    private String theme;

    /**
     * 讲师名字
     */
    private String lecturer;

    /**
     * 培训地点
     */
    private String site;

    /**
     * TeamView的房间号
     */
    private String conferenceId;

    /**
     * 上传github的资料地址
     */
    private String githubUrl;

    /**
     * 培训详情
     */
    private String detail;
    /**
     * 点赞数
     */
    private Integer likeNumber;
    /**
     * 培训展示的图片
     */
    private Long imageId;


    public Train(Long userId, Date trainStartTime, Date trainEndTime, String theme, String lecturer, String site, String conferenceId, String githubUrl, String detail, Long imageId) {
        this.userId = userId;
        this.trainStartTime = trainStartTime;
        this.trainEndTime = trainEndTime;
        this.theme = theme;
        this.lecturer = lecturer;
        this.site = site;
        this.conferenceId = conferenceId;
        this.githubUrl = githubUrl;
        this.detail = detail;
        this.imageId = imageId;
    }
}