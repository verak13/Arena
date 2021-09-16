import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationsComponent } from './navigations.component';
import { NavigationAdminComponent } from './navigation-admin/navigation-admin.component';
import { NavigationUserComponent } from './navigation-user/navigation-user.component';
import { NavigationNotLoggedInComponent } from './navigation-not-logged-in/navigation-not-logged-in.component';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import {AppRoutingModule} from "../app-routing.module";


@NgModule({
  declarations: [
    NavigationsComponent,
    NavigationAdminComponent,
    NavigationUserComponent,
    NavigationNotLoggedInComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    AppRoutingModule
  ],
  exports: [
    NavigationsComponent,
    NavigationAdminComponent,
    NavigationUserComponent,
    NavigationNotLoggedInComponent
  ]
})
export class NavigationsModule { }
