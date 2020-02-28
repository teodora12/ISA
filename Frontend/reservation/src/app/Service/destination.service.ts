import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {

  constructor(private http: HttpClient) { }

  getDestinations() {
    return this.http.get('api/destinations');
  }
  //
  // getDestination(id: String) {
  //   return this.http.get('api/destinations/'.concat(id));
  // }

  addDestination(destination: any) {
    return this.http.post('api/destinations', destination);
  }
}
