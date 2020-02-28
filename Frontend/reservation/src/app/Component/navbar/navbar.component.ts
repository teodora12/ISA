import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../Service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  userRole: String;
  constructor( private router: Router ) { }

  ngOnInit() {
    const user = JSON.parse(localStorage.getItem('loggedUser'));
    if (user === null) {
      this.userRole = '';
    } else {
      for (const role of user.roles) {
        if (role.authority === 'ROLE_ADMIN') {
          this.userRole = 'ROLE_ADMIN';
        }
      }
    }
  }


  logout() {
    localStorage.clear();
    this.userRole = '';
    this.router.navigate(['/logIn']);
  }


}
