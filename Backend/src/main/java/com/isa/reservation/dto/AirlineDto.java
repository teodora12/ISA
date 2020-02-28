package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Destination;
import com.isa.reservation.model.Airline;
import com.isa.reservation.model.Flight;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
//              DESTINATION DTO, CRATE AND UPDATE FLIGHT DTO
public class AirlineDto implements Serializable {

    private Long id;

    private String name;

    private Address address;

    private String description;

    private Set<DestinationDto> availableDestinations;

    private Set<FlightShowInfoDto> flights;

    private double numberOfRating;

    private double sumRating;

    private double averageRating;

    public AirlineDto(Airline airline) {
        this.id = airline.getId();
        this.name = airline.getName();
        this.address = airline.getAddress();
        this.description = airline.getDescription();
//        this.availableDestinations = airline.getAvailableDestinations();
        this.availableDestinations = new HashSet<>();
        for (Destination d : airline.getAvailableDestinations()) {
            availableDestinations.add(new DestinationDto(d));
        }

        this.flights = new HashSet<>();
        Set<Flight> flightsGet = airline.getFlights();
        for(Flight f : flightsGet){
            flights.add(new FlightShowInfoDto(f));      //u airlineDto, lista dto-ova da ne bi bilo duplih ref
        }
        this.averageRating = airline.getAverageRating();
        this.setNumberOfRating(airline.getNumberOfRating());
        this.setSumRating(airline.getSumRating());
    }

    public AirlineDto() {
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

    public Set<DestinationDto> getAvailableDestinations() {
        return availableDestinations;
    }

    public void setAvailableDestinations(Set<DestinationDto> availableDestinations) {
        this.availableDestinations = availableDestinations;
    }

    public Set<FlightShowInfoDto> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightShowInfoDto> flights) {
        this.flights = flights;
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
