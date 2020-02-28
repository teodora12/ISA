package com.isa.reservation.service;

import com.isa.reservation.dto.AvailableRoomsDto;
import com.isa.reservation.dto.CreateRoomReservationDto;
import com.isa.reservation.dto.RoomDto;
import com.isa.reservation.model.Room;
import com.isa.reservation.model.RoomReservation;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface RoomReservationService {

    Set<RoomDto> findAvailableRooms(AvailableRoomsDto roomsDto);
    RoomReservation createRoomReservation(CreateRoomReservationDto createRoomReservationDto);

}
