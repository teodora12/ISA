import { Component, OnInit } from '@angular/core';
import {HotelService} from '../../../../Service/hotel.service';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../../../Service/room.service';

@Component({
  selector: 'app-hotels-profile-rooms',
  templateUrl: './hotels-profile-rooms.component.html',
  styleUrls: ['./hotels-profile-rooms.component.css']
})
export class HotelsProfileRoomsComponent implements OnInit {

  hotel: any;
  rooms: any;
  hotelId: string;
  numOfGuests: any;
  available: any;
  userRole: any;

  constructor(private hotelService: HotelService, private currentRoute: ActivatedRoute, private router: Router,
              private roomService: RoomService) {
    this.hotel = {
      rooms: [{
        name: '', price: '', isAvailable: '', maxNumberOfGuests: '', numberOfBeds: '',
        dateOfArrival: '', dateOfDeparture: ''
      }]
    };
    this.rooms = {
      name: '', price: '', isAvailable: '', maxNumberOfGuests: '', numberOfBeds: '',
      dateOfArrival: '', dateOfDeparture: ''
    };

  }


  ngOnInit() {

    this.currentRoute.params.subscribe(params => {
      this.hotelId = params['id'];
      this.hotelService.getHotel(this.hotelId).subscribe(hotel => {
        this.hotel = hotel;

/*
        let i, j;

        for ( i = 0; i < ((this.hotel).rooms).length ; i++) {
          j = 1;
          this.numOfGuests = [];
          while (j <= ((this.hotel).rooms)[i].maxNumberOfGuests) {
            this.numOfGuests.push(j);
            j++;

            this.available = ((this.hotel).rooms)[i].available;
            if (this.available === true) {
              this.available = 'yes';
            } else {
              this.available = 'no';
            }

          }
        }
*/


    });
    });

    const user = JSON.parse(localStorage.getItem('loggedUser'));
    if (user === null) {
      this.userRole = '';
    } else {
      for (const role of user.roles) {
        if (role.authority === 'ROLE_ADMIN') {
          this.userRole = 'ROLE_ADMIN';
        }
      }
    }

  }


}
