package com.isa.reservation.repository;

import com.isa.reservation.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findRatingsByUserId(Long userId);
    Rating findRatingByUserIdAndCarServiceId(Long userId, Long carServiceId);
    Rating findRatingByUserIdAndCarId(Long userId, Long carId);
    Rating findRatingByUserIdAndAirlineId(Long userId, Long airlineId);
    Rating findRatingByUserIdAndFlightId(Long userId, Long flightId);
}
