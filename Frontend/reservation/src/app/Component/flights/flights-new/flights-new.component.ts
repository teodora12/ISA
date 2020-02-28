import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AirlineService} from '../../../Service/airline.service';
import {FlightService} from '../../../Service/flight.service';
import { NgbCalendar, NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {AirplaneSeatArrangementServiceService} from '../../../Service/airplane-seat-arrangement-service.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {NgbTime} from '@ng-bootstrap/ng-bootstrap/timepicker/ngb-time';
import {UserService} from '../../../Service/user.service';


@Component({
  selector: 'app-flights-new',
  templateUrl: './flights-new.component.html',
  styleUrls: ['./flights-new.component.css']
})
export class FlightsNewComponent implements OnInit {

  flight: any;
  airline: {};
  airplaneSeatArrangements: any;
  selectedDestinations: [{}];
  departureDate: NgbDate;
  departureTime: NgbTime;
  arrivalDate: NgbDate;
  arrivalTime: NgbTime;
  userRole: any;
  user: any;


  constructor(private currentRoute: ActivatedRoute, private flightService: FlightService,
              private airlineService: AirlineService, private calendar: NgbCalendar,
              private airplaneSeatArrangementService: AirplaneSeatArrangementServiceService,
              private router: Router, private toastr: ToastrManager, private userService: UserService) {
    // this.airline = {availableDestinations: [{address: {}}]};

    this.flight = {
      airlineId: '', fromDest: undefined, toDest: undefined, duration: '', distance: '', destinations: [],
      flightChanges: 0, price: '', arrivalDateAndTime: Date, departureDateAndTime: Date, seatArrangement: undefined,
      baggageDescription: '', additionalServices: ''
    };
    this.user = {inChargeOf: 0};
  }

  ngOnInit() {
    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
      });
    }
    this.airline = {availableDestinations: []};
    // this.departureDate = this.calendar.getToday();
   // this.arrivalDate = this.calendar.getToday();
    // this.arrivalDate = this.calendar.getNext(this.calendar.getToday(), 'd', 10);
    this.airplaneSeatArrangementService.getSeatArrangements().subscribe(arrangement => {
      this.airplaneSeatArrangements = arrangement;
    });
    this.currentRoute.params.subscribe(params => {
      this.flight.airlineId = params['id'];
      this.airlineService.getAirline(this.flight.airlineId).subscribe(airline => {
        this.airline = airline;
      });
    }, error => {
      // in case of error, add the callback to bring the item back and re-throw the error.
    });
  }

  onDateSelect($event) {
  if (!this.departureDate && !this.arrivalDate) {
    this.departureDate = $event;
  } else if (this.departureDate && !this.arrivalDate && $event.after(this.departureDate)) {
    this.arrivalDate = $event;
  } else {
    this.arrivalDate = null;
    this.departureDate = $event;
  }
}


  addFlight(): void {
    if (this.flight.fromDest === undefined || this.flight.toDest === undefined) {
      this.toastr.errorToastr('Departure and arrival destinations are required', 'Required fields');
    } else if (this.departureDate === undefined || this.departureTime === null || this.arrivalDate === undefined ||
      this.arrivalTime === null  || this.arrivalTime === undefined || this.departureTime === undefined) {
      this.toastr.errorToastr('Departure and arrival date and time are required', 'Required fields');
    } else if (this.flight.seatArrangement === undefined) {
      this.toastr.errorToastr('Seat arrangement is required', 'Required fields');
    } else {
      if (this.selectedDestinations !== undefined) {
        for (const dest of this.selectedDestinations) {
          if (dest !== undefined) {
            if (dest instanceof Array) {
              for (const d of dest) {
                if (d['id'] !== undefined) {
                  this.flight.destinations.push(d['id']);
                }
              }
            } else if (dest['id'] !== undefined) {
              this.flight.destinations.push(dest['id']);
            }
          }
        }
        this.flight.flightChanges = this.flight.destinations.length;
      }

      this.flight.destinations.push(this.flight.fromDest);
      this.flight.destinations.push(this.flight.toDest);

      // month - 1 jer ga racuna od jedan, a date od 0
      this.flight.departureDateAndTime = new Date(Date.UTC(this.departureDate.year, this.departureDate.month - 1, this.departureDate.day,
        this.departureTime.hour, this.departureTime.minute, 0, 0));
      this.flight.arrivalDateAndTime = new Date(Date.UTC(this.arrivalDate.year, this.arrivalDate.month - 1, this.arrivalDate.day,
        this.arrivalTime.hour, this.arrivalTime.minute, 0, 0));

      this.flightService.createFlight(this.flight).subscribe(flight => {
        this.flight = flight;
        this.toastr.successToastr('Flight created', 'Success');
        this.router.navigate(['/airlines', this.flight.airlineId]);
      });
    }
  }

  onChange($event) {
    this.selectedDestinations.push($event);
  }
}
