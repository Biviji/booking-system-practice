package com.example.booking_system_practice.service;

import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.repository.UserInMemoryRepository;
import com.example.booking_system_practice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {
        User user = new User(null, name, email);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("\nUser has not been found!\n"));
    }
}
