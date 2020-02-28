package com.isa.reservation.dto;

import com.isa.reservation.model.FlightDestination;

public class FlightDestinationDto {

    private Long id;

    private DestinationDto destination;

    private FlightShowInfoDto flight;        //ima  airline id

    private String description;

    public FlightDestinationDto(){}

    public FlightDestinationDto(FlightDestination flightDestination){
        id = flightDestination.getId();

        description = flightDestination.getDescription();
        //flight = new FlightShowInfoDto(flightDestination.getFlight());
        destination = new DestinationDto(flightDestination.getDestination());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DestinationDto getDestination() {
        return destination;
    }

    public void setDestination(DestinationDto destination) {
        this.destination = destination;
    }

    public FlightShowInfoDto getFlight() {
        return flight;
    }

    public void setFlight(FlightShowInfoDto flight) {
        this.flight = flight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
