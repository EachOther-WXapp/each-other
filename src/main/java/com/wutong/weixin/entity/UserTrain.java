package com.wutong.weixin.entity;

import java.util.Date;

/**
 * @author：wutong
 * @date：2018-04-29
 * @email：919964333@qq.com
 * 
 */
public class UserTrain extends Entity {
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
     * 用户id
     */
    private Long userId;

    /**
     * 培训id
     */
    private Long trainId;

    /**
     * 获取 id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 更新时间
     *
     * @return 更新时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置 更新时间
     *
     * @param modifiedTime 更新时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 获取 用户id
     *
     * @return 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取 培训id
     *
     * @return 培训id
     */
    public Long getTrainId() {
        return trainId;
    }

    /**
     * 设置 培训id
     *
     * @param trainId 培训id
     */
    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }
}