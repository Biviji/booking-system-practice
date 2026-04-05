package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUserId(Long userId);
    List<Reservation> findByRoomRoomId(Long roomId);
    List<Reservation> findByCheckInDateBetween(LocalDate start, LocalDate end);
}
