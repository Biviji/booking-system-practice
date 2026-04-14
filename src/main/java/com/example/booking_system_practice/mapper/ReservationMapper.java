package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.dto.request.CreateReservationRequest;
import com.example.booking_system_practice.dto.response.ReservationResponse;
import com.example.booking_system_practice.entity.Reservation;
import com.example.booking_system_practice.entity.Room;
import com.example.booking_system_practice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class, RoomMapper.class})
public interface ReservationMapper {

    @Mappings({
            @Mapping(source = "reservationId", target = "reservationId"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "room", target = "room")
    })
    ReservationResponse toResponse(Reservation reservation);


    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "reservationStatus", ignore = true)
    Reservation toEntity(CreateReservationRequest request, User user, Room room);
}
