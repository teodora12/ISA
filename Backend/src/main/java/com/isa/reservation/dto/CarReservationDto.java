package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.CarReservation;

import java.util.Date;

public class CarReservationDto {

    private Long id;

    private Date pickUpDate;

    private Date dropOffDate;

    private Address pickUpAddress;

    private Address dropOffAddress;

    private CarDto carDto;

   public CarReservationDto() {}

   public CarReservationDto(CarReservation carReservation){
       this.id = carReservation.getId();
       this.pickUpDate = carReservation.getPickUpDate();
       this.dropOffDate = carReservation.getDropOffDate();
       this.dropOffAddress=carReservation.getDropOffAddress();
       this.pickUpAddress = carReservation.getPickUpAddress();
       this.carDto = new CarDto(carReservation.getCar());
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
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

    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Address pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public Address getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(Address dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }
}
