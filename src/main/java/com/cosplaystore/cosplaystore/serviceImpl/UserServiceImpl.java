package com.cosplaystore.cosplaystore.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.mapper.UserMapper;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.repository.UserRepo;
import com.cosplaystore.cosplaystore.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public User register(UserRegisterRequest userRegisterRequest) {
        User user = userMapper.toUser(userRegisterRequest);
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");

        // TODO Auto-generated method stub
    }

}
