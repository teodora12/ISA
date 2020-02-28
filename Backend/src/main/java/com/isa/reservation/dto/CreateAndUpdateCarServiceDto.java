package com.isa.reservation.dto;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.CarService;

public class CreateAndUpdateCarServiceDto {

    private Long id;
    private String name;
    private Address address;
    private String description;

    public CreateAndUpdateCarServiceDto() { }


//    public CreateAndUpdateCarServiceDto (Long id, String name, Address addess, String description){
//        this.id = id;
//        this.name = name;
//        this.address = addess;
//        this.description = description;
//    }
//

    public CreateAndUpdateCarServiceDto (CarService carService){
        this.id = carService.getId();
        this.name = carService.getName();
        this.address = carService.getAddress();
        this.description = carService.getDescription();
    }
    public Long getId() { return id; }

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
}

