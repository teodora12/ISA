package com.isa.reservation.service;

import com.isa.reservation.dto.AirlineDto;
import com.isa.reservation.dto.Rating.RatingAirlineDto;
import com.isa.reservation.model.Airline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlineService {

    List<Airline> getAllAirlines();
    Airline getAirlineById(Long id);
    Airline addAirline(Airline airline);
    boolean deleteAirline(Airline airline);
    Airline updateAirline(AirlineDto airline);
    Airline saveAirline(Airline airline);
    Double ratingAirline(RatingAirlineDto ratingAirlineDto);

}
