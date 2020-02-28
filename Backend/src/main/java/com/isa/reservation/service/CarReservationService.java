package com.isa.reservation.service;

import com.isa.reservation.dto.AvailableCarsDto;
import com.isa.reservation.dto.CarDto;
import com.isa.reservation.dto.CreateCarReservationDto;
import com.isa.reservation.model.CarReservation;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CarReservationService {

    Set<CarDto> findAvailableCars(AvailableCarsDto availableCarsDto);
    CarReservation createReservation(CreateCarReservationDto createCarReservationDto);
    CarReservation deleteReservation(Long id);
}
