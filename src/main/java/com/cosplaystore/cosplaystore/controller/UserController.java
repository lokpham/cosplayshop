package com.cosplaystore.cosplaystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @PostMapping("/register")
    public String postMethodName(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        // TODO: process POST request
        return "";
    }

}
