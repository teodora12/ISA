package com.isa.reservation.dto;

import com.isa.reservation.model.FlightReservation;
import com.isa.reservation.model.Reservation;

import java.util.Date;

public class ReservationDto {

private Long id;
    private FlightReservation flightReservation;
    private CarReservationDto carReservation;
    private Date dateCreated;
    private RoomReservationDto roomReservation;


    public ReservationDto() {

    }

    public ReservationDto(Reservation reservation) {

        this.id = reservation.getId();
        if (reservation.getCarReservation() != null){
            if (reservation.getCarReservation().getPickUpDate() != null) {
                this.carReservation = new CarReservationDto(reservation.getCarReservation());
            }
        }
        if (reservation.getRoomReservation() != null){
            if (reservation.getRoomReservation().getArrivalDate() != null) {
                this.roomReservation = new RoomReservationDto(reservation.getRoomReservation());
            }
        }
        this.flightReservation = reservation.getFlightReservation();
        this.dateCreated = reservation.getDateCreated();
    }

    public RoomReservationDto getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservationDto roomReservation) {
        this.roomReservation = roomReservation;
    }

    public CarReservationDto getCarReservation() {
        return carReservation;
    }

    public void setCarReservation(CarReservationDto carReservation) {
        this.carReservation = carReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
