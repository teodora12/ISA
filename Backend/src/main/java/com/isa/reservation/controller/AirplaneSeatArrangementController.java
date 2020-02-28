package com.isa.reservation.controller;

import com.isa.reservation.model.AirplaneSeatArrangement;
import com.isa.reservation.service.AirplaneSeatArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/seatArrangement")
public class AirplaneSeatArrangementController {

    @Autowired
    private AirplaneSeatArrangementService airplaneSeatArrangementService;

    @GetMapping
    private ResponseEntity<List<AirplaneSeatArrangement>> getSeatArrangements(){
        List<AirplaneSeatArrangement> airplaneSeatArrangements = this.airplaneSeatArrangementService.getAllAirplaneSeatArrangements();
        if (airplaneSeatArrangements == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(airplaneSeatArrangements);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<AirplaneSeatArrangement> getSeatArrangement(@PathVariable Long id){
        AirplaneSeatArrangement airplaneSeatArrangement = this.airplaneSeatArrangementService.getAirplaneSeatArrangementById(id);
        if (airplaneSeatArrangement == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(airplaneSeatArrangement);
    }
}
