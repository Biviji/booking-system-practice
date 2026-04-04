package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
