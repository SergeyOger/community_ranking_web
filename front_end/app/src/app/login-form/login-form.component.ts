import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  hide = true;
  email = '';
  password = '';
  loginForm: FormGroup;
  showLoginError = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
  }


  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    });
  }

  getEmailErrorMessage() {
    return this.loginForm.get('email').hasError('required') ? 'You must enter email address' :
      this.loginForm.get('email').hasError('email') ? 'Not a valid email' :
        '';
  }

  getPasswordErrorMessage() {
    return this.loginForm.get('password').hasError('required') ? 'You must enter password' :
      this.loginForm.get('password').hasError('minlength') ? 'Password length must be more than 8 chars' : '';
  }

  isFormValid(): boolean {
    return this.loginForm.valid;
  }

  login() {
    this.showLoginError = !this.authService.login(this.loginForm.value);
  }
}
