import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FlightService} from '../../../Service/flight.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {UserService} from '../../../Service/user.service';
import {FriendsService} from '../../../Service/friends.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {ReservationService} from '../../../Service/reservation.service';


@Component({
  selector: 'app-flight-ticket-reservation',
  templateUrl: './flight-ticket-reservation.component.html',
  styleUrls: ['./flight-ticket-reservation.component.css']
})
export class FlightTicketReservationComponent implements OnInit {

  selectedSeats: any;
  flight: any;
  seatsInRows: any;
  userRole: any;
  user: any;
  friends: any;
  invitedFriends: any;
  flightReservation: any;
  reservation: any;

  constructor(private currentRoute: ActivatedRoute, private flightService: FlightService, private userService: UserService,
              private friendsService: FriendsService, private toastr: ToastrManager, private reservationService: ReservationService,
              private router: Router) {
    this.flight = {seatArrangement: {seatRows: 0, seatColumns: 0}, seats: []};
    this.seatsInRows = [];
    this.selectedSeats = [];
    this.friends = [];
    this.invitedFriends = [];
    this.reservation = {userId: '', flightReservation: {}, carReservation: {}, roomReservation: {}};
    this.flightReservation = {flightId: '', userId: '', passengersOnSeats: []}; // {seat: {},passengerName: '', passengerLastName: '',
    // passengerPassport: '', passengerId: 0
  }

  ngOnInit() {

    this.userRole = this.userService.getLoggedUserType();
    this.currentRoute.params.subscribe(params => {
      const flightId = params['id'];
      this.flightReservation.flightId = flightId;
      this.flightService.getFlightAllAtributes(flightId).subscribe(flight => {
        this.flight = flight;
        let maxRowNumber = 0;
        for (const seat of this.flight.seats) {
          if (seat.seatRow > maxRowNumber) {
            maxRowNumber = seat.seatRow;
          }
        }
        for (let i = 0; i < maxRowNumber; i++) {
          const row = [];
          this.seatsInRows.push(row);
        }
        // for (let i = 0; i < this.flight.seatArrangement.seatRows; i++) {     //ne moze tako jer nece pratiti raspored aviona
        //   const row = [];                                                    // admin moze da menja sedista, brise dodaje
        //   this.seatsInRows.push(row);
        // }
        for (const seat of this.flight.seats) {
          const index = seat.seatRow - 1;
          this.seatsInRows[index].push(seat);
        }
        for (let i = 0; i < this.seatsInRows.length; i++) {         // sortiranje po kolonama
          const sorted = this.seatsInRows[i].sort((t1, t2) => {
            const name1 = t1.seatColumn;
            const name2 = t2.seatColumn;
            if (name1 > name2) {
              return 1;
            }
            if (name1 < name2) {
              return -1;
            }
            return 0;
          });
        }
      });
    });

    let userTemp = JSON.parse(localStorage.getItem('loggedUser'));

    this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
      this.user = user;
      userTemp = user;
      this.flightReservation.userId = userTemp.id;
      this.reservation.userId = userTemp.id;
      this.friendsService.getFriendsByUser(userTemp['username']).subscribe(allFriendshipsList => {
        let list;
        list = allFriendshipsList;
        for (const f of list) {
          if (f.accepted === true) {
            if (f.user2.username === userTemp.username) {    // ako je trenutni user1, dodaj user2 u prijatelje ako je prihvaceno
              this.friends.push(f.user1);
            } else {                 // ako je trenutni user1 dodaj user2 u prijatelje ako je prihvaceno
              this.friends.push(f.user2);
            }
          }
        }
      });
    });
  }

  seatSelected(seat: any) {
    let isFound = false;                            // da li se nalazi menju selektovanim sedistima
    for (let i = 0; i < this.selectedSeats.length; i++) {
      if (this.selectedSeats[i].id === seat.id) {
        isFound = true;

        let numOfSeatsWithNOPersons = 0;
        let currentSeatHasPerson = true;
        const seatsWithoutPassengersIds = [];
        const seatsWithPassengersIds = [];
        let currentSeatIdINReservation = -1;

        for (let j = 0; j < this.flightReservation.passengersOnSeats.length; j++) {
          if (this.flightReservation.passengersOnSeats[j].passengerId === 0 ||
            this.flightReservation.passengersOnSeats[j].passengerId === this.user.id) {
            numOfSeatsWithNOPersons++;                                                // broj sedista bez putnika
            seatsWithoutPassengersIds.push(j);
            if (this.flightReservation.passengersOnSeats[j].seat.id === seat.id) {    // da li je za trenutno sediste dodata osoba
              currentSeatHasPerson = false;
              currentSeatIdINReservation = j;                              //  cuva id sedista koje se brise, da ne prolazim opt kroz for
            }
          } else {
            seatsWithPassengersIds.push(j);
            if (this.flightReservation.passengersOnSeats[j].seat.id === seat.id) {
              currentSeatIdINReservation = j;
            }
          }
        }
        if (numOfSeatsWithNOPersons > 1 || currentSeatHasPerson) {         // ako ima vise slobodnih, i na trenutnom je
          // neka osoba obrisi to sediste
          if (currentSeatHasPerson) {     // prvo vratim osobu u friends i izbacim iz invites
            for (let k = 0; k < this.invitedFriends.length; k++) {
              if (this.invitedFriends[k].id === this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerId) {
                const tempFriend = this.invitedFriends[k];
                this.friends.push(tempFriend);
                this.invitedFriends.splice(k, 1);
                // this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);

                this.toastr.warningToastr('Friend invitation is canceled', 'Canceled friend invitation');
                break;
              }
            }
          } else {            // ako je broj praznih veci od 1
            if (this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerId === this.user.id) {
              // ako se brise mesto user-a koji pravi rezervaciju
              for (const id of seatsWithoutPassengersIds) {    // uzima id od svakog mesta bez invite-a
                if (id !== currentSeatIdINReservation) {
                  this.flightReservation.passengersOnSeats[id].passengerId = this.user.id;
                  this.flightReservation.passengersOnSeats[id].passengerName = this.user.name;

                  this.flightReservation.passengersOnSeats[id].passengerLastName = this.user.lastName;

                  this.flightReservation.passengersOnSeats[id].passengerPassport =
                    this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerPassport;
                }
              }
            }
          }
          this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);   // obrise i rezervaciju

        } else if (numOfSeatsWithNOPersons <= 1) {      // ako se nalazi samo jedno slobodno (za ovog koji rezervise)
          if (seatsWithPassengersIds.length > 0) {      // a ako ima friend invitation-a, brise se invitation umesto tog slobodnog
            const oldInvited = this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerId;
            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerId = this.user.id;
            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerName =
              this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerName;

            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerLastName =
              this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerLastName;

            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerPassport =
              this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerPassport;
            // premesta podatke putnika bez id-ja(ovog koji rezervise), a brise pod inviteovanog


            for (let k = 0; k < this.invitedFriends.length; k++) {
              if (this.invitedFriends[k].id === oldInvited) {
                const tempFriend = this.invitedFriends[k];
                this.friends.push(tempFriend);
                this.invitedFriends.splice(k, 1);
                // this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);

                this.toastr.warningToastr('Friend invitation is canceled', 'Canceled friend invitation');
              }
            }
          }
          this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);   // obrise i rezervaciju

        }

        this.selectedSeats.splice(i, 1);        // ako se nalazi(znaci da je sad unchecked, pa ga brise iz selektovanih
        break;
      }
    }
    if (!isFound) {
      let oneReservedSeat;
      if (this.selectedSeats.length === 0) {
        oneReservedSeat = {
          seat: seat, passengerName: this.user.name, passengerLastName: this.user.lastName,
          passengerPassport: '', passengerId: this.user.id
        };
      } else {
        oneReservedSeat = {seat: seat, passengerName: '', passengerLastName: '', passengerPassport: '', passengerId: 0};
      }
      this.selectedSeats.push(seat);


      this.flightReservation.passengersOnSeats.push(oneReservedSeat);
    }
  }

  inviteFriend(friend: any, index: number) {
    this.invitedFriends.push(friend);
    this.friends.splice(index, 1);
    for (let i = 0; i < this.flightReservation.passengersOnSeats.length; i++) {
      if (this.flightReservation.passengersOnSeats[i].passengerId === 0) {
        this.flightReservation.passengersOnSeats[i].passengerId = friend.id;
        this.flightReservation.passengersOnSeats[i].passengerName = friend.name;
        this.flightReservation.passengersOnSeats[i].passengerLastName = friend.lastName;
        break;
      }
    }
  }

  getReservedCar(carReservation: any) {
    this.reservation.carReservation = carReservation;
  }

  getReservedHotel(hotelReservation: any) {
    this.reservation.roomReservation = hotelReservation;
  }

  reserve() {
    this.reservation.flightReservation = this.flightReservation;
    if (this.flightReservation.passengersOnSeats.length > 0) {
      let isValid = true;
      for (const pass of this.flightReservation.passengersOnSeats) {
        if (pass.passengerName === '' || pass.passengerLastName === '' || pass.passengerPassport === '') {
          isValid = false;
        }
      }
      if (!isValid) {
        this.toastr.errorToastr('You have not filled required passenger data', 'Required fields are empty');
      } else {
        this.reservationService.createReservation(this.reservation).subscribe(res => {

          this.toastr.successToastr('Reservation successfully created', 'Reservation created');
          this.router.navigate(['/home']);
          const reserv = res['id'];
          this.reservationService.sendCreatedReservationEmail(reserv).subscribe( val => {
            console.log('Email sent');
          }, error1 => {console.log('Email not sent'); });
        });
      }
    } else {
      this.toastr.errorToastr('You have not selected any seats', 'No seats selected');
    }

  }

}
