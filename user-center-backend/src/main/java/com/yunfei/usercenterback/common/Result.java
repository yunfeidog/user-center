package com.yunfei.usercenterback.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class Result<T> implements Serializable {
    private Integer code;
    private T data;
    private String message;
    private String description;

    public Result(Integer code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public Result(Integer code, T data,String message) {
        this(code,data,message,"");
    }

    public Result(Integer code,T data){
        this(code,data,"","");
    }

    public Result(Code code) {
        this(code.getCode(),null,code.getMessage(),code.getDescription());

    }
}
