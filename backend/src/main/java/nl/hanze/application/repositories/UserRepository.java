package nl.hanze.application.repositories;

import nl.hanze.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameEquals(String userName);

    User deleteById(int id);

}

