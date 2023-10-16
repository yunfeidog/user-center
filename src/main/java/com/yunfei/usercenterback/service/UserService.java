package com.yunfei.usercenterback.service;

import com.yunfei.usercenterback.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunfei.usercenterback.model.dto.UserLoginDto;
import com.yunfei.usercenterback.model.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author houyunfei
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-10-16 08:52:27
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userRegisterDto 用户注册信息
     * @return 用户id
     */
    long userRegister(UserRegisterDto userRegisterDto);

    /**
     * 用户登录
     *
     * @param userLoginDto 用户登录信息
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(UserLoginDto userLoginDto, HttpServletRequest request);

}
