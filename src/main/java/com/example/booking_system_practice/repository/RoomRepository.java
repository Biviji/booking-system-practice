package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room saveRoom(Room room);
    List<Room> getAllRooms();
    Optional<Room> getRoomById(Long id);
    List<Room> getRoomByAvailability(boolean isAvailable);
}
