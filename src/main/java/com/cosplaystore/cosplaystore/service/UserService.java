package com.cosplaystore.cosplaystore.service;

import com.cosplaystore.cosplaystore.model.User;

public interface UserService {

    User getUserByEmail(String email);

    User getUser(int id);

    void disable(int id);

    void undisable(int id);
}
