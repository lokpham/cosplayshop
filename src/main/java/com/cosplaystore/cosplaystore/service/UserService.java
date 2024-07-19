package com.cosplaystore.cosplaystore.service;

import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.model.User;

public interface UserService {

    User getUser(int id);

    User register(UserRegisterRequest userRegisterRequest);
}
