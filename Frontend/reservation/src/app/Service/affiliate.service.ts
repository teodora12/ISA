import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AffiliateService {

  id: string;
  constructor(private http: HttpClient) { }


  getAffiliate(id: string) {
    return this.http.get('api/affiliates/'.concat(id));
  }

  saveAffiliate(affiliate: any) {
    return this.http.put('api/affiliates/update', affiliate);
  }

  createAffiliate(affiliate: any) {
    return this.http.post('api/affiliates/create', affiliate);
  }

  addAffiliateToCarService(serviceId: string, affiliateId: string) {

    this.id = serviceId + '/affiliate/' + affiliateId;
    console.log('api/carServices/'.concat(this.id));
    // @ts-ignore
    return this.http.post('api/carServices/'.concat(this.id));
  }

  deleteAffiliate(id: string) {
    return this.http.delete('api/affiliates/delete/'.concat(id));
  }
}
