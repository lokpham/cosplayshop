package com.cosplaystore.cosplaystore.service;

import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.dto.response.AuthReponse;
import com.cosplaystore.cosplaystore.model.User;

public interface AuthService {
    public String encodePassword(String password);

    public User register(UserRegisterRequest userRegisterRequest);

    public AuthReponse login(LoginRequest loginRequest);
}
