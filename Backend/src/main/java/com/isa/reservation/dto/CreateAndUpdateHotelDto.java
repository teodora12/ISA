package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Hotel;

import java.util.Set;

public class CreateAndUpdateHotelDto {


    private Long id;

    private String name;

    private Address address;

    private String description;

    private double averageRating;

    public CreateAndUpdateHotelDto(){

    }

    public CreateAndUpdateHotelDto(Hotel hotel){
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.description = hotel.getDescription();
        this.averageRating = hotel.getAverageRating();
    }

    public CreateAndUpdateHotelDto(Long id, String name, Address address, String description, double averageRating){
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
