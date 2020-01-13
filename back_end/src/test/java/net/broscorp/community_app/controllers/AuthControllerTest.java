package net.broscorp.community_app.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.broscorp.community_app.configuration.JwtTokenProvider;
import net.broscorp.community_app.model.AuthBody;
import net.broscorp.community_app.model.User;
import net.broscorp.community_app.repository.UserRepository;
import net.broscorp.community_app.service.CommunityUserDetailsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

  private static final String EMAIL = "test2@test";
  private static final String PASSWORD = "11111111a";
  private static final String ROLE = "ROLE_USER";
  private static final String TOKEN = "TOKEN_FOR_USER";
  private static ObjectMapper objectMapper;
  @MockBean
  Authentication authentication;
  @MockBean
  CommunityUserDetailsService userDetailsService;
  @MockBean
  private User user;
  @MockBean
  private UserRepository userRepository;
  @MockBean
  private JwtTokenProvider jwtTokenProvider;
  @MockBean
  private AuthenticationManager authenticationManager;
  @Autowired
  private MockMvc mockMvc;

  @BeforeAll
  static void init() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void loginWithCorrectCredentials() throws Exception {

    // WITH
    AuthBody authBody = new AuthBody();
    authBody.setEmail(EMAIL);
    authBody.setPassword(PASSWORD);

    when(user.getRole()).thenReturn(ROLE);
    when(authenticationManager.authenticate(any())).thenReturn(authentication);
    when(userRepository.findUserByEmail(EMAIL)).thenReturn(user);
    when(jwtTokenProvider.createToken(EMAIL, ROLE)).thenReturn(TOKEN);

    // WHEN

    // THEN
    mockMvc.perform(post("/api/auth/login").
        content(objectMapper.writeValueAsString(authBody))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());


  }

  @Test
  void loginWithWrongCredentials() throws Exception {

    // WITH
    AuthBody authBody = new AuthBody();
    authBody.setEmail(EMAIL);
    authBody.setPassword(PASSWORD);

    when(user.getRole()).thenReturn(ROLE);
    when(authenticationManager.authenticate(any()))
        .thenThrow(AuthenticationCredentialsNotFoundException.class);

    // WHEN

    // THEN
    mockMvc.perform(post("/api/auth/login").
        content(objectMapper.writeValueAsString(authBody))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isUnauthorized());
  }

  @Test
  void register() throws Exception {

    // WITH
    User user = new User();
    user.setEmail(EMAIL);
    user.setRole(ROLE);
    user.setPassword(PASSWORD);
    user.setActive(true);
    user.setFirstName("test");
    user.setLastName("test");
    user.setPhoneNumber("0000000000");
    user.setRepository("repo");

    // WHEN
    when(userDetailsService.findUserByEmail(EMAIL)).thenReturn(null);
    doNothing().when(userDetailsService).saveUser(user);

    // THEN
    mockMvc.perform(post("/api/auth/register").
        content(objectMapper.writeValueAsString(user))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());

  }
}