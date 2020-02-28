package com.isa.reservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.reservation.dto.CreateFlightDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Table
@Entity(name = "FlIGHTS")
public class Flight implements Serializable {

    //U FLIGHT-AIRLINES TREBA PROMENITI NULLABLE NA FALSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "arrival_date_time")
    private Date arrivalDateTime;

    @Column(name = "departure_date_time")
    private Date departureDateTime;

    @NotNull
    @Column
    private double duration;

    @NotNull
    @Column
    private int distance;   //(ili remoteness

    @NotNull
    @Column(name = "flight_changes")
    private int flightChanges;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    //@JoinColumn(name="airline_id", referencedColumnName="airline_id", nullable=true)
    private Airline airline;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "FLIGHT_CHANGES_LOCATIONS", joinColumns = { @JoinColumn(name = "flight_id") },
//            inverseJoinColumns = { @JoinColumn(name = "destination_id") })
//    private Set<Destination> flightChangesLocations = new HashSet<Destination>();


    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<FlightDestination> destinations;

    @OneToOne
    private AirplaneSeatArrangement seatArrangement;

    @Column
    private double economyPrice;

    @Column
    private double premiumEconomyPrice;

    @Column
    private double businessPrice;

    @Column
    private double firstPrice;

    @Column(name = "average_rating")
    private double averageRating;

    @Column
    private double numberOfRating;

    @Column
    private double sumRating;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},fetch=FetchType.LAZY, orphanRemoval = true)
    private Set<Seat> seats;

    @Column
    private String baggageDescription;

    @Column
    private String additionalServices;

    public Flight() {
        //flightChangesLocations = new HashSet<>();
        destinations = new HashSet<>();
        seats = new HashSet<>();
    }

    public Flight(CreateFlightDto flightDto){       //airine i destinations u kontroleru, jer se salje id samo
        setId(flightDto.getId());
        setArrivalDateAndTime(flightDto.getArrivalDateAndTime());
        setAverageRating(flightDto.getAverageRating());
        setDepartureDateAndTime(flightDto.getDepartureDateAndTime());
        setDistance(flightDto.getDistance());
        setDuration(flightDto.getDuration());
        setEconomyPrice(flightDto.getEconomyPrice());
        setPremiumEconomyPrice(flightDto.getPremiumEconomyPrice());
        setBusinessPrice(flightDto.getBusinessPrice());
        setFirstPrice(flightDto.getFirstPrice());
        setSeatArrangement(flightDto.getSeatArrangement());
        setFlightChanges(flightDto.getFlightChanges());

        if (flightDto.getSeats() == null) {
            setSeats(new HashSet<>());
        } else {
            setSeats(flightDto.getSeats());
        }
        setDestinations(new HashSet<>());
        setBaggageDescription(flightDto.getBaggageDescription());
        setAdditionalServices(flightDto.getAdditionalServices());
        setSumRating(flightDto.getSumRating());
        setNumberOfRating(flightDto.getNumberOfRating());
    }
//    public Flight(FlightShowInfoDto flightDto, Airline airline){ //za create
//
//        setId(flightDto.getId());
//        setArrivalDateAndTime(flightDto.getArrivalDateAndTime());
//        setAverageRating(flightDto.getAverageRating());
//        setDepartureDateAndTime(flightDto.getDepartureDateAndTime());
//        setDistance(flightDto.getDistance());
//        setDuration(flightDto.getDuration());
//        setFlightChanges(flightDto.getFlightChanges());
//        setPrice(flightDto.getPrice());
//        setFromDest(flightDto.getFromDest());
//        setTo(flightDto.getToDest());
//        setAirline(airline);
//        setSeatArrangement(flightDto.getSeatArrangement());
//        seats = new HashSet<>();
//        //SEATS PODESAVA U CONTROLLERU
//    }

//    public Flight(FlightShowInfoDto flightDto){  //update //MORA FLIGHT DTO SA SEATS
//
//        setId(flightDto.getId());
//        setArrivalDateAndTime(flightDto.getArrivalDateAndTime());
//        setAverageRating(flightDto.getAverageRating());
//        setDepartureDateAndTime(flightDto.getDepartureDateAndTime());
//        setDistance(flightDto.getDistance());
//        setDuration(flightDto.getDuration());
//        setPrice(flightDto.getPrice());
//
//
//        setAirline(airline);
//        setSeatArrangement(flightDto.getSeatArrangement());
//        setSeats(new HashSet<>());
//        setDestinations(new HashSet<>());
//
//    }

 /*   public Flight(FlightDto flightDto){

        setId(flightDto.getId());
        setArrivalDateAndTime(flightDto.getArrivalDateAndTime());
        setAverageRating(flightDto.getAverageRating());
        setDepartureDateAndTime(flightDto.getDepartureDateAndTime());
        setDistance(flightDto.getDistance());
        setDuration(flightDto.getDuration());
        setFlightChanges(flightDto.getFlightChanges());
        setPrice(flightDto.getPrice());
        setFromDest(flightDto.getFromDest());
        setToDest(flightDto.getToDest());

    }*/

    public Long getId() {
        return id;
    }

    public Date getArrivalDateAndTime() {
        return arrivalDateTime;
    }

    public Date getDepartureDateAndTime() {
        return departureDateTime;
    }

    public double getDuration() {
        return duration;
    }

    public int getDistance() {
        return distance;
    }

    public int getFlightChanges() {
        return flightChanges;
    }

    public Airline getAirline() {
        return airline;
    }

    public Set<FlightDestination> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<FlightDestination> destinations) {
        this.destinations = destinations;
    }


    public double getAverageRating() {
        return averageRating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArrivalDateAndTime(Date arrivalDateAndTime) {
        this.arrivalDateTime = arrivalDateAndTime;
    }

    public void setDepartureDateAndTime(Date departureDateAndTime) {
        this.departureDateTime = departureDateAndTime;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setFlightChanges(int flightChanges) {
        this.flightChanges = flightChanges;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

//    public void setFlightChangesLocations(Set<Destination> flightChangesLocations) {
//        this.flightChangesLocations = flightChangesLocations;
//    }

//    public Destination getFromDest() {
//        return fromDest;
//    }
//
//    public void setFromDest(Destination fromDest) {
//        this.fromDest = fromDest;
//    }
//
//    public Destination getTo() {
//        return toDest;
//    }
//
//    public void setTo(Destination toDest) {
//        this.toDest = toDest;
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

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public AirplaneSeatArrangement getSeatArrangement() {
        return seatArrangement;
    }

    public void setSeatArrangement(AirplaneSeatArrangement seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    public Set<Seat> getSeats() { return seats; }

    public void setSeats(Set<Seat> seats) { this.seats = seats; }

    public String getBaggageDescription() {
        return baggageDescription;
    }

    public void setBaggageDescription(String luggageDescription) {
        this.baggageDescription = luggageDescription;
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
