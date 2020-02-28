package com.isa.reservation.controller;


import com.isa.reservation.dto.AvailableCarsDto;
import com.isa.reservation.dto.CarDto;
import com.isa.reservation.dto.CarReservationDto;
import com.isa.reservation.dto.CreateCarReservationDto;
import com.isa.reservation.model.CarReservation;
import com.isa.reservation.service.CarReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.Set;

@RestController
@RequestMapping(value="/api/carReservations")
public class CarReservationController {

    @Autowired
    CarReservationService carReservationService;

    @PostMapping(value = "/available")
    public ResponseEntity<Set<CarDto>> getAvailableCars( @RequestBody AvailableCarsDto availableCarsDto){
        Set<CarDto> cars = this.carReservationService.findAvailableCars(availableCarsDto);
        if(cars == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CarReservationDto> reserveCar(@RequestBody CreateCarReservationDto createCarReservationDto){
       CarReservation carReservation = carReservationService.createReservation(createCarReservationDto);
       if(carReservation == null){
           return ResponseEntity.notFound().build();
       }
       CarReservationDto carReservationDto = new CarReservationDto(carReservation);
       return ResponseEntity.ok(carReservationDto);

    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity deleteReservation(@PathVariable Long id){
        CarReservation carReservation = carReservationService.deleteReservation(id);
        if(carReservation == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().build();
        }
    }



}
