package com.isa.reservation.controller;


import com.isa.reservation.dto.CreateReservationDto;
import com.isa.reservation.dto.ReservationDto;
import com.isa.reservation.model.PassengerOnFlightSeat;
import com.isa.reservation.model.Reservation;
import com.isa.reservation.service.FlightReservationService;
import com.isa.reservation.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationsService reservationsService;

    @Autowired
    private FlightReservationService flightReservationService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId(@PathVariable String username) {
        List<Reservation> reservations = reservationsService.getReservationsByUser(username);
        if(reservations == null){
            return null;
        }

        List<ReservationDto> reservationDtos = new ArrayList<>();
        if (reservationDtos == null) {
            return ResponseEntity.notFound().build();
        }

        for (Reservation reservation : reservations) {
            reservationDtos.add(new ReservationDto(reservation));
        }
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CreateReservationDto> reserve(@RequestBody CreateReservationDto reservationDto) {
        Reservation reservation = reservationsService.createReservation(reservationDto);

        reservationDto = new CreateReservationDto(reservation);
        return ResponseEntity.ok(reservationDto);

    }

    @GetMapping(value = "/invite/{id}")
    public ResponseEntity<List<PassengerOnFlightSeat>> getPassengerSeatReservation(@PathVariable Long id) {

        PassengerOnFlightSeat pass = this.flightReservationService.getPassengerSeatById(id);
        if (pass == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(pass, HttpStatus.OK);
    }

    @DeleteMapping(value = "/invite/{id}")
    public ResponseEntity deleteInvitation(@PathVariable Long id) {
        this.flightReservationService.deleteInvite(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelReservation(@PathVariable Long id) {

        try {
            this.reservationsService.cancelReservation(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(value = "/airline/{id}")
    public ResponseEntity<List<ReservationDto>> getReservationsByAirline(@PathVariable Long id){
        try {
            List<ReservationDto> reservations = this.reservationsService.getAllByAirlineId(id);
            return new ResponseEntity(reservations, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/email/{id}")
    public void sendReservationInfoMailAfterCreating(@PathVariable Long id) {
        try {
            this.reservationsService.sendReservationEmail(id);
        } catch (Exception e) {}
    }
}
