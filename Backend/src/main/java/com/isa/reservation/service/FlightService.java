package com.isa.reservation.service;

import com.isa.reservation.dto.*;
import com.isa.reservation.dto.Rating.RatingFlightDto;
import com.isa.reservation.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {

    List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    Flight addFlight(Flight flight);
    Flight updateFlight(CreateFlightDto flight);
    Flight saveFlight(Flight flight);
    boolean deleteFlight(Flight flight);
    List<FlightShowInfoDto> search(FlightSearchDto flightSearchDto);
    Flight updateFlightSeats(FlightShowInfoDto flight);
    Double ratingFlight(RatingFlightDto ratingFlightDto);
    List<FlightShowInfoDto> filter(FlightFilterDto filter);

}
