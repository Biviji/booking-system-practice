package com.example.booking_system_practice.service;

import com.example.booking_system_practice.dto.request.LoginRequest;
import com.example.booking_system_practice.dto.request.RegisterRequest;
import com.example.booking_system_practice.dto.response.AuthResponse;
import com.example.booking_system_practice.dto.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.exception.BadRequestException;
import com.example.booking_system_practice.exception.ConflictException;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.mapper.UserMapper;
import com.example.booking_system_practice.repository.UserRepository;
import com.example.booking_system_practice.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

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
        String normalizedEmail = normalizeEmail(request.getEmail());

        if (userRepository.existsByUserEmail(normalizedEmail)) {
            throw new ConflictException("Email already registered: " + normalizedEmail);
        }

        User user = new User();
        user.setUserName(request.getName());
        user.setUserEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public AuthResponse login(LoginRequest request) {
        String normalizedEmail = normalizeEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        normalizedEmail,
                        request.getPassword()
                ));

        User user = userRepository.findByUserEmail(normalizedEmail)
                .orElseThrow(() -> new NotFoundException("user not found"));
        String token = jwtService.generateToken(user.getUserId(), user.getUserEmail());
        return new AuthResponse(token, user.getUserId(), user.getUserEmail());
    }

    private String normalizeEmail(String email) {

        if (email == null) {
            throw new BadRequestException("Email cannot be null");
        }

        return email.trim().toLowerCase(Locale.ROOT);
    }
}
