import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRole } from 'src/app/model/user-role';
import { EventsService } from 'src/app/services/events.service';
import { LogInService } from 'src/app/services/log-in.service';
import { ReservationsService } from 'src/app/services/reservations.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {

  event: any;
  rows: any;
  seats: any[];
  role: String;
  numColumns: number;
  numRows: number;
  cancelDisabled: boolean;
  makeDisabled: boolean;
  loader: boolean;

  constructor(public snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private eventsService: EventsService,
    private reservationsService: ReservationsService,
    private router: Router,
    private loginService: LogInService) { 
      this.role = this.loginService.getRole() === UserRole.ROLE_USER ? 'user' : 'admin';
      this.seats = [];
      this.rows = [];
      this.numColumns = 50;
      this.numRows = 40;
      this.cancelDisabled = true;
      this.makeDisabled = true;
  }

  ngOnInit(): void {
    this.loader = true;
    this.eventsService.getEvent(this.route.snapshot.paramMap.get('id')).subscribe(
      result => {
        console.log(result);
        this.event = result;
        const groups = this.event.seats.map((e, i) => { 
          return i % this.numColumns === 0 ? this.event.seats.slice(i, i + this.numColumns) : null; 
        }).filter(e => { return e; });
        this.rows = groups;
        console.log(result);
        this.loader = false;
      }
    );
  }

  onClick(event: any) {
    console.log(event)
    if (this.seats.includes(event.number)) {
        this.seats.forEach((element,index)=>{
          if(element==event.number) this.seats.splice(index,1);
        });
    } else {
        this.seats.push(event.number);
    }
    this.checkConflicts();
    console.log(this.seats)
  }

  checkConflicts() {
    let foundSelectedFree = false;
    for (let seatNum of this.seats) {
      for (let seat of this.event.seats) {
        if (seatNum == seat.number && seat.occupiedByMe == false) { 
          this.makeDisabled = false;
          foundSelectedFree = true;
        }
      }
    }
    if (!foundSelectedFree) {
      this.makeDisabled = true;
    }
    let foundSelectedMine = false;
    for (let seatNum of this.seats) {
      for (let seat of this.event.seats) {
        if (seatNum == seat.number && seat.occupiedByMe == true) { 
          this.cancelDisabled = false;
          foundSelectedMine = true;
        }
      }
    }
    if (!foundSelectedMine) {
      this.cancelDisabled = true;
    }
    if (!this.makeDisabled && !this.cancelDisabled) {
      this.makeDisabled = true;
      this.cancelDisabled = true;
    }
  }

  isSelected(seat: any) {
    return this.seats.includes(seat);
  }

  isDisabledSeat(seat: any) {
    if (seat.includes("PARTER")) return !this.event.parterPrice;
    if (seat.includes("WEST")) return !this.event.westPrice;
    if (seat.includes("EAST")) return !this.event.eastPrice;
    if (seat.includes("NORTH")) return !this.event.northPrice;
    if (seat.includes("SOUTH")) return !this.event.southPrice;
    if (seat.includes("VIP")) return !this.event.vipPrice;
  }

  isParter(seat: any) {
    return seat.includes("PARTER");
  }
  isWest(seat: any) {
    return seat.includes("WEST");
  }
  isEast(seat: any) {
    return seat.includes("EAST");
  }
  isNorth(seat: any) {
    return seat.includes("NORTH");
  }
  isSouth(seat: any) {
    return seat.includes("SOUTH");
  }
  isVip(seat: any) {
    return seat.includes("VIP");
  }

  getToolTipData(seat: any) {
    return seat.number
  }

  makeReservation() {
    let payload = {
      "eventId": Number(this.route.snapshot.paramMap.get('id')),
      "userEmail": this.loginService.getUserEmail(),
      "seatNums": this.seats
    }
    this.loader = true;
    this.reservationsService.makeReservation(payload).subscribe(
      result => {
        this.snackBar.open('You have made reservations for seats: ' + result, 'Ok', { duration: 2000 });
        this.router.navigate(['/user/view-reservations']);
        this.loader = false;
      },
      error => {
        this.snackBar.open(error.error, 'Ok', { duration: 2000 });
        this.loader = false;
      }
    );
  }

  cancelReservation() {
    let payload = {
      "eventId": Number(this.route.snapshot.paramMap.get('id')),
      "userEmail": this.loginService.getUserEmail(),
      "seatNums": this.seats
    }
    this.loader = true;
    this.reservationsService.cancelReservation(payload).subscribe(
      result => {
        this.snackBar.open('You have cancelled reservations for seats: ' + result, 'Ok', { duration: 2000 });
        this.router.navigate(['/user/view-reservations']);
        this.loader = false;
      },
      error => {
        this.snackBar.open(error.error, 'Ok', { duration: 2000 });
        this.loader = false;
      }
    );
  }

}