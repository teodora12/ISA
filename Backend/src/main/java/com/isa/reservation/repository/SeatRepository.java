package com.isa.reservation.repository;

import com.isa.reservation.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Seat findSeatById(Long id);
}
