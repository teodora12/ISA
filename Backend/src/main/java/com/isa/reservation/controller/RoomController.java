package com.isa.reservation.controller;

import com.isa.reservation.dto.CreateAndUpdateRoomDto;
import com.isa.reservation.dto.HotelDto;
import com.isa.reservation.dto.RoomDto;
import com.isa.reservation.model.Hotel;
import com.isa.reservation.model.Room;
import com.isa.reservation.service.HotelService;
import com.isa.reservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAndUpdateRoomDto> createRoom(@RequestBody CreateAndUpdateRoomDto roomDto ){

        System.out.println("CREATE USAO");
        Hotel hotel = this.hotelService.getHotelById(roomDto.getHotelId());
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setPrice(roomDto.getPrice());
        room.setName(roomDto.getName());
        room.setAvailable(true);
        room.setMaxNumberOfGuests(roomDto.getMaxNumberOfGuests());
        room.setNumberOfBeds(roomDto.getNumberOfBeds());
 //       room.setDateOfArrival(roomDto.getDateOfArrival());
 //       room.setDateOfDeparture(roomDto.getDateOfDeparture());
        room.setHotel(hotel);
        System.out.println(room);
        room = this.roomService.addRoom(room);
        return ResponseEntity.ok(new CreateAndUpdateRoomDto(room));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAndUpdateRoomDto> updateRoom(@RequestBody CreateAndUpdateRoomDto roomDto){

        Room room = roomService.getRoomById(roomDto.getId());

        if(room == null){
            return ResponseEntity.notFound().build();
        }

        room.setId(roomDto.getId());
        room.setPrice(roomDto.getPrice());
        room.setName(roomDto.getName());
        room.setAvailable(roomDto.isAvailable());
        room.setMaxNumberOfGuests(roomDto.getMaxNumberOfGuests());
        room.setNumberOfBeds(roomDto.getNumberOfBeds());
        room.setDateOfArrival(roomDto.getDateOfArrival());
        room.setDateOfDeparture(roomDto.getDateOfDeparture());
        Hotel hotel = this.hotelService.getHotelById(roomDto.getHotelId());
        room.setHotel(hotel);

        room = this.roomService.updateRoom(room);

        return  new ResponseEntity<>(new CreateAndUpdateRoomDto(room), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms(){
        List<RoomDto> roomDtos = new ArrayList<>();
        List<Room> rooms = roomService.getRooms();

        if(rooms == null){
            return ResponseEntity.notFound().build();
        }

        for(Room room : rooms){
            roomDtos.add(new RoomDto(room));
        }

        return ResponseEntity.ok(roomDtos);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id){

        Room room = roomService.getRoomById(id);
        RoomDto roomDto = new RoomDto(room);
        if(room == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(roomDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteRoom(@PathVariable Long id){
        Room room = roomService.getRoomById(id);

        if(room == null){
            return ResponseEntity.notFound().build();
        }

        this.roomService.deleteRoom(room);
        return  ResponseEntity.ok().build();
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAndUpdateRoomDto> saveRoom(@RequestBody CreateAndUpdateRoomDto roomDto){

        Room room = new Room();
        room.setId(roomDto.getId());
        room.setPrice(roomDto.getPrice());
        room.setName(roomDto.getName());
        room.setId(roomDto.getId());
        room.setAvailable(roomDto.isAvailable());
        room.setMaxNumberOfGuests(roomDto.getMaxNumberOfGuests());
        room.setNumberOfBeds(roomDto.getNumberOfBeds());
        room.setDateOfArrival(roomDto.getDateOfArrival());
        room.setDateOfDeparture(roomDto.getDateOfDeparture());
        Hotel hotel = this.hotelService.getHotelById(roomDto.getHotelId());
        room.setHotel(hotel);

        room = this.roomService.saveRoom(room);
        return new ResponseEntity<>(new CreateAndUpdateRoomDto(room), HttpStatus.OK);
    }

    @GetMapping(value = "/numberOfBeds")
    public ResponseEntity<Set<Integer>> getBedsOfRoom(){
        Set<Integer> numberBeds = this.roomService.getNumberOfBeds();
        return new ResponseEntity<>(numberBeds,HttpStatus.OK);
    }


    @GetMapping(value = "/numberOfGuests")
    public ResponseEntity<Set<Integer>> getNumGuestsOfRoom(){
        Set<Integer> numberGuests = this.roomService.getMaxNumberOfGuests();
        return new ResponseEntity<>(numberGuests,HttpStatus.OK);
    }


}
