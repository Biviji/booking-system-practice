package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.DTO.request.CreateReservationRequest;
import com.example.booking_system_practice.DTO.response.ReservationResponse;
import com.example.booking_system_practice.entity.Reservation;
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
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "reservationStatus", ignore = true)
    Reservation toEntity(CreateReservationRequest request);
}
