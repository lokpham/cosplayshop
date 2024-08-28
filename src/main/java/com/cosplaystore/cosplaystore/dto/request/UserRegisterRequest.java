package com.cosplaystore.cosplaystore.dto.request;

import com.cosplaystore.cosplaystore.annotation.ValidDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRegisterRequest {

    @Pattern(regexp = "^[\\p{L}\\s]{3,12}$", message = "Name must have a length between 3 and 16 characters.")
    private String name;

    @Email
    private String email;

    @NotBlank
    private String picture;
}
