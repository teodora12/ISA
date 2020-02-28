package com.isa.reservation.dto;

import com.isa.reservation.model.Room;
import com.isa.reservation.model.RoomReservation;

import java.util.Date;

public class RoomReservationDto {

    private Long id;
    private Date arrivalDate;
    private Date departureDate;
    private int numberOfBeds;
    private RoomDto roomDto;
    private int numberOfNights;
    private Long roomId;

    public RoomReservationDto() {
    }

    public RoomReservationDto(RoomReservation roomReservation){
        this.id = roomReservation.getId();
        this.arrivalDate = roomReservation.getArrivalDate();
        this.departureDate = roomReservation.getDepartureDate();
        this.numberOfBeds = roomReservation.getNumberOfBeds();

      for(Room r : roomReservation.getRooms()){
          r.setDateOfArrival(this.arrivalDate);
          r.setDateOfDeparture(this.departureDate);
          this.roomDto = new RoomDto(r);
      }
 /*       roomReservation.getRoom().setDateOfDeparture(this.dateDeparture);   //da li ovo treba
        roomReservation.getRoom().setDateOfArrival(this.dateArrival);       // ???
        this.roomDto = new RoomDto(roomReservation.getRoom());
   */

        this.numberOfNights = roomReservation.getNumberOfNights();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }
}
