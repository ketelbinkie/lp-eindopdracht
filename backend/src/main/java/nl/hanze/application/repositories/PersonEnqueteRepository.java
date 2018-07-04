package nl.hanze.application.repositories;

import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonEnqueteRepository extends JpaRepository<PersonEnquete, Integer> {
    PersonEnquete findPersonEnqueteByPersonPeriod(PersonPeriod period);
}
