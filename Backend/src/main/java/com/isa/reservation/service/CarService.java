package com.isa.reservation.service;

import com.isa.reservation.dto.AvailableCarsDto;
import com.isa.reservation.dto.CarDto;
import com.isa.reservation.dto.CreateAndUpdateCarDto;
import com.isa.reservation.dto.Rating.RatingCarDto;
import com.isa.reservation.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CarService {

    List<Car> getAllCars();
    Car getCarById(Long id);
    Car addCar(CreateAndUpdateCarDto carDto);
    Car updateCar(CreateAndUpdateCarDto carDto);
    Car deleteCar(Long id);
    Set<CarDto> findAvailableCars(AvailableCarsDto availableCarsDto);
    Set<String> findCarTypes();
    Set<Integer> findNumberOfSeats();
    Double ratingCar(RatingCarDto ratingCarDto);
}
