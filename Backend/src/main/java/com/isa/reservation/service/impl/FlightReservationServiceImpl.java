package com.isa.reservation.service.impl;

import com.isa.reservation.model.FlightReservation;
import com.isa.reservation.model.PassengerOnFlightSeat;
import com.isa.reservation.model.Seat;
import com.isa.reservation.model.User;
import com.isa.reservation.repository.FlightReservationRepository;
import com.isa.reservation.repository.PassengerOnFlightSeatRepository;
import com.isa.reservation.repository.UserRepository;
import com.isa.reservation.service.FlightReservationService;
import com.isa.reservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FlightReservationServiceImpl implements FlightReservationService {

    @Autowired
    private FlightReservationRepository flightReservationRepository;

    @Autowired
    private PassengerOnFlightSeatRepository passengerOnFlightSeatRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatService seatService;



    @Override
    public FlightReservation createFlightReservation(FlightReservation flightReservation) {

        FlightReservation reservation = new FlightReservation();
        reservation.setFlightId(flightReservation.getFlightId());
        reservation.setUserId(flightReservation.getUserId());

        for (PassengerOnFlightSeat passengerOnFlightSeat : flightReservation.getPassengersOnSeats()) {
            passengerOnFlightSeat.getSeat().setState("taken");
            Seat seat = this.seatService.updateSeat(passengerOnFlightSeat.getSeat());
            passengerOnFlightSeat.setSeat(seat);
            passengerOnFlightSeat = this.passengerOnFlightSeatRepository.save(passengerOnFlightSeat);
            reservation.getPassengersOnSeats().add(passengerOnFlightSeat);

            if (passengerOnFlightSeat.getPassengerId() != 0 &&                  // ako je 0 nema nalog
                    passengerOnFlightSeat.getPassengerId() != flightReservation.getUserId()) {
                try {
                    User user = this.userRepository.findUserById(passengerOnFlightSeat.getPassengerId());
                    sendInvitationMail(user, passengerOnFlightSeat.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        reservation = this.flightReservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public PassengerOnFlightSeat getPassengerSeatById(Long id) {

        PassengerOnFlightSeat pass = this.passengerOnFlightSeatRepository.findPassengerOnFlightSeatById(id);
        return pass;
    }

    @Override
    public void deleteInvite(Long id) {
        this.passengerOnFlightSeatRepository.deleteById(id);
    }


    private void sendInvitationMail(User user, Long id) throws MailException, InterruptedException {


        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Friend invitation for flight");

        String url="http://localhost:4200/accept/" + id;
        mail.setText("Click here to review and answer to invitation:" + " " + url);
        javaMailSender.send(mail);

        System.out.println("Email sent!");

    }

}
