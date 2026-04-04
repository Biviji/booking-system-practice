package com.example.booking_system_practice.service;

import com.example.booking_system_practice.DTO.request.CreateUserRequest;
import com.example.booking_system_practice.DTO.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.mapper.UserMapper;
import com.example.booking_system_practice.repository.UserInMemoryRepository;
import com.example.booking_system_practice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = userMapper.toEntity(request);
        User saved = userRepository.saveUser(user);
        return userMapper.toResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return userMapper.toResponse(user);
    }
}
