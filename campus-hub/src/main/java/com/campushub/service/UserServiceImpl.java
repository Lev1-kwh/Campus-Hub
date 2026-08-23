package com.campushub.service;

import com.campushub.dto.LoginRequest;
import com.campushub.entity.User;
import com.campushub.mapper.UserMapper;
import com.campushub.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements Userservice{
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    public UserServiceImpl(UserMapper userMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil=jwtUtil;
    }


    @Override
    public void register(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }
    @Override
    public String login(LoginRequest loginRequest){
        User user  = userMapper.selectByUsername(loginRequest.getUsername());
        if (user==null||!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
        {  return null;}
        String token = jwtUtil.generateToken(user);
        return token;
    }
}
