package com.isa.reservation.service.impl;

import com.isa.reservation.model.Seat;
import com.isa.reservation.repository.SeatRepository;
import com.isa.reservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findSeatById(id);
    }

    @Override
    public Seat addSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Seat seat) {
        seatRepository.delete(seat);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Seat updateSeat(Seat seat) {

        return this.seatRepository.save(seat);
    }


}
