import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {User} from '../shared/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {
  hide = true;
  user = new User();

  constructor(private authService: AuthService, private router: Router) {
  }

  name = new FormControl('', Validators.required);
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.minLength(8)]);
  repeatedPassword = new FormControl('', [Validators.required, Validators.minLength(8)]);
  phone = new FormControl(
    '',
    [
      Validators.pattern('^(([\\+38]?)+([0-9]){10})$'),
      Validators.required]
  );
  repository = new FormControl(
    '',
    [
      Validators.pattern('^(https:\\/\\/github\\.com\\/)([\\w]+)(\\/)(([\\w]+)(-)?)+$'),
      Validators.required
    ]);


  ngOnInit() {
  }

  getPhoneErrorMessage() {
    return this.phone.hasError('required') ? 'You must enter a phone number' :
      this.phone.hasError('pattern') ? 'Not a valid phone number' :
        '';
  }

  getRepositoryErrorMessage() {
    return this.repository.hasError('required') ? 'You must enter repository address' :
      this.repository.hasError('pattern') ? 'Not a valid repository address' :
        '';
  }

  getEmailErrorMessage() {
    return this.email.hasError('required') ? 'You must enter email' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  getPasswordErrorMessage(validator) {
    return validator.hasError('required') ? 'You must enter password' :
      validator.hasError('minlength') ? 'Password length must be more than 8 chars' : '';
  }

  isFormValid(): boolean {
    return this.name.valid && this.email.valid
      && this.password.valid && this.repeatedPassword.valid
      && this.phone.valid && this.repository.valid;
  }

  registerUser() {
    this.authService.register(this.user);
  }

}
