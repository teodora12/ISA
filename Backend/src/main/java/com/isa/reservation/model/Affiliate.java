package com.isa.reservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.reservation.dto.CreateAndUpdateAffiliateDto;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "AFFILIATES")
public class Affiliate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "affiliate_id")
    private Long id;


  //  @JoinColumn(name="affiliates_carServices", referencedColumnName="carService_id", nullable=false)
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CarService carService;

    @OneToOne
    private Address address;

    @Column
    private String name;


    public Affiliate () {}

    public Affiliate(CreateAndUpdateAffiliateDto affiliateDto){
        setId(affiliateDto.getId());
        setName(affiliateDto.getName());
        setAddress(affiliateDto.getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
