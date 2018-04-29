package com.wutong.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author：wutong
 * @date：2018-04-29
 * @email：919964333@qq.com
 * 用户参加培训表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
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

    public UserTrain(Long userId, Long trainId) {
        this.userId = userId;
        this.trainId = trainId;
    }
}