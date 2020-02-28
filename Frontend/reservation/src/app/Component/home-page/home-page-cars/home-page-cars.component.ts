import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../Service/user.service';
import {CarServiceService} from '../../../Service/car-service.service';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home-page-cars',
  templateUrl: './home-page-cars.component.html',
  styleUrls: ['./home-page-cars.component.css']
})
export class HomePageCarsComponent implements OnInit {

  carService: any;
  carServices: any;
  userRole: string;
  carServiceId: string;
  event: any;



  constructor(private carServiceService: CarServiceService, private router: Router, private toastr: ToastrManager) {
    this.carService = { id: '', name: ''};
  }


  ngOnInit() {
    this.getCarServices();
    const user = JSON.parse(localStorage.getItem('loggedUser'));
    if (user === null) {
      this.userRole = '';
    } else {
      for (const role of user.roles) {
        if (role.authority === 'ROLE_ADMIN') {
          this.userRole = 'ROLE_ADMIN';
        }  else if (role.authority === 'ROLE_USER') {
          this.userRole = 'ROLE_USER';
        } else if (role.authority === 'ROLE_ADMIN_AIRLINE') {
          this.userRole = 'ROLE_ADMIN_AIRLINE';
        } else if (role.authority === 'ROLE_ADMIN_HOTEL') {
          this.userRole = 'ROLE_ADMIN_HOTEL';
        } else {
          this.userRole = 'ROLE_ADMIN_SERVICE';
        }
      }
    }
  }

  getCarServices(): void {
    this.carServiceService.allCarServices().subscribe(carServics => {
      this.carServices = carServics;
    });
  }



  ispis() {
    console.log(this.carService.id);
  }

  getId(carService): void {
    this.carService.id  = carService.id;
  }



}
