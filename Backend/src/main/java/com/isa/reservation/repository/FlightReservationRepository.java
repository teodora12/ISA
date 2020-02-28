package com.isa.reservation.repository;

import com.isa.reservation.model.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

    FlightReservation findFlightReservationById(Long id);
}
