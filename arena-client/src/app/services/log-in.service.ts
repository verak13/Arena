import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { StorageService } from "./storage.service";
import { Observable } from "rxjs";
import { LogIn, UserRole } from "../model/user-role";
import {UserModel} from "../model/user-model";
import { environment } from 'src/environments/environment';

const APIEndpoint = environment.APIEndpoint;


@Injectable({
  providedIn: 'root'
})
export class LogInService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private storageService: StorageService
  ) {
  }

  logIn(auth: LogIn): Observable<any> {
    return this.http.post(APIEndpoint + '/auth/log-in',
      auth, { headers: this.headers, responseType: 'json' });
  }

  activateAccount(id: number): Observable<any> {
    return this.http.post(APIEndpoint + '/auth/activate' + id, {});
  }

  signUp(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(APIEndpoint + '/auth/sign-up', user);
  }

  logOut(): void {
    this.storageService.clearStorage();
  }

  getRole(): UserRole {
    if (!localStorage.getItem('user')) {
      return UserRole.UNAUTHORIZED;
    }
    return JSON.parse(<string>localStorage.getItem('user')).role === 'ROLE_ADMINISTRATOR' ?
      UserRole.ROLE_ADMINISTRATOR : (JSON.parse(<string>localStorage.getItem('user')).role === 'ROLE_USER' ? UserRole.ROLE_USER : UserRole.UNAUTHORIZED);
  }

  getUserEmail(): string {
    return JSON.parse(localStorage.getItem('user')).email;
  }
}
