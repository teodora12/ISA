package com.isa.reservation.model;

import com.isa.reservation.dto.CreateAndUpdateCarServiceDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "CARSERVICES")
public class CarService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carService_id")
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Address address;

    @Column
    private String description;


    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="carService")
    private Set<Car> cars;

    @Column
    private double averageRating;

    @Column
    private double numberOfRating;

    @Column
    private double sumRating;

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="carService")
    private Set<Affiliate> affiliates;

    public CarService(){
        cars = new HashSet<Car>();
    }

    public CarService(CreateAndUpdateCarServiceDto carServiceDto){
        setId(carServiceDto.getId());
        setName(carServiceDto.getName());
        setAddress(carServiceDto.getAddress());
        setDescription(carServiceDto.getDescription());
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Set<Affiliate> getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(Set<Affiliate> affiliates) {
        this.affiliates = affiliates;
    }

    public double getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(double numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public double getSumRating() {
        return sumRating;
    }

    public void setSumRating(double sumRating) {
        this.sumRating = sumRating;
    }
}
