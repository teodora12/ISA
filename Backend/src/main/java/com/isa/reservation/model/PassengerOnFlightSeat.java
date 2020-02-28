package com.isa.reservation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class PassengerOnFlightSeat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_on_flight_seat_id")
    private Long id;

    @Column
    private Long passengerId;

    @Column
    private String passengerName;

    @Column
    private String passengerLastName;

    @Column
    private String passengerPassport;

    @ManyToOne
    private Seat seat;

    public PassengerOnFlightSeat() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerPassport() {
        return passengerPassport;
    }

    public void setPassengerPassport(String passengerPassport) {
        this.passengerPassport = passengerPassport;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
