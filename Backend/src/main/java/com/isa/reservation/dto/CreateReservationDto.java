package com.isa.reservation.dto;

import com.isa.reservation.model.FlightReservation;
import com.isa.reservation.model.Reservation;

import java.util.Date;

public class CreateReservationDto {

    private Long id;
    private Long userId;
    private FlightReservation flightReservation;
    private CreateCarReservationDto carReservation;
    private CreateRoomReservationDto roomReservation;
    private Date dateCreated;

    public CreateReservationDto(){}

    public CreateReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.dateCreated = reservation.getDateCreated();
        this.userId = reservation.getUser().getId();
        if (reservation.getCarReservation() != null) {
            this.carReservation = new CreateCarReservationDto(reservation.getCarReservation());
        }
        if (reservation.getRoomReservation() != null) {
            this.roomReservation = new CreateRoomReservationDto(reservation.getRoomReservation());
        }
        this.flightReservation = reservation.getFlightReservation();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    public CreateCarReservationDto getCarReservation() {
        return carReservation;
    }

    public void setCarReservation(CreateCarReservationDto carReservation) {
        this.carReservation = carReservation;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public CreateRoomReservationDto getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(CreateRoomReservationDto roomReservation) {
        this.roomReservation = roomReservation;
    }
}
