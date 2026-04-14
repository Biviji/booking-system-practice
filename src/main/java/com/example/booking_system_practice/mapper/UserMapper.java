package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.dto.request.CreateUserRequest;
import com.example.booking_system_practice.dto.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "reservationList", ignore = true)
    User toEntity(CreateUserRequest request);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "name")
    @Mapping(source = "userEmail", target = "email")
    UserResponse toResponse(User user);
}
