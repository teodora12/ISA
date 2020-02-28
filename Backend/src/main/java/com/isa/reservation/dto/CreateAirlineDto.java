package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Destination;
import com.isa.reservation.model.Airline;

//NEMA FLIGHTS I AVAILABLE DESTINATIONS i AVERAGE RATING
public class CreateAirlineDto {

    private Long id;
    private String name;
    private Address address;
    private String description;
    private double averageRating;;
    private double numberOfRating;
    private double sumRating;
    public CreateAirlineDto(){}
    public CreateAirlineDto(Airline airline){
        this.averageRating = airline.getAverageRating();
        this.id = airline.getId();
        this.name = airline.getName();
        this.address = airline.getAddress();
        this.description = airline.getDescription();
        this.averageRating = airline.getAverageRating();
        this.sumRating = airline.getSumRating();
        this.numberOfRating = airline.getNumberOfRating();
    }

    public CreateAirlineDto(Long id, String name, Address address, String description){
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;

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

    public double getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(double numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public double getSumRating() {
        return sumRating;
    }

    public void setSumRating(double sumRating) {
        this.sumRating = sumRating;
    }
}
