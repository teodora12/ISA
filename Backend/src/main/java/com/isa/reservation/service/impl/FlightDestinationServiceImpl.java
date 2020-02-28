package com.isa.reservation.service.impl;

import com.isa.reservation.model.Flight;
import com.isa.reservation.model.FlightDestination;
import com.isa.reservation.repository.FlightDestinationRepository;
import com.isa.reservation.service.FlightDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightDestinationServiceImpl implements FlightDestinationService {

    @Autowired
    private FlightDestinationRepository flightDestinationRepository;

    @Override
    public List<FlightDestination> getAllFlightDestinations() {
        return flightDestinationRepository.findAll();
    }

    @Override
    public FlightDestination getFlightDestinationById(Long id) {
        return flightDestinationRepository.findFlightDestinationById(id);
    }

    @Override
    public FlightDestination addFlightDestination(FlightDestination flightDestination) {
        return flightDestinationRepository.save(flightDestination);
    }

    @Override
    public void deleteFlightDestnation(FlightDestination flightDestnation) {
        flightDestinationRepository.deleteById(flightDestnation.getId());
    }

    @Override
    public FlightDestination updateFlightDestination(FlightDestination flightDestination) {
        FlightDestination fd = flightDestinationRepository.findFlightDestinationById(flightDestination.getId());
        fd.setDescription(flightDestination.getDescription());
        fd.setDestination(flightDestination.getDestination());
        fd.setFlight(flightDestination.getFlight());

        return flightDestinationRepository.save(fd);
    }

    @Override
    public void deleteAndAddNewFlightDestinationsByFlight(Long flightId, List<FlightDestination> flightDestinations) {

        List<FlightDestination> flightDestinationsSaved = this.flightDestinationRepository.findAll();

        for (FlightDestination savedFD : flightDestinationsSaved) {

            if (savedFD.getFlight().getId().equals(flightId)) {
                boolean isValid = false;
                for (FlightDestination newFD : flightDestinations) {
                    if (savedFD.getDestination().getId().equals(newFD.getDestination().getId())) {       //ako se vec nalazi u bazi samo je description drugi
                        isValid = true;     //nalazi se u novim (ne brisi)
                        if (!savedFD.getDescription().equals(newFD.getDescription())) {
                            savedFD.setDescription(newFD.getDescription());
                            savedFD = this.flightDestinationRepository.save(savedFD);               //update-uje ga na novu vr
                        }
                    }
                } if (!isValid) {                                                                   //ako se ne nalazi u novim
                    this.flightDestinationRepository.deleteById(savedFD.getId());
                }
            }
        }
        flightDestinationsSaved = this.flightDestinationRepository.findAll();
        for (FlightDestination newFD : flightDestinations) {
            boolean isSaved = false;
            for (FlightDestination savedFD : flightDestinationsSaved) {                               //ako je nova upisi je
                if (savedFD.getFlight().getId().equals(flightId)
                        && savedFD.getDestination().getId().equals(newFD.getDestination().getId())
                        && savedFD.getDescription().equals(newFD.getDescription())) {

                    isSaved = true;
                    break;
                }
            }
            if (!isSaved){
                this.flightDestinationRepository.save(newFD);
            }
        }

    }
}
