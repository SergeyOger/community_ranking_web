import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Course} from '../shared/Course';

@Component({
  selector: 'app-courses-page',
  templateUrl: './courses-page.component.html',
  styleUrls: ['./courses-page.component.css']
})
export class CoursesPageComponent implements OnInit {

  private coursesPath = '/api/courses';

  private courses: Course[];
  private displayedColumns: string[] = ['title', 'text', 'points'];

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getCourses();
  }

  private getCourses() {
    this.http.get<Course[]>(this.coursesPath).subscribe(
      courses => {
        this.courses = courses;
      },
      error => {
        alert(error.error);
      }
    );
  }

}
