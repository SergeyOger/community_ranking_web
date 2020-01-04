package net.broscorp.community_app.controllers.course_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

  private MockMvc mockMvc;

  @Mock CourseService courseService;
  @InjectMocks
  CourseController courseController;

  @BeforeEach
  void setup() {

    mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
  }

  @Test
  void getCourses() throws Exception {
    Course course = new Course();
    course.setCourseTitle("Test");
    course.setCourseDescription("Description");
    course.setActive(true);

    Course course1 = new Course();
    course1.setCourseTitle("Test1");
    course1.setCourseDescription("Description2");
    course1.setActive(true);

    List<Course> courses = new ArrayList<>();
    courses.add(course);
    courses.add(course1);

    when(courseService.getCourseList()).thenReturn(courses);

    mockMvc.perform(get("/courses")).andExpect(status().isOk());
    mockMvc.perform(get("/courses")).andExpect(content().contentType(APPLICATION_JSON));
    mockMvc.perform(get("/courses")).andExpect(jsonPath("$[0].courseTitle").value("Test"));
    mockMvc
        .perform(get("/courses"))
        .andExpect(jsonPath("$[1].courseDescription").value("Description2"));
  }

  @Test
  void getCourseByName() throws Exception {

    Course course = new Course();
    course.setCourseTitle("Test");
    course.setCourseDescription("Test");
    course.setActive(true);

    when(courseService.getCourseByName("Test")).thenReturn(course);

    mockMvc.perform(get("/courses/Test")).andExpect(status().isOk());
    mockMvc.perform(get("/courses/Test")).andExpect(content().contentType(APPLICATION_JSON));
    mockMvc.perform(get("/courses/Test")).andExpect(jsonPath("$.courseTitle").value("Test"));
  }

  @Test
  void addCourse() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();

    Course course = new Course();
    course.setCourseTitle("Test");
    course.setCourseDescription("Description");
    course.setActive(true);

    when(courseService.saveCourse(course)).thenReturn(course);

    mockMvc
        .perform(
            post("/courses")
                .content(objectMapper.writeValueAsString(course))
                .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.courseTitle").value("Test"));
  }

  @Test
  void deleteCourseById() throws Exception {

    when(courseService.deleteCourseByName("test")).thenReturn(true);
    mockMvc
        .perform(delete("/courses/test"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$").value("true"));
  }
}
