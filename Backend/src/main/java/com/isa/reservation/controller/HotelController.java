package com.isa.reservation.controller;

import com.isa.reservation.dto.CreateAndUpdateHotelDto;
import com.isa.reservation.dto.HotelDto;
import com.isa.reservation.model.Hotel;
import com.isa.reservation.model.Room;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAndUpdateHotelDto> createHotel(@RequestBody CreateAndUpdateHotelDto hotelDto){

        Hotel hotel = new Hotel();
        hotel.setId(hotelDto.getId());
        hotel.setName(hotelDto.getName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setAverageRating(hotelDto.getAverageRating());
        hotel.setAddress(hotelDto.getAddress());
        hotelDto.setAddress(this.addressService.addAddress(hotelDto.getAddress()));
        hotel = this.hotelService.addHotel(hotel);
        System.out.println(hotelDto.getAddress().getLatitude() + "ssssssssss");
        System.out.println(hotelDto.getAddress().getLongitude() + "aaaaaaaaaaaa");
        return ResponseEntity.ok(new CreateAndUpdateHotelDto(hotel));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateAndUpdateHotelDto> updateHotel(@RequestBody CreateAndUpdateHotelDto hotelDto){

;
        Hotel hotel = this.hotelService.getHotelById(hotelDto.getId());

        if(hotel == null){
            return ResponseEntity.notFound().build();
        }

        hotel.setId(hotelDto.getId());
        hotel.setName(hotelDto.getName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setAverageRating(hotelDto.getAverageRating());
        hotel.setAddress(hotelDto.getAddress());

        hotel = this.hotelService.updateHotel(hotel);

        return  new ResponseEntity<>(new CreateAndUpdateHotelDto(hotel), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotels(){

        List<Hotel> hotels = this.hotelService.getHotels();
        List<HotelDto> hotelDtos = new ArrayList<>();
        if(hotels == null){
            return ResponseEntity.notFound().build();
        }
        for(Hotel hotel : hotels){
            hotelDtos.add(new HotelDto(hotel));
        }
        return ResponseEntity.ok(hotelDtos);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id){

        Hotel hotel = hotelService.getHotelById(id);
        HotelDto hotelDto = new HotelDto(hotel);
        if(hotel == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotelDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteHotel(@PathVariable Long id){
        Hotel hotel = this.hotelService.getHotelById(id);

        if(hotel == null){
            return ResponseEntity.notFound().build();
        }

        this.hotelService.deleteHotel(hotel);
        return  ResponseEntity.ok().build();
    }

    @PostMapping(value="/{idHotel}/room/{idRoom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity addRoomToHotel(@PathVariable Long idHotel, @PathVariable Long idRoom){
        System.out.println("FFFFFFFFFFFFF add room to hotel");
        Room room = hotelService.addRoomToHotel(idHotel,idRoom);

        System.out.println(idHotel + idRoom);

        if(room == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok().build();

    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HotelDto> saveHotel(@RequestBody HotelDto hotelDto){

        Hotel hotel = new Hotel();
        hotel.setAverageRating(hotelDto.getAverageRating());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setName(hotelDto.getName());
        hotel.setId(hotelDto.getId());
        hotel.setAddress(hotelDto.getAddress());

        hotel = this.hotelService.saveHotel(hotel);
        return new ResponseEntity<>(new HotelDto(hotel),HttpStatus.OK);
    }

}
