package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public Optional<User> getUserById(Long id);
}
