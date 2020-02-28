package com.isa.reservation.controller;

import com.isa.reservation.dto.FlightDestinationDto;
import com.isa.reservation.model.Flight;
import com.isa.reservation.model.FlightDestination;
import com.isa.reservation.service.FlightDestinationService;
import com.isa.reservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/flightDestinations")
public class FlightDestinationController {

    @Autowired
    FlightDestinationService flightDestinationService;
    @Autowired
    FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDestination>> getFlightDestinations(){
        List<FlightDestination> flightDestinations = this.flightDestinationService.getAllFlightDestinations();

        List<FlightDestinationDto> flightDestinationDtos = new ArrayList<>();
        for(FlightDestination flightDestination :flightDestinations) {
            FlightDestinationDto flightDestinationDto = new FlightDestinationDto(flightDestination);
            flightDestinationDtos.add(flightDestinationDto);

        }
        if(flightDestinations == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(flightDestinationDtos, HttpStatus.OK);
    }

 /*   @GetMapping(value = "/{id}")
    public ResponseEntity<List<FlightDestinationDto>> getFlightDestinationsByFlightId(@PathVariable Long id) {
        Flight f = this.flightService.getFlightById(id);
        List<FlightDestination> flightDestinations = flightDestinationService.findFlightDestinationsByFlight(f);
        List<FlightDestinationDto> flightDestinationDtos = new ArrayList<>();
        for(FlightDestination f : flightDestinations){
            flightDestinationDtos.add(new FlightDestinationDto(f));      //u airlineDto, lista dto-ova da ne bi bilo duplih ref
        }
        if(flightDestinations == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(flightDestinationDtos, HttpStatus.OK);
    }
*/
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDestinationDto> addFlightDestination(@RequestBody FlightDestinationDto flightDestinationDto){

        FlightDestination flightDestination = new FlightDestination(flightDestinationDto);
        flightDestination.setFlight(this.flightService.getFlightById(flightDestinationDto.getFlight().getId()));
        flightDestination = this.flightDestinationService.addFlightDestination(flightDestination);

        return ResponseEntity.ok(new FlightDestinationDto(flightDestination));

    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FlightDestinationDto> updateAirline(@RequestBody FlightDestinationDto flightDestinationDto) {

        FlightDestination flightDestination = this.flightDestinationService.getFlightDestinationById(flightDestinationDto.getId());
        if (flightDestination == null){
            return ResponseEntity.notFound().build();
        }
        flightDestination.setFlight(flightService.getFlightById(flightDestinationDto.getFlight().getId()));

        flightDestination = this.flightDestinationService.updateFlightDestination(new FlightDestination(flightDestinationDto));

        return ResponseEntity.ok(new FlightDestinationDto(flightDestination));
    }
}
