package com.isa.reservation.repository;

import com.isa.reservation.model.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {

      CarReservation findCarReservationById(Long id);
      List<CarReservation> findCarReservationsByPickUpDateGreaterThanEqual (Date PickUpDate);
      List<CarReservation> findCarReservationsByPickUpDateGreaterThanAndDropOffDateLessThanEqual(Date PickUpDate, Date PickUpDate1);

}
