import { Component, OnInit } from '@angular/core';
import {AirlineService} from '../../../../Service/airline.service';
import {FlightService} from '../../../../Service/flight.service';
import {UserService} from '../../../../Service/user.service';
import {ReservationService} from '../../../../Service/reservation.service';

@Component({
  selector: 'app-admin-airline',
  templateUrl: './admin-airline.component.html',
  styleUrls: ['./admin-airline.component.css']
})
export class AdminAirlineComponent implements OnInit {

  monthlyVisible: any;
  weeklyVisible: any;
  dailyVisible: any;
  userRole: any;
  user: any;
  airline: any;
  reservations: any;
  incomePeriod: number;
  income: number;
  // polarAreaChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales', 'Telesales', 'Corporate Sales'];
  // polarAreaChartData: number[] = [300, 500, 100, 40, 120];
  // polarAreaLegend: any = true;
  //
  // polarAreaChartType: any = 'polarArea';

  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabelsMonthly: string[] = ['0', '5', '10', '15', '20', '25', '30'];
  public barChartLabelsWeekly: string[] = ['1', '2', '3', '4', '5', '6', '7'];
  public barChartLabelsDaily: string[] = ['00:00', '04:00', '08:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00'];
  public barChartType: any = 'bar';
  public barChartLegend: any = true;

  public barChartDataMonthly: any[];
  public barChartDataWeekly: any[];
  public barChartDataDaily: any[];
  // public barChartDataMonthly: any[] = [
  //   {data: [65, 59, 80, 81, 56, 55, 40], label: 'Monthly income'}
  // ];
  // public barChartDataWeekly: any[] = [
  //   {data: [5, 9, 0, 8, 16, 5, 4], label: 'Weekly income'}
  // ];
  // public barChartDataDaily: any[] = [
  //   {data: [65, 59, 80, 81, 56, 55, 40, 10, 9], label: 'Daily income'}
  // ];
// ,
// {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}

  constructor(private airlineService: AirlineService, private flightService: FlightService, private userService: UserService,
              private reservationService: ReservationService) {
    this.monthlyVisible = true;
    this.weeklyVisible = false;
    this.dailyVisible = false;
    this.reservations = [];

    let data = [0, 0, 0, 0, 0, 0, 0];
    this.barChartDataMonthly = [{data: [0, 0, 0, 0, 0, 0, 0], label: 'Monthly sales'}];
    // this.barChartDataMonthly.data = data;
    this.barChartDataWeekly = [{data: [0, 0, 0, 0, 0, 0, 0], label: 'Weekly sales'}];
    // this.barChartDataWeekly.data = data;
    data = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    this.barChartDataDaily = [{data: [0, 0, 0, 0, 0, 0, 0, 0, 0], label: 'Daily sales'}];
    // this.barChartDataDaily.data = data;

    this.airline = {flights: [{from: {name: '', shortName: '', address: {}}, to: {name: '', shortName: '', address: {}}}]};
    this.incomePeriod = 0;
    this.income = 0;
  }

  ngOnInit() {

    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
        this.airlineService.getAirline(this.user.inChargeOf).subscribe( airline => {
          this.airline = airline;

          const now = new Date();

          this.reservationService.getReservationsByAirline(this.airline.id).subscribe( reservations => {
            this.reservations = reservations;
            for (const reservation of this.reservations) {
              const departure = new Date(reservation.dateCreated);
              if (departure.getUTCMonth() === now.getUTCMonth() &&
                departure.getUTCFullYear() === now.getUTCFullYear()) {
                for (const pass of reservation.flightReservation.passengersOnSeats) {
                  if (pass.seat.state === 'taken') {
                    for (const data of this.barChartDataMonthly) {
                      if (departure.getUTCDay() < 5) {
                        data.data[0] = data.data[0] + 1;
                      } else if (departure.getUTCDay() < 10) {
                        data.data[1] = data.data[1] + 1;
                      } else if (departure.getUTCDay() < 15) {
                        data.data[2] = data.data[2] + 1;
                      } else if (departure.getUTCDay() < 20) {
                        data.data[3] = data.data[3] + 1;
                      } else if (departure.getUTCDay() < 25) {
                        data.data[4] = data.data[4] + 1;
                      } else if (departure.getUTCDay() < 30) {
                        data.data[5] = data.data[5] + 1;
                      }
                    }
                    if (now.getUTCDay() - 7 <= departure.getUTCDay() && departure.getUTCDay() <= now.getUTCDay()) {
                      for (const data of this.barChartDataWeekly) {
                        if (now.getUTCDay() - 7 === departure.getUTCDay()) {
                          data.data[0] = data.data[0] + 1;
                        } else if (now.getUTCDay() - 6 === departure.getUTCDay()) {
                          data.data[1] = data.data[1] + 1;
                        } else if (now.getUTCDay() - 5 === departure.getUTCDay()) {
                          data.data[2] = data.data[2] + 1;
                        } else if (now.getUTCDay() - 4 === departure.getUTCDay()) {
                          data.data[3] = data.data[3] + 1;
                        } else if (now.getUTCDay() - 3 === departure.getUTCDay()) {
                          data.data[4] = data.data[4] + 1;
                        } else if (now.getUTCDay() - 2 === departure.getUTCDay()) {
                          data.data[5] = data.data[5] + 1;
                        } else {
                          data.data[6] = data.data[6] + 1;
                        }
                      }
                    }
                    if (now.getUTCDay() === departure.getUTCDay()) {
                      for (const data of this.barChartDataDaily) {
                        if (departure.getUTCHours() < 4 ) {
                          data.data[0] = data.data[0] + 1;
                        } else if (departure.getUTCHours() < 8) {
                          data.data[1] = data.data[1] + 1;
                        } else if (departure.getUTCHours() < 12) {
                          data.data[2] = data.data[2] + 1;
                        } else if (departure.getUTCHours() < 14) {
                          data.data[3] = data.data[3] + 1;
                        } else if (departure.getUTCHours() < 16) {
                          data.data[4] = data.data[4] + 1;
                        } else if (departure.getUTCHours() < 20) {
                          data.data[5] = data.data[5] + 1;
                        } else if (departure.getUTCHours() < 22) {
                          data.data[6] = data.data[6] + 1;
                        } else {
                          data.data[7] = data.data[7] + 1;
                        }
                      }
                    }
                  }

                }
              }
            }
          });

          // this.flightService.getFlights().subscribe(fli => {
          //   // @ts-ignore
          //   for (const f of fli) {
          //     console.log(f);
          //     const temp = f['airlineDto']['id'];
          //     if ( temp === this.airline.id) {
          //       for (const dest of f.destinations) {
          //         if (dest.description === 'departure') {
          //           f.fromDest = dest;
          //         } else if (dest.description === 'arrival') {
          //           f.toDest = dest;
          //         }
          //       }
          //       this.flights.push(f);
          //     }
          //   }
          // });

          // for (const flight of this.airline['flights']) {
          //
          // }
        });
      });
    }
  }

  calculateIncome() {
    const now = new Date();
    const to = new Date();
    to.setDate(to.getDate() - this.incomePeriod);

    this.income = 0;
    for (const reservation of this.reservations) {
      const departure = new Date(reservation.dateCreated);
      if (departure > to) {
        for (const pass of reservation.flightReservation.passengersOnSeats) {
          this.income = this.income + pass.seat.price;
        }
      }
    }

  }


  showMonthly() {
    this.monthlyVisible = true;
    this.weeklyVisible = false;
    this.dailyVisible = false;
  }

  showWeekly() {
    this.monthlyVisible = false;
    this.weeklyVisible = true;
    this.dailyVisible = false;
  }

  showDaily () {
    this.monthlyVisible = false;
    this.weeklyVisible = false;
    this.dailyVisible = true;
  }

  // events
  public chartClicked(e): void {
  }

  public chartHovered(e): void {
  }

}
