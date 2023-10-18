package com.yunfei.usercenterback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunfei.usercenterback.common.Code;
import com.yunfei.usercenterback.common.Result;
import com.yunfei.usercenterback.common.ResultUtils;
import com.yunfei.usercenterback.common.UserConstant;
import com.yunfei.usercenterback.exception.BussinessException;
import com.yunfei.usercenterback.model.domain.User;
import com.yunfei.usercenterback.model.dto.UserLoginDto;
import com.yunfei.usercenterback.model.dto.UserRegisterDto;
import com.yunfei.usercenterback.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            throw new BussinessException(Code.PARAMS_ERROR);
        }
        long id = userService.userRegister(userRegisterDto);
        return ResultUtils.success(id);
    }

    @PostMapping("/login")
    public Result<User> userLogin(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        if (userLoginDto == null) {
            throw new BussinessException(Code.PARAMS_ERROR);
        }
        String userAccount = userLoginDto.getUserAccount();
        String userPassword = userLoginDto.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BussinessException(Code.PARAMS_ERROR);
        }
        User user = userService.userLogin(userLoginDto, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public Result<Integer> logout(HttpServletRequest request) {
        if (request == null) {
            throw new BussinessException(Code.NOT_LOGIN);
        }
        userService.logout(request);
        return ResultUtils.success(1);
    }

    @GetMapping("/search")
    public Result<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BussinessException(Code.NO_AUTH);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("username", username);
        }
        List<User> userList = userService.list(userQueryWrapper);
        List<User> users = userList.stream().map(user -> {
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
        return ResultUtils.success(users);
    }

    @PostMapping("/delete/{id}")
    public Result<Boolean> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BussinessException(Code.NO_AUTH);
        }
        if (id <= 0) {
            throw new BussinessException(Code.PARAMS_ERROR);
        }
        boolean flag = userService.removeById(id);
        return ResultUtils.success(flag);
    }

    /**
     * 判断是不是管理员
     *
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        //仅管理员可以查询
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null || user.getUserRole() != UserConstant.ADMIN_USER_ROLE) {
            return false;
        }
        return true;
    }

    @GetMapping("/current")
    public Result<User> gerCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null) {
            throw new BussinessException(Code.NOT_LOGIN);
        }
        Long userId = user.getId();
        User user1 = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user1);
        return ResultUtils.success(safetyUser);
    }
}
