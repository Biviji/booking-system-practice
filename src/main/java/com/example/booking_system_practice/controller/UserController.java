package com.example.booking_system_practice.controller;

import com.example.booking_system_practice.DTO.request.CreateUserRequest;
import com.example.booking_system_practice.DTO.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
