package com.isa.reservation.repository;

import com.isa.reservation.model.Affiliate;
import com.isa.reservation.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {
    Affiliate findAffiliateById(Long id);
}
