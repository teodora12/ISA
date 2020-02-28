package com.isa.reservation.dto;

import com.isa.reservation.model.CarService;

public class SortCarServicesByCity {

    private CarService carService;
    private String cityName;

    public SortCarServicesByCity() {

    }
    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
