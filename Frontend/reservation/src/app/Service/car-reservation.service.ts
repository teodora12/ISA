import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarReservationService {

  constructor(private http: HttpClient) { }


  getAvailableCars(forSearch) {
    return this.http.post('api/carReservations/available', forSearch);
  }

  reserve(reservation) {
    return this.http.post('api/carReservations/create', reservation);
  }

  delete(id) {
    return this.http.delete('api/carReservations/delete/'.concat(id) );
  }
}
