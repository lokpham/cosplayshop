package com.cosplaystore.cosplaystore.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

import com.cosplaystore.cosplaystore.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {

    private int id;
    private String username;

    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birth_day;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean disable;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
}
