package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation saveReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(Long id);
    List<Reservation> getReservationByUserId(Long userId);
    List<Reservation> getReservationByRoomId(Long roomId);
    List<Reservation> getReservationByDateRange(LocalDate start, LocalDate end);
}
