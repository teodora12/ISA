package com.isa.reservation.service.impl;

import com.isa.reservation.model.Rating;
import com.isa.reservation.repository.RatingRepository;
import com.isa.reservation.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    public Rating findRatingByUserIdAndCarServiceId(Long userId, Long carServiceId){
        Rating rating = ratingRepository.findRatingByUserIdAndCarServiceId(userId, carServiceId);
        if(rating == null){
            return null;
        }
        else{
            return rating;
        }
    }

   public List<Rating> findRatingsByUserId(Long userId){
       List<Rating> ratings = ratingRepository.findRatingsByUserId(userId);
       if(ratings == null){
           return null;
       }
       else{
           return ratings;
       }
   }
 @Override
    public Rating findRatingByUserIdAndCarId(Long userId, Long carId){
        Rating rating = ratingRepository.findRatingByUserIdAndCarId(userId, carId);
        if(rating == null){
            return null;
        }
        else{
            return rating;
        }
    }

    @Override
    public Rating findRatingByUserIdAndAirlineId(Long userId, Long airlineId){

        Rating rating = ratingRepository. findRatingByUserIdAndAirlineId(userId, airlineId);
        return rating;
    }

    @Override
    public Rating saveRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }


    @Override
    public Rating findByUserIdAndFligftId(Long userId, Long flightId){
        Rating rating = ratingRepository.findRatingByUserIdAndFlightId(userId,flightId);
        return rating;
    }

}

