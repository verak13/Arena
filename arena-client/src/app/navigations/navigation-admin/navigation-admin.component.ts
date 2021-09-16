import { EventEmitter } from '@angular/core';
import { Output } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation-admin',
  templateUrl: './navigation-admin.component.html',
  styleUrls: ['./navigation-admin.component.scss']
})
export class NavigationAdminComponent implements OnInit {

  @Output() logOut = new EventEmitter<void>();

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  logOutUser(): void {
    this.logOut.emit();
  }

}
