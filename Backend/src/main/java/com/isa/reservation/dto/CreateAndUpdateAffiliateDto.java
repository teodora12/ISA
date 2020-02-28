package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Affiliate;


public class CreateAndUpdateAffiliateDto {

    private Long id;

    private Address address;

    private String name;


    public CreateAndUpdateAffiliateDto () {

    }

    public CreateAndUpdateAffiliateDto (Affiliate affiliate){
        this.id = affiliate.getId();
        this.name = affiliate.getName();
        this.address = affiliate.getAddress();
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
