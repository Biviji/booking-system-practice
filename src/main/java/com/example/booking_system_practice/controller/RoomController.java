package com.example.booking_system_practice.controller;

import com.example.booking_system_practice.dto.request.CreateRoomRequest;
import com.example.booking_system_practice.dto.response.RoomResponse;
import com.example.booking_system_practice.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    public RoomController (RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse createRoom(@Valid @RequestBody CreateRoomRequest request) {
        return roomService.createRoom(request);
    }

    @GetMapping
    public List<RoomResponse> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomResponse getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/availability")
    public List<RoomResponse> getRoomByAvailability(@RequestParam(name = "isAvailable", defaultValue = "true") boolean isAvailable) {
        return roomService.getRoomByAvailability(isAvailable);
    }

}
