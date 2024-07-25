package com.cosplaystore.cosplaystore.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;

    private String password;
}
