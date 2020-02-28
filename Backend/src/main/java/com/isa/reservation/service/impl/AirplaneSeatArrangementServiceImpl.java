package com.isa.reservation.service.impl;

import com.isa.reservation.model.AirplaneSeatArrangement;
import com.isa.reservation.repository.AirplaneSeatArrangementRepository;
import com.isa.reservation.service.AirplaneSeatArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneSeatArrangementServiceImpl implements AirplaneSeatArrangementService {

    @Autowired
    private AirplaneSeatArrangementRepository airplaneSeatArrangementRepository;

    @Override
    public List<AirplaneSeatArrangement> getAllAirplaneSeatArrangements() {
        return airplaneSeatArrangementRepository.findAll();
    }

    @Override
    public AirplaneSeatArrangement getAirplaneSeatArrangementByName(String name) {
        return airplaneSeatArrangementRepository.findAirplaneSeatArrangementByName(name);
    }

    @Override
    public AirplaneSeatArrangement getAirplaneSeatArrangementById(Long id) {
        return airplaneSeatArrangementRepository.findAirplaneSeatArrangementById(id);
    }

    @Override
    public AirplaneSeatArrangement addAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement) {
        return airplaneSeatArrangementRepository.save(airplaneSeatArrangement);
    }

    @Override
    public void deleteAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement) {
        airplaneSeatArrangementRepository.delete(airplaneSeatArrangement);
    }

    @Override
    public AirplaneSeatArrangement updateAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement) {

        AirplaneSeatArrangement savedAirplaneSeatArrangement = this.airplaneSeatArrangementRepository.findAirplaneSeatArrangementById(airplaneSeatArrangement.getId());
        savedAirplaneSeatArrangement.setName(airplaneSeatArrangement.getName());
        savedAirplaneSeatArrangement.setSeatRows(airplaneSeatArrangement.getSeatRows());
        savedAirplaneSeatArrangement.setSeatColumns(airplaneSeatArrangement.getSeatColumns());

        return airplaneSeatArrangementRepository.save(airplaneSeatArrangement);
    }

    @Override
    public AirplaneSeatArrangement saveAirplaneSeatArrangement(AirplaneSeatArrangement airplaneSeatArrangement) {
        return airplaneSeatArrangementRepository.save(airplaneSeatArrangement);
    }
}
