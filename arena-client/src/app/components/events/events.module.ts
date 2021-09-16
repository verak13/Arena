import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ViewEventsComponent } from './view-events/view-events.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { EventComponent } from './event/event.component';
import { ViewReservationsComponent } from './view-reservations/view-reservations.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatDividerModule } from '@angular/material/divider';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { NgxMatMomentModule } from "@angular-material-components/moment-adapter";
import { MatTooltipModule } from '@angular/material/tooltip' 
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner'; 

@NgModule({
  declarations: [ViewEventsComponent, CreateEventComponent, EventComponent, ViewReservationsComponent],
  imports: [
    CommonModule,
    SharedModule,
    MatDividerModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    NgxMatTimepickerModule,
    NgxMatDatetimePickerModule,
    NgxMatMomentModule,
    MatTooltipModule,
    MatProgressSpinnerModule
  ]
})
export class EventsModule { }
