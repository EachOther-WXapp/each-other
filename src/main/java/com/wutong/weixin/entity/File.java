package com.wutong.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Author：Tom
 * Date：2017/11/07
 * Email：bibi8086@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class File extends Entity {
    /**
     * 文件id
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
     * 文件路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件sha1
     */
    private String sha1;

    /**
     * 文件状态
     */
    private Integer status;
    /**
     * 文件类型
     */
    private Integer type;


    public File(Long id, String path, Long size, String md5, String sha1, Integer type) {
        this.id = id;
        this.path = path;
        this.size = size;
        this.md5 = md5;
        this.sha1 = sha1;
        this.type = type;
    }
}