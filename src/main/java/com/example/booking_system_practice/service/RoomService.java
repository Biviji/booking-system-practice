package com.example.booking_system_practice.service;

import com.example.booking_system_practice.DTO.request.CreateRoomRequest;
import com.example.booking_system_practice.DTO.response.RoomResponse;
import com.example.booking_system_practice.entity.Room;
import com.example.booking_system_practice.exception.NotFoundException;
import com.example.booking_system_practice.mapper.RoomMapper;
import com.example.booking_system_practice.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Transactional
    public RoomResponse createRoom(CreateRoomRequest request) {
        Room room = roomMapper.toEntity(request);
        Room saved = roomRepository.save(room);
        return roomMapper.toResponse(saved);
    }

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room with ID " + id + " not found"));
        return roomMapper.toResponse(room);
    }

    public List<RoomResponse> getRoomByAvailability(boolean isAvailable) {
        return roomRepository.findByIsAvailable(isAvailable).stream().map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

}
