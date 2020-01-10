package net.broscorp.community_app.controllers;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.community_app.model.Course;
import net.broscorp.community_app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/courses")
public class CourseController {

  private CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public List<Course> getCourses() {
    log.debug("Retrieving all courses");
    return courseService.getCourseList();
  }

  @GetMapping("/{courseName}")
  public Course getCourseByName(@PathVariable String courseName) {
    log.debug("Requested course name {}", courseName);
    return courseService.getCourseByName(courseName);
  }

  @PostMapping
  public Course addCourse(@RequestBody Course course) {
    log.debug("Received course {}", course);
    return courseService.saveCourse(course);
  }

  @PutMapping
  public Course updateCourse(@RequestBody Course course) {
    log.debug("Received course {}", course);
    return courseService.updateCourseByName(course);
  }

  @DeleteMapping("/{courseTitle}")
  public boolean deleteCourseById(@PathVariable String courseTitle) {
    log.debug("Deleted course {}", courseTitle);
    return courseService.deleteCourseByName(courseTitle);
  }
}
