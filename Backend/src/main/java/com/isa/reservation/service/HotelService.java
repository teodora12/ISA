package com.isa.reservation.service;

import com.isa.reservation.model.Hotel;
import com.isa.reservation.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    List<Hotel> getHotels();
    Hotel getHotelById(Long id);
    Hotel getHotelByName(String name);
    void deleteHotel(Hotel hotel);
    Hotel addHotel(Hotel hotel);
    Hotel saveHotel(Hotel hotel);
    Hotel updateHotel(Hotel hotel);
    Room addRoomToHotel(Long idHotel, Long idRoom);


}
