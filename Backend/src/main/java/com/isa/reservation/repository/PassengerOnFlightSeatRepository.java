package com.isa.reservation.repository;

import com.isa.reservation.model.PassengerOnFlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerOnFlightSeatRepository extends JpaRepository<PassengerOnFlightSeat, Long> {

    PassengerOnFlightSeat findPassengerOnFlightSeatById(Long id);
}
