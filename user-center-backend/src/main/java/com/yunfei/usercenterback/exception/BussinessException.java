package com.yunfei.usercenterback.exception;

import com.yunfei.usercenterback.common.Code;

/**
 * 自定义异常 业务异常
 */
public class BussinessException extends RuntimeException {
    private final Integer code;
    private final String description;

    public BussinessException(Integer code, String message, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BussinessException(Code code) {
        super(code.getMessage());
        this.code = code.getCode();
        this.description = code.getDescription();
    }

    public BussinessException(Code code, String description) {
        super(code.getMessage());
        this.code = code.getCode();
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
