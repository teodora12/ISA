package com.isa.reservation.controller;

import com.isa.reservation.dto.AirlineDto;
import com.isa.reservation.dto.CreateAirlineDto;
import com.isa.reservation.dto.FlightShowInfoDto;
import com.isa.reservation.dto.DestinationDto;
import com.isa.reservation.dto.Rating.RatingAirlineDto;
import com.isa.reservation.model.Airline;
import com.isa.reservation.model.Destination;
import com.isa.reservation.model.Flight;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.AirlineService;
import com.isa.reservation.service.DestinationService;
import com.isa.reservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService; // interface se uvek autowired
    @Autowired
    private AddressService addressService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public ResponseEntity<List<AirlineDto>> getAirlines(){
        List<Airline> airlines = this.airlineService.getAllAirlines();
        if(airlines == null){
            return ResponseEntity.notFound().build();
        }
        List<AirlineDto> airlineDtos = new ArrayList<>();
        for(Airline airline :airlines) {
            AirlineDto airlineDto = new AirlineDto(airline);
            airlineDtos.add(airlineDto);
            for(Flight f : airline.getFlights()){
                airlineDto.getFlights().add(new FlightShowInfoDto(f));      //u airlineDto, lista dto-ova da ne bi bilo duplih ref
            }
            for (Destination destination : airline.getAvailableDestinations()) {
                airlineDto.getAvailableDestinations().add(new DestinationDto(destination));
            }
        }


        return new ResponseEntity<>(airlineDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AirlineDto> getAirlineById(@PathVariable Long id) {
        Airline airline = airlineService.getAirlineById(id);
        if(airline == null) {
            return ResponseEntity.notFound().build();
        }
        AirlineDto airlineDto = new AirlineDto(airline);

        return new ResponseEntity<>(airlineDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAirlineDto> addAirline(@RequestBody CreateAirlineDto airlineDTO){
        airlineDTO.setAddress(this.addressService.addAddress(airlineDTO.getAddress())); //unosi novu adresu u tabelu adresa
        Airline airline = this.airlineService.addAirline(new Airline(airlineDTO));
        return ResponseEntity.ok(new CreateAirlineDto(airline));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN_AIRLINE')")
    public ResponseEntity<AirlineDto> updateAirline(@RequestBody AirlineDto airlineDTO) {
        Airline airline = this.airlineService.getAirlineById(airlineDTO.getId());
        if(airline == null){
            return ResponseEntity.notFound().build();
        }

//        airline = new Airline(airlineDTO);
//        Set<Flight> flights = new HashSet<>();
//        Set<Destination> availableDestinations = new HashSet<>();
//
//        for(FlightShowInfoDto f : airlineDTO.getFlights()){
//            flights.add(this.flightService.getFlightById(f.getId()));
//        }
//        for (DestinationDto d : airlineDTO.getAvailableDestinations()) {
//            availableDestinations.add(this.destinationService.getDestinationById(d.getId()));
//        }
//
//
//        airline.setAvailableDestinations(availableDestinations);
//        airline.setFlights(flights);
        airline = this.airlineService.updateAirline(airlineDTO);
        return ResponseEntity.ok(new AirlineDto(airline));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_AIRLINE')")
    public ResponseEntity deleteAirline(@PathVariable Long id){
        Airline airline = this.airlineService.getAirlineById(id);
        if(airline == null) {
            return ResponseEntity.notFound().build();
        }
        boolean success = this.airlineService.deleteAirline(airline);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping(value = "/rating")
    //   @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Double> ratingAirline(@RequestBody RatingAirlineDto ratingAirlineDto) {
        Double rating = this.airlineService.ratingAirline(ratingAirlineDto);
        if(rating == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rating);
    }


}
