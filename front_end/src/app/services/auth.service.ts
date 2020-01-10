import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = '/api/auth/login';
  private registrationUlr = '/api/auth/register';
  isLoggedIn = false;
  redirectUrl: string;

  constructor(private http: HttpClient, private router: Router) {
  }

  login(form): boolean {
    this.http.post<any>(this.loginUrl, form)
    .subscribe(res => {
      if (res.token) {
        sessionStorage.setItem('token', res.token);
        sessionStorage.setItem('role', res.role);
        this.isLoggedIn = true;
        this.router.navigate(['home']);
      }
    }, (err) => {
      console.log(err);
    });
    return this.isLoggedIn;
  }

  logout() {
    sessionStorage.clear();
    this.isLoggedIn = false;
    this.router.navigateByUrl('/home');
  }

  register(user) {
    return this.http.post<any>(this.registrationUlr, user)
    .subscribe(res => {
      this.router.navigate(['login']);
    }, (err) => {
      console.log(err);
      alert(err.error);
    });
  }

  isAdmin(): boolean {
    return (sessionStorage.getItem('role') === 'ROLE_ADMIN');
  }
}
