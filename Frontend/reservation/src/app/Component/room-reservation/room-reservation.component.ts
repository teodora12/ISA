import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {HotelService} from '../../Service/hotel.service';
import {RoomService} from '../../Service/room.service';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {RoomReservationService} from '../../Service/room-reservation.service';

@Component({
  selector: 'app-room-reservation',
  templateUrl: './room-reservation.component.html',
  styleUrls: ['./room-reservation.component.css']
})
export class RoomReservationComponent implements OnInit {
  @Output() hotelReserved = new EventEmitter<boolean>();

  hotel: any;
  hotels: any;
  hotelId: any;

  rooms: any;

  type: any;
  numberOfBeds: any;
  number: any;
  availableRooms: any;

  hoveredDate: NgbDate;
  arrivalDate: NgbDate;
  departureDate: NgbDate;

  arrivalTime: any;
  departureTime: any;
  minPrice: any;
  maxPrice: any;
  forSearch: any;
  numberOfNights: number;

  reservation: any;
  res: any;

  showForm: boolean;
  showHotels: boolean;
  showHotelName: boolean;
  showAvailableRooms: boolean;
  showFinish: boolean;

  one_day: any;
  date1_ms: any;
  date2_ms: any;
  difference_ms: any;

  roomAllReservations: any;
  userRole: any;


  constructor(private hotelService: HotelService, private roomService: RoomService, private router: Router,
              private toastr: ToastrManager, private roomReservationService: RoomReservationService) { }

  ngOnInit() {

    this.showForm = false;
    this.showHotels = true;
    this.showHotelName = false;
    this.showAvailableRooms = false;
    this.showFinish = false;
    this.hotel = {name: '', address: {country: '', city: '', street: '', addressNumber: ''}, description: ''};
    this.reservation = {roomId: '', arrivalDate: '', departureDate: '', numberOfBeds: '', numberOfNights: ''};
    this.forSearch = {hotelId: '', type: '', dateOfArrival: Date, dateOfDeparture: Date, minPrice: '',
                      maxPrice: '', numberOfBeds: '', numberOfNights: '', averageRating: ''};
    this.roomAllReservations = {rooms: [], arrivalDate: Date, departureDate: Date, numberOfBeds: '', numberOfNights: ''};

    this.getHotels();

  }

  getHotels(): void {
    this.hotelService.getHotels().subscribe(hotels => {
      this.hotels = hotels;
    });
  }

  choose(hotel): void {
    this.hotel.name = hotel.name;
    this.showForm = true;
    this.showHotels = false;
    this.hotelId = hotel.id;
    this.showHotelName = true;
    this.getNumberOfBeds();

  }

  getNumberOfBeds(): void {
    this.roomService.getNumberOfBeds().subscribe(number => {
      this.numberOfBeds = number;
    });
  }

  search(): void {
    this.showForm = false;
    this.forSearch.hotelId = this.hotelId;
    this.showAvailableRooms = true;

    let hours = '0';
    let minutes = '0';
    if (this.arrivalTime !== '') {
      hours = this.arrivalTime.split(':')[0];
      minutes = this.arrivalTime.split(':')[1];
    }
    let x = +hours[0];      // radi pretvaranje stringa u broj
    let y = +minutes[0];

    this.forSearch.dateOfArrival = new Date(Date.UTC(this.arrivalDate.year, this.arrivalDate.month - 1, this.arrivalDate.day,
      x, y, 0, 0));

    hours = '0';
    minutes = '0';
    if (this.departureTime !== '') {
      hours = this.departureTime.split(':')[0];
      minutes = this.departureTime.split(':')[1];
    }
    x = +hours[0];      // radi pretvaranje stringa u broj
    y = +minutes[0];

    this.forSearch.dateOfDeparture = new Date(Date.UTC(this.departureDate.year, this.departureDate.month - 1, this.departureDate.day,
      x, y, 0, 0));

    this.inBetween();

    this.forSearch.numberOfNights = this.numberOfNights;

    this.roomReservationService.getAvailableRooms(this.forSearch).subscribe(rooms => {
      this.availableRooms = rooms;


      for (const room of this.availableRooms) {
        room.price = ((Number(room.price)) * this.numberOfNights);
      }
    });

  }

  reserve(room): any {
//    this.showAvailableRooms = false;
    this.showFinish = true;
    this.reservation.roomId = room.id;

    this.reservation.arrivalDate = this.forSearch.dateOfArrival;
    this.reservation.departureDate = this.forSearch.dateOfDeparture;
    this.reservation.numberOfBeds = this.forSearch.numberOfBeds;
    this.reservation.numberOfNights = this.forSearch.numberOfNights;

    this.roomAllReservations.arrivalDate = this.reservation.arrivalDate;
    this.roomAllReservations.departureDate = this.reservation.departureDate;
    this.roomAllReservations.numberOfBeds = this.reservation.numberOfBeds;
    this.roomAllReservations.numberOfNights = this.reservation.numberOfNights;

    this.roomAllReservations.rooms.dateOfArrival = this.reservation.arrivalDate;
    this.roomAllReservations.rooms.dateOfDeparture = this.reservation.departureDate;
    this.roomAllReservations.rooms.numberOfNights = this.reservation.numberOfNights;
    this.roomAllReservations.rooms.numberOfBeds = this.reservation.numberOfBeds;

    this.roomAllReservations.rooms.push(this.reservation);

    /*
         this.roomReservationService.reserve(this.reservation).subscribe(reservation => {
           this.res = reservation;
           console.log(this.res);

           this.toastr.successToastr('You reserved room!', 'Success');
     //      this.router.navigate(['/home']);
         });
    */

  }

  finishedRoomReservation() {
    this.hotelReserved.emit(this.roomAllReservations);
  }

  inBetween() {
    this.one_day = 1000 * 60 * 60 * 24;
    this.date1_ms = this.forSearch.dateOfArrival.getTime();
    this.date2_ms = this.forSearch.dateOfDeparture.getTime();

    this.difference_ms = this.date2_ms - this.date1_ms;
    this.numberOfNights = Math.round((this.difference_ms / this.one_day));
  }


  onDateSelect($event) {
    if (!this.arrivalDate && !this.departureDate) {
      this.arrivalDate = $event;
    } else if (this.arrivalDate && !this.departureDate && $event.after(this.arrivalDate)) {
      this.departureDate = $event;
    } else {
      this.departureDate = null;
      this.arrivalDate = $event;
    }
  }

  isHovered(date: NgbDate) {
    return this.arrivalDate && !this.departureDate &&
      this.hoveredDate && date.after(this.arrivalDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.arrivalDate) && date.before(this.departureDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.arrivalDate) || date.equals(this.departureDate)
      || this.isInside(date) || this.isHovered(date);
  }



}
