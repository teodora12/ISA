package com.isa.reservation.repository;

import com.isa.reservation.model.AirplaneSeatArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneSeatArrangementRepository extends JpaRepository<AirplaneSeatArrangement, Long> {

    AirplaneSeatArrangement findAirplaneSeatArrangementById(Long id);
    AirplaneSeatArrangement findAirplaneSeatArrangementByName(String name);
}
