package com.example.booking_system_practice.entity;

import com.example.booking_system_practice.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Long roomId;
    private String roomNumber;
    private RoomType roomType;
    private double roomPrice;
    private Boolean isAvailable;
}
