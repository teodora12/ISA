package com.isa.reservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.reservation.dto.CreateAndUpdateCarDto;
import org.glassfish.jersey.client.ClientAsyncExecutor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "CARS")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private boolean inUse;

    @Column
    private int yearOfManufacture;

    @Column
    private int numberOfSeats;

    @Column
    private double averageRating;

    @Column
    private double numberOfRating;

    @Column
    private double sumRating;

    @Column
    private int price;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="cars_carServices", referencedColumnName="carService_id", nullable=false)
    private CarService carService;


    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="car")
    private Set<CarReservation> carReservations;

//    @Column
//    private boolean discount;
//
//    @Column
//    private Date startDiscount;
//
//    @Column
//    private Date finishDiscount;

    public Car(){
        this.inUse = false;
        carReservations = new HashSet<CarReservation>();
    }

    public Car(CreateAndUpdateCarDto carDto){

        setId(carDto.getId());
        setName(carDto.getName());
        setType(carDto.getType());
        setMake(carDto.getMake());
        setModel(carDto.getModel());
        setYearOfManufacture(carDto.getYearOfManufacture());
        setNumberOfSeats(carDto.getNumberOfSeats());
        setPrice(carDto.getPrice());
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

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public Set<CarReservation> getCarReservations() {
        return carReservations;
    }

    public void setCarReservations(Set<CarReservation> carReservations) {
        this.carReservations = carReservations;
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
