package com.example.booking_system_practice.service;

import com.example.booking_system_practice.DTO.request.CreateReservationRequest;
import com.example.booking_system_practice.DTO.response.ReservationResponse;
import com.example.booking_system_practice.entity.Reservation;
import com.example.booking_system_practice.enums.ReservationStatus;
import com.example.booking_system_practice.entity.Room;
import com.example.booking_system_practice.entity.User;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.mapper.ReservationMapper;
import com.example.booking_system_practice.repository.ReservationRepository;
import com.example.booking_system_practice.repository.RoomRepository;
import com.example.booking_system_practice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationResponse createReservation(CreateReservationRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User with id " + request.getUserId() + " not found!"));
        Room room = roomRepository.getRoomById(request.getRoomId()).orElseThrow(() -> new NotFoundException("Room with ID " + request.getRoomId() + " not found!"));

        if (!room.getIsAvailable()) {
            throw new RuntimeException("Room is not available!");
        }

        List<Reservation> existingReservations = reservationRepository.getReservationByRoomId(request.getRoomId());

        boolean isOverlap = existingReservations.stream()
                .anyMatch(r -> !r.getCheckOutDate().isBefore(request.getCheckInDate()) &&
                !request.getCheckInDate().isAfter(request.getCheckOutDate()));

        if (isOverlap) {
            throw new RuntimeException("Room is already booked for these dates!");
        }

        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        Reservation saved = reservationRepository.saveReservation(reservation);

        return reservationMapper.toResponse(saved);
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.getAllReservations().stream()
                .map(reservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = reservationRepository.getReservationById(id)
                .orElseThrow(() -> new NotFoundException("Reservation with ID " + id + " has not been found!"));
        return reservationMapper.toResponse(reservation);
    }

    public List<ReservationResponse> getReservationByUserId(Long userId) {
        return reservationRepository.getReservationByUserId(userId).stream()
                .map(reservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationByRoomId(Long roomId) {
        return reservationRepository.getReservationByRoomId(roomId).stream()
                .map(reservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationByDateRange(LocalDate start, LocalDate end) {
        return reservationRepository.getReservationByDateRange(start, end).stream()
                .map(reservationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
