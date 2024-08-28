package com.yunfei.usercenterback.exception;

import com.yunfei.usercenterback.common.Code;
import com.yunfei.usercenterback.common.Result;
import com.yunfei.usercenterback.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BussinessException.class)
    public Result businessExceptionHandler(BussinessException e) {
        log.error("businessException:" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(Code.SYSTEM_ERROR, e.getMessage(), "");
    }
}
