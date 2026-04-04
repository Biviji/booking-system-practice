package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RoomInMemoryRepository implements RoomRepository {
    private final Map<Long, Room> roomMap = new HashMap<>();
    private Long nextRoomId = 1L;

    @Override
    public Room save(Room room) {
        if (room.getRoomId() == null) {
            room.setRoomId(nextRoomId++);
        }
        roomMap.put(room.getRoomId(), room);
        return room;
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(roomMap.values());
    }

    @Override
    public Optional<Room> findById(Long id) {
        return Optional.ofNullable(roomMap.get(id));
    }

    @Override
    public List<Room> findByAvailable(boolean isAvailable) {
        return roomMap.values().stream()
                .filter(room -> room.getIsAvailable().equals(isAvailable))
                .collect(Collectors.toList());
    }
}
