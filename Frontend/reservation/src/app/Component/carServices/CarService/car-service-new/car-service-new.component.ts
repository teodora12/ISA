import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';
import {CarServiceService} from '../../../../Service/car-service.service';

@Component({
  selector: 'app-car-service-new',
  templateUrl: './car-service-new.component.html',
  styleUrls: ['./car-service-new.component.css']
})
export class CarServiceNewComponent implements OnInit {

  carService: any;
  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private carServiceService: CarServiceService, private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, public toastr: ToastrManager, private router: Router) {
  }

  ngOnInit() {
    this.carService = {name: '', address: {country: '', city: '', street: '', addressNumber: ''}, description: ''};

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

          for (const comp of place.address_components) {
            for (const type of comp.types) {      // jer moze da ima vise tipova, npr [locality, political]
              if (type === 'country') {
                this.carService.address.country = comp.long_name;
              } else if (type === 'locality' || type === 'administrative_area_level_3') {
                this.carService.address.city = comp.long_name;
              } else if (type === 'route') {
                this.carService.address.street = comp.long_name;
              } else if (type === 'street_number') {
                this.carService.address.addressNumber = comp.long_name;
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
  createCarService(): void {
    this.carService.address.latitude = this.latitude;
    this.carService.address.longitude = this.longitude;
    this.carServiceService.createCarService(this.carService).subscribe(newCarSevice => {
      this.carService = newCarSevice;
      this.toastr.successToastr('Rent a car service created', 'Success');
      // @ts-ignore
      this.router.navigate(['/rentAcarServices', {}], {});
    });
  }
}
