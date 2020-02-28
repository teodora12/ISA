package com.isa.reservation.model;

import com.isa.reservation.dto.DestinationDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "DESTINATIONS")
public class Destination implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String shortName;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private Set<FlightDestination> flights;


    public Destination(){
        flights = new HashSet<>();
    };

    public Destination (DestinationDto destinationDto) {
        id = destinationDto.getId();
        name = destinationDto.getName();
        shortName = destinationDto.getShortName();
        address = destinationDto.getAddress();

        flights = new HashSet<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getShortName() { return shortName; }

    public void setShortName(String shortName) { this.shortName = shortName; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public Set<FlightDestination> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightDestination> flights) {
        this.flights = flights;
    }
}
