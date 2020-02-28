package com.isa.reservation.dto;

import java.util.Date;

public class AvailableCarsDto {


//    private String pickUpDate;
//
//    private String dropOffDate;
//
//    private Address pickUpAddress;
//
//    private Address dropOffAddress;

    private Date pickUpDate;
    private Date dropOffDate;

    private Long pickUpAddress;
    private Long dropOffAddress;

    private int numberOfSeats;

    private int minPrice;

    private int maxPrice;

    private String type;

    private Long carServiceId;


    public AvailableCarsDto () {}

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

    public Long getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Long pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public Long getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(Long dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCarServiceId() {
        return carServiceId;
    }

    public void setCarServiceId(Long carServiceId) {
        this.carServiceId = carServiceId;
    }
}
