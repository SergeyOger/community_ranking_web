import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Course} from '../shared/Course';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) {
  }

  urlForCourses = '/api/courses';

  addCourse(form: FormGroup): Observable<Course> {
    return this.http.post<Course>(this.urlForCourses, form.value);
  }

  loadCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.urlForCourses);
  }

  deleteCourse(title: string): Observable<any> {
    return this.http.delete(this.urlForCourses + '/' + title);
  }

  getCourseByTitle(title: string): Observable<Course> {
    return this.http.get<Course>(this.urlForCourses + '/' + title);
  }
}
