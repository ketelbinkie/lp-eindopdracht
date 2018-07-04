package nl.hanze.application.repositories;

import nl.hanze.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameEquals(String userName);
    User findByPersonId(int id);
    User findByUsernameEqualsAndPasswordEquals(String username, String password);
    User deleteById(int id);
}

