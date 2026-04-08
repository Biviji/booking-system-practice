package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUserId(Long userUserId);
    List<Reservation> findByRoomRoomId(Long roomId);
    List<Reservation> findByCheckInDateBetween(LocalDate start, LocalDate end);
}
