package com.isa.reservation.repository;

import com.isa.reservation.model.Reservation;
import com.isa.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsByUser(User user);
    List<Reservation> findAll();
    Reservation findReservationById(Long id);
}
