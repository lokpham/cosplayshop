package com.cosplaystore.cosplaystore.dto.response;

import com.cosplaystore.cosplaystore.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthReponse {

    private String email;
    private String accessToken;
    private String name;
    private String picture;
    private Role role;
}
