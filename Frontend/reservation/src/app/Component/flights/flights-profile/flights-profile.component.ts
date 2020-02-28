
import {FlightService} from '../../../Service/flight.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AirlineService} from '../../../Service/airline.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {toNumbers} from '@angular/compiler-cli/src/diagnostics/typescript_version';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Component, OnInit} from '@angular/core';
import {AirplaneSeatArrangementServiceService} from '../../../Service/airplane-seat-arrangement-service.service';
import {UserService} from '../../../Service/user.service';
// import {NgbTime} from '@ng-bootstrap/ng-bootstrap/timepicker/ngb-time';

@Component({
  selector: 'app-flights-profile',
  templateUrl: './flights-profile.component.html',
  styleUrls: ['./flights-profile.component.css']
})
export class FlightsProfileComponent implements OnInit {

  userRole: string;
  user: any;
  flight: any;
  airline: any;
  connecting: any;

  date1: any;     // za ispise datuma u labeli
  date2: any;
  fromDest: any;
  toDest: any;

  selectedDestinations: [{}];
  departureDate: NgbDate;
  departureTime: string;
  arrivalDate: NgbDate;
  arrivalTime: string;

  constructor(private currentRoute: ActivatedRoute, private flightService: FlightService,
              private airlineService: AirlineService, private toastr: ToastrManager, private router: Router,
              private airplaneSeatArrangement: AirplaneSeatArrangementServiceService, private userService: UserService) {

    this.flight = {destinations: [{}], seats: [{}], airlineDto: {}, fromDest: '', toDest: ''};
    this.selectedDestinations = [{}];
    this.connecting = [{address: {}}];
    this.fromDest = {address: {}};
    this.toDest = {address: {}};
    this.user = {inChargeOf: 0};
  }

  ngOnInit() {
    this.connecting = [];
    this.airline = {availableDestinations: []};
    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
      });
    }
    // const user = JSON.parse(localStorage.getItem('loggedUser'));
    // if (user === null) {
    //   this.userRole = '';
    // } else {
    //   for (const role of user.roles) {
    //     if (role.authority === 'ROLE_ADMIN') {
    //       this.userRole = 'ROLE_ADMIN';
    //     }
    //   }
    // }
    this.currentRoute.params.subscribe(params => {
      const flightId = params['id'];
      this.flightService.getFlight(flightId).subscribe(flight => {
        this.flight = flight;

        for (const dest of this.flight.destinations) {
          if (dest.description === 'departure') {
            this.flight.fromDest = dest.destination.id;
            this.fromDest = dest.destination;
          } else if (dest.description === 'arrival') {
            this.flight.toDest = dest.destination.id;
            this.toDest = dest.destination;
          } else {
            this.connecting.push(dest.destination);
            // this.selectedDestinations.push(dest.destination.id);
          }
        }
        let date = new Date(this.flight.departureDateAndTime);
        this.date1 = date;
        this.departureDate = new NgbDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDay());
        this.departureTime = date.getUTCHours().toString() + ':' + date.getUTCMinutes();

        date = new Date(this.flight.arrivalDateAndTime);
        this.date2 = date;
        this.arrivalDate = new NgbDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDay());
        this.arrivalTime = date.getUTCHours().toString() + ':' + date.getUTCMinutes();

        this.airlineService.getAirline(this.flight.airlineDto.id).subscribe(airline => {
          this.airline = airline;
        });
        this.airplaneSeatArrangement.getSeatArrangement(this.flight.seatArrangement).subscribe( seat => {
          this.flight.seatArrangement = seat;
        }, err => {
          console.log(err);
        });
      });
    });
  }

  deleteDestination(index): void {
    this.connecting.splice(index, 1);
  }

  addDestinations(): void {
    if (this.selectedDestinations !== undefined) {
      for (const dest of this.selectedDestinations) {
        if (dest !== undefined) {
          if (dest instanceof Array) {
            for (const d of dest) {
              this.connecting.push(d);
            }
          } else if (dest['id'] !== undefined) {
            this.connecting.push(dest);
          }
        }
      }
    }
    this.selectedDestinations = [{}];
  }

  reservation() {
    this.router.navigate(['/flights/reservation', this.flight.id]);
  }

  onChange($event) {
    this.selectedDestinations.push($event);
  }

  saveFlight() {
    if (this.flight.fromDest === undefined || this.flight.toDest === undefined) {
      this.toastr.errorToastr('Departure and arrival destinations are required', 'Required fields');
    } else if (this.departureDate === undefined || this.departureTime === null || this.arrivalDate === undefined ||
      this.arrivalTime === null || this.arrivalTime === undefined || this.departureTime === undefined) {
      this.toastr.errorToastr('Departure and arrival date and time are required', 'Required fields');
    } else {

      // const temp = this.flight.destinations;        // jer je bila lista veza flight-destination
      this.flight.destinations = [];                // a dto prima samo listu indexa svih destinacija
      // for (const d of  temp) {
      //   this.flight.destinations.push(d.destination.id);
      // }
      for (const dest of this.connecting) {
        if (dest !== undefined) {
          if (dest['id'] !== undefined) {
            this.flight.destinations.push(dest['id']);
          }
        }
      }
      this.flight.flightChanges = this.flight.destinations.length;

      this.flight.destinations.push(this.flight.fromDest);
      this.flight.destinations.push(this.flight.toDest);

      let hours = '0';
      let minutes = '0';
      if (this.departureTime !== '') {
        hours = this.departureTime.split(':')[0];
        minutes = this.departureTime.split(':')[1];
        // let suffix = hours >= 12 ? 'pm' : 'am';
        // hours = hours % 12 || 12;
        // hours = hours < 10 ? '0' + hours : hours;
      }
      let x = +hours[0];      // radi pretvaranje stringa u broj
      let y = +minutes[0];
      this.flight.departureDateAndTime = new Date(Date.UTC(this.departureDate.year, this.departureDate.month - 1, this.departureDate.day,
        x, y, 0, 0));

      // month - 1 jer ga racuna od jedan, a date od 0
      hours = '0';
      minutes = '0';
      if (this.arrivalTime !== '') {
        hours = this.arrivalTime.split(':')[0];
        minutes = this.arrivalTime.split(':')[1];
      }
      x = +hours[0];      // radi pretvaranje stringa u broj
      y = +minutes[0];
      this.flight.arrivalDateAndTime = new Date(Date.UTC(this.arrivalDate.year, this.arrivalDate.month - 1, this.arrivalDate.day,
        x, y, 0, 0));

      this.flight.airlineId = this.flight.airlineDto.id;
      this.flightService.saveFlight(this.flight).subscribe(flight => {
        this.flight = flight;
        this.toastr.successToastr('Flight updated', 'Success');
        this.router.navigate(['/airlines', this.flight.airlineId]);
      });
    }
  }
}
