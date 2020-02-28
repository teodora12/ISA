package com.isa.reservation.dto;


import com.isa.reservation.model.CarReservation;

import java.util.Date;

public class CreateCarReservationDto {

    private Date pickUpDate;

    private Date  dropOffDate;

    private Long pickUpAddressId;

    private Long dropOffAddressId;

    private Long carId;

    public CreateCarReservationDto() {}

    public CreateCarReservationDto(CarReservation carReservation) {
        pickUpDate = carReservation.getPickUpDate();
        dropOffDate = carReservation.getDropOffDate();
        pickUpAddressId = carReservation.getPickUpAddress().getId();
        dropOffAddressId = carReservation.getDropOffAddress().getId();
        carId = carReservation.getCar().getId();
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Long getPickUpAddressId() {
        return pickUpAddressId;
    }

    public void setPickUpAddressId(Long pickUpAddressId) {
        this.pickUpAddressId = pickUpAddressId;
    }

    public Long getDropOffAddressId() {
        return dropOffAddressId;
    }

    public void setDropOffAddressId(Long dropOffAddressId) {
        this.dropOffAddressId = dropOffAddressId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
