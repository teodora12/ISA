package com.isa.reservation.dto;

import com.isa.reservation.model.Car;

public class CarDto {

    private Long id;

    private String name;

    private String type;

    private String make;

    private String model;

    private boolean inUse;

    private int yearOfManufacture;

    private int numberOfSeats;

    private double averageRating;

    private int price;

    private Long carServiceId;


    public CarDto(Car car) {

        this.id = car.getId();
        this.carServiceId = car.getCarService().getId();
        this.name = car.getName();
        this.type = car.getType();
        this.make = car.getMake();
        this.model = car.getModel();
        this.inUse = car.isInUse();
        this.yearOfManufacture = car.getYearOfManufacture();
        this.numberOfSeats = car.getNumberOfSeats();
        this.averageRating = car.getAverageRating();
        this.price = car.getPrice();
    }

    public CarDto () {

    }

    public Long getCarServiceId() {
        return carServiceId;
    }

    public void setCarServiceId(Long carServiceId) {
        this.carServiceId = carServiceId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
