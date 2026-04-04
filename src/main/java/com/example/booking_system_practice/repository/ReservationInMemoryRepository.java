package com.example.booking_system_practice.repository;

import com.example.booking_system_practice.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReservationInMemoryRepository implements ReservationRepository {

    private final Map<Long, Reservation> reservationMap = new HashMap<>();
    private Long nextReservationId = 1L;

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getReservationId() == null) {
            reservation.setReservationId(nextReservationId++);
        }
        reservationMap.put(reservation.getReservationId(), reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservationMap.values());
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(reservationMap.get(id));
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return reservationMap.values().stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByRoomId(Long roomId) {
        return reservationMap.values().stream()
                .filter(r -> r.getRoomId().equals(roomId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByDateRange(LocalDate start, LocalDate end) {
        return reservationMap.values().stream()
                .filter(r -> !r.getCheckInDate().isBefore(start) && r.getCheckInDate().isAfter(end))
                .collect(Collectors.toList());
    }
}
