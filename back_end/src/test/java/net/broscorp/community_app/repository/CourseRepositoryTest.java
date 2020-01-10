package net.broscorp.community_app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import net.broscorp.community_app.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class CourseRepositoryTest {

  private static final String COURSE_TITLE = "Test course 1";
  private static final String PATH_TO_SCRIPT = "classpath:scripts/inserts.sql";
  private static final String COURSE_DESCRIPTION = "Description for course 1";
  private static final boolean COURSE_STATUS = true;

  @Autowired
  CourseRepository courseRepository;

  @Test
  void testSaveStudentMethod() {

    // WITH
    Course course = new Course();
    course.setCourseTitle(COURSE_TITLE);
    course.setCourseDescription(COURSE_DESCRIPTION);
    course.setActive(COURSE_STATUS);
    long beforeInsertCount = courseRepository.count();

    // WHEN
    Course savedCourse = courseRepository.save(course);

    // THEN
    assertNotNull(savedCourse);
    assertEquals(beforeInsertCount + 1, courseRepository.count());
  }

  @Test
  @Sql(
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = PATH_TO_SCRIPT)
  void testGetAllStudentsMethod() {

    // WITH
    List<Course> students = new ArrayList<>();

    // WHEN
    courseRepository.findAll().forEach(students::add);

    //THEN
    assertEquals(Course.class, students.get(0).getClass());
  }

  @Test
  @Sql(
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = PATH_TO_SCRIPT)
  void testDeleteMethod() {

    // WITH
    long beforeDeleteCounter = courseRepository.count();

    // WHEN
    courseRepository.deleteById(COURSE_TITLE);
    long afterDeleteCounter = courseRepository.count();

    // THEN
    assertEquals(beforeDeleteCounter - 1, afterDeleteCounter);
  }
}
