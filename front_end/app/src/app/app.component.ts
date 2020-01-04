import {Component} from '@angular/core';
import {AuthService} from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private service: AuthService) {
  }

  authenticated(): boolean {
    return this.service.isLoggedIn;
  }

  logout() {
    this.service.logout();
  }

  private isAdmin(): boolean {
    return this.service.isAdmin();
  }
}
