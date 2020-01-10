package net.broscorp.community_app.repository;

import net.broscorp.community_app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

  User findUserByEmail(String email);

  void deleteByEmail(String email);
}
