package com.cosplaystore.cosplaystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.dto.response.AuthReponse;
import com.cosplaystore.cosplaystore.dto.response.ResponseObject;
import com.cosplaystore.cosplaystore.dto.response.UserResponse;
import com.cosplaystore.cosplaystore.service.AuthService;
import com.cosplaystore.cosplaystore.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> postMethodName(@Valid @RequestBody LoginRequest loginRequest) {
        AuthReponse authReponse = authService.login(loginRequest);

        return ResponseEntity.ok().body(ResponseObject.builder()
                .data(authReponse).message("Login success!").status_code(HttpStatus.OK.value())
                .build());
    }

    @PutMapping("disable")
    public ResponseEntity<ResponseObject> disable(@RequestParam @Min(1) int id) {

        userService.disable(id);
        return ResponseEntity.ok().body(ResponseObject.builder()
                .data(null).message("Disable success!").status_code(HttpStatus.OK.value())
                .build());
    }

    @PutMapping("undisable")
    public ResponseEntity<ResponseObject> undisable(@RequestParam @Min(1) int id) {
        userService.undisable(id);

        return ResponseEntity.ok().body(ResponseObject.builder()
                .data(null).message("Undisable success!").status_code(HttpStatus.OK.value())
                .build());
    }
}
