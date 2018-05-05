package com.wutong.weixin.controller;

import com.wutong.weixin.service.FileService;
import com.wutong.weixin.utils.response.ResponseMessage;
import com.wutong.weixin.utils.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：Tom
 * Date：2017/11/1
 * Email：bibi8086@gmail.com
 */
@RestController
@RequestMapping(value = "/file/", produces = "application/json; charset=UTF-8")
@Api(tags = {"eachOther - file"}, description = "文件和图片")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FileService fileService;

    @ApiOperation( nickname = "images/", value = "图片显示", notes = "图片显示：将以文件流形式返回，浏览器将以该文件格式显示文件内容")
    @ApiImplicitParam(required = true, dataType = "Long", name = "id", value = "文件id", paramType = "path")
    @GetMapping(value = "images/{id}")
    public ResponseMessage imageShow(@PathVariable Long id) {
        fileService.imageShow(id);
        return ResponseUtil.ok();
    }


}
