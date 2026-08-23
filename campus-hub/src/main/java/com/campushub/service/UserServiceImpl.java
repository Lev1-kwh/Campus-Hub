package com.campushub.service;

import com.campushub.entity.User;
import com.campushub.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements Userservice{
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(User user){
        userMapper.insert(user);    }
}
