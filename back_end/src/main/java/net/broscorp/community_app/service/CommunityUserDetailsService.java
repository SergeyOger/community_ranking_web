package net.broscorp.community_app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.community_app.model.User;
import net.broscorp.community_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommunityUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder bCryptPasswordEncoder;

  public List<User> getUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    log.debug("Received user list {}", users);
    return users;
  }

  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  public void saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(true);
    user.setRole("ROLE_USER");
    log.debug("Saved user {}", user);
    userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findUserByEmail(email);
    log.debug("Retrieved user {} by email {}", user, email);
    if (user != null) {
      List<GrantedAuthority> authorities = Collections.singletonList(
          new SimpleGrantedAuthority(user.getRole()));
      return buildUserForAuthentication(user, authorities);
    }
    throw new UsernameNotFoundException("Username not found");
  }

  private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), authorities);
  }

  public void deleteUserByEmail(String email) {
    userRepository.deleteByEmail(email);
  }
}
