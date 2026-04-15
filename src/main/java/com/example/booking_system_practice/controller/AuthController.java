package com.example.booking_system_practice.controller;

import com.example.booking_system_practice.dto.request.LoginRequest;
import com.example.booking_system_practice.dto.request.RegisterRequest;
import com.example.booking_system_practice.dto.response.AuthResponse;
import com.example.booking_system_practice.dto.response.UserResponse;
import com.example.booking_system_practice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
