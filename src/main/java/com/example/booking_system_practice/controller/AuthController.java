package com.example.booking_system_practice.controller;

import com.example.booking_system_practice.DTO.request.LoginRequest;
import com.example.booking_system_practice.DTO.request.RegisterRequest;
import com.example.booking_system_practice.DTO.response.AuthResponse;
import com.example.booking_system_practice.DTO.response.UserResponse;
import com.example.booking_system_practice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
