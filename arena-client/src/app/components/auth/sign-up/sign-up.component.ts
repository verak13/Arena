import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LogInService} from "../../../services/log-in.service";
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage.service";
import {validateMatchPassword} from "../../../validator/custom-validator-match-password";
import {validateLength} from "../../../validator/custom-validator-zero-min-eight-length";
import {UserModel} from "../../../model/user-model";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  form: FormGroup;
  private fb: FormBuilder;

  constructor(
    fb: FormBuilder,
    public snackBar: MatSnackBar,
    private logInService: LogInService,
    public router: Router
  ) {
    this.fb = fb;
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
  }

  ngOnInit(): void {
  }

  submit() {
    const signUp = new UserModel(-1, this.form.value.email, this.form.value.firstName, this.form.value.lastName, this.form.value.password);

    this.logInService.signUp(signUp).subscribe(
      result => {

        this.snackBar.open('Successfully signed up!', 'Ok', {duration: 2000});
        this.router.navigate(['/']);
      },
      error => {
        this.snackBar.open('Error while signing up!', 'Ok', {duration: 2000});
      }
    );
  }
}
