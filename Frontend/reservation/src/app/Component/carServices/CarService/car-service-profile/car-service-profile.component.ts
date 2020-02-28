import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {ToastrManager} from 'ng6-toastr-notifications';
import {MapsAPILoader} from '@agm/core';
import {CarServiceService} from '../../../../Service/car-service.service';
import {FormControl} from '@angular/forms';
import {AffiliateService} from '../../../../Service/affiliate.service';
import {UserService} from '../../../../Service/user.service';

declare var google: any;

@Component({
  selector: 'app-car-service-profile',
  templateUrl: './car-service-profile.component.html',
  styleUrls: ['./car-service-profile.component.css']
})

export class CarServiceProfileComponent implements OnInit {


  carService: any;
  carServiceId: any;
  userRole: string;
  user: any;
  admin: boolean;
  charge: any;
  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;
  constructor(private currentRoute: ActivatedRoute, private carServiceService: CarServiceService, private affiliateService: AffiliateService,
              private router: Router, private toastr: ToastrManager,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, private userService: UserService) {

    this.carService = {address: {latitude: '', longitude: '', country: '', city: '', street: '', addressNumber: ''}};
    this.user = { inChargeOf: ''};
  }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => {
      this.carServiceId = params['id'];
      this.carServiceService.getCarService(this.carServiceId).subscribe(carService => {
        this.carService = carService;
        this.latitude = this.carService['address']['latitude'];    // lokacija iz baze koju ucitava
        this.longitude = this.carService['address']['longitude'];
      });
    });

    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
      });
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
      if (this.userRole !== '') {
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
                  this.carService['address']['country'] = comp.long_name;
                } else if (type === 'locality' || type === 'administrative_area_level_3') {
                  this.carService['address']['city'] = comp.long_name;
                } else if (type === 'route') {
                  this.carService['address']['street'] = comp.long_name;
                } else if (type === 'street_number') {
                  this.carService['address']['addressNumber'] = comp.long_name;
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
      }
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

  saveCarService(): void {
    this.carServiceService.saveCarService(this.carService).subscribe(carService => {
      this.carService = carService;
      this.toastr.successToastr('Car service updated', 'Success');
      // @ts-ignore
      this.router.navigate(['/rentAcarServices'], {});
    });
  }



  affiliateProfile(affiliate: any): void {
    this.router.navigate(['/affiliate', affiliate.id]);
  }

  deleteAffiliate(affiliate): void {
    this.affiliateService.deleteAffiliate(affiliate.id).subscribe(status => {
      this.toastr.successToastr('Affiliate deleted', 'Success');
      this.router.navigate(['/rentAcarServices'], {});
    });
  }

}
