package com.cosplaystore.cosplaystore.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.UserMapper;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.repository.UserRepo;
import com.cosplaystore.cosplaystore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(int id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {

            throw new GeneralException(Message.USERNAME_NOTFOUND);
        }

    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {

            throw new GeneralException(Message.USERNAME_NOTFOUND);
        }
    }

    @Override
    public void disable(int id) {
        User user = getUser(id);
        if (user != null) {
            user.setDisable(true);
            userRepo.save(user);
        }
    }

    @Override
    public void undisable(int id) {

        User user = getUser(id);
        if (user != null) {
            user.setDisable(false);
            userRepo.save(user);
        }
    }

}
