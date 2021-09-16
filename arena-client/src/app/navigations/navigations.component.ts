import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRole } from '../model/user-role';
import { LogInService } from '../services/log-in.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-navigations',
  templateUrl: './navigations.component.html',
  styleUrls: ['./navigations.component.scss']
})
export class NavigationsComponent implements OnInit {

  role: UserRole = UserRole.UNAUTHORIZED;
  admin: UserRole = UserRole.ROLE_ADMINISTRATOR;
  user: UserRole = UserRole.ROLE_USER;
  unauthorized: UserRole = UserRole.UNAUTHORIZED;

  constructor(private storageService: StorageService,
    private loginService: LogInService,
    private router: Router) {
    this.storageService.watchStorage().subscribe(() => {
      const user = JSON.parse(<string>localStorage.getItem('user'));
      if(user != null){
        this.role = user.role === 'ROLE_ADMINISTRATOR' ? UserRole.ROLE_ADMINISTRATOR : (user.role === 'ROLE_USER' ? UserRole.ROLE_USER : UserRole.UNAUTHORIZED);
      }
    });

    const user = JSON.parse(<string>localStorage.getItem('user'));
    if(user != null){
      this.role = user.role === 'ROLE_ADMINISTRATOR' ? UserRole.ROLE_ADMINISTRATOR : (user.role === 'ROLE_USER' ? UserRole.ROLE_USER : UserRole.UNAUTHORIZED);
    }
     }

  ngOnInit(): void {
  }

  logOut(): void {
    this.loginService.logOut();
    this.router.navigate(['/']);
    this.role = 0;
  }

}
