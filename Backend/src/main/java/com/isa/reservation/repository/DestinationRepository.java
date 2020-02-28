package com.isa.reservation.repository;

import com.isa.reservation.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findDestinationById(Long id);
}
