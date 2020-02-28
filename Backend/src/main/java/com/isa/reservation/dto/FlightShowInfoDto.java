package com.isa.reservation.dto;

import com.isa.reservation.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Za search
 *
 * IMA AIRLINE - SAMO IME,
 *
 * NEMA LISTU FLIGHT_DESTINATION, VEC LISTU DESTINACIJA-DTO NA KOJE PRESEDA, I DVA OBJEKTA FROM I TO(DESTINATION-DTO)
 * I ID OD SEAT ARRNGEMENT-A
 * Sva ostala polja su ista kao Flight
 * */
public class FlightShowInfoDto {

    private Long id;
    private String airline ;
    private Date arrivalDateAndTime;
    private Date departureDateAndTime;
    private double duration;
    private int distance;   //(ili remoteness
    private int flightChanges;
    private Set<DestinationDto> destinations;
    private DestinationDto from;
    private DestinationDto to;
    private double economyPrice;
    private double premiumEconomyPrice;
    private double businessPrice;
    private double firstPrice;
    private double averageRating;
    private AirplaneSeatArrangement seatArrangement;
    private Set<Seat> seats;
    private String baggageDescription;
    private String additionalServices;

    private double numberOfRating;
    private double sumRating;

    public FlightShowInfoDto(Flight flight){
        this.id = flight.getId();
        this.airline = flight.getAirline().getName();
        this.arrivalDateAndTime = flight.getArrivalDateAndTime();
        this.departureDateAndTime = flight.getDepartureDateAndTime();
        this.duration = flight.getDuration();
        this.distance = flight.getDistance();
        this.flightChanges = flight.getFlightChanges();
        this.destinations = new HashSet<>();
        for (FlightDestination d : flight.getDestinations()) {

            if (d.getDescription().equals("connecting")){
                destinations.add(new DestinationDto(d.getDestination()));
            } else if (d.getDescription().equals("departure")){
                this.from = new DestinationDto(d.getDestination());
            } else {
                this.to = new DestinationDto(d.getDestination());
            }
        }

        this.economyPrice = flight.getEconomyPrice();
        this.premiumEconomyPrice = flight.getPremiumEconomyPrice();
        this.businessPrice = flight.getBusinessPrice();
        this.firstPrice = flight.getFirstPrice();
        this.averageRating = flight.getAverageRating();
        this.seatArrangement = flight.getSeatArrangement();
        seats = flight.getSeats();
        this.additionalServices = flight.getAdditionalServices();
        this.baggageDescription = flight.getBaggageDescription();
        this.sumRating = flight.getSumRating();
        this.numberOfRating = flight.getNumberOfRating();
    }

    public FlightShowInfoDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getArrivalDateAndTime() {
        return arrivalDateAndTime;
    }

    public void setArrivalDateAndTime(Date arrivalDateAndTime) {
        this.arrivalDateAndTime = arrivalDateAndTime;
    }

    public Date getDepartureDateAndTime() {
        return departureDateAndTime;
    }

    public void setDepartureDateAndTime(Date departureDateAndTime) {
        this.departureDateAndTime = departureDateAndTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getFlightChanges() {
        return flightChanges;
    }

    public void setFlightChanges(int flightChanges) {
        this.flightChanges = flightChanges;
    }

    public Set<DestinationDto> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<DestinationDto> destinations) {
        this.destinations = destinations;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public double getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(double economyPrice) {
        this.economyPrice = economyPrice;
    }

    public double getPremiumEconomyPrice() {
        return premiumEconomyPrice;
    }

    public void setPremiumEconomyPrice(double premiumEconomyPrice) {
        this.premiumEconomyPrice = premiumEconomyPrice;
    }

    public double getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(double businessPrice) {
        this.businessPrice = businessPrice;
    }

    public double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public AirplaneSeatArrangement getSeatArrangement() {
        return seatArrangement;
    }

    public void setSeatArrangement(AirplaneSeatArrangement seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    public DestinationDto getFrom() {
        return from;
    }

    public void setFrom(DestinationDto from) {
        this.from = from;
    }

    public DestinationDto getTo() {
        return to;
    }

    public void setTo(DestinationDto to) {
        this.to = to;
    }

    public String getBaggageDescription() {
        return baggageDescription;
    }

    public void setBaggageDescription(String baggageDescription) {
        this.baggageDescription = baggageDescription;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
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
