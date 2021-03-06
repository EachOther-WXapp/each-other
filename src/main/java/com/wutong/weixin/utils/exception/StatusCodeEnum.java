package com.wutong.weixin.utils.exception;

/**
 */
public enum StatusCodeEnum {

    /**
     * http错误码对应的错误信息
     */
    SUCCESS(200, "OK"),
    BAD_REQUEST(400, "Bad Request!"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    SERVER_ERROR(500, "Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    //授权相关 状态码同 401
    INVALID_AUTHORIZATION(401, "无效的Authorization"),
    USERNAME_OR_PASSWORD_ERROR(401, "用户名或密码错误"),

    USER_NOT_ADMIN_FORBIDDEN_LOGIN(403, "非管理员用户禁止登录"),
    ONLY_SUPER_ADMIN_CAN_ACCESS(403, "只有超级管理员才能访问"),
    SUPER_ADMIN_CANNOT_RESET_SELF_PWD(403, "超级管理员不可以重置自己的密码"),
    VERSION_ERROR(499, "程序需要升级"),

    PARAMETER_MISSING_OR_INCORRECT(1005, "参数缺失或不正确"),

    /**
     * 评论
     */
    COMMENT_ID_INVALID(2000, "无效的评论id"),

    /**
     * train 培训
     */
    TRAIN_ID_INVALID(2100,"无效的培训id"),

    /**
     * vote 投票
     */
    VOTE_ONLY_ONE(2200, "只能发起一个投票,请在已有投票中添加投票选项"),
    VOTE_ID_INVALID(2201, "非法的投票组id"),
    VOTE_ID_COUNT_MORE(2202, "投票已有8个,不能新增"),
    VOTE_OPTION_ID_INVALID(2203, "非法的选项id"),

    /**
     * 文件服务
     */
    FILE_SUFFIX_NOT_SUPPORT(2300, "文件格式不支持"),
    FILE_UPLOAD_FAILED(2301, "文件上传失败"),
    FILE_NOT_EXIST(2302, "文件不存在"),
    IMAGE_NOT_EXIST(2303, "图片不存在"),
    IMAGE_IS_EMPTY(2304,"上传图片为空"),

    ;


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;

    StatusCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
