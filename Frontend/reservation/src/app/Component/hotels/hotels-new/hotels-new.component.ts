import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {HotelService} from '../../../Service/hotel.service';
import {FormControl} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';
/// <reference types="@types/googlemaps" />
@Component({
  selector: 'app-hotels-new',
  templateUrl: './hotels-new.component.html',
  styleUrls: ['./hotels-new.component.css']
})
export class HotelsNewComponent implements OnInit {

  hotel: any;

  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;


  constructor(private hotelService: HotelService, private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, public toastr: ToastrManager, private router: Router) { }

  ngOnInit() {
    this.hotel = {name: '', address: {country: '', city: '', street: '', addressNumber: ''}, description: '',
                  averageRating: ''};

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
                this.hotel.address.country = comp.long_name;
              } else if (type === 'locality' || type === 'administrative_area_level_3') {
                this.hotel.address.city = comp.long_name;
              } else if (type === 'route') {
                this.hotel.address.street = comp.long_name;
              } else if (type === 'street_number') {
                this.hotel.address.addressNumber = comp.long_name;
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

          console.log(this.longitude);
          console.log(this.latitude);

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



  createHotel(): void {
    this.hotelService.createHotel(this.hotel).subscribe(
      hotel => {this.hotel = hotel;
      console.log('KREIRAN ' + hotel);
      alert('KREIRAN' + hotel);
      });

  }

}
