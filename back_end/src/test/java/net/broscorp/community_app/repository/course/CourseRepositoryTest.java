package net.broscorp.community_app.repository.course;

import net.broscorp.community_app.model.Course;
import net.broscorp.community_app.repository.CourseRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CourseRepositoryTest {

  @Autowired
  CourseRepository courseRepository;

  @Test
  void testSaveStudentMethod() {
    // setup
    Course course = new Course();
    course.setCourseTitle("Title");
    course.setCourseDescription("Title");
    course.setActive(true);
    long beforeInsertCount = courseRepository.count();
    // execute
    Course savedCourse = courseRepository.save(course);
    // verify
    assertNotNull(savedCourse);
    assertEquals(beforeInsertCount + 1, courseRepository.count());
  }

  @Test
  @Sql(
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = "classpath:scripts/inserts.sql")
  void testGetAllStudentsMethod() {
    List<Course> students = new ArrayList<>();
    courseRepository.findAll().forEach(students::add);
    assertEquals(Course.class, students.get(0).getClass());
  }

  @Test
  @Sql(
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = "classpath:scripts/inserts.sql")
  void testDeleteMethod() {
    long beforeDeleteCounter = courseRepository.count();
    courseRepository.deleteById("Test course 1");
    long afterDeleteCounter = courseRepository.count();

    assertEquals(beforeDeleteCounter - 1, afterDeleteCounter);
  }
}
