package com.isa.reservation.dto;

import com.isa.reservation.model.Destination;
import com.isa.reservation.model.Flight;
import com.isa.reservation.model.FlightDestination;
import com.isa.reservation.model.Seat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Za get, getAll, search Flighta
 *
 * IMA AIRLINE_DTO, FLIGHT_DESTINATION_DTO, I ID OD SEAT ARRNGEMENT-A
 * Sva ostala polja su ista kao Flight
 * */
public class FlightDto {

    private Long id;
    private CreateAirlineDto airlineDto ;
    private Date arrivalDateAndTime;
    private Date departureDateAndTime;
    private double duration;
    private int distance;   //(ili remoteness
    private int flightChanges;
    private Set<FlightDestinationDto> destinations;
    private double economyPrice;
    private double premiumEconomyPrice;
    private double businessPrice;
    private double firstPrice;
    private double averageRating;
    private Long seatArrangement;
    private Set<Seat> seats;
    private String baggageDescription;
    private String additionalServices;

    private double numberOfRating;
    private double sumRating;

    public FlightDto(Flight flight){
        this.id = flight.getId();
        this.airlineDto = new CreateAirlineDto(flight.getAirline());
        this.arrivalDateAndTime = flight.getArrivalDateAndTime();
        this.departureDateAndTime = flight.getDepartureDateAndTime();
        this.duration = flight.getDuration();
        this.distance = flight.getDistance();
        this.flightChanges = flight.getFlightChanges();
        this.destinations = new HashSet<>();
        for (FlightDestination d : flight.getDestinations()) {
            destinations.add(new FlightDestinationDto(d));
        }

        //        this.flightChangesLocations = flight.getFlightChangesLocations();
        this.economyPrice = flight.getEconomyPrice();
        this.premiumEconomyPrice = flight.getPremiumEconomyPrice();
        this.businessPrice = flight.getBusinessPrice();
        this.firstPrice = flight.getFirstPrice();
        this.averageRating = flight.getAverageRating();
//        this.fromDest = flight.getFromDest();
//        this.toDest = flight.getTo();
        this.seatArrangement = flight.getSeatArrangement().getId();
        seats = flight.getSeats();

        this.baggageDescription = flight.getBaggageDescription();
        this.additionalServices = flight.getAdditionalServices();

        this.numberOfRating = flight.getNumberOfRating();
        this.sumRating = flight.getSumRating();
    }

    public FlightDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateAirlineDto getAirlineDto() {
        return airlineDto;
    }

    public void setAirlineDto(CreateAirlineDto airlineDto) {
        this.airlineDto = airlineDto;
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

    public Set<FlightDestinationDto> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<FlightDestinationDto> destinations) {
        this.destinations = destinations;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    //    public Set<Destination> getFlightChangesLocations() {
//        return flightChangesLocations;
//    }

//    public Destination getFromDest() {
//        return fromDest;
//    }
//
//    public void setFromDest(Destination fromDest) {
//        this.fromDest = fromDest;
//    }
//
//    public Destination getToDest() {
//        return toDest;
//    }
//
//    public void setToDest(Destination toDest) {
//        this.toDest = toDest;
//    }



//    public void setFlightChangesLocations(Set<Destination> flightChangesLocations) {
//        this.flightChangesLocations = flightChangesLocations;
//    }


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

    public Long getSeatArrangement() {
        return seatArrangement;
    }

    public void setSeatArrangement(Long seatArrangement) {
        this.seatArrangement = seatArrangement;
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
