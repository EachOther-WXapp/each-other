package com.wutong.weixin.utils.application;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public class RequestHolder {

    /**
     * 注意：使用multipart/form-data形式提交文件，不要这样获取request对象，最好使用入参形式
     * @return
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
