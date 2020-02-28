import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CarService} from '../../../../Service/car.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {UserService} from '../../../../Service/user.service';

@Component({
  selector: 'app-car-profile',
  templateUrl: './car-profile.component.html',
  styleUrls: ['./car-profile.component.css']
})
export class CarProfileComponent implements OnInit {

  car: any;
  carId: string;
  userRole: string;
  user: any;
  admin: boolean;
  carServiceId: string;
  inCharge: any;

  constructor(private currentRoute: ActivatedRoute, private carService: CarService,
              private router: Router, private  toastr: ToastrManager, private userService: UserService) {
    this.car = {name: {}, carServiceId: ''};
    this.user = {inChargeOf: ''};
  }

  ngOnInit() {
    this.admin = false;
    this.currentRoute.params.subscribe(params => {
      this.carId = params['id'];
      this.carService.getCar(this.carId).subscribe(car => {
        this.car = car;
        this.carServiceId = this.car.carServiceId;
      });
    });

    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
        if (this.user.inChargeOf !== null) {
          this.inCharge = this.user.inChargeOf;
          if (this.inCharge === this.carServiceId) {
            this.admin = true;
          } else {
            this.admin = false;
          }
        } else {
          this.admin = false;
        }
      });
    }

  }

  saveCar(): void {
    this.carService.saveCar(this.car).subscribe(car => {
      this.car = car;
      this.toastr.successToastr('Car updated', 'Success');
      // @ts-ignore
      this.router.navigate(['/rentAcarServices'], {});
    });
  }


}
