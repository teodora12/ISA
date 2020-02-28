package com.isa.reservation.dto;

import com.isa.reservation.model.Room;

import java.util.Date;

public class RoomDto {
    //name,id,price,hotel

    private String name;
    private Long id;
    private double price;
 //   private HotelDto hotelDto;
    private boolean isAvailable;
    private int maxNumberOfGuests;
    private int numberOfBeds;
    private Date dateOfArrival;
    private Date dateOfDeparture;
    private int numberOfNights;
    private double averageRating;

    public RoomDto(){

    }

    public RoomDto(Room room){
        this.name = room.getName();
        this.id = room.getId();
        this.price = room.getPrice();
        this.isAvailable = room.isAvailable();
        this.maxNumberOfGuests = room.getMaxNumberOfGuests();
        this.numberOfBeds = room.getNumberOfBeds();

        this.dateOfArrival = room.getDateOfArrival();
        this.dateOfDeparture = room.getDateOfDeparture();
    }

    public RoomDto(Room room,int numOfNights){
  //      this.name = room.getName();
  //      this.id = room.getId();
  //      this.price = room.getPrice();
        this.isAvailable = room.isAvailable();
  //      this.maxNumberOfGuests = room.getMaxNumberOfGuests();
        this.numberOfBeds = room.getNumberOfBeds();
        this.numberOfNights = numOfNights;
        this.dateOfArrival = room.getDateOfArrival();
        this.dateOfDeparture = room.getDateOfDeparture();
  //      this.averageRating = room.getAverageRating();

    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

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

    public int getMaxNumberOfGuests() {
        return maxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(int maxNumberOfGuests) {
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
/*
    public HotelDto getHotelDto() {
        return hotelDto;
    }

    public void setHotelDto(HotelDto hotelDto) {
        this.hotelDto = hotelDto;
    }
*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
