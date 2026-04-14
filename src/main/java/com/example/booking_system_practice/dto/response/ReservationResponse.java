package com.example.booking_system_practice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ReservationResponse {
    private Long reservationId;
    private UserResponse user;
    private RoomResponse room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
