import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(private http: HttpClient) { }

  rateCarService(carServiceRatingDto: any) {
    return this.http.put('api/carServices/rating', carServiceRatingDto);

  }

  rateCar(carRatingDto: any) {
    return this.http.put('api/cars/rating', carRatingDto);

  }

  rateAirline(ratingAirlineDto: any) {
    return this.http.put('api/airlines/rating', ratingAirlineDto);

  }

  rateFlight(retingFlightDto: any) {
    return this.http.put('api/flights/rating', retingFlightDto);

  }

  compare(comp: any) {
    return this.http.post('api/cars/compare', comp);
  }
}
