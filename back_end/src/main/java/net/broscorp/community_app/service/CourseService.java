package net.broscorp.community_app.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.community_app.exceptions.CourseNotFoundException;
import net.broscorp.community_app.model.Course;
import net.broscorp.community_app.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseService {

  private CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> getCourseList() {
    List<Course> courses = new ArrayList<>();
    courseRepository.findAll().forEach(courses::add);
    log.debug("In getCourses() was returned {} courses", courses.size());
    return courses;
  }

  public Course getCourseByName(String courseName) {
    if (courseRepository.existsById(courseName)) {
      Course course = courseRepository.findById(courseName).get();
      log.debug("In getCourseByName() by {} was returned course {}", courseName, course);
    }
    throw new CourseNotFoundException();
  }

  public Course saveCourse(Course course) {
    Course savedCourse = courseRepository.save(course);
    log.debug("In saveCourse, requested course {}, saved course {}", course, savedCourse);
    return courseRepository.save(course);
  }

  public Course updateCourseByName(Course course) {
    Course updatedCourse = courseRepository.save(course);
    log.debug(
        "In updateCourse, received course is {} ang returned course is {}", course, updatedCourse);
    return updatedCourse;
  }

  public boolean deleteCourseByName(String courseName) {
    if (courseRepository.existsById(courseName)) {
      courseRepository.deleteById(courseName);
      log.debug("In deleteCourseById(), course with name {} was successfully deleted", courseName);
      return true;
    }
    throw new CourseNotFoundException();
  }
}
