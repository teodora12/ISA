package com.isa.reservation.dto;

import com.isa.reservation.model.*;

import java.util.HashSet;
import java.util.Set;

public class CarServiceDto {

    private Long id;

    private String name;

    private Address address;

    private String description;

    private Set<CarDto> cars;

    private double averageRating;

    private Set<AffiliateDto> affiliates;



    public CarServiceDto(CarService carService){
        this.averageRating = carService.getAverageRating();
        this.id = carService.getId();
        this.name = carService.getName();
        this.address = carService.getAddress();
        this.description = carService.getDescription();
        this.cars = new HashSet<>();
        for(Car c : carService.getCars()){
            cars.add(new CarDto(c));
        }
        this.averageRating = carService.getAverageRating();
        this.affiliates =  new HashSet<>();
        for(Affiliate a : carService.getAffiliates()){
            affiliates.add(new AffiliateDto(a));
        }
    }

    public CarServiceDto (){

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

    public void setAdrdess(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CarDto> getCars() {
        return cars;
    }

    public void setCars(Set<CarDto> cars) {
        this.cars = cars;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Set<AffiliateDto> getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(Set<AffiliateDto> affiliates) {
        this.affiliates = affiliates;
    }
}
