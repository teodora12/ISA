package com.isa.reservation.controller;

import com.isa.reservation.dto.DestinationDto;
import com.isa.reservation.model.Destination;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/destinations")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<DestinationDto>> getDestinations(){

        List<Destination> destinations = destinationService.getAllDestinations();
        List<DestinationDto> destinationDtos = new ArrayList<>();
        for (Destination destination : destinations)
        {
            destinationDtos.add(new DestinationDto(destination));
        }
        if (destinations == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(destinationDtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DestinationDto> addDestination(@RequestBody DestinationDto destinationDto){

        Destination destination = new Destination(destinationDto);
        destination.setAddress(addressService.addAddress(destinationDto.getAddress()));
        //      FLIGHTS JE NA POCETKU PRAZNO, PA SE NE DODAJE
        return new ResponseEntity(destinationService.addDestination(destination), HttpStatus.OK);
    }

}
