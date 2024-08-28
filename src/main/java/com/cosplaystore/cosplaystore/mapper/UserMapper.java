package com.cosplaystore.cosplaystore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.response.UserResponse;
import com.cosplaystore.cosplaystore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(LoginRequest loginRequest);

    UserResponse toUserResponse(User user);

}
