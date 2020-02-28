package com.isa.reservation.service;

import com.isa.reservation.dto.AvailableRoomsDto;
import com.isa.reservation.dto.RoomDto;
import com.isa.reservation.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RoomService {

    List<Room> getRooms();
    Room getRoomById(Long id);
    Room getRoomByName(String name);
    Room addRoom(Room room);
    void deleteRoom(Room room);
    Room updateRoom(Room room);
    Room saveRoom(Room room);
    Set<RoomDto> getAvailableRooms(AvailableRoomsDto availableRoomsDto);
    Set<Integer> getNumberOfBeds();
    Set<Integer> getMaxNumberOfGuests();

}
