package com.yunfei.usercenterback.model.dto;


import lombok.Data;

@Data
public class UserRegisterDto {
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
