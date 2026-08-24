package com.campushub.service;

import com.campushub.dto.LoginRequest;
import com.campushub.entity.User;

public interface UserService {
    void register(User user);
    String  login(LoginRequest loginRequest);
}
