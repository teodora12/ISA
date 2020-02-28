package com.isa.reservation.model;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name =  "RATING")
public class Rating   implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @Column
    private Long airlineId;

    @Column
    private double airlaneRating;

    @Column
    private Long flightId;

    @Column
    private double flightRating;
    @Column
    private Long roomId;
    @Column
    private double roomRating;


    @Column
    private Long hotelId;
    @Column
    private double hotelRating;

    @Column
    private Long carServiceId;
    @Column
    private double carServiceRating;

    @Column
    private Long carId;

    @Column
    private double carRating;
    @Column
    private Long userId;

    public Rating() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public double getAirlaneRating() {
        return airlaneRating;
    }

    public void setAirlaneRating(double airlaneRating) {
        this.airlaneRating = airlaneRating;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public double getFlightRating() {
        return flightRating;
    }

    public void setFlightRating(double flightRating) {
        this.flightRating = flightRating;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public double getRoomRating() {
        return roomRating;
    }

    public void setRoomRating(double roomRating) {
        this.roomRating = roomRating;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public double getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(double hotelRating) {
        this.hotelRating = hotelRating;
    }

    public Long getCarServiceId() {
        return carServiceId;
    }

    public void setCarServiceId(Long carServiceId) {
        this.carServiceId = carServiceId;
    }

    public double getCarServiceRating() {
        return carServiceRating;
    }

    public void setCarServiceRating(double carServiceRating) {
        this.carServiceRating = carServiceRating;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public double getCarRating() {
        return carRating;
    }

    public void setCarRating(double carRating) {
        this.carRating = carRating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
