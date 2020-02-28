import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {HomePageComponent} from './Component/home-page/home-page.component';
import {HomePageFlightsComponent} from './Component/home-page/home-page-flights/home-page-flights.component';
import {HomePageHotelsComponent} from './Component/home-page/home-page-hotels/home-page-hotels.component';
import {HomePageCarsComponent} from './Component/home-page/home-page-cars/home-page-cars.component';
import {AirlinesComponent} from './Component/airlines/airlines.component';
import {AirlinesNewComponent} from './Component/airlines/airlines-new/airlines-new.component';
import {FormsModule} from '@angular/forms';
import {AirlinesProfileComponent} from './Component/airlines/airlines-profile/airlines-profile.component';
import {AirlinesProfileFlightsComponent} from './Component/airlines/airlines-profile/airlines-profile-flights/airlines-profile-flights.component';
import {LogInComponent} from './Component/users/log-in/log-in.component';
import {RegistrationComponent} from './Component/users/registration/registration.component';
import {HotelsComponent} from './Component/hotels/hotels.component';
import {HotelsNewComponent} from './Component/hotels/hotels-new/hotels-new.component';
import {DestinationComponent} from './Component/destination/destination.component';
import {HotelsProfileComponent} from './Component/hotels/hotels-profile/hotels-profile.component';
import {HotelsProfileRoomsComponent} from './Component/hotels/hotels-profile/hotels-profile-rooms/hotels-profile-rooms.component';
import {RoomsNewComponent} from './Component/hotels/rooms-new/rooms-new.component';
import {FlightsNewComponent} from './Component/flights/flights-new/flights-new.component';
import {FlightsProfileComponent} from './Component/flights/flights-profile/flights-profile.component';
import {CarServiceNewComponent} from './Component/carServices/CarService/car-service-new/car-service-new.component';
import {AllCarServicesComponent} from './Component/carServices/CarService/all-car-services/all-car-services.component';
import {CarServiceProfileComponent} from './Component/carServices/CarService/car-service-profile/car-service-profile.component';
import {AffiliatesComponent} from './Component/carServices/Affiliate/affiliates/affiliates.component';
import {CarProfileComponent} from './Component/carServices/Car/car-profile/car-profile.component';
import {CarNewComponent} from './Component/carServices/Car/car-new/car-new.component';
import {AffiliateNewComponent} from './Component/carServices/Affiliate/affiliate-new/affiliate-new.component';
import {FlightTicketReservationComponent} from './Component/flights/flight-ticket-reservation/flight-ticket-reservation.component';
import {FormForReservationComponent} from './Component/CarReservation/form-for-reservation/form-for-reservation.component';
import {ActivationComponent} from './Component/users/activation/activation.component';
import {ProfileComponent} from './Component/users/profile/profile.component';
import {ProfileFriendsComponent} from './Component/users/profile/profile-friends/profile-friends.component';
import {ProfileHistoryComponent} from './Component/users/profile/profile-history/profile-history.component';
import {RoomReservationComponent} from './Component/room-reservation/room-reservation.component';
import {FlightInvitationComponent} from './Component/flights/flight-ticket-reservation/flight-invitation/flight-invitation.component';
import {FlightFastReservationComponent} from './Component/airlines/flight-fast-reservation/flight-fast-reservation.component';
import {FlightSeatsComponent} from './Component/flights/flights-profile/flight-seats/flight-seats.component';
import {AdminAirlineComponent} from './Component/users/profile/admin-airline/admin-airline.component';



const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomePageComponent,
    children : [
      {path: 'flights', component: HomePageFlightsComponent},
      {path: 'hotels', component: HomePageHotelsComponent},
      {path: 'cars', component: HomePageCarsComponent}
    ]
  },
  {path: 'airlines', component: AirlinesComponent
  },
  {
    path: 'airlines/new', component: AirlinesNewComponent
  },
  {
    path: 'airlines/:id', component: AirlinesProfileComponent,
      children : [
        {path: 'flights', component: AirlinesProfileFlightsComponent},
        {path: 'fast-reservation', component: FlightFastReservationComponent}
      ]
  },
  {
    path: 'hotels', component: HotelsComponent
  },
  {
    path: 'hotels/new', component: HotelsNewComponent
  },
  {
    path: 'logIn', component: LogInComponent
  },
  {
    path: 'register', component : RegistrationComponent
  },
  {
    path: 'destinations', component: DestinationComponent
  },
  {
    path: 'flights/new/:id', component: FlightsNewComponent
  },
  {
    path: 'flights/:id', component: FlightsProfileComponent,
    children : [
      { path : 'seats', component: FlightSeatsComponent }
    ]
  },
  {
    path: 'flights/reservation/:id', component: FlightTicketReservationComponent,
    children : [
      { path : 'carReservation', component: FormForReservationComponent}
    ]
  },
  {
    path: 'hotels/:id', component: HotelsProfileComponent,
      children : [
        { path: 'rooms', component: HotelsProfileRoomsComponent }
      ]
  },
  {
    path: 'rooms/new/:id', component: RoomsNewComponent
  },
  {
    path: 'carService/new', component : CarServiceNewComponent
  },
  {
    path: 'rentAcarServices', component : AllCarServicesComponent
  },
  {
    path: 'carService/:id', component : CarServiceProfileComponent
  },
  {
    path: 'affiliate/:id', component : AffiliatesComponent
  },
  {
    path: 'car/:id', component : CarProfileComponent
  },
  {
    path: 'cars/new/:id', component : CarNewComponent
  },
  {
    path: 'affiliates/new/:id', component : AffiliateNewComponent
  },
  // {
  //   path: 'reserveCar', component: FormForReservationComponent
  // },
  {
    path: 'profile', component : ProfileComponent,
    children : [
      { path: 'friends', component: ProfileFriendsComponent },
      {path: 'history', component: ProfileHistoryComponent},
      { path: 'airline', component: AdminAirlineComponent }
    ]
  },
  {
    path: 'activate/:email', component: ActivationComponent
  },
  {
    path: 'accept/:id', component: FlightInvitationComponent
  },
  {
    path: 'reserveRoom', component: RoomReservationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes), FormsModule],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
