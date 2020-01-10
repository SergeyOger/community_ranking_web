package net.broscorp.community_app.controllers;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.community_app.model.User;
import net.broscorp.community_app.service.CommunityUserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

  private CommunityUserDetailsService userService;

  public UserController(CommunityUserDetailsService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getUsers() {
    log.debug("Retrieving all users");
    return userService.getUsers();
  }

  @PutMapping
  public void createUser(@RequestBody User user) {
    log.debug("Received user {}", user);
    userService.saveUser(user);
  }

  @DeleteMapping("/{email}")
  public void deleteUser(@PathVariable String email) {
    log.debug("Received user email {}", email);
    userService.deleteUserByEmail(email);
  }
}
