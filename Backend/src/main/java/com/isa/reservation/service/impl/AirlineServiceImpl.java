package com.isa.reservation.service.impl;

import com.isa.reservation.dto.AirlineDto;
import com.isa.reservation.dto.DestinationDto;
import com.isa.reservation.dto.FlightShowInfoDto;
import com.isa.reservation.dto.Rating.RatingAirlineDto;
import com.isa.reservation.model.*;
import com.isa.reservation.repository.AirlineRepository;
import com.isa.reservation.repository.RatingRepository;
import com.isa.reservation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private DestinationService destinationService;


    @Override
    public List<Airline> getAllAirlines() {
        return this.airlineRepository.findAll();
    }

    @Override
    public Airline getAirlineById(Long id) { return this.airlineRepository.findAirlineById(id); }
    @Override
    public Airline addAirline(Airline airline) { return this.airlineRepository.save(airline); }

    @Override
    public boolean deleteAirline(Airline airline) {

        try {
            this.airlineRepository.delete(airline);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Airline updateAirline(AirlineDto airlineDTO) {


        Airline airline = this.airlineRepository.findAirlineById(airlineDTO.getId());

        airline = new Airline(airlineDTO);
        Set<Flight> flights = new HashSet<>();
        Set<Destination> availableDestinations = new HashSet<>();

        for(FlightShowInfoDto f : airlineDTO.getFlights()){
            flights.add(this.flightService.getFlightById(f.getId()));
        }
        for (DestinationDto d : airlineDTO.getAvailableDestinations()) {
            availableDestinations.add(this.destinationService.getDestinationById(d.getId()));
        }

        airline.setAvailableDestinations(availableDestinations);
        airline.setFlights(flights);

        return this.airlineRepository.save(airline);
    }

    @Override
    public Airline saveAirline(Airline airline) {
        return this.airlineRepository.save(airline);
    }

    @Override
    public Double ratingAirline(RatingAirlineDto ratingAirlineDto) {
        Airline airline = airlineRepository.findAirlineById(ratingAirlineDto.getAirlineId());
        if(airline == null){
            return null;
        }

        User user = userService.findByUsername(ratingAirlineDto.getUsername());
        Rating rating = ratingService.findRatingByUserIdAndAirlineId(user.getId(), ratingAirlineDto.getAirlineId());

        if(rating == null){
            Rating rating1 = new Rating();
            rating1.setUserId(user.getId());
            rating1.setAirlineId(ratingAirlineDto.getAirlineId());
            rating1.setAirlaneRating(ratingAirlineDto.getRating());

            airline.setSumRating(airline.getSumRating() + ratingAirlineDto.getRating());
            airline.setNumberOfRating(airline.getNumberOfRating() + 1);
            double average = airline.getSumRating() / airline.getNumberOfRating();

            int scale = (int) Math.pow(10, 1);
            double rez = (double) Math.round(average * scale) / scale;
            airline.setAverageRating(rez);
            this.airlineRepository.save(airline);
            this.ratingService.saveRating(rating1);

            return airline.getAverageRating();
        } else{
            if(rating.getAirlaneRating() == 0) {
                rating.setAirlineId(ratingAirlineDto.getAirlineId());
                rating.setAirlaneRating(ratingAirlineDto.getRating());

                airline.setSumRating(airline.getSumRating() + ratingAirlineDto.getRating());
                airline.setNumberOfRating(airline.getNumberOfRating() + 1);
                double average = airline.getSumRating() / airline.getNumberOfRating();

                int scale = (int) Math.pow(10, 1);
                double rez = (double) Math.round(average * scale) / scale;
                airline.setAverageRating(rez);
                this.airlineRepository.save(airline);
                this.ratingService.saveRating(rating);
                return airline.getAverageRating();

            }else{
                return null;
            }
        }
    }


}
