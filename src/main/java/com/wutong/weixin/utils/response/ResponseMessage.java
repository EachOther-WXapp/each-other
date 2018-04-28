package com.wutong.weixin.utils.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 * 响应结果消息
 */
@ApiModel(description = "响应结果消息")
public class ResponseMessage<T> implements Serializable {

    @ApiModelProperty(required = true, value = "状态码")
    private Integer code;

    @ApiModelProperty(required = true, value = "调用结果消息")
    private String msg;

    @ApiModelProperty(required = true, value = "成功时响应数据")
    private T data;

    @ApiModelProperty(required = true, value = "时间戳", dataType = "Long")
    private Long timestamp;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public ResponseMessage() {
    }

    public ResponseMessage(Integer code, String msg, T data, Long timestamp) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
