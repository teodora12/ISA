package com.isa.reservation.dto.Rating;

public class RatingCarDto {

    private String username;

    private Long carId;

    private double rating;

    RatingCarDto() {}

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
