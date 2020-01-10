package net.broscorp.community_app.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import net.broscorp.community_app.configuration.JwtTokenProvider;
import net.broscorp.community_app.service.CommunityUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class UserControllerTest {

  MockMvc mockMvc;

  @MockBean
  private CommunityUserDetailsService userService;

  @Test
  void getUsers() throws Exception {

    // WITH
    when(userService.getUsers()).thenReturn(new ArrayList<>());

    // WHEN

    // THEN
    mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

  }

  @Test
  void createUser() {
  }

  @Test
  void deleteUser() {
  }
}