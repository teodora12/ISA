package com.isa.reservation.dto;


import com.isa.reservation.model.Car;
import com.isa.reservation.model.CarService;

public class CreateAndUpdateCarDto {

    private Long id;

    private String name;

    private String type;

    private String make;

    private String model;

    private int yearOfManufacture;

    private int numberOfSeats;

    private int price;

    public CreateAndUpdateCarDto(){

    }
    public CreateAndUpdateCarDto (Car car){
        this.id = car.getId();
        this.name = car.getName();
        this.make = car.getMake();
        this.model = car.getModel();
        this.type = car.getType();
        this.yearOfManufacture = car.getYearOfManufacture();
        this.numberOfSeats = car.getNumberOfSeats();
        this.price = car.getPrice();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
