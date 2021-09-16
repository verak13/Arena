import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NavigationsModule } from './navigations/navigations.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { AuthModule } from './components/auth/auth.module';
import { UsersModule } from './components/users/users.module';
import { EventsModule } from './components/events/events.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { HttpAuthInterceptor } from './interceptors/http-auth.interceptor';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule,
    NavigationsModule,
    AuthModule,
    UsersModule,
    EventsModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true } ],
  bootstrap: [AppComponent]
})
export class AppModule { }
