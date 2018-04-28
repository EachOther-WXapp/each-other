package com.wutong.weixin.utils.exception;

/**
 *
 * 基础异常类
 * 默认状态码为：500
 */
public class BaseException extends RuntimeException{

    private int status = 500;

    public int getStatus() {
        return status;
    }

    public BaseException(String message) {
        this(500, message);
    }

    public BaseException(int status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(String message, Throwable cause) {
        this(500, message, cause);
    }

    public BaseException(int status, String message, Throwable cause) {
        super(message, cause);
    }



}
