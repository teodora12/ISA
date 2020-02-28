package com.isa.reservation.service;


import com.isa.reservation.dto.CreateAndUpdateCarServiceDto;
import com.isa.reservation.dto.Rating.RatingCarServiceDto;
import com.isa.reservation.dto.SearchSearchServiceDto;
import com.isa.reservation.model.Affiliate;
import com.isa.reservation.model.Car;
import com.isa.reservation.model.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarServiceService {

    List<CarService> getAllCarServices();
    CarService addCarService(CreateAndUpdateCarServiceDto carServiceDto);
    CarService getCarServiceById(Long id);
    CarService updateCarService(CreateAndUpdateCarServiceDto carServiceDto);
    CarService deleteCarService(Long id);
    Car addCarToService(Long idService, Long idCar);
    Affiliate addAffiliateToService(Long idService, Long idAffiliate);
    Double ratingCarService(RatingCarServiceDto ratingCarServiceDto);
    List<CarService> carServicesSortByName();
     List<CarService> carServicesSortByCity();
     List<com.isa.reservation.model.CarService> search(SearchSearchServiceDto searchSearchServiceDto);
}
