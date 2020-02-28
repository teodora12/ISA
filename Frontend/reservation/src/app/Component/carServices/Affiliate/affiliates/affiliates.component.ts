import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {MapsAPILoader} from '@agm/core';
import {AffiliateService} from '../../../../Service/affiliate.service';
import {UserService} from '../../../../Service/user.service';

declare var google: any;
@Component({
  selector: 'app-affiliates',
  templateUrl: './affiliates.component.html',
  styleUrls: ['./affiliates.component.css']
})
export class AffiliatesComponent implements OnInit {

  affiliate: any;
  affiliateId: any;
  userRole: string;
  public latitude: number;
  public longitude: number;
  user: any;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private currentRoute: ActivatedRoute, private affiliateService: AffiliateService,
              private router: Router, private toastr: ToastrManager,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, private userService: UserService) {

    this.affiliate = {address: {}, carServiceId: ''};
    this.user = {inChargeOf: ''};
  }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => {
      this.affiliateId = params['id'];
      this.affiliateService.getAffiliate(this.affiliateId).subscribe(affiliate => {

        this.affiliate = affiliate;
        this.latitude = this.affiliate['address']['latitude'];
        this.longitude = this.affiliate['address']['longitude'];
      });
    });

    // set google maps defaults
    this.zoom = 8;
    this.latitude = 39.8282;
    this.longitude = -98.5795;

    // create search FormControl
    this.searchControl = new FormControl();

    // set current position
   //   this.setCurrentPosition();

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
                  this.affiliate['address']['country'] = comp.long_name;
                } else if (type === 'locality' || type === 'administrative_area_level_3') {
                  this.affiliate['address']['city'] = comp.long_name;
                } else if (type === 'route') {
                  this.affiliate['address']['street'] = comp.long_name;
                } else if (type === 'street_number') {
                  this.affiliate['address']['addressNumber'] = comp.long_name;
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

    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (userTemp !== null) {
      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
        this.user = user;
      });
    }
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

  saveAffiliate(): void {
    this.affiliateService.saveAffiliate(this.affiliate).subscribe(affiliate => {
      this.affiliate = affiliate;
      this.toastr.successToastr('Affiliate updated', 'Success');
      // @ts-ignore
      this.router.navigate(['/rentAcarServices'], {});
    });
  }





}
