import { Component, OnInit } from '@angular/core';
import {HotelService} from '../../Service/hotel.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  hotels: any;
  hotel: any;
  userRole: string;

  constructor(private hotelService: HotelService, private router: Router) { }

  ngOnInit() {
    this.getHotels();

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

  getHotels(): void {
    this.hotelService.getHotels().subscribe(
      (hotels) => {this.hotels = hotels;
      });
  }
/*
  deleteHotel(): void {
    this.hotelService.deleteHotel(this.hotel).subscribe(
      hotel => { this.hotel = hotel;
        alert('IZBRISAN' + hotel);
      });
  }
*/

  hotelProfile(hotel: any ): void {
    this.router.navigate(['/hotels', hotel.id]);
  }


}
