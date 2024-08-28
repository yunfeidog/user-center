package com.yunfei.usercenterback.common;

/**
 * 用户常量 权限默认就是 public static final
 */
public interface UserConstant {
    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login_state";

    /**
     * 权限  0：普通用户  1：管理员
     */
    int DEFAULT_USER_ROLE = 0;

    int ADMIN_USER_ROLE = 1;

}
