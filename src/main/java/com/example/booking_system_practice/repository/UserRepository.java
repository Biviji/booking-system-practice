package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public User save(User user);
    public List<User> findAll();
    public Optional<User> findById(Long id);
}
