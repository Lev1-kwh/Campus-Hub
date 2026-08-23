package com.campushub.service;

import com.campushub.dto.LoginRequest;
import com.campushub.entity.User;
import com.campushub.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements Userservice{
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServiceImpl(UserMapper userMapper,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void register(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }
    @Override
    public User login(LoginRequest loginRequest){
        User user  = userMapper.selectByUsername(loginRequest.getUsername());
        if (user==null){
            return null;
        }
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return null;
        }
        return user;
    }
}
