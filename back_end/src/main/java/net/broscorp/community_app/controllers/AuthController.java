package net.broscorp.community_app.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.community_app.configuration.JwtTokenProvider;
import net.broscorp.community_app.model.AuthBody;
import net.broscorp.community_app.model.User;
import net.broscorp.community_app.repository.UserRepository;
import net.broscorp.community_app.service.CommunityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CommunityUserDetailsService userDetailsService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody AuthBody authData) {
    log.debug("Received auth request {}", authData);
    try {
      String email = authData.getEmail();
      log.info("Received email {}", email);
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(email, authData.getPassword()));
      User user = userRepository.findUserByEmail(email);
      String token = jwtTokenProvider
          .createToken(email, user.getRole());
      Map<Object, Object> model = new HashMap<>();
      model.put("email", email);
      model.put("token", token);
      model.put("role", user.getRole());
      log.debug("Login user has email {} and role {}", user.getEmail(), user.getRole());
      return ok(model);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody User user) {
    log.debug("User registration data {}", user);
    if (userDetailsService.findUserByEmail(user.getEmail()) != null) {
      log.debug("Error saving user, user with email {} already exist", user.getEmail());
      throw new BadCredentialsException(
          "User with username: " + user.getEmail() + " already exists");
    }
    userDetailsService.saveUser(user);
    Map<Object, Object> model = new HashMap<>();
    model.put("message", "User registered successfully");
    return ok(model);
  }
}