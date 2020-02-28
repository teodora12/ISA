import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {

  constructor(private http: HttpClient) { }

  allCarServices() {
    return this.http.get('api/carServices');

  }

  createCarService(carService) {
    return this.http.post('api/carServices/create', carService);
  }

  getCarService(id: string) {
    return this.http.get('api/carServices/'.concat(id));
  }

  saveCarService(carService: any) {
    return this.http.put('api/carServices/update', carService);
  }


  getAffiliate(id: string) {
    return this.http.get('api/affiliates/'.concat(id));
  }


  deleteCarService(id: string) {
    return this.http.delete('api/carServices/delete/'.concat(id));
  }

  searchCarServices(name: any) {
    return this.http.put('api/carServices/search', name);
  }

}
