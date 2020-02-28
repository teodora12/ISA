package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Affiliate;


public class AffiliateDto {

    private Long id;

    private Address address;

    private String name;

    private Long carServiceId;

    public AffiliateDto (Affiliate affiliate){
        this.carServiceId = affiliate.getCarService().getId();
        this.id =affiliate.getId();
        this.address = affiliate.getAddress();
        this.name = affiliate.getName();
    }

    public AffiliateDto()
    {

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
