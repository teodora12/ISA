import { Component, OnInit } from '@angular/core';
import {ReservationService} from '../../../../Service/reservation.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';

@Component({
  selector: 'app-flight-invitation',
  templateUrl: './flight-invitation.component.html',
  styleUrls: ['./flight-invitation.component.css']
})
export class FlightInvitationComponent implements OnInit {

  reservation: any;
  constructor(private currentRoute: ActivatedRoute, private reservationService: ReservationService, private router: Router,
              private toastr: ToastrManager) { }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => {
      const resId = params['id'];
      this.reservationService.getInvitation(resId).subscribe( res => {
        this.reservation = res;
      });
    });
  }

  accept() {
    this.toastr.successToastr('Invitation accepted', 'Success');
    this.router.navigate(['/home']);
  }

  delete() {
    this.reservationService.deleteInvitation(this.reservation.id).subscribe( res => {
      this.toastr.warningToastr('Invitation deleted', 'Deleted');
      this.router.navigate(['/home']);
    });
  }

}
