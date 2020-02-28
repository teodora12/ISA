package com.isa.reservation.service;

import com.isa.reservation.model.Destination;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DestinationService {
    List<Destination> getAllDestinations();
    Destination getDestinationById(Long id);
    Destination addDestination(Destination destination);
    Destination updateDestination(Destination destination);
    void deleteDestination(Destination destination);

}
