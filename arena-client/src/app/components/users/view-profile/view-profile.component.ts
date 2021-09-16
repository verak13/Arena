import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {validateMatchPassword} from "../../../validator/custom-validator-match-password";
import {validateLength} from "../../../validator/custom-validator-zero-min-eight-length";
import {UserRole} from "../../../model/user-role";
import {LogInService} from "../../../services/log-in.service";
import {UserService} from "../../../services/user.service";
import {UserModel} from "../../../model/user-model";
import {AdminService} from "../../../services/admin.service";

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.scss']
})
export class ViewProfileComponent implements OnInit {

  form: FormGroup;
  role: String = "";
  id: number = -1;

  constructor(private fb: FormBuilder,
              public snackBar: MatSnackBar,
              private logInService: LogInService,
              private userService: UserService,
              private adminService: AdminService) {

    this.form = this.fb.group({
        email: ['', [Validators.required]],
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        password: [''],
        passwordConfirm: ['']
      },
      {
        validator: [validateMatchPassword('password', 'passwordConfirm'), validateLength('password')]
      });

    const currentRole = this.logInService.getRole();
    this.role = currentRole === UserRole.ROLE_USER ? 'user' : 'admin';
  }

  ngOnInit(): void {
    if (this.role === 'user') {
      this.userService.getProfile(JSON.parse(<string>localStorage.getItem('user')).id).toPromise().then(res => {
        this.form.patchValue({
          firstName: res.firstName,
          lastName: res.lastName,
          email: res.email
        });
        this.id = res.id;
      }, err => {
        this.snackBar.open("Server error: " + err, "Close", {duration: 2000});
      })
    } else {
      this.adminService.getProfile(JSON.parse(<string>localStorage.getItem('user')).id).toPromise().then(res => {
        this.form.patchValue({
          firstName: res.firstName,
          lastName: res.lastName,
          email: res.email
        });
        this.id = res.id;
      }, err => {
        this.snackBar.open("Server error: " + err, "Close", {duration: 2000});
      })
    }
  }

  submit(): void {
    if (this.role === 'user') {
      this.userService.editProfile(new UserModel(
        this.id,
        this.form.controls['email'].value,
        this.form.controls['firstName'].value,
        this.form.controls['lastName'].value,
        this.form.controls['password'].value.length == 0 ? "________" : this.form.controls['password'].value
      )).toPromise().then(() => {
        this.snackBar.open("Successfully changed profile information", "Close", {duration: 2000});
      }, err => {
        this.snackBar.open("Server error: " + err, "Close", {duration: 2000});
      })
    } else {
      this.adminService.editProfile(new UserModel(
        this.id,
        this.form.controls['email'].value,
        this.form.controls['firstName'].value,
        this.form.controls['lastName'].value,
        this.form.controls['password'].value.length == 0 ? "________" : this.form.controls['password'].value
      )).toPromise().then(() => {
        this.snackBar.open("Successfully changed profile information", "Close", {duration: 2000});
      }, err => {
        this.snackBar.open("Server error: " + err, "Close", {duration: 2000});
      })
    }

  }
}
