package com.example.booking_system_practice.DTO.request;

import com.example.booking_system_practice.enums.RoomType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRoomRequest {
    private String roomNumber;
    private RoomType roomType;
    private Double roomPrice;
    private Boolean isAvailable;
}
