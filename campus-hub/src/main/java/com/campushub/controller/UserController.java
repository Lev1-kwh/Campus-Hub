package com.campushub.controller;

import com.campushub.dto.LoginRequest;
import com.campushub.dto.LoginResponse;
import com.campushub.entity.User;
import com.campushub.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userservice.register(user);
        return "注册成功";
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
       String token= userservice.login(loginRequest);
        LoginResponse loginResponse = new LoginResponse();

        if (token == null) {
            loginResponse.setMessage("用户名不存在或者密码错误，登录失败");
            return loginResponse;
        } else {
            loginResponse.setMessage("登录成功");
            loginResponse.setToken(token);
            return loginResponse;
        }
    }
}
