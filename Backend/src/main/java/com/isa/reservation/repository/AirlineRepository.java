package com.isa.reservation.repository;

import com.isa.reservation.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    //Ukoliko je potrebna neka metoda za pristup bazi a ne nalazi se u Jpa, ovde se pise.

    Airline findAirlineByName(String name);
    Airline findAirlineById(Long id);
}
