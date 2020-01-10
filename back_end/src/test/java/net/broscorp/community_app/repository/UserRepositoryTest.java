package net.broscorp.community_app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.broscorp.community_app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class UserRepositoryTest {

  private static final String USER_EMAIL = "test@test";
  private static final String PATH_TO_SCRIPT = "classpath:scripts/insert_into_users.sql";

  @Autowired
  UserRepository userRepository;

  @Test
  @Sql(
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = PATH_TO_SCRIPT)
  void findUserByEmail() {

    //WITH

    // WHEN
    User user = userRepository.findUserByEmail(USER_EMAIL);

    // THEN
    Assertions.assertNotNull(user);
  }

  @Test
  @Sql(
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = PATH_TO_SCRIPT)
  void deleteByEmail() {
    long beforeDeleteCounter = userRepository.count();

    // WHEN
    userRepository.deleteByEmail(USER_EMAIL);
    userRepository.deleteByEmail(USER_EMAIL);
    long afterDeleteCounter = userRepository.count();

    // THEN
    assertEquals(beforeDeleteCounter - 1, afterDeleteCounter);
  }
}