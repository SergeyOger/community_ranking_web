import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationFormComponent} from '../registration-form/registration-form.component';
import {LoginFormComponent} from '../login-form/login-form.component';
import {StudentPageComponent} from '../student-page/student-page.component';
import {HomeComponent} from '../home/home.component';
import {CoursesInfoComponent} from '../courses-info/courses-info.component';
import {CompanyInfoComponent} from '../company-info/company-info.component';
import {AdminPageComponent} from '../admin-page/admin-page.component';
import {ErrorPageComponent} from '../error-page/error-page.component';
import {CoursesPageComponent} from '../courses-page/courses-page.component';
import {AuthGuard} from '../services/auth.guard';
import {AdminAuthGuard} from '../admin-page/admin-page.guard';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/home'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'registration',
    component: RegistrationFormComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'student',
    canActivate: [AuthGuard],
    component: StudentPageComponent
  },
  {
    path: 'courses',
    canActivate: [AuthGuard],
    component: CoursesInfoComponent
  },
  {
    path: 'course-list',
    canActivate: [AuthGuard],
    component: CoursesPageComponent
  },
  {
    path: 'company',
    component: CompanyInfoComponent
  },
  {
    path: 'admin',
    canActivate: [AdminAuthGuard],
    component: AdminPageComponent
  },
  {
    path: 'error',
    component: ErrorPageComponent
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
