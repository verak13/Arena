import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogInComponent } from './components/auth/log-in/log-in.component';
import { SignUpComponent } from './components/auth/sign-up/sign-up.component';
import { CreateEventComponent } from './components/events/create-event/create-event.component';
import { EventComponent } from './components/events/event/event.component';
import { ViewEventsComponent } from './components/events/view-events/view-events.component';
import { ViewReservationsComponent } from './components/events/view-reservations/view-reservations.component';
import { ViewProfileComponent } from './components/users/view-profile/view-profile.component';
import { AdminGuard } from './guards/admin.guard';
import { AuthGuard } from './guards/auth.guard';
import { LogInGuard } from './guards/log-in.guard';
import { UserGuard } from './guards/user.guard';

const routes: Routes = [
  {
  path: '',
    component: LogInComponent,
    canActivate: [LogInGuard]
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
    canActivate: [LogInGuard]
  },
  {
    path: 'admin/view-profile',
    component: ViewProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'user/view-profile',
    component: ViewProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    component: ViewEventsComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'user',
    component: ViewEventsComponent,
    canActivate: [UserGuard]
  },
  {
    path: 'admin/create-event',
    component: CreateEventComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/update-event/:id',
    component: CreateEventComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/view-event/:id',
    component: EventComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'user/view-event/:id',
    component: EventComponent,
    canActivate: [UserGuard]
  },
  {
    path: 'admin/view-reservations',
    component: ViewReservationsComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/view-reservations-event/:id',
    component: ViewReservationsComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'user/view-reservations',
    component: ViewReservationsComponent,
    canActivate: [UserGuard]
  },
  {
    path: '**',
    redirectTo: '/'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
