package com.yunfei.usercenterback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunfei.usercenterback.model.domain.User;
import com.yunfei.usercenterback.model.dto.UserLoginDto;
import com.yunfei.usercenterback.model.dto.UserRegisterDto;
import com.yunfei.usercenterback.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            throw new RuntimeException("参数不能为空");
        }
        long id = userService.userRegister(userRegisterDto);
        return id;
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        if (userLoginDto == null) {
            throw new RuntimeException("参数不能为空");
        }
        String userAccount = userLoginDto.getUserAccount();
        String userPassword = userLoginDto.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new RuntimeException("用户名或密码不能为空");
        }
        return userService.userLogin(userLoginDto, request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("username", username);
        }
        return userService.list(userQueryWrapper);
    }

    @PostMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        if (id < 0) {
            return false;
        }
        return userService.removeById(id);
    }
}
