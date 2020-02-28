package com.isa.reservation.controller;

import com.isa.reservation.dto.CarDto;
import com.isa.reservation.dto.CreateAndUpdateCarDto;
import com.isa.reservation.dto.Rating.CompareDto;
import com.isa.reservation.dto.Rating.RatingCarDto;
import com.isa.reservation.model.Car;
import com.isa.reservation.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/api/cars")
public class CarController {

    @Autowired
    private CarService carService;



    @GetMapping(value = "/{id}")
    public ResponseEntity<CarDto> getCarServiceById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        CarDto carServiceDto = new CarDto(car);
        if(car == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(carServiceDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<CarDto>> getCars(){
        List<Car> cars = this.carService.getAllCars();
        List<CarDto> carDtos = new ArrayList<>();
        for(Car car :cars) {
            carDtos.add(new CarDto(car));
        }
        if(cars == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity<CreateAndUpdateCarDto> addCar(@RequestBody CreateAndUpdateCarDto carDto){
        Car car = this.carService.addCar(carDto);
        return ResponseEntity.ok(new CreateAndUpdateCarDto(car));
    }


    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity<CreateAndUpdateCarDto> updateCar(@RequestBody CreateAndUpdateCarDto carDto) {
       Car car = this.carService.getCarById(carDto.getId());
        if(car == null){
            return ResponseEntity.notFound().build();
        }

        car = this.carService.updateCar(carDto);
        return ResponseEntity.ok(new CreateAndUpdateCarDto(car));
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity deleteCar(@PathVariable Long id){
        Car car = this.carService.deleteCar(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/types")
    public ResponseEntity<Set<String>> getTypesOfCar() {
        Set<String> types = carService.findCarTypes();
        return  new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping(value = "/numberOfSeats")
    public ResponseEntity<Set<Integer>> getNumberOfSeats() {
        Set<Integer> numberOfSeats = carService.findNumberOfSeats();
        return  new ResponseEntity<>(numberOfSeats, HttpStatus.OK);
    }


    @PutMapping(value = "/rating")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Double> ratingCarService(@RequestBody RatingCarDto ratingCarDto) {
        Double rating = this.carService.ratingCar(ratingCarDto);
        if(rating == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rating);
    }

    @PostMapping(value ="/compare")
    public boolean compare(@RequestBody CompareDto compareDto){
        if(compareDto.getToday().after(compareDto.getDropOff())){
            return true;
        }
        else{
            return false;
        }

    }

}
