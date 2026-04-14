package com.example.booking_system_practice.service;

import com.example.booking_system_practice.dto.request.CreateUserRequest;
import com.example.booking_system_practice.dto.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.exception.ConflictException;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.mapper.UserMapper;
import com.example.booking_system_practice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        String normalizedEmail = normalizeEmail(request.getEmail());

        if (userRepository.existsByUserEmail(normalizedEmail)) {
            throw new ConflictException("Email already registered: " + normalizedEmail);
        }

        User user = userMapper.toEntity(request);
        user.setUserName(request.getName().trim());
        user.setUserEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return userMapper.toResponse(user);
    }

    private String normalizeEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        return email.trim().toLowerCase(Locale.ROOT);
    }
}
