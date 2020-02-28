package com.isa.reservation.service.impl;

import com.isa.reservation.dto.AvailableRoomsDto;
import com.isa.reservation.dto.RoomDto;
import com.isa.reservation.model.Room;
import com.isa.reservation.repository.RoomRepository;
import com.isa.reservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Set<RoomDto> getAvailableRooms(AvailableRoomsDto availableRoomsDto) {

        Set<Room> rooms = this.roomRepository.findRoomsByHotel_IdAndNumberOfBedsGreaterThanEqual
                (availableRoomsDto.getHotelId(),availableRoomsDto.getNumberOfBeds());




        Set<RoomDto> roomDtos = new HashSet<>();
        for(Room room: rooms){
            System.out.println(room.getName() + ": IME SOBE");
            roomDtos.add(new RoomDto(room,availableRoomsDto.getNumberOfNights()));

        }


        return roomDtos;
    }

    @Override
    public Set<Integer> getNumberOfBeds() {
        Set<Integer> roomNumOfBeds = new HashSet<>();

        List<Room> rooms = getRooms();
        for(Room room : rooms){
            roomNumOfBeds.add(room.getNumberOfBeds());
        }
        return  roomNumOfBeds;

    }

    @Override
    public Set<Integer> getMaxNumberOfGuests() {
        Set<Integer> roomNumOfGuests = new HashSet<>();

        List<Room> rooms = getRooms();
        for(Room room : rooms){
            roomNumOfGuests.add(room.getMaxNumberOfGuests());
        }
        return  roomNumOfGuests;
    }

    @Override
    public List<Room> getRooms() {
        return this.roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) { return this.roomRepository.findRoomById(id); }

    @Override
    public Room getRoomByName(String name) { return this.roomRepository.findRoomByName(name); }

    @Override
    public Room addRoom(Room room) { return this.roomRepository.save(room); }

    @Override
    public void deleteRoom(Room room) { this.roomRepository.delete(room); }

    @Override
    public Room updateRoom(Room room) {

        Room savedRoom = this.roomRepository.findRoomById(room.getId());
        savedRoom.setName(room.getName());
        savedRoom.setPrice(room.getPrice());
        savedRoom.setId(room.getId());
  //      savedRoom.setHotel(room.getHotel());

        return this.roomRepository.save(savedRoom);
    }

    @Override
    public Room saveRoom(Room room) { return this.roomRepository.save(room); }

}
