package com.cosplaystore.cosplaystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.dto.response.ResponseObject;
import com.cosplaystore.cosplaystore.mapper.UserMapper;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> postMethodName(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        User user = userService.register(userRegisterRequest);

        return ResponseEntity.ok().body(ResponseObject.builder()
                .data(userMapper.toUserResponse(user)).message("Register success!").status_code(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> postMethodName(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest);

        return ResponseEntity.ok().body(ResponseObject.builder()
                .data(userMapper.toUserResponse(user)).message("Login success!").status_code(HttpStatus.OK.value())
                .build());
    }

}
