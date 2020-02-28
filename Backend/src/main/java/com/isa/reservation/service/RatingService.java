package com.isa.reservation.service;


import com.isa.reservation.model.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {


    Rating findRatingByUserIdAndCarServiceId(Long userId, Long carServiceId);
    List<Rating> findRatingsByUserId(Long userId);
    Rating findRatingByUserIdAndCarId(Long userId, Long carId);
    Rating findRatingByUserIdAndAirlineId(Long userId, Long airlineId);
    Rating saveRating(Rating rating);
    Rating findByUserIdAndFligftId(Long userId, Long flightId);

}
