package com.isa.reservation.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class FlightReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_reservation_id")
    private Long id;

    @Column
    private Long flightId;

    @Column
    private Long userId;                    // onaj koji je napravio rezervaciju

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "flight_reservation", referencedColumnName = "flight_reservation_id")
    private Set<PassengerOnFlightSeat> passengersOnSeats;

    public FlightReservation() {

        passengersOnSeats = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<PassengerOnFlightSeat> getPassengersOnSeats() {
        return passengersOnSeats;
    }

    public void setPassengersOnSeats(Set<PassengerOnFlightSeat> passengersOnSeats) {
        this.passengersOnSeats = passengersOnSeats;
    }
}
