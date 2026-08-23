package com.campushub.controller;

import com.campushub.dto.LoginRequest;
import com.campushub.entity.User;
import com.campushub.service.Userservice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Userservice userservice;

    public UserController(Userservice userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userservice.register(user);
        return "注册成功";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        User user = userservice.login(loginRequest);

        if (user == null) {
            return "用户名不存在或者密码错误，登录失败";
        } else {
            return "登录成功";
        }
    }
}
