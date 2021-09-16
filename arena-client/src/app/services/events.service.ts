import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { EventModel } from '../model/event-model';

const APIEndpoint = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private http: HttpClient) { }

  getEvents(payload: any): Observable<any> {
    return this.http.get(APIEndpoint + '/events/by-page?page=' + payload.page + '&size=' + payload.size + '&sort=startDate,desc')
  }

  getEvent(payload: any): Observable<any> {
    return this.http.get(APIEndpoint + '/events/' + payload)
  }

  createEvent(payload: EventModel): Observable<any> {
    return this.http.post(APIEndpoint + '/events', payload)
  }

  updateEvent(payload: EventModel): Observable<any> {
    return this.http.put(APIEndpoint + '/events', payload)
  }

  deleteEvent(payload: any): Observable<any> {
    return this.http.delete(APIEndpoint + '/events/' + payload)
  }

}
