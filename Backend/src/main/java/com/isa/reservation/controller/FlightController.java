package com.isa.reservation.controller;

import com.isa.reservation.dto.*;
import com.isa.reservation.dto.Rating.RatingFlightDto;
import com.isa.reservation.model.*;
import com.isa.reservation.service.*;
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
@RequestMapping(value = "api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private FlightDestinationService flightDestinationService;
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getFligths(){
        List<Flight> flights = this.flightService.getAllFlights();
        List<FlightDto> flightDtos = new ArrayList<>();
        if(flights == null){
            return ResponseEntity.notFound().build();
        }

        for (Flight flight : flights){
            flightDtos.add(new FlightDto(flight));
        }
        return new ResponseEntity<>(flightDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id){

        Flight flight = this.flightService.getFlightById(id);
        if(flight == null){
            return ResponseEntity.notFound().build();
        }
        //return ResponseEntity.ok(flight);
        return new ResponseEntity(new FlightDto(flight), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<FlightShowInfoDto> getFlightAllAtributesById(@PathVariable Long id){

        Flight flight = this.flightService.getFlightById(id);
        if(flight == null){
            return ResponseEntity.notFound().build();
        }
        //return ResponseEntity.ok(flight);
        return new ResponseEntity(new FlightShowInfoDto(flight), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN_AIRLINE')")
    public ResponseEntity<CreateFlightDto> addFlight(@RequestBody CreateFlightDto flightDto){

        Airline airline = this.airlineService.getAirlineById(flightDto.getAirlineId());
        Flight flight = new Flight(flightDto);  //pretvara dto u flight i salje ga u bazu

        double temp = flight.getSeatArrangement().getSeatRows() / 4;
        int brRedovaKlase = (int)temp;
        for (int i = 0; i < flight.getSeatArrangement().getSeatRows(); i++){
            for (int j = 0; j < flight.getSeatArrangement().getSeatColumns() ; j++){
                if (i < brRedovaKlase || i > brRedovaKlase * 4) {
                    flight.getSeats().add(this.seatService.addSeat(new Seat(i + 1, j + 1, "free", "ECONOMY", flight.getEconomyPrice(), 0)));
                } else if (i < brRedovaKlase * 2){
                    flight.getSeats().add(this.seatService.addSeat(new Seat(i + 1, j + 1, "free", "PREMIUM_ECONOMY", flight.getPremiumEconomyPrice(), 0)));
                } else if (i < brRedovaKlase * 3){
                    flight.getSeats().add(this.seatService.addSeat(new Seat(i + 1, j + 1, "free", "BUSINESS", flight.getBusinessPrice(), 0)));
                } else{
                    flight.getSeats().add(this.seatService.addSeat(new Seat(i + 1, j + 1, "free", "FIRST", flight.getFirstPrice(), 0)));
                }
            }
        }

        flight.setAirline(airline);
        flight = this.flightService.addFlight(flight);


        Set<FlightDestination> flightDestinations = new HashSet<>();
        int flightChanges = 0;
        for (Long flightDestinationId : flightDto.getDestinations()) {
            Destination destination = this.destinationService.getDestinationById(flightDestinationId);          //pretvori dto u objekat
            if (destination == null) {continue;}
            FlightDestination flightDestination = new FlightDestination();
            flightDestination.setFlight(flight);
            flightDestination.setDestination(destination);
            if (flightDto.getFromDest().equals(flightDestinationId)){
                flightDestination.setDescription("departure");
            } else if (flightDto.getToDest().equals(flightDestinationId)){
                flightDestination.setDescription("arrival");
            } else {
                flightDestination.setDescription("connecting");
                flightChanges++;
            }

            flightDestination = flightDestinationService.addFlightDestination(flightDestination);       //upise ga u bazu
            flight.getDestinations().add(flightDestination);                                            //upise ga u flight sa id-ijem
        }

        flight = this.flightService.getFlightById(flight.getId());      //uzima novi upisan,sada ima destinacije
       // flight.setFlightChanges(flightChanges);                         //postavlja mu flightChanges i update-uje

       // flight = this.flightService.updateFlight(flight);
        return new ResponseEntity(new CreateFlightDto(flight), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public ResponseEntity<Set<FlightShowInfoDto>> search(@RequestBody FlightSearchDto flightSearchDto) {

        Set<FlightShowInfoDto> flightDtos = new HashSet<>();

        flightDtos.addAll(this.flightService.search(flightSearchDto));
        return new ResponseEntity(flightDtos, HttpStatus.OK);

    }

    @PostMapping(value = "/filter")
    public ResponseEntity<List<FlightShowInfoDto>> filter(@RequestBody FlightFilterDto flightFilterDto) {

        List<FlightShowInfoDto> flightDtos = new ArrayList<>();

        flightDtos = this.flightService.filter(flightFilterDto);
        return new ResponseEntity(flightDtos, HttpStatus.OK);

    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN_AIRLINE')")
    public ResponseEntity<CreateFlightDto> updateFlight(@RequestBody CreateFlightDto flightDto){

        Flight flight = this.flightService.getFlightById(flightDto.getId());
        if (flight == null){
            return ResponseEntity.notFound().build();
        }

        flight = this.flightService.updateFlight(flightDto);

        return new ResponseEntity(new CreateFlightDto(flight), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN_AIRLINE')")
    public ResponseEntity deleteFlight(@PathVariable Long id){
        Flight flight = this.flightService.getFlightById(id);

        if(flight == null){
            return ResponseEntity.notFound().build();
        }
        boolean success = this.flightService.deleteFlight(flight);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping(value = "/seats")
    @PreAuthorize("hasRole('ROLE_ADMIN_AIRLINE')")
    public ResponseEntity<CreateFlightDto> updateFlightSeats(@RequestBody FlightShowInfoDto flightDto){

        Flight flight = this.flightService.getFlightById(flightDto.getId());
        if (flight == null){
            return ResponseEntity.notFound().build();
        }

        try {
            flight = this.flightService.updateFlightSeats(flightDto);
            return new ResponseEntity(new CreateFlightDto(flight), HttpStatus.OK);
        }catch (Exception e) {

        }
        return new ResponseEntity(new CreateFlightDto(flight), HttpStatus.FORBIDDEN);
    }

    @PutMapping(value = "/rating")
    //   @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Double> ratingFlight(@RequestBody RatingFlightDto ratingFlightDto) {
        Double rating = this.flightService.ratingFlight(ratingFlightDto);
        if(rating == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rating);
    }


}
