package com.example.booking_system_practice.dto.response;

import com.example.booking_system_practice.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomResponse {
    private Long roomId;
    private String roomNumber;
    private RoomType roomType;
    private Double roomPrice;
    private Boolean isAvailable;
}
