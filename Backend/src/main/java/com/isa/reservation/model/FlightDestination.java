package com.isa.reservation.model;

import com.isa.reservation.dto.FlightDestinationDto;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "FLIGHT_DESTINATIONS")
@Table
public class FlightDestination implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_destination_id")
    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "flight_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "destination_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Destination destination;

    @Column
    private String description;         //arrival, departure, ""

    public FlightDestination() {}

    public FlightDestination(FlightDestinationDto flightDestinationDto){
        id = flightDestinationDto.getId();
        description = flightDestinationDto.getDescription();
    }

    public FlightDestination(Flight flight, Destination destination) {
        this.flight = flight;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
