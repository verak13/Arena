import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRole } from 'src/app/model/user-role';
import { LogInService } from 'src/app/services/log-in.service';
import { ReservationsService } from 'src/app/services/reservations.service';

@Component({
  selector: 'app-view-reservations',
  templateUrl: './view-reservations.component.html',
  styleUrls: ['./view-reservations.component.scss']
})
export class ViewReservationsComponent implements OnInit {

  loader: boolean;
  page = 0;
  pageSize = 7;
  reservations = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};
  role: String;
  constructor(private loginService: LogInService,
    private route: ActivatedRoute,
    private reservationsService: ReservationsService,
    private router: Router,
    public snackBar: MatSnackBar) {
    this.role = this.loginService.getRole() === UserRole.ROLE_USER ? 'user' : 'admin';
  }

  ngOnInit(): void {
    this.loader = true;
    this.page = 0;
    if (this.role === 'admin') {
      if (this.route.snapshot.paramMap.get('id') != null) {
        console.log(this.route.snapshot.paramMap.get('id'))
        this.reservationsService.getReservationsByEvent({ page: this.page, size: this.pageSize, eventId: this.route.snapshot.paramMap.get('id') }).subscribe(
          result => {
            this.reservations = result;
            this.loader = false;
          }
        );
      } else {
        this.reservationsService.getAllReservations({ page: this.page, size: this.pageSize }).subscribe(
          result => {
            this.reservations = result;
            this.loader = false;
          }
        );
      }
    } else {
      this.reservationsService.getMyReservations({ page: this.page, size: this.pageSize }).subscribe(
        result => {
          this.reservations = result;
          console.log(result)
          this.loader = false;
        }
      );
    }
  }

  onPagination(page: number) {
    this.loader = true;
    this.page = page;
    if (this.role === 'admin') {
      if (this.route.snapshot.paramMap.get('id') != null) {
        console.log(this.route.snapshot.paramMap.get('id'))
        this.reservationsService.getReservationsByEvent({ page: this.page, size: this.pageSize, eventId: this.route.snapshot.paramMap.get('id') }).subscribe(
          result => {
            this.reservations = result;
            this.loader = false;
          }
        );
      } else {
        this.reservationsService.getAllReservations({ page: this.page, size: this.pageSize }).subscribe(
          result => {
            this.reservations = result;
            this.loader = false;
          }
        );
      }
    } else {
      this.reservationsService.getMyReservations({ page: this.page, size: this.pageSize }).subscribe(
        result => {
          this.reservations = result;
          this.loader = false;
        }
      );
    }
  }

}
