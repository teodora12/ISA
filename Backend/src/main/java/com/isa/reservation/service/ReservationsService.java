package com.isa.reservation.service;

import com.isa.reservation.dto.CreateReservationDto;
import com.isa.reservation.dto.ReservationDto;
import com.isa.reservation.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationsService {

    List<Reservation> getReservationsByUser(String username);
    Reservation createReservation(CreateReservationDto reservation);
    List<Reservation> findAll();
    void cancelReservation(Long id);
    List<ReservationDto> getAllByAirlineId(Long id);
    void sendReservationEmail(Long id) throws InterruptedException;
}
