package com.isa.reservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "ROOMS")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column
    private String name;

    @Column  //cena za odredjeni vremenski period
    private double price;

    @Column//(name = "avg_rating_room")
    private double averageRating;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
 //   @JoinColumn(name = "rooms_hotel", referencedColumnName = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column//(name = "available", nullable = true)
    private boolean isAvailable;

    @Column//(name = "max_guests", nullable = true)
    private int maxNumberOfGuests;

    @Column//(name = "num_beds", nullable = true)
    private int numberOfBeds;

    @Column//(name = "arrival", nullable = true)
    private Date dateOfArrival;

    @Column//(name = "departure", nullable = true)
    private Date dateOfDeparture;


    @JsonManagedReference
    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private RoomReservation roomReservation;


    public Room(){
        this.averageRating = 0;
        this.isAvailable = true;


    }

/*
    public RoomReservation getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
    }
*/
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getMaxNumberOfGuests() {
        return maxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(int maxNumberOfGuests) {
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public RoomReservation getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
    }
}
