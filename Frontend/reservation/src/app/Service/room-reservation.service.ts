import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoomReservationService {

  constructor(private http: HttpClient) { }

  getAvailableRooms(forSearch) {
    return this.http.post('/api/roomReservations/available', forSearch);
  }

  reserve(reservation) {
    return this.http.post('/api/roomReservations/create', reservation);
  }


}
