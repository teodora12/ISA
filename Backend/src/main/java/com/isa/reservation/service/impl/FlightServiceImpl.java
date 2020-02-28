package com.isa.reservation.service.impl;

import com.isa.reservation.dto.*;
import com.isa.reservation.dto.Rating.RatingFlightDto;
import com.isa.reservation.model.*;
import com.isa.reservation.repository.*;
import com.isa.reservation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private FlightDestinationService flightDestinationService;
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Override
    public List<Flight> getAllFlights() {
        return this.flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return this.flightRepository.getOne(id);
    }

    @Override
    public Flight addFlight(Flight flight) {
        return this.flightRepository.save(flight);
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return this.flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(CreateFlightDto flightDto) {

        Airline airline = this.airlineService.getAirlineById(flightDto.getAirlineId());

        Flight flight = this.flightRepository.getOne(flightDto.getId());

        flight = new Flight(flightDto);
        flight.setAirline(airline);

        flight.setDestinations(new HashSet<>());
        List<FlightDestination> flightDestinations = new ArrayList<>();
        int flightChanges = 0;
        for (Long destId : flightDto.getDestinations()) {
            Destination destination = this.destinationService.getDestinationById(destId);          //pretvori dto u objekat
            if (destination == null) {continue;}
            FlightDestination flightDestination = new FlightDestination(flight, destination);

            if (destId.equals(flightDto.getToDest())){
                flightDestination.setDescription("arrival");
            }else if (destId.equals(flightDto.getFromDest())){
                flightDestination.setDescription("departure");
            } else {
                flightDestination.setDescription("connecting");
                flightChanges++;
            }
            flightDestinations.add(flightDestination);
         }
        flight.setFlightChanges(flightChanges);
        flight = this.flightRepository.save(flight);
        this.flightDestinationService.deleteAndAddNewFlightDestinationsByFlight(flight.getId(), flightDestinations);     //brise stare veze


        return flight;
    }

    @Override
    @Transactional
    public boolean deleteFlight(Flight flight) {

        boolean success;
       try {
           this.flightRepository.deleteById(flight.getId());
           success = true;
       } catch (Exception e) {
           success = false;
       }
       return success;
    }

    @Override
    public List<FlightShowInfoDto> search(FlightSearchDto flightSearchDto) {

        List<Flight> flights = new ArrayList<>();

        ArrayList<Flight> flightsDestinations = new ArrayList<>();
        if (flightSearchDto.getType() == 1) {                //ako je u jednom pravcu

            flights = this.flightRepository.findFlightsByDepartureDateTimeGreaterThanEqualAndArrivalDateTimeLessThanEqualAndDepartureDateTimeLessThanAndArrivalDateTimeGreaterThan(
                    flightSearchDto.getFromDate(), flightSearchDto.getToDate(),
                    flightSearchDto.getToDate(), flightSearchDto.getFromDate());
            flightsDestinations = searchFromAndToDestination(flightSearchDto, flights);

        } else if (flightSearchDto.getType() == 3){          //ako je multicity

            flights = this.flightRepository.findFlightsByDepartureDateTimeGreaterThanEqualAndArrivalDateTimeLessThanEqualAndDepartureDateTimeLessThanAndArrivalDateTimeGreaterThanAndFlightChangesGreaterThanEqual(
                    flightSearchDto.getFromDate(), flightSearchDto.getToDate(),
                    flightSearchDto.getToDate(), flightSearchDto.getFromDate(), 1);
            flightsDestinations = searchFromAndToDestination(flightSearchDto, flights);

        } else {                                        //round trip - trazi u oba smera
            flights = this.flightRepository.findFlightsByDepartureDateTimeGreaterThanEqualAndArrivalDateTimeLessThanEqualAndDepartureDateTimeLessThanAndArrivalDateTimeGreaterThan(
                    flightSearchDto.getFromDate(), flightSearchDto.getToDate(),
                    flightSearchDto.getToDate(), flightSearchDto.getFromDate());
            flightsDestinations = searchFromAndToDestination(flightSearchDto, flights);

            FlightSearchDto inverseSearch = new FlightSearchDto(flightSearchDto.getTo(), flightSearchDto.getFrom(),
                    flightSearchDto.getFromDate(), flightSearchDto.getToDate(), flightSearchDto.getType(),
                    flightSearchDto.getSeatClass(), flightSearchDto.getPersons());                    //menja from i to - za povratak

            List<Flight> flightDestinationsReturn = searchFromAndToDestination(inverseSearch, flights);

            flightsDestinations.addAll(flightDestinationsReturn);
        }



        ArrayList<FlightShowInfoDto> flightDtos = new ArrayList<>();

        for (Flight flight : flightsDestinations){
            flightDtos.add(new FlightShowInfoDto(flight));
        }
        return flightDtos;

    }

    private ArrayList<Flight> searchFromAndToDestination(FlightSearchDto flightSearchDto, List<Flight> flights){

        String[] stringsFrom = flightSearchDto.getFrom().split(" ");
        String[] stringsTo = flightSearchDto.getTo().split(" ");

        ArrayList<Flight> flightsDestinations = new ArrayList<>();
        for (Flight flight : flights) {
            boolean from = false;
            boolean to = false;

            Set<FlightDestination> fdd = flight.getDestinations();
            for (FlightDestination flightDestination : flight.getDestinations()) {

                if (flightDestination.getDescription().equals("departure")){
                    for (String fromString : stringsFrom) {                 //za svaku rec iz from trazi
                        if ((flightDestination.getDestination().getName().toLowerCase().contains(fromString.toLowerCase()) ||
                                flightDestination.getDestination().getShortName().toLowerCase().contains(fromString.toLowerCase()) ||
                                flightDestination.getDestination().getAddress().getCountry().toLowerCase().contains(fromString.toLowerCase()) ||
                                flightDestination.getDestination().getAddress().getCity().toLowerCase().contains(fromString.toLowerCase())) &&
                                !fromString.equals("")){

                            from = true;
                            break;
                        }
                    }
                } else if (flightDestination.getDescription().equals("arrival")){

                    for (String toString : stringsTo) {                 //za svaku rec iz from trazi
                        if ((flightDestination.getDestination().getName().toLowerCase().contains(toString.toLowerCase()) ||
                                flightDestination.getDestination().getShortName().toLowerCase().contains(toString.toLowerCase()) ||
                                flightDestination.getDestination().getAddress().getCountry().toLowerCase().contains(toString.toLowerCase()) ||
                                flightDestination.getDestination().getAddress().getCity().toLowerCase().contains(toString.toLowerCase())) &&
                                !toString.equals("")){

                            to = true;
                            break;
                        }
                    }
                }
                if (from && to ) {

                    int seatCount = 0;
                    for (Seat seat : flight.getSeats()) {

                        if (seat.getSeatClass().equals(flightSearchDto.getSeatClass()) &&       //ako je sedste odgovarajuce
                             seat.getState().equals("free")){                                   //klase i slobodno
                            seatCount++;
                            if (seatCount >= flightSearchDto.getPersons()){break;}              //ako vec ima dovoljno,
                                                                                                //ne trazi dalje
                        }
                    }

                    if (seatCount >= flightSearchDto.getPersons()) {
                        flightsDestinations.add(flight);                //ako odgovaraju i from i to destinacije
                    }
                    break;                                  //ne proveravaj dalje za taj flight jer vec odgovara
                                                            //po destinacijama, ako ne odgovara po broju mesta,
                                                            //nece odgovarati ni u sledecem prolazu
                }
            }
        }

        return flightsDestinations;
    }

    @Override
    public Flight updateFlightSeats(FlightShowInfoDto flightDto) {

        Flight flight = this.flightRepository.findFlightById(flightDto.getId());
        List<Seat> seats = new ArrayList<>();
        for (Seat seat : flightDto.getSeats()) {
            if (seat.getSeatClass() != null) {
                if (seat.getSeatClass().equals("ECONOMY")) {            // postavi cenu ako je novo sediste, ili je klasa promenjena
                    seat.setPrice(flight.getEconomyPrice());
                } else if (seat.getSeatClass().equals("PREMIUM_ECONOMY")) {
                    seat.setPrice(flight.getPremiumEconomyPrice());
                } else if (seat.getSeatClass().equals("BUSINESS")) {
                    seat.setPrice(flight.getBusinessPrice());
                } else if (seat.getSeatClass().equals("FIRST")) {
                    seat.setPrice(flight.getFirstPrice());
                }
            }
            seat = this.seatService.updateSeat(seat);                 // snimi promenjeno sediste
            seats.add(seat);
        }

        for (Seat seat : seats) {
            boolean contains = false;
            for (Seat savedSeat : flight.getSeats()) {
                if (seat.getId().equals(savedSeat.getId())) {
                    contains = true;
                }
            }
            if (!contains) {                // ako je novo dodaj ga u flight
                flight.getSeats().add(seat);
            }
        }

        flight = this.flightRepository.save(flight);
        return flight;

    }

    @Override
    public  Double ratingFlight(RatingFlightDto ratingFlightDto){

        Flight flight = flightRepository.findFlightById(ratingFlightDto.getFlightId());
        if(flight == null){
            return null;
        }
        User user = userService.findByUsername(ratingFlightDto.getUsername());
        Rating rating = ratingService.findByUserIdAndFligftId(user.getId(),ratingFlightDto.getFlightId());

        if(rating == null){
            Rating rating1 = new Rating();
            rating1.setUserId(user.getId());
            rating1.setFlightId(ratingFlightDto.getFlightId());
            rating1.setFlightRating(ratingFlightDto.getRating());

            flight.setSumRating(flight.getSumRating() + ratingFlightDto.getRating());
            flight.setNumberOfRating(flight.getNumberOfRating() + 1);
            double average = flight.getSumRating() / flight.getNumberOfRating();

            int scale = (int) Math.pow(10, 1);
            double rez = (double) Math.round(average * scale) / scale;
            flight.setAverageRating(rez);
            this.flightRepository.save(flight);
            this.ratingService.saveRating(rating1);

            return flight.getAverageRating();

        } else {
            if(rating.getFlightRating() == 0){
                rating.setFlightId(ratingFlightDto.getFlightId());
                rating.setFlightRating(ratingFlightDto.getRating());

                flight.setSumRating(flight.getSumRating() + ratingFlightDto.getRating());
                flight.setNumberOfRating(flight.getNumberOfRating() + 1);
                double average = flight.getSumRating() / flight.getNumberOfRating();

                int scale = (int) Math.pow(10, 1);
                double rez = (double) Math.round(average * scale) / scale;
                flight.setAverageRating(rez);
                this.flightRepository.save(flight);
                this.ratingService.saveRating(rating);
                return flight.getAverageRating();

            }
            else{
                return null;
            }


        }

    }

    @Override
    public List<FlightShowInfoDto> filter(FlightFilterDto flightFilter) {

        List<FlightShowInfoDto> oldFlights = flightFilter.getFlights();
        List<FlightShowInfoDto> newFlights = new ArrayList<>();


        for (FlightShowInfoDto flight : oldFlights) {
            boolean isValid = true;
            if (!flightFilter.getAirline().equals("")) {
                if (!flight.getAirline().toLowerCase().contains(flightFilter.getAirline().toLowerCase())) {
                    isValid = false;
                }
            }

            if (flight.getEconomyPrice() < flightFilter.getFromPrice() &&               // ako ni jedna ne upada u min
                    flight.getPremiumEconomyPrice() < flightFilter.getFromPrice() &&
                    flight.getBusinessPrice() < flightFilter.getFromPrice() &&
                    flight.getFirstPrice() < flightFilter.getFromPrice()) {
                isValid = false;
            }

            if (flightFilter.getToPrice() != 0) {                                         // ako su svi veci od max
                if (flight.getEconomyPrice() > flightFilter.getFromPrice() &&
                        flight.getPremiumEconomyPrice() > flightFilter.getFromPrice() &&
                        flight.getBusinessPrice() > flightFilter.getFromPrice() &&
                        flight.getFirstPrice() > flightFilter.getFromPrice()) {
                    isValid = false;
                }
            }

            if (flight.getDuration() < flightFilter.getFromDuration()) {
                isValid = false;
            }

            if (flightFilter.getToDuration() != 0) {
                if (flight.getDuration() > flightFilter.getToDuration()) {
                    isValid = false;
                }
            }


            if (isValid) {
                newFlights.add(flight);
            }
        }

        return newFlights;
    }

}
