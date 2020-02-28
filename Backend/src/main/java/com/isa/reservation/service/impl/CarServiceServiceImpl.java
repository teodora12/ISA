package com.isa.reservation.service.impl;

import com.isa.reservation.dto.CreateAndUpdateCarServiceDto;
import com.isa.reservation.dto.Rating.RatingCarServiceDto;
import com.isa.reservation.dto.SearchSearchServiceDto;
import com.isa.reservation.model.*;
import com.isa.reservation.repository.AffiliateRepository;
import com.isa.reservation.repository.CarRepository;
import com.isa.reservation.repository.CarServiceRepository;
import com.isa.reservation.repository.RatingRepository;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.CarServiceService;
import com.isa.reservation.service.RatingService;
import com.isa.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.*;

@Service
public class CarServiceServiceImpl implements CarServiceService {
    @Autowired
    private CarServiceRepository carServiceRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AffiliateRepository affiliateRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<CarService> getAllCarServices() {

        return this.carServiceRepository.findAll();
    }

    @Override
    public CarService getCarServiceById(Long id) {
        return this.carServiceRepository.findCarServiceById(id);
    }

    @Override
    public CarService addCarService(CreateAndUpdateCarServiceDto carServiceDto) {
        carServiceDto.setAddress(this.addressService.addAddress(carServiceDto.getAddress()));
        CarService carService = new CarService(carServiceDto);
        return this.carServiceRepository.save(carService);
    }


    @Override
    public CarService updateCarService(CreateAndUpdateCarServiceDto carServiceDto){

        CarService carService =  this.carServiceRepository.findCarServiceById(carServiceDto.getId());
        carService.setName(carServiceDto.getName());
        carService.setAddress(carServiceDto.getAddress());
        carService.setDescription(carServiceDto.getDescription());
        this.carServiceRepository.save(carService);
        return carService;
    }


    @Override
    public CarService deleteCarService(Long id){
       List<Boolean> inUse = new ArrayList<>();
       Boolean canDelete = true;
        CarService carService = this.getCarServiceById(id);
        if(carService == null){
            return null;
        }
            Set<Car> cars = carService.getCars();

            for(Car car : cars){
                if(car.isInUse() == true){
                    inUse.add(true);
                }
                else{
                    inUse.add(false);
                }
            }
            for(Boolean b : inUse){
                if(b == true){
                    System.out.println("u upotrebi je");
                    canDelete = false;
                }
            }

            if(canDelete == false) {
                return null;
            }else{
                this.carServiceRepository.delete(carService);
                return carService;
            }
    }

     public Car addCarToService(Long idService, Long idCar){
       CarService carService =  this.carServiceRepository.findCarServiceById(idService);

       Car car = this.carRepository.findCarById(idCar);
       car.setCarService(carService);
       carService.getCars().add(car);
       this.carServiceRepository.save(carService);
       this.carRepository.save(car);
       return car;
    }

    @Override
    public Affiliate addAffiliateToService(Long idService, Long idAffiliate){
        CarService carService =  this.carServiceRepository.findCarServiceById(idService);

        Affiliate affiliate = this.affiliateRepository.findAffiliateById(idAffiliate);
        affiliate.setCarService(carService);
        carService.getAffiliates().add(affiliate);
        this.carServiceRepository.save(carService);
        this.affiliateRepository.save(affiliate);
        return affiliate;
    }

     @Override
     public Double ratingCarService(RatingCarServiceDto ratingCarServiceDto) {
         CarService carService = carServiceRepository.findCarServiceById(ratingCarServiceDto.getCarServiceId());
         if (carService == null) {
             return null;
         }
         User user = userService.findByUsername(ratingCarServiceDto.getUsername());
          Rating rating = ratingService.findRatingByUserIdAndCarServiceId(user.getId(),ratingCarServiceDto.getCarServiceId());
         if (rating == null) {
             Rating rating1 = new Rating();
             rating1.setUserId(user.getId());
             rating1.setCarServiceId(ratingCarServiceDto.getCarServiceId());
             rating1.setCarServiceRating(ratingCarServiceDto.getRating());


             carService.setSumRating(carService.getSumRating() + ratingCarServiceDto.getRating());
             carService.setNumberOfRating(carService.getNumberOfRating() + 1);
             double average = carService.getSumRating() / carService.getNumberOfRating();

             int scale = (int) Math.pow(10, 1);
             double rez = (double) Math.round(average * scale) / scale;
             carService.setAverageRating(rez);
             this.carServiceRepository.save(carService);
             this.ratingRepository.save(rating1);
             return carService.getAverageRating();

         }

         else{
             if (rating.getCarServiceRating() == 0) {
                 rating.setCarServiceId(ratingCarServiceDto.getCarServiceId());
                 rating.setCarServiceRating(ratingCarServiceDto.getRating());

                 carService.setSumRating(carService.getSumRating() + ratingCarServiceDto.getRating());
                 carService.setNumberOfRating(carService.getNumberOfRating() + 1);
                 double average = carService.getSumRating() / carService.getNumberOfRating();

                 int scale = (int) Math.pow(10, 1);
                 double rez = (double) Math.round(average * scale) / scale;
                 carService.setAverageRating(rez);
                 this.carServiceRepository.save(carService);
                 this.ratingRepository.save(rating);
                 return carService.getAverageRating();

         }
         else{
             return null;
             }
         }

     }



     @Override
     public List<CarService> carServicesSortByName(){
        List<CarService> carServices = this.carServiceRepository.findAll();

         Comparator<CarService> carServiceComparator = Comparator.comparing(CarService::getName);
//         Comparator<CarService> carServiceComparator = Comparator.comparing(CarService::getName).reversed();
         Collections.sort(carServices, carServiceComparator);
        return carServices;
     }

    @Override
    public List<CarService> carServicesSortByCity(){
//        List<CarService> carServices = this.carServiceRepository.findAll();
//        List<SortCarServicesByCity> sortCarServicesByCities = new ArrayList<>();
//
//        for (CarService carService: carServices){
//            SortCarServicesByCity sortCarServicesByCity = new SortCarServicesByCity();
//            sortCarServicesByCity.setCarService(carService);
//            sortCarServicesByCity.setCityName(carService.getAddress().getCity());
//            sortCarServicesByCities.add(sortCarServicesByCity);
//        }
//
//        Comparator<SortCarServicesByCity> carServiceComparator = Comparator.comparing(SortCarServicesByCity::getCityName);
//        Collections.sort(sortCarServicesByCities, carServiceComparator);
////        Comparator<SortCarServicesByCity> carServiceComparator = Comparator.comparing(SortCarServicesByCity::getCityName).reversed();
//
//        List<CarService> response = new ArrayList<>();
//        for(SortCarServicesByCity c: sortCarServicesByCities){
//            CarService carService;
//            carService = c.getCarService();
//            response.add(carService);
//        }
//
//        return response;
        return null;
    }

    @Override
    public List<CarService> search(SearchSearchServiceDto searchSearchServiceDto) {


        List<CarService> carServices = new ArrayList<>();
        carServices = this.carServiceRepository.findCarServicesByNameLikeOrNameContains(searchSearchServiceDto.getServiceName(), searchSearchServiceDto.getServiceName());


        return carServices;
    }

}
