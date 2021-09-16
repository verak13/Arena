import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { EventsService } from 'src/app/services/events.service';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {

  form: FormGroup;
  eventId: any;
  loader: boolean;

  constructor(private fb: FormBuilder,
    public snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private eventsService: EventsService,
    private router: Router) {
      this.form = this.fb.group({
        name : ['', Validators.required],
        description : ['', Validators.required],
        parterPrice : [null],
        westPrice : [null],
        eastPrice : [null],
        northPrice : [null],
        southPrice : [null],
        vipPrice : [null],
        startDate : [null, Validators.required],
        endDate : [null, Validators.required],
        deadline : [null, Validators.required]
      });
      this.eventId = this.route.snapshot.paramMap.get('id');
    }

  ngOnInit(): void {
    console.log(this.eventId);
    this.loader = false;
    if (this.eventId != null) {
      this.loader = true;
      this.eventsService.getEvent(this.eventId).subscribe(
        result => {
          this.form.controls["name"].patchValue(result.name);
          this.form.controls["description"].patchValue(result.description);
          this.form.controls["parterPrice"].patchValue(result.parterPrice);
          this.form.controls["westPrice"].patchValue(result.westPrice);
          this.form.controls["eastPrice"].patchValue(result.eastPrice);
          this.form.controls["northPrice"].patchValue(result.northPrice);
          this.form.controls["southPrice"].patchValue(result.southPrice);
          this.form.controls["vipPrice"].patchValue(result.vipPrice);
          this.form.controls["startDate"].patchValue(result.startDate);
          this.form.controls["endDate"].patchValue(result.endDate);
          this.form.controls["deadline"].patchValue(result.deadline);
          this.loader = false;
        }
      );
    }
  }

  onValueChanged(key: string, value: any) {
    this.form.controls[key].patchValue(value.target.value.toISOString());
  }

  submit() {
    let payload = {
      "id": this.eventId == null ? 0 : Number(this.eventId),
      "name": this.form.value.name,
      "description": this.form.value.description,
      "parterPrice": this.form.value.parterPrice,
      "westPrice": this.form.value.westPrice,
      "eastPrice": this.form.value.eastPrice,
      "northPrice": this.form.value.northPrice,
      "southPrice": this.form.value.southPrice,
      "vipPrice": this.form.value.vipPrice,
      "startDate": this.form.value.startDate,
      "endDate": this.form.value.endDate,
      "deadline": this.form.value.deadline
    }
    console.log(payload);
    this.loader = true;
    if (this.eventId == null) {
      this.eventsService.createEvent(payload).subscribe(
        result => {
          this.loader = false;
          this.snackBar.open('Successfully added event!.', 'Ok', { duration: 2000 });
          this.form.reset();
        },
        error => {
          this.loader = false;
          this.snackBar.open(error.error, 'Ok', { duration: 2000 });
        }
      );
    } else {
      this.eventsService.updateEvent(payload).subscribe(
        result => {
          this.loader = false;
          this.snackBar.open('Successfully updated event!.', 'Ok', { duration: 2000 });
          this.router.navigate(['/admin']);
        },
        error => {
          this.loader = false;
          this.snackBar.open(error.error, 'Ok', { duration: 2000 });
        }
      );
    }
  }

}
