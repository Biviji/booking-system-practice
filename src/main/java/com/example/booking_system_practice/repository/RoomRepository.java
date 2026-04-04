package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);
    List<Room> findAll();
    Optional<Room> findById(Long id);
    List<Room> findByAvailable(boolean isAvailable);
}
