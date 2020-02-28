import { Component, OnInit } from '@angular/core';
import {RoomService} from '../../../Service/room.service';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../../Service/hotel.service';

@Component({
  selector: 'app-rooms-new',
  templateUrl: './rooms-new.component.html',
  styleUrls: ['./rooms-new.component.css']
})
export class RoomsNewComponent implements OnInit {

  hotel: any;
  room: any;
  userRole: string;
  hotelId: string;
  newRoomId: string;
  path: string;

  constructor(private roomService: RoomService, private router: Router, private currentRoute: ActivatedRoute,
              private hotelService: HotelService) {
    this.room = {
      name: '', price: '', maxNumberOfGuests: '', numberOfBeds: ''
    };
  }

  ngOnInit() {

    this.path = this.router.url;
    this.room.hotelId = this.path.substring(this.path.lastIndexOf('/') + 1);
/*
    this.currentRoute.params.subscribe(params => {
      this.room.hotelId = params['id'];
      this.hotelService.getHotel(this.room.hotelId).subscribe(hotel => {
        this.hotel = hotel;
        this.hotelId = this.hotel.id;
      });

    });
*/
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

  createRoom(): void {
    this.roomService.createRoom(this.room).subscribe(room => {
      this.room = room;
      this.newRoomId = this.room.hotelId;
 //     this.addRoomToHotel();
//      this.router.navigate(['/hotels', this.hotelId],{});

    });

  }

  addRoomToHotel(): void {
    this.roomService.addRoomToHotel(this.hotelId, this.newRoomId).subscribe(status => {
      console.log('ADD ROOM TO HOTEL!');
    });
  }

}
