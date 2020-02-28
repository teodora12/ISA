package com.isa.reservation.service.impl;

import com.isa.reservation.dto.AvailableCarsDto;
import com.isa.reservation.dto.CarDto;
import com.isa.reservation.dto.CreateCarReservationDto;
import com.isa.reservation.model.Address;
import com.isa.reservation.model.Car;
import com.isa.reservation.model.CarReservation;
import com.isa.reservation.model.Reservation;
import com.isa.reservation.repository.CarRepository;
import com.isa.reservation.repository.ReservationRepository;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.CarService;
import com.isa.reservation.repository.CarReservationRepository;
import com.isa.reservation.service.CarReservationService;
import com.isa.reservation.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CarReservationServiceImpl implements CarReservationService {

    @Autowired
    CarReservationRepository carReservationRepository;

    @Autowired
    CarService carService;

    @Autowired
    AddressService addressService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ReservationsService reservationsService;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Set<CarDto> findAvailableCars(AvailableCarsDto availableCarsDto) {

        Set<CarDto> availableCars = carService.findAvailableCars(availableCarsDto);

//        String pickUpS = availableCarsDto.getPickUpDate();
//        String dropOffS = availableCarsDto.getDropOffDate();
//        Date pickUp = null;
//        Date dropOff = null;

//         try{
//             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//             pickUp  =  dateFormat.parse(pickUpS);
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }
//
//        try{
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            dropOff  =  dateFormat.parse(dropOffS);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }c

        Set<CarDto> forDelete = new HashSet<>();

        List<CarReservation> carReservationsFirst = carReservationRepository.findCarReservationsByPickUpDateGreaterThanEqual(availableCarsDto.getPickUpDate());
        List<CarReservation> carReservationsSecond = carReservationRepository.findCarReservationsByPickUpDateGreaterThanAndDropOffDateLessThanEqual(availableCarsDto.getPickUpDate(), availableCarsDto.getPickUpDate());
        List<CarReservation> carReservations = Stream.concat(carReservationsFirst.stream(), carReservationsSecond.stream()).collect(Collectors.toList());
        for (CarReservation carReservation : carReservations) {
            if (!(carReservation.getPickUpDate().after(availableCarsDto.getDropOffDate()))) {
                for (CarDto car : availableCars) {
                    if (carReservation.getCar().getId() == car.getId()) {
                        forDelete.add(car);
                    }
                }
            }
        }

        availableCars.removeAll(forDelete);
        return availableCars;
    }

    @Override
    @Transactional
    public CarReservation createReservation(CreateCarReservationDto createCarReservationDto) {

        Long idCar = createCarReservationDto.getCarId();
        Car car = carService.getCarById(idCar);
        car.setInUse(true);

        Long pickUpAddrId = createCarReservationDto.getPickUpAddressId();
        Long dropOffAddrId = createCarReservationDto.getDropOffAddressId();

        Address pickUpAddress = addressService.getAddressById(pickUpAddrId);
        Address dropOffAddress = addressService.getAddressById(dropOffAddrId);


        long diff = createCarReservationDto.getDropOffDate().getTime() - createCarReservationDto.getPickUpDate().getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        long total = car.getPrice() * days;
        CarReservation carReservation = new CarReservation();
        carReservation.setCar(car);
        carReservation.setTotalPrice(total);
        carReservation.setPickUpAddress(pickUpAddress);
        carReservation.setDropOffAddress(dropOffAddress);
        carReservation.setPickUpDate(createCarReservationDto.getPickUpDate());
        carReservation.setDropOffDate(createCarReservationDto.getDropOffDate());
        return this.carReservationRepository.save(carReservation);
    }

    public CarReservation deleteReservation(Long id) {
        CarReservation carReservation = this.carReservationRepository.findCarReservationById(id);
        if(carReservation == null){
            return null;
        }
        Car car = carReservation.getCar();
        car.setCarService(null);
        carRepository.save(car);
        carReservation.setCar(null);
        carReservationRepository.save(carReservation);
         List<Reservation> reservations = reservationsService.findAll();
        for(Reservation r: reservations){
            if(r.getCarReservation() == carReservation){
                r.setCarReservation(null);
                reservationRepository.save(r);
            }
        }


        this.carReservationRepository.delete(carReservation);
       return carReservation;
    }

}

