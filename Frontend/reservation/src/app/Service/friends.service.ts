import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {logging} from 'selenium-webdriver';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  constructor(private http: HttpClient) { }

  getFriendsByUser(username: string) {
    return this.http.get('api/friends/'.concat(username));
  }

  getAll() {
    return this.http.get('api/friends/all');
  }

  acceptFriendship(id: string) {
    return this.http.get('api/friends/accept/'.concat(id));
  }

  deleteFriendship(id: string) {
    return this.http.delete('api/friends/'.concat(id));
  }

  search(searchString: string, id: number) {
    return this.http.get('api/friends/search/'.concat(id.toString()).concat('/').concat(searchString));
  }

  sendFriendRequest(friendship: any) {
    return this.http.post('api/friends', friendship);
  }
}
