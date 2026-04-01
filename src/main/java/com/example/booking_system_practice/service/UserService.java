package com.example.booking_system_practice.service;

import com.example.booking_system_practice.DTO.request.CreateUserRequest;
import com.example.booking_system_practice.DTO.response.UserResponse;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.repository.UserInMemoryRepository;
import com.example.booking_system_practice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = new User(null, request.getName(), request.getEmail());
//        Save a user to assign ID after DB creation
        User saved = userRepository.save(user);
//        Transform saved user to a response DTO to hide internal data (id)
        return toResponse(saved);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getUserId(), user.getUserName(), user.getUserEmail());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return toResponse(user);
    }
}
