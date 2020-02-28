package com.isa.reservation.model;

import com.isa.reservation.dto.AirlineDto;
import com.isa.reservation.dto.CreateAirlineDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "AIRLINES")
public class Airline implements Serializable {

    //Fali Spisak karata sa popustima za brzu rezervaciju
    //• Konfiguraciju segmenata i mesta u avionima
    //• Cenovnik i informacije o prtljagu

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Address address;

    @Column
    private String description;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "airline_destinations", joinColumns = @JoinColumn(name="airline", referencedColumnName="airline_id"),
               inverseJoinColumns = @JoinColumn(name="destination", referencedColumnName="destination_id"))
    private Set<Destination> availableDestinations;

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy = "airline")
    private Set<Flight> flights;

    @Column(name = "average_rating")
    private double averageRating;

    @Column
    private double numberOfRating;

    @Column
    private double sumRating;

    public Airline(){
        flights = new HashSet<>();
        availableDestinations = new HashSet<>();
    }

    public Airline(CreateAirlineDto airlineDto){

        setId(airlineDto.getId());
        setName(airlineDto.getName());
        setDescription(airlineDto.getDescription());
        setAddress(airlineDto.getAddress());

        flights = new HashSet<>();
        availableDestinations = new HashSet<>();
    }

    public Airline(AirlineDto airlineDto){

        setId(airlineDto.getId());
        setName(airlineDto.getName());
        setDescription(airlineDto.getDescription());
        setAddress(airlineDto.getAddress());
        //setAvailableDestinations(airlineDto.getAvailableDestinations());          u kontroleru
        setAverageRating(airlineDto.getAverageRating());
        setNumberOfRating(airlineDto.getNumberOfRating());
        setSumRating(airlineDto.getSumRating());
        flights = new HashSet<>();
        availableDestinations = new HashSet<>();


    }

    public Airline(Long id, String name, Address address, String description, Set<Destination> availableDestinations, Set<Flight> flights, double averageRating, double numberOfRating, double sumRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.availableDestinations = availableDestinations;
        this.flights = flights;
        this.averageRating = averageRating;
        this.numberOfRating = numberOfRating;
        this.sumRating = sumRating;
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

    public Set<Destination> getAvailableDestinations() {
        return availableDestinations;
    }

    public void setAvailableDestinations(Set<Destination> availableDestinations) {
        this.availableDestinations = availableDestinations;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
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

