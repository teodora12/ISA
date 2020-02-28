package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Destination;
import com.isa.reservation.model.FlightDestination;

import java.util.HashSet;
import java.util.Set;

public class DestinationDto {

    private Long id;

    private String name;

    private String shortName;

    private Address address;

    private Set<FlightDestinationDto> flights;

    public DestinationDto(){}

    public DestinationDto(Destination destination) {

        id = destination.getId();
        name = destination.getName();
        shortName = destination.getShortName();
        address = destination.getAddress();
        flights = new HashSet<>();

//        for (FlightDestination flightDestination : destination.getFlights()) {
//            flights.add(new FlightDestinationDto(flightDestination));
//        }
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<FlightDestinationDto> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightDestinationDto> flights) {
        this.flights = flights;
    }
}
