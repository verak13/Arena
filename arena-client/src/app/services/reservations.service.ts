import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReservationModel } from '../model/reservation-model';

const APIEndpoint = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  constructor(private http: HttpClient) { }

  getAllReservations(payload: any): Observable<any> {
    return this.http.get(APIEndpoint + '/reservations/all/by-page?page=' + payload.page + '&size=' + payload.size + '&sort=event.startDate,desc')
  }

  getMyReservations(payload: any): Observable<any> {
    return this.http.get(APIEndpoint + '/reservations/my/by-page?page=' + payload.page + '&size=' + payload.size + '&sort=event.startDate,desc')
  }

  getReservationsByEvent(payload: any): Observable<any> {
    return this.http.get(APIEndpoint + '/reservations/by-event/' + payload.eventId + '/by-page?page=' + payload.page + '&size=' + payload.size + '&sort=event.startDate,desc')
  }

  getReservation(payload: any): Observable<any> {
    return this.http.get(APIEndpoint + '/reservations/' + payload)
  }

  makeReservation(payload: ReservationModel): Observable<any> {
    return this.http.post(APIEndpoint + '/reservations/make-reservation', payload)
  }

  cancelReservation(payload: ReservationModel): Observable<any> {
    return this.http.post(APIEndpoint + '/reservations/cancel-reservation', payload)
  }

}
