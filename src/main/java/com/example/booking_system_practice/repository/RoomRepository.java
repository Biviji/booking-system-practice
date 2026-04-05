package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByIsAvailable(boolean isAvailable);
}
