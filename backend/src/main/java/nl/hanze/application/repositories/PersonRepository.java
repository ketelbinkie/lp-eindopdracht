package nl.hanze.application.repositories;

import nl.hanze.application.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllByDateOfBirthBetween(Date before, Date after);
}