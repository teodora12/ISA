package com.isa.reservation.repository;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressById(Long id);
}
