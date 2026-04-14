package com.example.booking_system_practice.dto.request;

import com.example.booking_system_practice.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRoomRequest {

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Room type is required")
    private RoomType roomType;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price must be positive")
    private Double roomPrice;

    @NotNull(message = "Availability status is required")
    private Boolean isAvailable;
}
