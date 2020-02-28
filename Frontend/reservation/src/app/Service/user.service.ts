import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient, private router: Router) {
  }


  login(user): any {
    return this.http.post('api/auth/login', user, {observe: 'response'}).pipe(map(response => response));

  }

  changePassword(passwordChanger: any) {
    return this.http.post('api/auth/change-password', passwordChanger);
  }

  register(user): any {
    return this.http.post('api/users/register', user);

  }

  activate(email: any): any {
    return this.http.put('api/users/register/activate', email);
  }

  getUserByUsername(username: string) {
    return this.http.get('api/users/'.concat(username));
  }

  updateUser(user: any) {
    return this.http.put('api/users', user);
  }

  isLoggedIn() {
    const user = JSON.parse(localStorage.getItem('loggedUser'));

    if (user === null) {
      return false;
    }
    const expired_date = new Date(new Date(parseInt(user.exp, 10) * 1000));
    const now_date = new Date();
    if ((expired_date.getDate() <= now_date.getDate()) &&
      (expired_date.getTime() <= now_date.getTime())) {
      this.logout();
      return false;
    }
    return true;

  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/logIn']);
  }


  getToken(): string {
    const currentUser = JSON.parse(localStorage.getItem('loggedUser'));
    if (currentUser !== null) {
      currentUser.token = currentUser.token.replace(':', '');
    }
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

  getLoggedUserType() {
  const user = JSON.parse(localStorage.getItem('loggedUser'));
    let userRole;
  if (user === null) {
    userRole = '';
  } else {
    for (const role of user.roles) {
      if (role.authority === 'ROLE_ADMIN') {
        userRole = 'ROLE_ADMIN';
      } else if (role.authority === 'ROLE_USER') {
        userRole = 'ROLE_USER';
      } else if (role.authority === 'ROLE_ADMIN_AIRLINE') {
        userRole = 'ROLE_ADMIN_AIRLINE';
      } else if (role.authority === 'ROLE_ADMIN_HOTEL') {
        userRole = 'ROLE_ADMIN_HOTEL';
      } else {
        userRole = 'ROLE_ADMIN_SERVICE';
      }
    }
  }
  return userRole;
}

}


