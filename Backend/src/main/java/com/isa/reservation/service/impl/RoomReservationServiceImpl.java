package com.isa.reservation.service.impl;

import com.isa.reservation.dto.AvailableRoomsDto;
import com.isa.reservation.dto.CreateRoomReservationDto;
import com.isa.reservation.dto.RoomDto;
import com.isa.reservation.model.Room;
import com.isa.reservation.model.RoomReservation;
import com.isa.reservation.repository.RoomReservationRepository;
import com.isa.reservation.service.RoomReservationService;
import com.isa.reservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    private RoomService roomService;

    @Override
    public Set<RoomDto> findAvailableRooms(AvailableRoomsDto availableRoomsDto) {

        Set<RoomDto> availableRooms = this.roomService.getAvailableRooms(availableRoomsDto);
        System.out.println(availableRoomsDto.getDateOfArrival());
        System.out.println(availableRoomsDto.getDateOfDeparture());
        Set<RoomDto> forDelete = new HashSet<>();

        List<RoomReservation> roomReservationsFirst = this.roomReservationRepository.findRoomReservationsByArrivalDateGreaterThan(availableRoomsDto.getDateOfArrival());
        List<RoomReservation> roomReservationsSecond = this.roomReservationRepository.findRoomReservationsByArrivalDateGreaterThanAndDepartureDateLessThan(availableRoomsDto.getDateOfArrival(),availableRoomsDto.getDateOfArrival());
        List<RoomReservation> roomReservations = Stream.concat(roomReservationsFirst.stream(),roomReservationsSecond.stream()).collect(Collectors.toList());

        for (RoomReservation roomReservation : roomReservations) {
            if(!(roomReservation.getArrivalDate().after(availableRoomsDto.getDateOfDeparture()))){
                for (RoomDto roomDto : availableRooms) {
                    for(Room r : roomReservation.getRooms()) {
                        if (r.getId() == roomDto.getId()) {
                            forDelete.add(roomDto);
                        }
                    }
                }
            }
        }

        availableRooms.removeAll(forDelete);
        return availableRooms;
    }

    @Override
    @Transactional
    public RoomReservation createRoomReservation(CreateRoomReservationDto createRoomReservationDto) {

        Set<RoomDto> sobe = new HashSet<>();
        RoomReservation roomReservation = new RoomReservation();
        Set<Room> rooms = new HashSet<>();
        int numOfNights = createRoomReservationDto.getNumberOfNights();
        int numOfBeds = createRoomReservationDto.getNumberOfBeds();

        for(RoomDto r: createRoomReservationDto.getRooms()){
 //           sobe.add(new RoomDto());
            rooms.add(new Room());
   /*         for(RoomDto rDto: sobe){
                rDto.setDateOfArrival(r.getDateOfArrival());
                rDto.setDateOfDeparture(r.getDateOfDeparture());
                rDto.setNumberOfBeds(r.getNumberOfBeds());
                rDto.setNumberOfNights(r.getNumberOfNights());
     */
                for(Room room : rooms){
                    room.setDateOfDeparture(createRoomReservationDto.getDepartureDate());
                    room.setDateOfArrival(createRoomReservationDto.getArrivalDate());
                    room.setNumberOfBeds(r.getNumberOfBeds());
                    room.setAvailable(false);
                    roomReservation.getRooms().add(room);
   //             }
            }
        }


        roomReservation.setArrivalDate(createRoomReservationDto.getArrivalDate());
        roomReservation.setDepartureDate(createRoomReservationDto.getDepartureDate());
        roomReservation.setNumberOfBeds(numOfBeds);
        roomReservation.setNumberOfNights(numOfNights);

        return this.roomReservationRepository.save(roomReservation);


    }
}
