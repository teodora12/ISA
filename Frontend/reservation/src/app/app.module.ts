import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './Component/home-page/home-page.component';
import { NavbarComponent } from './Component/navbar/navbar.component';
import { HomePageFlightsComponent } from './Component/home-page/home-page-flights/home-page-flights.component';
import { HomePageHotelsComponent } from './Component/home-page/home-page-hotels/home-page-hotels.component';
import { HomePageCarsComponent } from './Component/home-page/home-page-cars/home-page-cars.component';
import { AirlinesComponent } from './Component/airlines/airlines.component';
import { AirlinesNewComponent } from './Component/airlines/airlines-new/airlines-new.component';
import {AgmCoreModule} from '@agm/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ToastrModule} from 'ng6-toastr-notifications';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AirlinesProfileComponent } from './Component/airlines/airlines-profile/airlines-profile.component';
import { AirlinesProfileFlightsComponent } from './Component/airlines/airlines-profile/airlines-profile-flights/airlines-profile-flights.component';
import {TokenInterceptorService} from './Service/security/token-interceptor';
import {CanActivateService} from './Service/security/can-activate.service';
import { RegistrationComponent } from './Component/users/registration/registration.component';
import {LogInComponent} from './Component/users/log-in/log-in.component';
import { HotelsComponent } from './Component/hotels/hotels.component';
import { HotelsNewComponent } from './Component/hotels/hotels-new/hotels-new.component';
import { CarServiceNewComponent } from './Component/carServices/CarService/car-service-new/car-service-new.component';
import { AllCarServicesComponent } from './Component/carServices/CarService/all-car-services/all-car-services.component';
import { CarServiceProfileComponent } from './Component/carServices/CarService/car-service-profile/car-service-profile.component';
import { CarsComponent } from './Component/carServices/Car/cars/cars.component';
import { AffiliatesComponent } from './Component/carServices/Affiliate/affiliates/affiliates.component';
import { CarProfileComponent } from './Component/carServices/Car/car-profile/car-profile.component';
import { CarNewComponent } from './Component/carServices/Car/car-new/car-new.component';
import { AffiliateNewComponent } from './Component/carServices/Affiliate/affiliate-new/affiliate-new.component';
import { DestinationComponent } from './Component/destination/destination.component';
import { DestinationNewComponent } from './Component/destination/destination-new/destination-new.component';
import { HotelsProfileComponent } from './Component/hotels/hotels-profile/hotels-profile.component';
import { HotelsProfileRoomsComponent } from './Component/hotels/hotels-profile/hotels-profile-rooms/hotels-profile-rooms.component';
import { RoomsNewComponent } from './Component/hotels/rooms-new/rooms-new.component';
import { FlightsNewComponent } from './Component/flights/flights-new/flights-new.component';
import { NgSelectModule} from '@ng-select/ng-select';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FlightsProfileComponent } from './Component/flights/flights-profile/flights-profile.component';
import { FlightTicketReservationComponent } from './Component/flights/flight-ticket-reservation/flight-ticket-reservation.component';
import { MatCheckboxModule, MatButtonModule} from '@angular/material';
import {MatStepperModule} from '@angular/material/stepper';
import { ProfileComponent } from './Component/users/profile/profile.component';
import { FormForReservationComponent } from './Component/CarReservation/form-for-reservation/form-for-reservation.component';
import { ActivationComponent } from './Component/users/activation/activation.component';
import { ProfileFriendsComponent } from './Component/users/profile/profile-friends/profile-friends.component';
import {MatIconModule} from '@angular/material/icon';
import { ProfileHistoryComponent } from './Component/users/profile/profile-history/profile-history.component';
import { RoomReservationComponent } from './Component/room-reservation/room-reservation.component';
import { FlightInvitationComponent } from './Component/flights/flight-ticket-reservation/flight-invitation/flight-invitation.component';
import { ProfileUserInfoComponent } from './Component/users/profile/profile-user-info/profile-user-info.component';
import { FlightFastReservationComponent } from './Component/airlines/flight-fast-reservation/flight-fast-reservation.component';
import { FlightSeatsComponent } from './Component/flights/flights-profile/flight-seats/flight-seats.component';
import { AdminAirlineComponent } from './Component/users/profile/admin-airline/admin-airline.component';
import {ChartsModule} from 'ng2-charts';


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent,
    HomePageFlightsComponent,
    HomePageHotelsComponent,
    HomePageCarsComponent,
    AirlinesComponent,
    LogInComponent,
    RegistrationComponent,
    AirlinesNewComponent,
    AirlinesProfileComponent,
    AirlinesProfileFlightsComponent,
    HotelsComponent,
    HotelsNewComponent,
    DestinationComponent,
    DestinationNewComponent,
    HotelsProfileComponent,
    HotelsProfileRoomsComponent,
    RoomsNewComponent,
    FlightsNewComponent,
    FlightsProfileComponent,
    CarServiceNewComponent,
    AllCarServicesComponent,
    CarServiceProfileComponent,
    CarsComponent,
    AffiliatesComponent,
    CarProfileComponent,
    CarNewComponent,
    AffiliateNewComponent,
    FlightTicketReservationComponent,
    ProfileComponent,
    FormForReservationComponent,
    FlightTicketReservationComponent,
    ActivationComponent,
    ProfileFriendsComponent,
    ProfileHistoryComponent,
    RoomReservationComponent,
    ProfileHistoryComponent,
    FlightInvitationComponent,
    ProfileUserInfoComponent,
    FlightFastReservationComponent,
    FlightSeatsComponent,
    AdminAirlineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBaCRWKG19SlY8InIsNe8qZa-NvMuzB-VE',
      libraries: ['places']
    }),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    NgSelectModule,
    NgbModule,
    MatCheckboxModule,
    MatStepperModule,
    MatButtonModule,
    MatIconModule,
    ChartsModule,
    FormsModule
  ],
  providers: [
    CanActivateService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
