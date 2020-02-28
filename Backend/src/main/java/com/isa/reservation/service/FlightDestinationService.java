package com.isa.reservation.service;

import com.isa.reservation.model.Flight;
import com.isa.reservation.model.FlightDestination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightDestinationService {

    List<FlightDestination> getAllFlightDestinations();
    FlightDestination getFlightDestinationById(Long id);
    FlightDestination addFlightDestination(FlightDestination flightDestnation);
    void deleteFlightDestnation(FlightDestination flightDestnation);
    FlightDestination updateFlightDestination(FlightDestination flightDestnation);
    void deleteAndAddNewFlightDestinationsByFlight(Long flightId, List<FlightDestination> flightDestinations);

}
