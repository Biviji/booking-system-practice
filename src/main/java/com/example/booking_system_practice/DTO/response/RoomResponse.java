package com.example.booking_system_practice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    private String roomNumber;
    private String roomType;
    private Double roomPrice;
    private Boolean isAvailable;
}
