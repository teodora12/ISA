package com.isa.reservation.repository;

import com.isa.reservation.model.Flight;
import com.isa.reservation.model.FlightDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDestinationRepository extends JpaRepository<FlightDestination, Long> {

    FlightDestination findFlightDestinationById(Long id);
    List<FlightDestination> findFlightDestinationsByFlight(Flight flight);
}
