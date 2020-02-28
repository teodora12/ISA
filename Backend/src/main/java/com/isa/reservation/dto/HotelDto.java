package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Hotel;
import com.isa.reservation.model.Room;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelDto {
    //id,name,address,description,rooms,avgrating,

    private Long id;

    private String name;

    private Address address;

    private String description;

    private Set<RoomDto> rooms;

    private double averageRating;

    public HotelDto(){

    }

    public HotelDto(Hotel hotel){
        this.address = hotel.getAddress();
        this.averageRating = hotel.getAverageRating();
        this.description = hotel.getDescription();
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.rooms = new HashSet<>();

        for(Room r : hotel.getRooms()){
            rooms.add(new RoomDto(r));      // ....

        }
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

    public Set<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDto> rooms) {
        this.rooms = rooms;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
