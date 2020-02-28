package com.isa.reservation.repository;

import com.isa.reservation.model.Hotel;
import com.isa.reservation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    Room findRoomById(Long id);

    Room findRoomByName(String name);

  //   Set<Room> findRoomsByHotel_Id(Long hotelId);
     Set<Room> findRoomsByHotel_IdAndNumberOfBedsGreaterThanEqual(Long hotelId, int numOfBeds);


}
