package com.isa.reservation.controller;

import com.isa.reservation.dto.AvailableRoomsDto;
import com.isa.reservation.dto.CreateRoomReservationDto;
import com.isa.reservation.dto.RoomDto;
import com.isa.reservation.dto.RoomReservationDto;
import com.isa.reservation.model.Room;
import com.isa.reservation.model.RoomReservation;
import com.isa.reservation.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value="/api/roomReservations")
public class RoomReservationController {

    @Autowired
    RoomReservationService roomReservationService;

    @PostMapping(value = "/available")
    //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Set<RoomDto>> getAvailableRooms(@RequestBody AvailableRoomsDto availableRoomsDto){
        Set<RoomDto> rooms = this.roomReservationService.findAvailableRooms(availableRoomsDto);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<RoomReservationDto> createReservation(@RequestBody CreateRoomReservationDto createRoomReservationDto){

        System.out.println(createRoomReservationDto.getArrivalDate() + "aaaaaaaaaaa");
        System.out.println(createRoomReservationDto.getDepartureDate() + "dddddddddddd");

        RoomReservation roomReservation = this.roomReservationService.createRoomReservation(createRoomReservationDto);

        for(Room r: roomReservation.getRooms()) {
            r.setDateOfDeparture(roomReservation.getDepartureDate());
            r.setDateOfArrival(roomReservation.getArrivalDate());
        }

//        roomReservation.getRoom().setDateOfDeparture(roomReservation.getDepartureDate());
//        roomReservation.getRoom().setDateOfArrival(roomReservation.getArrivalDate());

        RoomReservationDto roomReservationDto = new RoomReservationDto(roomReservation);
        return new ResponseEntity<>(roomReservationDto,HttpStatus.OK);
    }

}
