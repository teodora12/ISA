import { Component, OnInit } from '@angular/core';
import {ReservationService} from '../../../../Service/reservation.service';
import {UserService} from '../../../../Service/user.service';
import {NgbDate, NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, Validators} from '@angular/forms';
import {RatingService} from '../../../../Service/rating.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {FlightService} from '../../../../Service/flight.service';
import {CarReservationService} from '../../../../Service/car-reservation.service';

@Component({
  selector: 'app-profile-history',
  templateUrl: './profile-history.component.html',
  styleUrls: ['./profile-history.component.css']
})
export class ProfileHistoryComponent implements OnInit {
  userRole: any;
  user: any;
  reservations: any;
  carReservation: any;
  listCarReservations: any;
  ctrl: any;
  carServiceRatingDto: any;
  carRatingDto: any;
  ratingCarS: any;
  ratingCar: any;
  rating: number;
  today: any;
  todayD: Date;
  dd: any;
  mm: any;
  yyyy: any;
  showRating: boolean;
  pickUp: Date;
  dropOff: Date;
  compare: any;
  rezz: boolean;
  ratingAirlineDto: any;
  ratingFlightDto: any;

  flightReservations: any;


  constructor( public toastr: ToastrManager, private reservationService: ReservationService, private userService: UserService,
               private config: NgbRatingConfig, private ratingService: RatingService, private flightService: FlightService,
               private carReservationService: CarReservationService) {
    config.max = 5;
    this.carServiceRatingDto = { carServiceId: '', username: '', rating: ''};
    this.carRatingDto = {carId: '', username: '', rating: ''};
    this.ratingAirlineDto = {airlineId: '', username: '', rating: ''};
    this.ratingFlightDto = {flightId: '', username: '', rating: ''};
    this.user = {username: ''};
    this.carReservation = {carDto: {name: '', carServiceId: ''},
      dropOffAddress: {id: '', country: '', city: '', street: '', addressNumber: ''},
      dropOffDate: '' , pickUpAddress: {id: '', country: '', city: '', street: '', addressNumber: ''},
      pickUpDate: '', carRating: '', carServiceRating: '' };
   this.today = {yyyy: '', mm: '', dd: ''};
   this.compare = {dropOff: '', today: ''};
   this.flightReservations = [];
  }

  ngOnInit() {
  //  this.showRating = true;
    this.ctrl = new FormControl(null, Validators.required);
    this.today = new Date();
    this.dd = this.today.getDate();
    this.mm = this.today.getMonth() + 1;
    this.yyyy = this.today.getFullYear();



    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    this.userService.getUserByUsername(userTemp.sub).subscribe( user => {
      this.user = user;
      this.reservationService.getReservations(this.user.username).subscribe(reservations => {
        this.reservations = reservations;

        this.listCarReservations = [];
        const list = [];
        for (const res of this.reservations) {
          list.push(res);
        }
        for (const r of this.reservations) {

          if (r.carReservation !== null) {
            this.pickUp = new Date(r.carReservation.pickUpDate);
            this.dropOff = new Date(r.carReservation.dropOffDate);


            this.compare.dropOff = this.dropOff;
            this.compare.today = this.today;
            this.ratingService.compare(this.compare).subscribe(rez => {

              if (rez === false) {
                r.carReservation.isValid = false;
              } else {
                r.carReservation.isValid = true;
              }
            });


            r.carReservation.pickUpDate = r.carReservation.pickUpDate.substring(0, 10);
            r.carReservation.dropOffDate = r.carReservation.dropOffDate.substring(0, 10);

            this.listCarReservations.push(r.carReservation);
          }
          const flightReservation = {flight: {}, reservationId: r.id, isValid: false, canCancel: false, id: r.flightReservation.id};
          this.flightService.getFlight(r.flightReservation.flightId).subscribe(flight => {
            flightReservation.flight = flight;
            for (const dest of flight['destinations']) {
              if (dest.description === 'departure') {
                flightReservation.flight['fromDest'] = dest;
              } else if (dest.description === 'arrival') {
                flightReservation.flight['toDest'] = dest;
              }
            }

            const now = new Date().toUTCString();
            const arrival = (new Date(flightReservation.flight['arrivalDateAndTime'])).toUTCString();

            const departureTemp = new Date(flightReservation.flight['arrivalDateAndTime']);
            departureTemp.setHours(departureTemp.getHours() + 3);
            const departure = departureTemp.toUTCString();

            if (now > departure) {
              flightReservation.canCancel = true;           // poletanje za vise od 3 sata
            }

            if (now < arrival) {
              flightReservation.isValid = true;             // gotova je
            } else {
              flightReservation.isValid = false;            // nije gotova
            }

            this.flightReservations.push(flightReservation);
          });
          console.log(r);
          console.log(this.reservations);
        }

      });

    });

  }

  cancelFlightReservation(flightReservation, i) {
    this.reservationService.cancelReservation(flightReservation.reservationId).subscribe( ret => {

      for (const reservation of this.reservations) {
        if (flightReservation.reservationId === reservation.id) {
          for (let j = 0; j < this.listCarReservations.length; j++) {
            if (this.listCarReservations[i].id === reservation.carReservation.id ) {
              this.listCarReservations.splice(j, 1);
              break;
            }
          }
        }
        break;
      }
      this.flightReservations.splice(i, 1);

      this.toastr.successToastr('Reservation successfully canceled', 'Success');
    }, error1 => {
      this.toastr.errorToastr('Error occurred during reservation cancellation', 'Error');
    });
  }


  cancelCarReservation(carReservation, j) {
    this.carReservationService.delete(carReservation.id).subscribe( ret => {
      this.listCarReservations.splice(j, 1);
      this.toastr.successToastr('Car reservation successfully canceled', 'Success');
    }, error1 => {
      this.toastr.errorToastr('Error occurred during car reservation cancellation', 'Error');
    });

  }

  rateCarFun(rate, carReservation): void {

    this.carRatingDto.rating = rate;
    this.carRatingDto.carId = carReservation.carDto.id;
    this.carRatingDto.username = this.user.username;
    this.ratingService.rateCar(this.carRatingDto).subscribe(rating => {
      this.ratingCar = rating;

    }, error1 => {
      this.toastr.errorToastr('You have already rated this car!', 'Error');

    });

  }


  rateCarServisFun(rate, carReservation): void {

    this.carServiceRatingDto.rating = rate;
    this.carServiceRatingDto.carServiceId = carReservation.carDto.carServiceId;

    this.carServiceRatingDto.username = this.user.username;

    this.ratingService.rateCarService(this.carServiceRatingDto).subscribe(rating => {
      this.ratingCarS = rating;

    }, error1 => {
      this.toastr.errorToastr('You have already rated this car service!', 'Error');

    });
  }


  rateAirline(rating, flightReservation): void {
    this.ratingAirlineDto.rating = rating;
    this.ratingAirlineDto.username = this.user.username;
    this.ratingAirlineDto.airlineId = flightReservation.flight.airlineDto.id;

    this.ratingService.rateAirline(this.ratingAirlineDto).subscribe(rating1 => {

    }, error2 => {
      this.toastr.errorToastr('You have already rated this airline!', 'Error');

    });

  }

  rateFlight(rating, flightReservation): void {
    this.ratingFlightDto.rating = rating;
    this.ratingFlightDto.username = this.user.username;
    this.ratingFlightDto.flightId = flightReservation.flight.id;

    this.ratingService.rateFlight(this.ratingFlightDto).subscribe(rating1 => {

    }, error3 => {
      this.toastr.errorToastr('You have already rated this flight!', 'Error');

    });
  }




}
