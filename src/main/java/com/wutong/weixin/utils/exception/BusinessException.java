package com.wutong.weixin.utils.exception;

/**
 * 业务逻辑异常类
 */
public class BusinessException extends BaseException {

    public BusinessException(StatusCodeEnum statusCodeEnum) {
        super(statusCodeEnum.getCode(), statusCodeEnum.getMsg());
    }

    public BusinessException(StatusCodeEnum statusCodeEnum, String message) {
        super(statusCodeEnum.getCode(), message);
    }

    public BusinessException(StatusCodeEnum statusCodeEnum, Throwable cause) {
        super(statusCodeEnum.getCode(), statusCodeEnum.getMsg(), cause);
    }

    public BusinessException(StatusCodeEnum statusCodeEnum, String message, Throwable cause) {
        super(statusCodeEnum.getCode(), message, cause);
    }
}
