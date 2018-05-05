package com.wutong.weixin.utils.enums;

/**
 * @author feng
 * @date 2018/1/21
 * @email gongshunfeng@zhichongjia.com
 */
public enum FileSaveType {

    TRAIN(1, "培训"),
    ;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * value 代码所代表的信息
     */
    private String value;

    FileSaveType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FileSaveType{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }
}
