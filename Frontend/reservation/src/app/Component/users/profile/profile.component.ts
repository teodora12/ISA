import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../Service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  userRole: any;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userRole = this.userService.getLoggedUserType();
  }

}
