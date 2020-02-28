package com.isa.reservation.repository;

import com.isa.reservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findFlightById(Long id);
    List<Flight> findFlightsByDepartureDateTimeGreaterThanEqualAndArrivalDateTimeLessThanEqualAndDepartureDateTimeLessThanAndArrivalDateTimeGreaterThan(Date departure, Date arrival, Date arr1, Date dep1);

    List<Flight> findFlightsByDepartureDateTimeGreaterThanEqualAndArrivalDateTimeLessThanEqualAndDepartureDateTimeLessThanAndArrivalDateTimeGreaterThanAndFlightChangesGreaterThanEqual(Date departure, Date arrival, Date arr1, Date dep1,int flightChanges);

}
