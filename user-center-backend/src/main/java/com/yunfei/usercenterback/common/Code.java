package com.yunfei.usercenterback.common;

/**
 * 错误码
 */
public enum Code {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    PARAMS_NULL_ERROR(40001,"请求参数为空",""),

    NO_AUTH(40100, "没有权限", ""),
    NOT_LOGIN(40101, "未登录", ""),

    SYSTEM_ERROR(50000, "系统内部异常", ""),

    ;
    private final Integer code;
    private final String message;
    private final String description;

    Code(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
