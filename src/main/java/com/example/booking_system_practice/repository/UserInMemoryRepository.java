package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserInMemoryRepository implements UserRepository {

    private final Map<Long, User> storage = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(nextId++);
        }
        storage.put(user.getUserId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
