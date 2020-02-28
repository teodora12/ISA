package com.isa.reservation.repository;

import com.isa.reservation.model.Car;
import com.isa.reservation.model.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarById(Long id);
    List<Car> findCarsByCarServiceIdAndNumberOfSeatsIsGreaterThanEqualAndTypeEquals(Long carServiceId, int numberOfSeats, String type);
    Set<Car> findCarsByCarServiceIdAndNumberOfSeatsIsGreaterThanEqualAndTypeEqualsAndPriceIsGreaterThanEqualAndPriceIsLessThanEqual(Long carServiceId, int numberOfSeats, String type, int minPrice, int maxPrice);
 //   Set<Car> findCarsByDiscountTrue();
}
