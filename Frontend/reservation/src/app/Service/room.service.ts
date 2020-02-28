import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  path: string;

  constructor(private http: HttpClient) { }

  getRooms() {
    return this.http.get('/api/rooms');
  }

  createRoom(room) {
    return this.http.post('/api/rooms/create', room);
  }

  addRoomToHotel(hotelId: string, roomId: string) {
    this.path = hotelId + '/room/' + roomId;
    // @ts-ignore
    return this.http.post('/api/hotels/'.concat(this.path));
  }

  getRoom(id: string) {
    return this.http.get('/api/rooms/'.concat(id));
  }

  saveRoom(room: any) {
    return this.http.post('/api/rooms/save', room);
  }

  getNumberOfBeds() {
    return this.http.get('/api/rooms/numberOfBeds');
  }

}
