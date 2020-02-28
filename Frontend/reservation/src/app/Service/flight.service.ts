import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }

  getFlights() {
    return this.http.get('api/flights');
  }

  getFlight(id: string) {
    return this.http.get('api/flights/'.concat(id));
  }

  getFlightAllAtributes(id: string) {
    return this.http.get('api/flights/all/'.concat(id));
  }

  search(flight: any) {
    return this.http.post('api/flights/search', flight);
  }

  createFlight(flight: any) {
    return this.http.post('api/flights', flight);
  }

  saveFlight(flight: any) {
    return this.http.put('api/flights', flight);
  }

  updateFlightSeats(flight: any) {
    return this.http.put('api/flights/seats', flight);
  }

  deleteFlight(id: string) {
    return this.http.delete('api/flights/'.concat(id));
  }

  filterFlights(filter: any) {
    return this.http.post('api/flights/filter', filter);
  }
}
