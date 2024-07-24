package com.cosplaystore.cosplaystore.dto.request;

import java.util.Date;

import com.cosplaystore.cosplaystore.annotation.ValidDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRegisterRequest {

    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$", message = "Usernames must consist of alphanumeric characters, underscores, or hyphens, with a length between 3 and 16 characters.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Password must between 5 to 20 characters long, no special characters")
    private String password;
    @Email
    private String email;

    @ValidDate
    private String birth_day;

}
