package com.example.booking_system_practice.DTO.response;

import com.example.booking_system_practice.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    private String roomNumber;
    private RoomType roomType;
    private Double roomPrice;
    private Boolean isAvailable;
}
