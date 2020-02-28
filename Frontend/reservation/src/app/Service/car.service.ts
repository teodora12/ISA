import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {


  id: string;

  constructor(private http: HttpClient) { }

  getCar(id: string) {
    return this.http.get('api/cars/'.concat(id));
  }

  saveCar(car: any) {
    return this.http.put('api/cars/update', car);
  }


  createCar(car: any) {
    return this.http.post('api/cars/create', car);
  }

  addCarToService(serviceId: string, carId: string) {

    this.id = serviceId + '/car/' + carId;
    // @ts-ignore
    return this.http.post('api/carServices/'.concat(this.id));
  }


  deleteCar(id: string) {
    return this.http.delete('api/cars/delete/'.concat(id));
  }

  getTypes() {
    return this.http.get('api/cars/types');
  }

  getNumberOfSeats() {
    return this.http.get('api/cars/numberOfSeats');
  }

}
