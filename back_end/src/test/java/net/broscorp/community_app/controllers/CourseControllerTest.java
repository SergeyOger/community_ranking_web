package net.broscorp.community_app.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import net.broscorp.community_app.controllers.CourseController;
import net.broscorp.community_app.model.Course;
import net.broscorp.community_app.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

  private static final String COURSES_URL = "/api/courses";
  private static final String FIRST_COURSE_TITLE = "Test course 1";
  private static final String FIRST_COURSE_DESCRIPTION = "Some test course 1";
  private static final boolean FIRST_COURSE_STATUS = true;
  private static final String SECOND_COURSE_TITLE = "Test course";
  private static final String SECOND_COURSE_DESCRIPTION = "Some test course";
  private static final boolean SECOND_COURSE_STATUS = false;
  @Mock
  CourseService courseService;
  @InjectMocks
  CourseController courseController;
  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
  }

  @Test
  void getCourses() throws Exception {

    // WITH
    Course course = new Course();
    course.setCourseTitle(FIRST_COURSE_TITLE);
    course.setCourseDescription(FIRST_COURSE_DESCRIPTION);
    course.setActive(FIRST_COURSE_STATUS);

    Course course1 = new Course();
    course1.setCourseTitle(SECOND_COURSE_TITLE);
    course1.setCourseDescription(SECOND_COURSE_DESCRIPTION);
    course1.setActive(SECOND_COURSE_STATUS);

    List<Course> courses = new ArrayList<>();
    courses.add(course);
    courses.add(course1);

    // WHEN
    when(courseService.getCourseList()).thenReturn(courses);

    // THEN
    mockMvc.perform(get(COURSES_URL)).andExpect(status().isOk());
    mockMvc.perform(get(COURSES_URL)).andExpect(content().contentType(APPLICATION_JSON));
    mockMvc.perform(get(COURSES_URL))
        .andExpect(jsonPath("$[0].courseTitle").value(FIRST_COURSE_TITLE));
    mockMvc.perform(get(COURSES_URL))
        .andExpect(jsonPath("$[0].courseDescription").value(FIRST_COURSE_DESCRIPTION));
    mockMvc.perform(get(COURSES_URL))
        .andExpect(jsonPath("$[0].active").value(FIRST_COURSE_STATUS));
    mockMvc.perform(get(COURSES_URL))
        .andExpect(jsonPath("$[1].courseTitle").value(SECOND_COURSE_TITLE));
    mockMvc.perform(get(COURSES_URL))
        .andExpect(jsonPath("$[1].courseDescription").value(SECOND_COURSE_DESCRIPTION));
    mockMvc.perform(get(COURSES_URL))
        .andExpect(jsonPath("$[1].active").value(SECOND_COURSE_STATUS));
  }

  @Test
  void getCourseByName() throws Exception {

    // WITH
    Course course = new Course();
    course.setCourseTitle(FIRST_COURSE_TITLE);
    course.setCourseDescription(FIRST_COURSE_DESCRIPTION);
    course.setActive(FIRST_COURSE_STATUS);

    // WHEN
    when(courseService.getCourseByName(FIRST_COURSE_TITLE)).thenReturn(course);

    // THEN
    mockMvc.perform(get(COURSES_URL + "/" + FIRST_COURSE_TITLE)).andExpect(status().isOk());
    mockMvc.perform(get(COURSES_URL + "/" + FIRST_COURSE_TITLE))
        .andExpect(content().contentType(APPLICATION_JSON));
    mockMvc.perform(get(COURSES_URL + "/" + FIRST_COURSE_TITLE))
        .andExpect(jsonPath("$.courseTitle").value(FIRST_COURSE_TITLE));
  }

  @Test
  void addCourse() throws Exception {

    // WITH
    ObjectMapper objectMapper = new ObjectMapper();

    Course course = new Course();
    course.setCourseTitle(SECOND_COURSE_TITLE);
    course.setCourseDescription(SECOND_COURSE_DESCRIPTION);
    course.setActive(SECOND_COURSE_STATUS);

    // WHEN
    when(courseService.saveCourse(course)).thenReturn(course);

    // THEN
    mockMvc
        .perform(
            post(COURSES_URL)
                .content(objectMapper.writeValueAsString(course))
                .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.courseTitle").value(SECOND_COURSE_TITLE));
  }

  @Test
  void deleteCourseById() throws Exception {

    // WITH

    // WHEN
    when(courseService.deleteCourseByName(SECOND_COURSE_TITLE)).thenReturn(true);

    // THEN
    mockMvc
        .perform(delete(COURSES_URL + "/" + SECOND_COURSE_TITLE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$").value("true"));
  }
}
