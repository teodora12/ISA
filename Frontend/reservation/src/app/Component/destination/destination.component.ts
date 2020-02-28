import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {DestinationService} from '../../Service/destination.service';
import {FormControl} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';
import {UserService} from '../../Service/user.service';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent implements OnInit {

  destinations: any;
  destination: any;
  userRole: any;

  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private destinationService: DestinationService, private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, public toastr: ToastrManager, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
      this.userRole = this.userService.getLoggedUserType();
      this.destination = {name: '', shortName: '', address : {country: '', city: '', street: '', addressNumber: '', latitude: '', longitude: ''}};
      this.destinationService.getDestinations().subscribe(destinations => {
      this.destinations = destinations;
    });
    // set google maps defaults
    this.zoom = 4;
    this.latitude = 39.8282;
    this.longitude = -98.5795;

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
                this.destination.address.country = comp.long_name;
              } else if (type === 'locality' || type === 'administrative_area_level_3' || type === 'administrative_area_level_2') {
                this.destination.address.city = comp.long_name;
              } else if (type === 'route') {
                this.destination.address.street = comp.long_name;
              } else if (type === 'street_number') {
                this.destination.address.addressNumber = comp.long_name;
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

          this.destination.address.latitude = this.latitude;
          this.destination.address.longitude = this.longitude;

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

  private createDestination(): void {
    this.destinationService.addDestination(this.destination).subscribe(destination => {
      this.destination = destination;
      this.toastr.successToastr('Destination created');
      window.location.reload();
     // this.router.navigate(['destinations']);
    });
  }
}
