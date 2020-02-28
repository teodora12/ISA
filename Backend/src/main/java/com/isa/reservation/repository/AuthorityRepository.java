package com.isa.reservation.repository;

import com.isa.reservation.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findAuthorityByName(String name);
}
