package net.broscorp.community_app.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

  @Mock
  CommunityUserDetailsService userDetailsService;
  @InjectMocks
  CourseService courseService;

  @Test
  void getCourseList() {
    when(userDetailsService.getUsers()).thenReturn(new ArrayList<>());
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courses")
        .accept(MediaType.APPLICATION_JSON)
        .
  }

  @Test
  void getCourseByName() {
  }

  @Test
  void saveCourse() {
  }

  @Test
  void updateCourseByName() {
  }

  @Test
  void deleteCourseByName() {
  }
}