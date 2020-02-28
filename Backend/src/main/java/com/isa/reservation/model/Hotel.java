package com.isa.reservation.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "HOTELS")
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Address address;

    @Column
    private String description;

    @OneToMany(cascade = {CascadeType.ALL} , fetch = FetchType.LAZY, mappedBy = "hotel")
//    @JoinTable(name = "hotel_rooms", joinColumns = @JoinColumn(name = "hotel", referencedColumnName = "hotel_id"),
//           inverseJoinColumns = @JoinColumn(name = "room", referencedColumnName = "room_id"))
    private Set<Room> rooms;



    @Column//(name ="avg_rating_hotel", nullable = true)
    private double averageRating;

    public Hotel(){
        rooms = new HashSet<Room>();
    }

    public Hotel(String name, Address address, String description, double averageRating) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }




}
