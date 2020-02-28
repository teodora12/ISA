import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AirlineService {

  constructor(private http: HttpClient) { }

  getAirlines() {
    return this.http.get('api/airlines');
  }

  addAirline(airline) {
    return this.http.post('api/airlines', airline);
  }
  getAirline(id: string) {
    return this.http.get('api/airlines/'.concat(id));
  }
  saveAirline(airline: any) {
    return this.http.put('api/airlines', airline);
  }

  deleteAirline(id: string) {
    return this.http.delete('api/airlines/'.concat(id));
  }

}
