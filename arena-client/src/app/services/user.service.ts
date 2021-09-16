import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../model/user-model";
import { environment } from 'src/environments/environment';

const APIEndpoint = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getProfile(id: String): Observable<UserModel> {
    return this.http.get<UserModel>(APIEndpoint + '/users/' + id)
  }

  editProfile(user: UserModel): Observable<UserModel> {
    return this.http.put<UserModel>(APIEndpoint + '/users',
      user);
  }
}
