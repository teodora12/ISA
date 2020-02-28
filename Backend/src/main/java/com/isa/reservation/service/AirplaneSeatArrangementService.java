package com.isa.reservation.service;

import com.isa.reservation.model.AirplaneSeatArrangement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirplaneSeatArrangementService {
    List<AirplaneSeatArrangement> getAllAirplaneSeatArrangements();
    AirplaneSeatArrangement getAirplaneSeatArrangementByName(String name);
    AirplaneSeatArrangement getAirplaneSeatArrangementById(Long id);
    AirplaneSeatArrangement addAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement);
    void deleteAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement);
    AirplaneSeatArrangement updateAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement);
    AirplaneSeatArrangement saveAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement);
}
