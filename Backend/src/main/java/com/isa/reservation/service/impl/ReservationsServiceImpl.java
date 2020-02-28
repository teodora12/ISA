package com.isa.reservation.service.impl;

import com.isa.reservation.dto.CreateReservationDto;
import com.isa.reservation.dto.CreateRoomReservationDto;
import com.isa.reservation.dto.ReservationDto;
import com.isa.reservation.model.*;
import com.isa.reservation.repository.FlightRepository;
import com.isa.reservation.repository.ReservationRepository;
import com.isa.reservation.repository.SeatRepository;
import com.isa.reservation.repository.UserRepository;
import com.isa.reservation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationsServiceImpl implements ReservationsService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightReservationService flightReservationService;

    @Autowired
    private CarReservationService carReservationService;

    @Autowired
    private RoomReservationService roomReservationService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Reservation> getReservationsByUser(String username) {

        User user = userService.getUserByUsername(username);

        List<Reservation> reservations = this.reservationRepository.findReservationsByUser(user);
        if(reservations == null){
            return null;
        }
        return reservations;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        if(reservations == null){
            return null;
        }
        return reservations;
    }

    @Override
    public Reservation createReservation(CreateReservationDto reservationDto) {

        Reservation reservation = new Reservation();
        reservation.setDateCreated(new Date());
        reservation.setFlightReservation(this.flightReservationService.
                createFlightReservation(reservationDto.getFlightReservation()));

        if (reservationDto.getCarReservation() != null) {
            if (reservationDto.getCarReservation().getDropOffDate() != null) {
                reservation.setCarReservation(this.carReservationService.
                        createReservation(reservationDto.getCarReservation()));
            }
        }

        if(reservationDto.getRoomReservation() != null){
            if(reservationDto.getRoomReservation().getDepartureDate() != null){
                RoomReservation roomReservation = this.roomReservationService.createRoomReservation(reservationDto.getRoomReservation());
                reservation.setRoomReservation(roomReservation);
            }
        }

        reservation.setUser(this.userService.getUserById(reservationDto.getUserId()));

        reservation = this.reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationRepository.findReservationById(id);

        if (reservation.getCarReservation() != null) {
            this.carReservationService.deleteReservation(reservation.getCarReservation().getId());
        }

        for (PassengerOnFlightSeat pass : reservation.getFlightReservation().getPassengersOnSeats()) {
            pass.getSeat().setState("free");                        // oslobodi svako sediste
            pass.setSeat(this.seatRepository.save(pass.getSeat()));
        }

        this.reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDto> getAllByAirlineId(Long id) {

        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = this.reservationRepository.findAll();

        for (Reservation reservation : allReservations) {
            Flight flight = this.flightRepository.getOne(reservation.getFlightReservation().getFlightId());

            if (flight.getAirline().getId().equals(id)) {
                reservations.add(reservation);
            }
        }

        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDtos.add(new ReservationDto(reservation));
        }

        return reservationDtos;
    }

    @Override
    public void sendReservationEmail(Long id) throws MailException, InterruptedException {

        Reservation reservation = this.reservationRepository.findReservationById(id);
        Flight flight = this.flightRepository.findFlightById(reservation.getFlightReservation().getFlightId());
        Destination from = new Destination();
        Destination to = new Destination();
        for (FlightDestination d : flight.getDestinations()) {
            if (d.getDescription().equals("arrival")) {
                to = d.getDestination();
            } else if (d.getDescription().equals("departure")) {
                from = d.getDestination();
            }
        }
        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(reservation.getUser().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("New reservation");

        String emailText = "You have made a new reservation ";
        emailText = emailText.concat("Flight from: " + from.getName() + " " + from.getAddress().getCountry() + ", " +
                from.getAddress().getCity()+" ");
        emailText = emailText.concat("to: " + to.getName() + " " + to.getAddress().getCountry() + ", " +
                to.getAddress().getCity()+" ");

        for (PassengerOnFlightSeat seat : reservation.getFlightReservation().getPassengersOnSeats()) {
            emailText = emailText.concat("For: "+seat.getPassengerName() + " "+seat.getPassengerLastName()+" passport: "+
                    seat.getPassengerId());
            emailText = emailText.concat(" Seat row: "+seat.getSeat().getSeatRow()+" column: "+seat.getSeat().getSeatColumn()+ " class: "+seat.getSeat().getSeatClass());
        }
        if (reservation.getCarReservation() != null){

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            String pickUp = dateFormat.format(reservation.getCarReservation().getPickUpDate());
            String dropOff = dateFormat.format(reservation.getCarReservation().getDropOffDate());

            emailText = emailText.concat("Car reservation");
            emailText = emailText.concat("Car: "+ reservation.getCarReservation().getCar().getMake()+
                    reservation.getCarReservation().getCar().getModel()+" " );
            emailText = emailText.concat("Pick up at: "+reservation.getCarReservation().getPickUpAddress().getCountry()+
                    reservation.getCarReservation().getPickUpAddress().getCity()+", "+
                    reservation.getCarReservation().getPickUpAddress().getStreet()+" "+
                    reservation.getCarReservation().getPickUpAddress().getAddressNumber()+" on "+ pickUp);

            emailText = emailText.concat("Drop off at: "+reservation.getCarReservation().getDropOffAddress().getCountry()+" "+
                    reservation.getCarReservation().getDropOffAddress().getCity()+", "+
                    reservation.getCarReservation().getDropOffAddress().getStreet()+" "+
                    reservation.getCarReservation().getDropOffAddress().getAddressNumber()+" on "+ dropOff);
        }
        mail.setText(emailText);

        try {
            javaMailSender.send(mail);
            System.out.println("Email sent!");
        } catch (Exception e) {
            System.out.println("Mail not sent!");
        }
    }


}
