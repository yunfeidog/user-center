package com.yunfei.usercenterback.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {
    private String userAccount;
    private String userPassword;
}
