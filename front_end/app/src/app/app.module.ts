import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {RegistrationFormComponent} from './registration-form/registration-form.component';
import {AppRoutingModule} from './routing/app-routing.module';
import {LoginFormComponent} from './login-form/login-form.component';
import {StudentPageComponent} from './student-page/student-page.component';
import {RouterModule} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {CoursesInfoComponent} from './courses-info/courses-info.component';
import {CompanyInfoComponent} from './company-info/company-info.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AdminPageComponent} from './admin-page/admin-page.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {TokenInterceptor} from './interceptor/token.interceptor';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {CoursesPageComponent} from './courses-page/courses-page.component';
import {MatTableModule} from '@angular/material/table';
import {MatListModule} from '@angular/material/list';
import {MatTabsModule} from '@angular/material/tabs';
import {MatExpansionModule} from "@angular/material/expansion";


@NgModule({
  declarations: [
    AppComponent,
    RegistrationFormComponent,
    LoginFormComponent,
    StudentPageComponent,
    HomeComponent,
    CoursesInfoComponent,
    CompanyInfoComponent,
    AdminPageComponent,
    ErrorPageComponent,
    CoursesPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatToolbarModule,
    MatButtonModule,
    AppRoutingModule,
    RouterModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    MatCardModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatListModule,
    MatTabsModule,
    MatExpansionModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
