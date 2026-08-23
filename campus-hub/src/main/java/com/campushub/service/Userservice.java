package com.campushub.service;

import com.campushub.dto.LoginRequest;
import com.campushub.entity.User;

public interface Userservice {
    void register(User user);
    User login(LoginRequest loginRequest);
}
