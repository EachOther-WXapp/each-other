package com.wutong.weixin.service;


import com.wutong.weixin.utils.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传下载服务
 */
public interface FileService  {


    /**
     * 图片显示
     *
     */
    void imageShow(Long id);

    /**
     *
     * @return 保存图片
     */
    Long fileUpload(FileType fileType, String fileBase64, String suffix, Integer type);

    /**
     *
     * @return 上传图片
     */
    Long imageForm(MultipartFile file);
}
