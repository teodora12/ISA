package com.isa.reservation.repository;

import com.isa.reservation.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

 //   List<RoomReservation> findRoomReservationsByRoom_Id(Long roomId);
    List<RoomReservation> findRoomReservationsByArrivalDateGreaterThan(Date arrivalDate);
    List<RoomReservation> findRoomReservationsByArrivalDateGreaterThanAndDepartureDateLessThan(Date d1,Date d2);

}
