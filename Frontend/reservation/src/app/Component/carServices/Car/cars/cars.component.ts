import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CarServiceService} from '../../../../Service/car-service.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {CarService} from '../../../../Service/car.service';
import {UserService} from '../../../../Service/user.service';


@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  carServicee: any;
  carServiceId: string;
  cars: any;
  userRole: string;
  user: any;
  path: any;
  admin: boolean;
  chargeOf: any;

  constructor(private currentRoute: ActivatedRoute, private carServiceService: CarServiceService, private carService: CarService,
              private router: Router, private  toastr: ToastrManager, private userService: UserService) {
    this.carServicee = {cars: {}};
    this.user = { inChargeOf: ''};
  }

  ngOnInit() {
    this.admin = false;
    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
        this.chargeOf = this.user.inChargeOf;

      });
    }
    this.currentRoute.params.subscribe(params => {
      this.carServiceId = params['id'];

      this.carServiceService.getCarService(this.carServiceId).subscribe(carService => {
        this.carServicee = carService;
        this.cars = this.carServicee.cars;

      });
    });


  }
  carProfile(car: any): void {
    this.router.navigate(['/car', car.id]);

  }

  deleteCar(car: any) {
    this.carService.deleteCar(car.id).subscribe(status => {
      this.toastr.successToastr('Car deleted', 'Success');
      this.router.navigate(['/rentAcarServices'], {});
    }, error1 => {
      this.toastr.errorToastr('This car is reserved!', 'Error');
    });
  }

}
