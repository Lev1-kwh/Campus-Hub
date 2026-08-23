package com.campushub.controller;

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
}
