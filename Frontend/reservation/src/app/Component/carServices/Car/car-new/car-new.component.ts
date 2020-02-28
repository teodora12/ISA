import {Component, OnInit} from '@angular/core';
import {CarService} from '../../../../Service/car.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';

@Component({
  selector: 'app-car-new',
  templateUrl: './car-new.component.html',
  styleUrls: ['./car-new.component.css']
})
export class CarNewComponent implements OnInit {

  car: any;
  path: string;
  newCarId: string;
  serviceId: string;

  constructor(private carService: CarService, public toastr: ToastrManager, private router: Router) { }

  ngOnInit() {
    this.car = {name: ''};
    this.path = this.router.url;
    this.serviceId = this.path.substring(this.path.lastIndexOf('/') + 1);
  }


  createCar(): void {
    this.carService.createCar(this.car).subscribe(newCar => {
      this.car = newCar;
      this.newCarId = this.car.id;
      this.addCarToService();
      this.toastr.successToastr('Car created', 'Success');
      // @ts-ignore
      this.router.navigate(['/carService', this.serviceId], {});
    });
  }


  addCarToService(): void {
   this.carService.addCarToService(this.serviceId, this.newCarId).subscribe(status => {

   });
  }
}
