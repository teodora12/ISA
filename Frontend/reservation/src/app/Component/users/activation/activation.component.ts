import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../Service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {


  email: any;
  path: any;

  constructor(private userService: UserService, private router: Router) {
    this.email = {email: ''};
  }


  ngOnInit() {
    this.path = this.router.url;
    this.email.email = this.path.substring(this.path.lastIndexOf('/') + 1);
    console.log(this.email);
    this.userService.activate(this.email).subscribe(user => {
     console.log(user);
    });

  }

  logIn(): any {
    this.router.navigate(['/logIn'], {});
  }

}
