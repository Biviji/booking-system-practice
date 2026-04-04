package com.example.booking_system_practice.controller;

import com.example.booking_system_practice.DTO.request.CreateReservationRequest;
import com.example.booking_system_practice.DTO.response.ReservationResponse;
import com.example.booking_system_practice.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationResponse createReservation(@Valid @RequestBody CreateReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ReservationResponse> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationByUserId(userId);
    }

    @GetMapping("/room/{roomId}")
    public List<ReservationResponse> getReservationsByRoomId(@PathVariable Long roomId) {
        return reservationService.getReservationByRoomId(roomId);
    }

    @GetMapping("/date-range")
    public List<ReservationResponse> getReservationsByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return reservationService.getReservationByDateRange(start, end);
    }
}
