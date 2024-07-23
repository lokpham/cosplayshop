package com.cosplaystore.cosplaystore.service;

import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.model.User;

public interface UserService {

    User getUserByUsername(String username);

    User getUser(int id);

    User register(UserRegisterRequest userRegisterRequest);

    User login(LoginRequest loginRequest);
}
