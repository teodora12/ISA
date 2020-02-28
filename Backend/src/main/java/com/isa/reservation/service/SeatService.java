package com.isa.reservation.service;

import com.isa.reservation.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {

    List<Seat> getAllSeats();
    Seat getSeatById(Long id);
    Seat addSeat(Seat seat);
    void deleteSeat(Seat seat);
    Seat updateSeat(Seat seat);
    // Seat saveSeat(Seat seat);

}
