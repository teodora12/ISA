package com.isa.reservation.dto;

import com.isa.reservation.model.AirplaneSeatArrangement;
import com.isa.reservation.model.Flight;
import com.isa.reservation.model.FlightDestination;
import com.isa.reservation.model.Seat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Za create i update Flight-a
 *
 * IMA LISTU ID-EVA FLIGHT_DESTINATIONS, ID AIRLINE-A, ID DESTINACIJA ARRIVAL I DEPARTURE
 * Sve ostalo polja iz Flight
 * */
public class CreateFlightDto {

    private Long id;
    private Date arrivalDateAndTime;
    private Date departureDateAndTime;
    private double duration;
    private int distance;
    private int flightChanges;
    private Set<Long> destinations;        //sa from i to
    private Long fromDest;
    private Long toDest;
    private double economyPrice;
    private double premiumEconomyPrice;
    private double businessPrice;
    private double firstPrice;
    private double averageRating;
    private Long airlineId;
    private AirplaneSeatArrangement seatArrangement;
    private Set<Seat> seats;
    private String baggageDescription;
    private String additionalServices;
    private double numberOfRating;
    private double sumRating;

    CreateFlightDto(){}
    public CreateFlightDto(Flight flight) {
        this.id = flight.getId();
        this.arrivalDateAndTime = flight.getArrivalDateAndTime();
        this.departureDateAndTime = flight.getDepartureDateAndTime();
        this.duration = flight.getDuration();
        this.distance = flight.getDistance();
        this.flightChanges = flight.getFlightChanges();
        this.economyPrice = flight.getEconomyPrice();
        this.premiumEconomyPrice = flight.getPremiumEconomyPrice();
        this.businessPrice = flight.getBusinessPrice();
        this.firstPrice = flight.getFirstPrice();
        this.averageRating = flight.getAverageRating();
        this.destinations = new HashSet<>();

        for (FlightDestination flightDestination : flight.getDestinations()) {
            if (flightDestination.getDescription().equals("departure")){
                this.fromDest = flightDestination.getDestination().getId();
            } else if (flightDestination.getDescription().equals("arrival")){
                this.toDest = flightDestination.getDestination().getId();
            }
            destinations.add(flightDestination.getId());
        }

        this.seats = flight.getSeats();
        this.airlineId = flight.getAirline().getId();
        this.seatArrangement = flight.getSeatArrangement();
        this.baggageDescription = flight.getBaggageDescription();
        this.additionalServices = flight.getAdditionalServices();
        this.numberOfRating = flight.getNumberOfRating();
        this.sumRating = flight.getSumRating();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Long> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<Long> destinations) {
        this.destinations = destinations;
    }

    public Long getFromDest() {
        return fromDest;
    }

    public void setFromDest(Long fromDest) {
        this.fromDest = fromDest;
    }

    public Long getToDest() {
        return toDest;
    }

    public void setToDest(Long toDest) {
        this.toDest = toDest;
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

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public AirplaneSeatArrangement getSeatArrangement() {
        return seatArrangement;
    }

    public void setSeatArrangement(AirplaneSeatArrangement seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
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
