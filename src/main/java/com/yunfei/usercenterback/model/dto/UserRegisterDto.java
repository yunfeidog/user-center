package com.yunfei.usercenterback.model.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDto implements Serializable {
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String ikunCode;
}
