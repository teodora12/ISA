import { Component, OnInit } from '@angular/core';
import {FlightService} from '../../../Service/flight.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {DestinationService} from '../../../Service/destination.service';
import {Router, RouterModule} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {until} from 'selenium-webdriver';
import elementTextContains = until.elementTextContains;

@Component({
  selector: 'app-home-page-flights',
  templateUrl: './home-page-flights.component.html',
  styleUrls: ['./home-page-flights.component.css']
})
export class HomePageFlightsComponent implements OnInit {

  from: string;
  to: string;
  flight: any;
  flights: any;

  destinations: any;
  hoveredDate: NgbDate;
  fromDate: NgbDate;
  toDate: NgbDate;

  isFilterVisible: any;

  airlineNameFilter: any;
  fromDurationFilter: any;
  toDurationFilter: any;
  fromPriceFilter: any;
  toPriceFilter: any;



  constructor(private flightService: FlightService, private destinationService: DestinationService, private router: Router,
              private toastr: ToastrManager) { }

  getSelected(event) {
    this.airlineNameFilter = '';
    this.fromDurationFilter = '';
    this.toDurationFilter = '';
    this.fromPriceFilter = '';
    this.toPriceFilter = '';
  }

  ngOnInit() {
    this.isFilterVisible = false;
    this.flight = { from: '', to: '', type: 1, persons: '1', seatClass: 'ECONOMY',
      toDate: Date, fromDate: Date};
    this.fromDate = undefined;
    this.toDate = undefined;
    this.destinationService.getDestinations().subscribe(destinations => {
      this.destinations = destinations;
    });
  }

  search() {
    if (this.fromDate === undefined || this.toDate === undefined) {
      this.toastr.warningToastr('Departure and arrival destinations are required', 'Required fields are empty');
    } else {
      this.flight.fromDate = new Date(Date.UTC(this.fromDate.year, this.fromDate.month - 1, this.fromDate.day,
        0, 0, 0, 0));
      this.flight.toDate = new Date(Date.UTC(this.toDate.year, this.toDate.month - 1, this.toDate.day,
        0, 0, 0, 0));

      this.flightService.search(this.flight).subscribe(flights => {
        this.flights = flights;
        if (this.flights.length === 0) {
          this.toastr.infoToastr('There are no flights matching search parameters.', 'No results found');
        }
      });
    }
  }

  flightProfile(flight) {
    this.router.navigate(['/flights', flight.id]);
  }

  onDateSelect($event) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = $event;
    } else if (this.fromDate && !this.toDate && $event.after(this.fromDate)) {
      this.toDate = $event;
    } else {
      this.toDate = null;
      this.fromDate = $event;
    }
  }

  setFilterVisible() {
    this.isFilterVisible = true;
  }

  addFilter() {

    const filter = {airline: this.airlineNameFilter, fromDuration: this.fromDurationFilter,
      toDuration: this.toDurationFilter, fromPrice: this.fromPriceFilter, toPrice: this.toPriceFilter, flights: this.flights};

    this.flightService.filterFlights(filter).subscribe( flights => {
      this.flights = flights;
      this.airlineNameFilter = '';
      this.fromDurationFilter = '';
      this.toDurationFilter = '';
      this.fromPriceFilter = '';
      this.toPriceFilter = '';
    });
    this.isFilterVisible = false;
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate &&
      this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate)
      || this.isInside(date) || this.isHovered(date);
  }

}
