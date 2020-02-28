package com.isa.reservation.service.impl;

import com.isa.reservation.model.Hotel;
import com.isa.reservation.model.Room;
import com.isa.reservation.repository.HotelRepository;
import com.isa.reservation.repository.RoomRepository;
import com.isa.reservation.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Hotel> getHotels() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id){ return this.hotelRepository.findHotelById(id); }

    @Override
    public Hotel getHotelByName(String name) { return this.hotelRepository.findHotelByName(name); }

    @Override
    public void deleteHotel(Hotel hotel) { this.hotelRepository.delete(hotel); }

    @Override
    public Hotel addHotel(Hotel hotel){ return this.hotelRepository.save(hotel); }

    @Override
    public Hotel saveHotel(Hotel hotel) { return this.hotelRepository.save(hotel); }

    @Override
    public Room addRoomToHotel(Long idHotel, Long idRoom){
        Hotel hotel =  this.hotelRepository.findHotelById(idHotel);

        Room room = this.roomRepository.findRoomById(idRoom);
        room.setHotel(hotel);
        hotel.getRooms().add(room);
        this.hotelRepository.save(hotel);
        this.roomRepository.save(room);

        return room;

    }

    @Override
    public Hotel updateHotel(Hotel hotel) {

        Hotel savedHotel = this.hotelRepository.findHotelById(hotel.getId());

        savedHotel.setAddress(hotel.getAddress());
        savedHotel.setAverageRating(hotel.getAverageRating());
        savedHotel.setDescription(hotel.getDescription());
        savedHotel.setName(hotel.getName());
   //     savedHotel.setRooms(hotel.getRooms());    ...
        savedHotel.setId(hotel.getId());

        return this.hotelRepository.save(savedHotel);
    }

}
