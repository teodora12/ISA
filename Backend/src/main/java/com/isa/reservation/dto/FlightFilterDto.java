package com.isa.reservation.dto;

import java.util.List;

public class FlightFilterDto {

    private String airline;
    private double fromDuration;
    private double toDuration;
    private double fromPrice;
    private double toPrice;
    private List<FlightShowInfoDto> flights;

    public FlightFilterDto(){}

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getFromDuration() {
        return fromDuration;
    }

    public void setFromDuration(double fromDuration) {
        this.fromDuration = fromDuration;
    }

    public double getToDuration() {
        return toDuration;
    }

    public void setToDuration(double toDuration) {
        this.toDuration = toDuration;
    }

    public double getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(double fromPrice) {
        this.fromPrice = fromPrice;
    }

    public double getToPrice() {
        return toPrice;
    }

    public void setToPrice(double toPrice) {
        this.toPrice = toPrice;
    }

    public List<FlightShowInfoDto> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightShowInfoDto> flights) {
        this.flights = flights;
    }
}
