package com.wutong.weixin.utils.response;


public class ResponseUtil {

    public static <T> ResponseMessage<T> ok(){
        return ok(null);
    }

    public static <T> ResponseMessage<T> ok(T data){
        return new ResponseMessage<>(200, "OK", data, System.currentTimeMillis());
    }

    public static <T> ResponseMessage<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> ResponseMessage<T> error(int status, String msg) {
        return new ResponseMessage<>(status, msg, null, System.currentTimeMillis());
    }

    public static <T> ResponseMessage<T> error(int status, String msg, T data) {
        return new ResponseMessage<>(status, msg, data, System.currentTimeMillis());
    }

}
