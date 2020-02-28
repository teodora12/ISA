import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {ToastrManager} from 'ng6-toastr-notifications';
import {AirlineService} from '../../../Service/airline.service';
import {FormControl} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {Router} from '@angular/router';
import {UserService} from '../../../Service/user.service';
/// <reference types="@types/googlemaps" />
@Component({
  selector: 'app-airlines-new',
  templateUrl: './airlines-new.component.html',
  styleUrls: ['./airlines-new.component.css']
})
export class AirlinesNewComponent implements OnInit {

  airline: any;
  userRole: any;
  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;


  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private airlineService: AirlineService, private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, public toastr: ToastrManager, private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {
    this.userRole = this.userService.getLoggedUserType();
    this.airline = {name: '', address: {country: '', city: '', street: '', addressNumber: ''}, description: ''};

    // set google maps defaults
    this.zoom = 4;
    this.latitude = 39.8282;
    this.longitude = -98.5795;

    // create search FormControl
    this.searchControl = new FormControl();

    // set current position
    this.setCurrentPosition();

    // load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      // @ts-ignore
      const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ['address']
      });
      autocomplete.addListener('place_changed', () => {
        this.ngZone.run(() => {
          // get the place result
          // @ts-ignore
          const place: google.maps.places.PlaceResult = autocomplete.getPlace();
          // verify result
          console.log(place);

          for (const comp of place.address_components) {
            for (const type of comp.types) {      // jer moze da ima vise tipova, npr [locality, political]
              if (type === 'country') {
                this.airline.address.country = comp.long_name;
              } else if (type === 'locality' || type === 'administrative_area_level_3') {
                this.airline.address.city = comp.long_name;
              } else if (type === 'route') {
                this.airline.address.street = comp.long_name;
              } else if (type === 'street_number') {
                this.airline.address.addressNumber = comp.long_name;
              }
            }
          }
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          // set latitude, longitude and zoom
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 12;
        });
      });
    });

  }

  private setCurrentPosition() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 12;
      });
    }
  }
  createAirline(): void {

    if (this.airline.address.country === '' || this.airline.address.city === '' || this.airline.address.street === '') {
      this.toastr.errorToastr('Country, city and street selection is required','Full address required');
    } else {
      this.airline.address.latitude = this.latitude;
      this.airline.address.longitude = this.longitude;


      this.airlineService.addAirline(this.airline).subscribe(airlineNew => {
        console.log(airlineNew);
        this.airline = airlineNew;
        this.toastr.successToastr('Airline created', 'Success');
        // @ts-ignore
        this.router.navigate(['/airlines'], {});
      });
    }
  }

}
