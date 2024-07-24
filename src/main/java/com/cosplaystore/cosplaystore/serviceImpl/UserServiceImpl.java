package com.cosplaystore.cosplaystore.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.UserMapper;
import com.cosplaystore.cosplaystore.model.Role;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.repository.UserRepo;
import com.cosplaystore.cosplaystore.service.AuthService;
import com.cosplaystore.cosplaystore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthService authService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
    public User register(UserRegisterRequest userRegisterRequest) {
        String password = userRegisterRequest.getPassword();
        String password_hash = authService.encodePassword(password);

        User user = userMapper.toUser(userRegisterRequest);
        user.setBirth_day(LocalDate.parse(userRegisterRequest.getBirth_day(), DATE_FORMATTER));
        user.setPassword_hash(password_hash);
        user.setRole(Role.USER);
        user.setDisable(false);

        return userRepo.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User user = getUserByUsername(loginRequest.getUsername());
        if (user.getPassword_hash().equals(authService.encodePassword(loginRequest.getPassword()))) {
            return user;
        } else {
            throw new GeneralException(Message.WRONG_PASSWORD);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepo.findByUsername(username);
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
