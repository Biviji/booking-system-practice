package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
