package com.isa.reservation.service.impl;

import com.isa.reservation.model.Destination;
import com.isa.reservation.repository.DestinationRepository;
import com.isa.reservation.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

        @Autowired
        private DestinationRepository destinationRepository;

        @Override
        public List<Destination> getAllDestinations() {
            return this.destinationRepository.findAll();
        }

        @Override
        public Destination getDestinationById(Long id) {
            return this.destinationRepository.findDestinationById(id);
        }

        @Override
        public Destination addDestination(Destination destination) {
            return this.destinationRepository.save(destination);
        }

        @Override
        public Destination updateDestination(Destination destination) {
            Destination savedDestination = this.destinationRepository.findDestinationById(destination.getId());
            savedDestination.setName(destination.getName());
            savedDestination.setShortName(destination.getShortName());
            savedDestination.setAddress(destination.getAddress());

            return this.destinationRepository.save(savedDestination);
        }

        @Override
        public void deleteDestination(Destination destination) {
            this.destinationRepository.delete(destination);
        }
    }


