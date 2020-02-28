import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {
  }

  getReservations(username) {
    return this.http.get('/api/reservations/'.concat(username));

  }

  createReservation(reservation: any) {
    return this.http.post('/api/reservations', reservation);
  }


  getInvitation(id: string) {
    return this.http.get('api/reservations/invite/'.concat(id));
  }

  getReservationsByAirline(id: string) {
    return this.http.get('api/reservations/airline/'.concat(id));
  }

  sendCreatedReservationEmail(id: string) {
    return this.http.get('api/reservations/email/'.concat(id));
  }

  deleteInvitation(id: string) {
    return this.http.delete('api/reservations/invite/'.concat(id));
  }

  cancelReservation(id: string) {
    return this.http.delete('api/reservations/'.concat(id));
  }
}
