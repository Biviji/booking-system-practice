package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.DTO.request.CreateRoomRequest;
import com.example.booking_system_practice.DTO.response.RoomResponse;
import com.example.booking_system_practice.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "roomId", ignore = true)
    Room toEntity(CreateRoomRequest request);
    RoomResponse toResponse(Room room);
}
