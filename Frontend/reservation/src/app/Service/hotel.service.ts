import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  getHotels() {
    return this.http.get('/api/hotels');
  }

  createHotel(hotel) {
    return this.http.post('/api/hotels/create', hotel);
  }

  getHotel(id: string) {
    return this.http.get('api/hotels/'.concat(id));
  }

  saveHotel(hotel: any) {
    return this.http.post('api/hotels/save', hotel);
  }

/*
  deleteHotel(hotel) {
    return this.http.
  }
*/
}

