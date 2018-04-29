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
 * 投票选项组
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VoteOption extends Entity {
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
     * 投票选项发起者id
     */
    private Long userId;

    /**
     * 投票组id(第一个发起者会自动创建voteId)
     */
    private Long voteId;

    /**
     * 投票选项
     */
    private String content;

    /**
     * 赞成数量
     */
    private Integer approveAmount;

    /**
     * 不赞成数量
     */
    private Integer disapproveAmount;

    public VoteOption(Long userId, Long voteId, String content) {
        this.userId = userId;
        this.voteId = voteId;
        this.content = content;
    }
}