package com.yunfei.usercenterback.common;

public class ResultUtils {
    public static <T> Result<T> success(T data) {
        return new Result<>(0, data, "ok");
    }

    public static Result error(Integer code, String message, String description) {
        return new Result(code, null, message, description);
    }

    public static Result error(Code code, String message, String description) {
        return new Result(code.getCode(), message, description);
    }

    public static Result error(Code code, String description) {
        return new Result(code.getCode(), code.getMessage(), description);
    }
}
