import { Component, OnInit } from '@angular/core';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {HotelService} from '../../../Service/hotel.service';
import {Route, Router} from '@angular/router';

@Component({
  selector: 'app-home-page-hotels',
  templateUrl: './home-page-hotels.component.html',
  styleUrls: ['./home-page-hotels.component.css']
})
export class HomePageHotelsComponent implements OnInit {


  hotels: any;
  hotel: any;

  constructor(private hotelService: HotelService, private router: Router) {
    this.hotel = {name: '', id: ''};
  }

  ngOnInit() {
    this.getHotels();

  }

  getHotels() {
    this.hotelService.getHotels().subscribe(hotels => {
      this.hotels = hotels;
    });
  }

}
