package com.cosplaystore.cosplaystore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password_hash", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserRegisterRequest userRegisterRequest);

}
