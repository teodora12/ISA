import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {MapsAPILoader} from '@agm/core';
import {HotelService} from '../../../Service/hotel.service';

@Component({
  selector: 'app-hotels-profile',
  templateUrl: './hotels-profile.component.html',
  styleUrls: ['./hotels-profile.component.css']
})
export class HotelsProfileComponent implements OnInit {

  hotel: any;
  hotelId: any;
  userRole: string;
  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private currentRoute: ActivatedRoute, private hotelService: HotelService,
              private router: Router, private toastr: ToastrManager,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone) {

    this.hotel = {address: {}};
  }

  ngOnInit() {

    this.currentRoute.params.subscribe(params => {
      this.hotelId = params['id'];
      this.hotelService.getHotel(this.hotelId).subscribe(hotel => {
        this.hotel = hotel;
        this.latitude = this.hotel['address']['latitude'];    // lokacija iz baze koju ucitava
        this.longitude = this.hotel['address']['longitude'];
      });
    });

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

    // set google maps defaults
    this.zoom = 8;
    this.latitude = 39.8282;
    this.longitude = -98.5795;

    // create search FormControl
    this.searchControl = new FormControl();

    // set current position
    //  this.setCurrentPosition();

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

          for (const comp of place.address_components) {
            for (const type of comp.types) {      // jer moze da ima vise tipova, npr [locality, political]
              if (type === 'country') {
                this.hotel['address']['country'] = comp.long_name;
              } else if (type === 'locality' || type === 'administrative_area_level_3') {
                this.hotel['address']['city'] = comp.long_name;
              } else if (type === 'route') {
                this.hotel['address']['street'] = comp.long_name;
              } else if (type === 'street_number') {
                this.hotel['address']['addressNumber'] = comp.long_name;
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

  saveHotel(): void {
    this.hotelService.saveHotel(this.hotel).subscribe(hotelNew => {

      this.hotel = hotelNew;

      this.router.navigate(['/hotels'], {});
    });
  }

}
