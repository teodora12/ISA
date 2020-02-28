package com.isa.reservation.controller;

import com.isa.reservation.dto.CarServiceDto;
import com.isa.reservation.dto.CreateAndUpdateCarServiceDto;
import com.isa.reservation.dto.Rating.RatingCarServiceDto;
import com.isa.reservation.dto.SearchSearchServiceDto;
import com.isa.reservation.model.Address;
import com.isa.reservation.model.Affiliate;
import com.isa.reservation.model.Car;
import com.isa.reservation.model.CarService;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.CarServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/carServices")
public class CarServiceContoller {

    @Autowired
    private CarServiceService carServiceService;

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/add")
    public List<Address> getAddreses (){
        List<Address> addressLis = this.addressService.getAllAddresses();
        return addressLis;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CarServiceDto> getCarServiceById(@PathVariable Long id) {
        CarService carService = carServiceService.getCarServiceById(id);
        CarServiceDto carServiceDto = new CarServiceDto(carService);
        if(carService == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(carServiceDto, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<CarServiceDto>> getCarServices(){
        List<CarService> carServices = this.carServiceService.getAllCarServices();
        List<CarServiceDto> carServiceDtos = new ArrayList<>();
        for(CarService carService :carServices) {
            carServiceDtos.add(new CarServiceDto(carService));
        }
        if(carServices == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(carServiceDtos, HttpStatus.OK);
    }



    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAndUpdateCarServiceDto> addCarService(@RequestBody CreateAndUpdateCarServiceDto carServiceDto){
        CarService carService = this.carServiceService.addCarService(carServiceDto);
        if(carService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CreateAndUpdateCarServiceDto(carService));
    }


    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity<CreateAndUpdateCarServiceDto> updateCarService(@RequestBody CreateAndUpdateCarServiceDto carServiceDto) {
        CarService carService = this.carServiceService.getCarServiceById(carServiceDto.getId());
        if(carService == null){
            return ResponseEntity.notFound().build();
        }

        carService = this.carServiceService.updateCarService(carServiceDto);
        return ResponseEntity.ok(new CreateAndUpdateCarServiceDto(carService));
    }


    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteCarService(@PathVariable Long id){
        CarService carService = this.carServiceService.deleteCarService(id);
        if(carService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{idService}/car/{idCar}")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity  addCarToService(@PathVariable Long idService,@PathVariable Long idCar) {
        Car car = carServiceService.addCarToService(idService,idCar);
       if (car == null){
           return ResponseEntity.notFound().build();
       }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{idService}/affiliate/{idAffiliate}")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity  affAffiliateToService(@PathVariable Long idService,@PathVariable Long idAffiliate) {
        Affiliate affiliate = carServiceService.addAffiliateToService(idService,idAffiliate);
        if (affiliate == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/rating")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Double> ratingCarService(@RequestBody RatingCarServiceDto ratingCarServiceDto) {
        System.out.println(ratingCarServiceDto.getCarServiceId());
        System.out.println(ratingCarServiceDto.getRating());
        System.out.println(ratingCarServiceDto.getUsername());
        Double rating = this.carServiceService.ratingCarService(ratingCarServiceDto);
        if(rating == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rating);
    }


    @GetMapping(value = "/sort/name")
    public List<CarServiceDto> CarServisesSortedByName (){
       List<CarService> carServices = this.carServiceService.carServicesSortByName();
       List<CarServiceDto> carServiceDtos = new ArrayList<>();
        for(CarService carService :carServices) {
            carServiceDtos.add(new CarServiceDto(carService));
        }
        return carServiceDtos;
    }


    @GetMapping(value = "/sort/city")
    public List<CarServiceDto> CarServisesSortedByCity(){
        List<CarService> carServices = this.carServiceService.carServicesSortByCity();
        List<CarServiceDto> carServiceDtos = new ArrayList<>();
        for(CarService carService :carServices) {
            carServiceDtos.add(new CarServiceDto(carService));
        }
        return carServiceDtos;
    }

    @PutMapping(value = "/search")
    public List<CarServiceDto> search(@RequestBody SearchSearchServiceDto searchSearchServiceDto){
        List<CarService> carServices = this.carServiceService.search(searchSearchServiceDto);
        if(carServices == null){
            return  null;
        }
        List<CarServiceDto> carServiceDtos = new ArrayList<>();
        for(CarService carService :carServices) {
            carServiceDtos.add(new CarServiceDto(carService));
        }
        return carServiceDtos;
    }



}
