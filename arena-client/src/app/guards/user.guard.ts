import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {LogInService} from "../services/log-in.service";
import {UserRole} from "../model/user-role";

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(
    public auth: LogInService,
    public router: Router
  ) {
  }

  canActivate(): boolean {
    const role = this.auth.getRole();
    if (role !== UserRole.ROLE_USER) {
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }

}
