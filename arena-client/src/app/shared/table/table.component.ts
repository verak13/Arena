import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { UserRole } from 'src/app/model/user-role';
import { LogInService } from 'src/app/services/log-in.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnChanges {

  @Input() dataSource = [];
  @Input() columnsToDisplay = [];
  @Input() columnsToIterate = [];
  @Output() Delete = new EventEmitter<number>();
  @Output() Click = new EventEmitter<number>();
  @Output() DoubleClick = new EventEmitter<number>();
  @Output() ReservationsByEvent = new EventEmitter<number>();
  @Output() Details = new EventEmitter<number>();
  @Output() UpdateEvent = new EventEmitter<number>();
  @Output() CancelReservation = new EventEmitter<any>();
  role: String;
  constructor(private loginService: LogInService) {
    this.role = this.loginService.getRole() === UserRole.ROLE_USER ? 'user' : 'admin';
  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)){
        let vary = this.get(propName);
        vary = changes[propName].currentValue;
      }
    }
  }
  deleted(id: number){
    this.Delete.emit(id);
  }
  clicked(id: number){
    this.Click.emit(id);
  }
  doubleClicked(id: number){
    this.DoubleClick.emit(id);
  }
  reservationsByEvent(data: number){
    this.ReservationsByEvent.emit(data);
  }
  details(data: number){
    this.Details.emit(data);
  }
  updateEvent(data: number){
    this.UpdateEvent.emit(data);
  }
  cancelReservation(data: any){
    this.CancelReservation.emit(data);
  }
  get(element: string): string[]{
    switch (element) {
      case 'dataSource':
        return this.dataSource;
      case 'columnsToDisplay':
        return this.columnsToDisplay;
      default:
        return this.columnsToIterate;
    }
  }
}
