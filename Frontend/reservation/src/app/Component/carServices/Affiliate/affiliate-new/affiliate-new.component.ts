import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';
import {AffiliateService} from '../../../../Service/affiliate.service';

@Component({
  selector: 'app-affiliate-new',
  templateUrl: './affiliate-new.component.html',
  styleUrls: ['./affiliate-new.component.css']
})
export class AffiliateNewComponent implements OnInit {


  affiliate: any;
  path: string;
  newAffiliateId: string;
  serviceId: string;
  public latitude: number;
  public longitude: number;

  public searchControl: FormControl;

  public zoom: number;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(private affiliateService: AffiliateService, private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone, public toastr: ToastrManager, private router: Router) { }

  ngOnInit() {
    this.affiliate = {name: '', address: {country: '', city: '', street: '', addressNumber: ''}};
    this.path = this.router.url;
    this.serviceId = this.path.substring(this.path.lastIndexOf('/') + 1);
    // set google maps defaults
    this.zoom = 4;
    this.latitude = 39.8282;
    this.longitude = -98.5795;

    // create search FormControl
    this.searchControl = new FormControl();

    // set current position
 //   this.setCurrentPosition();

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
                this.affiliate.address.country = comp.long_name;
              } else if (type === 'locality' || type === 'administrative_area_level_3') {
                this.affiliate.address.city = comp.long_name;
              } else if (type === 'route') {
                this.affiliate.address.street = comp.long_name;
              } else if (type === 'street_number') {
                this.affiliate.address.addressNumber = comp.long_name;
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

  createAffiliate(): void {
    this.affiliateService.createAffiliate(this.affiliate).subscribe(newAffiliate => {
      this.affiliate = newAffiliate;
      this.newAffiliateId = this.affiliate.id;
      this.addAffiliateToService();
      this.toastr.successToastr('Affiliate created', 'Success');
      // @ts-ignore
      this.router.navigate(['/carService', this.serviceId], {});
    });
  }


  addAffiliateToService(): void {
    console.log(this.serviceId);
    this.affiliateService.addAffiliateToCarService(this.serviceId, this.newAffiliateId).subscribe(status => {

    });
  }

}
