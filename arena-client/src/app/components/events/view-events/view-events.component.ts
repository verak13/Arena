import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserRole } from 'src/app/model/user-role';
import { EventsService } from 'src/app/services/events.service';
import { LogInService } from 'src/app/services/log-in.service';

@Component({
  selector: 'app-view-events',
  templateUrl: './view-events.component.html',
  styleUrls: ['./view-events.component.scss']
})
export class ViewEventsComponent implements OnInit {

  loader: boolean;
  page = 0;
  pageSize = 7;
  events = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};
  role: String;
  constructor(private loginService: LogInService,
    private eventsService: EventsService,
    private router: Router,
    public snackBar: MatSnackBar,) {
      this.role = this.loginService.getRole() === UserRole.ROLE_USER ? 'user' : 'admin';
    }

  ngOnInit(): void {
    this.loader = true;
    this.page = 0;
    this.eventsService.getEvents({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.events = result;
        this.loader = false;
      }
    );
  }

  onPagination(page: number) {
    this.loader = true;
    this.page = page;
    this.eventsService.getEvents({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.events = result;
        this.loader = false;
      }
    );
  }

  onDelete(event: number) {
    console.log(event);
  }

  details(event: number) {
    if (this.role === 'user') {
      this.router.navigate(['/user/view-event/' + event]);
    } else {
      this.router.navigate(['/admin/view-event/' + event]);
    }
  }

  updateEvent(event: number) {
    this.router.navigate(['/admin/update-event/' + event]);
  }

  reservationsByEvent(event: number) {
    this.router.navigate(['/admin/view-reservations-event/' + event]);
  }


}
