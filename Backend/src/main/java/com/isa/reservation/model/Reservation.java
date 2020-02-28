package com.isa.reservation.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Set;
import java.util.Date;

@Table
@Entity(name =  "RESERVATIONS")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    //@OneToOne
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private CarReservation carReservation;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private FlightReservation flightReservation;

    @Column
    private Date dateCreated;


    @OneToOne
    private RoomReservation roomReservation;

    public Reservation() {
    }

    public RoomReservation getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarReservation getCarReservation() {
        return carReservation;
    }

    public void setCarReservation(CarReservation carReservation) {
        this.carReservation = carReservation;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
