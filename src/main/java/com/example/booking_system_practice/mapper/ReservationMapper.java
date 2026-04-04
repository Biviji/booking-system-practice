package com.example.booking_system_practice.mapper;

import com.example.booking_system_practice.DTO.request.CreateReservationRequest;
import com.example.booking_system_practice.DTO.response.ReservationResponse;
import com.example.booking_system_practice.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "reservationStatus", ignore = true)
    Reservation toEntity(CreateReservationRequest request);
    ReservationResponse toResponse(Reservation reservation);
}
