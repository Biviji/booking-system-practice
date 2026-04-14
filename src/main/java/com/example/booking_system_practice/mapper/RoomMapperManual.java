package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.dto.request.CreateRoomRequest;
import com.example.booking_system_practice.dto.response.RoomResponse;
import com.example.booking_system_practice.entity.Room;

// How it works without MapStruct, a way to transform data manually

public class RoomMapperManual {
    public Room toEntity(CreateRoomRequest request) {
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(request.getRoomType());
        room.setRoomPrice(request.getRoomPrice());
        room.setIsAvailable(request.getIsAvailable());
        return room;
    }

    public RoomResponse toResponse (Room room) {
        return new RoomResponse(
                room.getRoomId(),
                room.getRoomNumber(),
                room.getRoomType(),
                room.getRoomPrice(),
                room.getIsAvailable()
        );
    }
}
