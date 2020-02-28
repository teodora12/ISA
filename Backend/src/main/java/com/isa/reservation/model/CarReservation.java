package com.isa.reservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table
@Entity(name = "CARRESERVATIONS")
public class CarReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_reservation_id")
    private Long id;

    @Column
    private Date pickUpDate;

    @Column
    private Date dropOffDate;

    @OneToOne
    private Address pickUpAddress;

    @OneToOne
    private Address dropOffAddress;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  //  @JoinColumn(name="reservations_car", referencedColumnName="car_id", nullable=false)
    private Car car;


    @Column
    private Long totalPrice;

    public CarReservation(){
    }


    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Address pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public Address getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(Address dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
