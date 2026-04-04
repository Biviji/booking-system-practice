package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.DTO.request.CreateUserRequest;
import com.example.booking_system_practice.DTO.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "name")
    @Mapping(source = "userEmail", target = "email")
    User toEntity(CreateUserRequest request);
    UserResponse toResponse(User user);
}
