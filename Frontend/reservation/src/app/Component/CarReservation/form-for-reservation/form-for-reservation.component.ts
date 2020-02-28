import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CarServiceService} from '../../../Service/car-service.service';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {CarService} from '../../../Service/car.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {CarReservationService} from '../../../Service/car-reservation.service';
import {UserService} from '../../../Service/user.service';


@Component({
  selector: 'app-form-for-reservation',
  templateUrl: './form-for-reservation.component.html',
  styleUrls: ['./form-for-reservation.component.css']
})
export class FormForReservationComponent implements OnInit {
  @Output() carReserved = new EventEmitter<boolean>();

  carService: any;
  carServices: any;
  path: string;
  carServiceId: any;
  searchName: any;
  searchedCarServices: any;
  searchCarS: boolean;
  searchByNameShowForm: boolean;

  addressCarService: any;
  addresses: Array<{address: {id: '', country: '', city: '', street: '', addressNumber: ''}}> = [];
  affiliates: any;
  pickUpAddress: any;
  dropOffAddress: any;
  pickAddressId: any;
  dropAddressId: any;
  availableCars: any;

  types: Set<String>;
  type: any;
  numberOfSeats: any;
  number: any;

  hoveredDate: NgbDate;
  pickUpDate: NgbDate;
  dropOffDate: NgbDate;
  pickUpTime: string;
  dropOffTime: string;
  minPrice: any;
  maxPrice: any;
  forSearch: any;
  reservation: any;
  res: any;
  showForm: boolean;
  showCarServices: boolean;
  numberOfDays: number;
  one_day: any;
   date1_ms: any;
   date2_ms: any;
  difference_ms: any;
  userRole: any;
  user: any;


  constructor(private carServiceService: CarServiceService, private carServicee: CarService, private userService: UserService,
              private router: Router, private toastr: ToastrManager, private carReservationService: CarReservationService) { }

  ngOnInit() {
    this.searchByNameShowForm = true;
    this.showForm = false;
    this.showCarServices = true;
    this.searchCarS = false;
    this.searchName = {serviceName: ''};
    this.carService = {name: '', address: {country: '', city: '', street: '', addressNumber: ''}, description: '', affiliates: {}};
    this.pickUpAddress = {id: '', country: '', city: '', street: '', addressNumber: ''};
    this.reservation = {carId: '', pickUpDate: '', dropOffDate: '', pickUpAddressId: '', dropOffAddressId: '' };
    this.dropOffAddress = {id: '', country: '', city: '', street: '', addressNumber: ''};
    this.forSearch = {carServiceId: '', type: '', pickUpDate: Date,
      dropOffDate: Date, minPrice: '', maxPrice: '', pickAddressId: '', dropAddressId: '', numberOfSeats: '' };
    this.getCarServices();

    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
      });
    }
  }


  choose(carService): void {
    this.searchCarS = false;
    this.showForm = true;
    this.showCarServices = false;
    this.carServiceId = carService.id;
    this.getAddrseses();
    this.getTypes();
    this.getNumberOfSeats();

  }


  getCarServices(): void {
    this.carServiceService.allCarServices().subscribe(carServics => {
      this.carServices = carServics;
    });
  }

  getAddrseses(): void {

   this.carServiceService.getCarService(this.carServiceId).subscribe(carServics => {
     this.carService = carServics;
     this.addressCarService = this.carService.address;
     this.addresses.push(this.addressCarService);

      this.affiliates = this.carService.affiliates;
      for (const a of this.affiliates) {
        this.addresses.push(a.address);
      }

   });

  }



  getTypes(): void {
    this.carServicee.getTypes().subscribe(types => {
      // @ts-ignore
      this.types = types;

    });

  }

  getNumberOfSeats(): void {
    this.carServicee.getNumberOfSeats().subscribe(seats => {
      this.numberOfSeats = seats;
    });
  }

  search(): void {
    this.searchCarS = false;
    this.searchByNameShowForm = false;
    this.showForm = false;
    this.forSearch.carServiceId = this.carServiceId;
    this.dropAddressId = this.dropOffAddress.id;
    this.pickAddressId = this.pickUpAddress.id;

    let hours = '0';
    let minutes = '0';


    if (this.pickUpTime === undefined) {
      this.toastr.errorToastr('Invalid parameters for search!', 'Error');
    }



      hours = this.pickUpTime.split(':')[0];
      minutes = this.pickUpTime.split(':')[1];


    let x = +hours[0];      // radi pretvaranje stringa u broj
    let y = +minutes[0];

    this.forSearch.pickUpDate = new Date(Date.UTC(this.pickUpDate.year, this.pickUpDate.month - 1, this.pickUpDate.day,
      x, y, 0, 0));


    if (this.dropOffTime === undefined) {
      this.toastr.errorToastr('Invalid parameters for search!', 'Error');
    }
      hours = '0';
      minutes = '0';
      hours = this.dropOffTime.split(':')[0];
      minutes = this.dropOffTime.split(':')[1];

    x = +hours[0];      // radi pretvaranje stringa u broj
    y = +minutes[0];

    this.forSearch.dropOffDate = new Date(Date.UTC(this.dropOffDate.year, this.dropOffDate.month - 1, this.dropOffDate.day,
      x, y, 0, 0));

    this.inBetween();
    this.carReservationService.getAvailableCars(this.forSearch).subscribe(cars => {
      this.availableCars = cars;

      for (const car of this.availableCars) {
        car.price = ((Number(car.price)) * this.numberOfDays);
      }

    }, error1 => {
      this.toastr.errorToastr('Invalid parameters for search!', 'Error');
    });

  }

  reserve(car): any {
    this.searchCarS = false;
    this.searchByNameShowForm = false;
   this.reservation.carId = car.id;
   this.reservation.pickUpAddressId = this.forSearch.pickAddressId;
   this.reservation.dropOffAddressId = this.forSearch.dropAddressId;
   this.reservation.pickUpDate = this.forSearch.pickUpDate;
   this.reservation.dropOffDate = this.forSearch.dropOffDate;

    this.toastr.successToastr('You reserved a car!', 'Success');

    this.carReserved.emit(this.reservation);

  }

  searchByName(): any {
    this.searchByNameShowForm = false;
    this.searchCarS = true;
    this.showCarServices = false;
    this.carServiceService.searchCarServices(this.searchName).subscribe(carS => {
      this.searchedCarServices = carS;
    });
  }

  inBetween() {
     this.one_day = 1000 * 60 * 60 * 24;
     this.date1_ms = this.forSearch.pickUpDate.getTime();
     this.date2_ms = this.forSearch.dropOffDate.getTime();

     this.difference_ms = this.date2_ms - this.date1_ms;
   this.numberOfDays = Math.round((this.difference_ms / this.one_day) + 1);
  }

  onDateSelect($event) {
    if (!this.pickUpDate && !this.dropOffDate) {
      this.pickUpDate = $event;
    } else if (this.pickUpDate && !this.dropOffDate && $event.after(this.pickUpDate)) {
      this.dropOffDate = $event;
    } else {
      this.dropOffDate = null;
      this.pickUpDate = $event;
    }
  }

  isHovered(date: NgbDate) {
    return this.pickUpDate && !this.dropOffDate &&
      this.hoveredDate && date.after(this.pickUpDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.pickUpDate) && date.before(this.dropOffDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.pickUpDate) || date.equals(this.dropOffDate)
      || this.isInside(date) || this.isHovered(date);
  }
}
