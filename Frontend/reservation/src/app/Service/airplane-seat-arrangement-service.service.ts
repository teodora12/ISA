import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AirplaneSeatArrangementServiceService {

  constructor( private http: HttpClient) {}

  getSeatArrangements() {
    return this.http.get('api/seatArrangement');
  }

  getSeatArrangement(id: string) {
    return this.http.get('api/seatArrangement/'.concat(id));
  }
}
