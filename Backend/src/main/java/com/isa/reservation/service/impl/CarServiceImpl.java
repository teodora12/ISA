package com.isa.reservation.service.impl;

import com.isa.reservation.dto.AvailableCarsDto;
import com.isa.reservation.dto.CarDto;
import com.isa.reservation.dto.CreateAndUpdateCarDto;
import com.isa.reservation.dto.Rating.RatingCarDto;
import com.isa.reservation.model.Car;
import com.isa.reservation.model.Rating;
import com.isa.reservation.model.User;
import com.isa.reservation.repository.CarRepository;
import com.isa.reservation.repository.RatingRepository;
import com.isa.reservation.service.CarService;
import com.isa.reservation.service.RatingService;
import com.isa.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Car> getAllCars() {

        return this.carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return this.carRepository.findCarById(id);
    }

    @Override
    public Car addCar(CreateAndUpdateCarDto carDto) {
        Car car = new Car(carDto);
        return this.carRepository.save(car);
    }


    @Override
    public Car updateCar(CreateAndUpdateCarDto carDto){

        Car car =  this.carRepository.findCarById(carDto.getId());
        car.setName(carDto.getName());
        car.setModel(carDto.getModel());
        car.setMake(carDto.getMake());
        car.setNumberOfSeats(carDto.getNumberOfSeats());
        car.setType(carDto.getType());
        car.setPrice(carDto.getPrice());
        car.setYearOfManufacture(carDto.getYearOfManufacture());
        this.carRepository.save(car);
        return car;
    }


    @Override
    public Car deleteCar(Long id){
        Car car = this.getCarById(id);
        if(car == null || car.isInUse() == true){
            return null;
        }
        preRemove(car);
        this.carRepository.delete(car);
        return car;
    }


    public void preRemove(Car car){
        car.setCarService(null);
        carRepository.save(car);
    }

    @Override
    public Set<CarDto> findAvailableCars(AvailableCarsDto availableCarsDto) {
        if(availableCarsDto.getMaxPrice() == 0){
            availableCarsDto.setMaxPrice(1000000);
        }
        Set<Car> cars = carRepository.findCarsByCarServiceIdAndNumberOfSeatsIsGreaterThanEqualAndTypeEqualsAndPriceIsGreaterThanEqualAndPriceIsLessThanEqual
                (availableCarsDto.getCarServiceId(), availableCarsDto.getNumberOfSeats(), availableCarsDto.getType(), availableCarsDto.getMinPrice(), availableCarsDto.getMaxPrice());

        Set<CarDto> carDtos = new HashSet<>();
        for(Car car :cars) {
            carDtos.add(new CarDto(car));
        }

        return carDtos;
    }

    @Override
    public Set<String> findCarTypes(){
        Set<String> types = new HashSet<String>();

        List<Car> cars = getAllCars();
        for (Car c: cars) {
            types.add(c.getType());
        }
        return  types;
    }

    @Override
    public  Set<Integer> findNumberOfSeats(){
        Set<Integer> numberOfSeats = new HashSet<Integer>();

        List<Car> cars = getAllCars();

        for (Car c: cars) {
            numberOfSeats.add(c.getNumberOfSeats());
        }
        return  numberOfSeats;
    }

    @Override
    public Double ratingCar(RatingCarDto ratingCarDto) {
        Car car = carRepository.findCarById(ratingCarDto.getCarId());
        if(car == null){
            return null;
        }
        User user = userService.findByUsername(ratingCarDto.getUsername());
        Rating rating = ratingService.findRatingByUserIdAndCarId(user.getId(),ratingCarDto.getCarId());

        if(rating == null) {
            Rating rating1 = new Rating();
            rating1.setUserId(user.getId());
            rating1.setCarId(ratingCarDto.getCarId());
            rating1.setCarRating(ratingCarDto.getRating());


            car.setSumRating(car.getSumRating() + ratingCarDto.getRating());
            car.setNumberOfRating(car.getNumberOfRating() + 1);
            double average = car.getSumRating() / car.getNumberOfRating();

            int scale = (int) Math.pow(10, 1);
            double rez = (double) Math.round(average * scale) / scale;
            car.setAverageRating(rez);
            this.carRepository.save(car);
            this.ratingRepository.save(rating1);

            return car.getAverageRating();

        } else{
            if(rating.getCarRating() == 0){
                rating.setCarId(ratingCarDto.getCarId());
                rating.setCarRating(ratingCarDto.getRating());


                car.setSumRating(car.getSumRating() + ratingCarDto.getRating());
                car.setNumberOfRating(car.getNumberOfRating() + 1);
                double average = car.getSumRating() / car.getNumberOfRating();

                int scale = (int) Math.pow(10, 1);
                double rez = (double) Math.round(average * scale) / scale;
                car.setAverageRating(rez);
                this.carRepository.save(car);
                this.ratingRepository.save(rating);
                return car.getAverageRating();

            }else{
                return null;
            }
        }
    }


//    @Override
//    public Set<CarDto> findCarsOnDiscount() {
//        Set<Car> cars = carRepository.findCarsByDiscountTrue();
//
//        Set<CarDto> carDtos = new HashSet<>();
//        for(Car car :cars) {
//            carDtos.add(new CarDto(car));
//        }
//
//        return carDtos;
//    }
}
