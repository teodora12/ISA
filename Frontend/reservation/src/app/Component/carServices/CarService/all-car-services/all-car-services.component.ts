import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CarServiceService} from '../../../../Service/car-service.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {UserService} from '../../../../Service/user.service';

@Component({
  selector: 'app-all-car-services',
  templateUrl: './all-car-services.component.html',
  styleUrls: ['./all-car-services.component.css']
})
export class AllCarServicesComponent implements OnInit {


  carService: any;
  carServices: any;
  userRole: string;
  user: any;
  admin: boolean;
  charge: any;
  adminCarS: any;
  carServiceId: any;
  path: any;

  constructor(private carServiceService: CarServiceService, private router: Router, private toastr: ToastrManager, private userService: UserService) {
    this.user = {inChargeOf: ''};
    this.carService = {id: ''};
    this.adminCarS = {id: '', name: '', description: '', averageRating: ''};
  }

  ngOnInit() {
    this.admin = false;
    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
        this.charge = this.user.inChargeOf;
        if (this.userRole === 'ROLE_ADMIN_SERVICE') {
          this.admin = true;
          this.carServiceService.getCarService(this.charge).subscribe(carServics => {
            this.adminCarS = carServics;
          });
        } else {
          this.admin = false;
          this.getCarServices();
        }
      });

    } else {
      this.admin = false;
      this.getCarServices();

    }

  }

 //  this.getCarServices();


  getCarServices(): void {
    this.admin = false;
    this.carServiceService.allCarServices().subscribe(carServics => {
      this.carServices = carServics;
    });
  }


  carServiceProfile(carService: any): void {
    this.router.navigate(['/carService', carService.id]);
  }



  deleteCarService(carService): void {
    // this.carSevices.splice(index, kolikonjih(npr 1))
    this.carServiceService.deleteCarService(carService.id).subscribe(status => {
      this.toastr.successToastr('Car service deleted', 'Success');
      this.refresh();
    }, error1  => {
      this.toastr.errorToastr('Car service has car/s which is in use!', 'Error');
    });
  }
  refresh(): void {
    window.location.reload();
  }

}
