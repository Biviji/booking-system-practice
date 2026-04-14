package com.example.booking_system_practice.service;

import com.example.booking_system_practice.dto.request.LoginRequest;
import com.example.booking_system_practice.dto.request.RegisterRequest;
import com.example.booking_system_practice.dto.response.AuthResponse;
import com.example.booking_system_practice.dto.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.mapper.UserMapper;
import com.example.booking_system_practice.repository.UserRepository;
import com.example.booking_system_practice.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUserEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }
        User user = new User();
        user.setUserName(request.getName());
        user.setUserEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByUserEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));
        String token = jwtService.generateToken(user.getUserId(), user.getUserEmail());
        return new AuthResponse(token, user.getUserId(), user.getUserEmail());
    }
}
