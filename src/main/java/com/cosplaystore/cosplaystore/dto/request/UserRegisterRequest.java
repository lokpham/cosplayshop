package com.cosplaystore.cosplaystore.dto.request;

import com.cosplaystore.cosplaystore.annotation.ValidDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRegisterRequest {

    @Pattern(regexp = "^[\\p{L}\\s]{3,12}$", message = "Name must have a length between 3 and 16 characters.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Password must between 5 to 20 characters long, no special characters")
    private String password;
    @Email
    private String email;

    @ValidDate
    private String birth_day;

}
