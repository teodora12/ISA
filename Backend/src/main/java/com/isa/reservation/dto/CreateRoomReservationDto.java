package com.isa.reservation.dto;

import com.isa.reservation.model.Room;
import com.isa.reservation.model.RoomReservation;

import java.util.Date;
import java.util.Set;

public class CreateRoomReservationDto {

    private Date arrivalDate;

    private Date departureDate;

    private int numberOfNights;

    private int numberOfBeds;

    private Long roomId;

    private Set<RoomDto> rooms;


    public CreateRoomReservationDto() {
    }

    public CreateRoomReservationDto(RoomReservation roomReservation) {
        this.arrivalDate = roomReservation.getArrivalDate();
        this.departureDate = roomReservation.getDepartureDate();
        this.numberOfBeds = roomReservation.getNumberOfBeds();
        this.numberOfNights = roomReservation.getNumberOfNights();
//        this.roomId = roomReservation.getRoom().getId();
        for(Room r: roomReservation.getRooms()){
            RoomDto soba = new RoomDto(r,this.numberOfNights);
    //        this.rooms.add(soba);
        }
    }



    public Set<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDto> rooms) {
        this.rooms = rooms;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
