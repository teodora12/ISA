package com.isa.reservation.service;

import com.isa.reservation.model.FlightReservation;
import com.isa.reservation.model.PassengerOnFlightSeat;
import org.springframework.stereotype.Service;

@Service
public interface FlightReservationService {

    FlightReservation createFlightReservation(FlightReservation flightReservation);
    PassengerOnFlightSeat getPassengerSeatById(Long id);
    void deleteInvite(Long id);
}
