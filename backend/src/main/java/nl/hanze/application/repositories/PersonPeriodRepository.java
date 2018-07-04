package nl.hanze.application.repositories;

import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.domain.TeamName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonPeriodRepository extends JpaRepository<PersonPeriod, Integer> {

    List<PersonPeriod> findAllByTeamNameId(Integer id);
    List<PersonPeriod> findAllByPersonId(Integer id);
    PersonPeriod findByPersonId(int personId);


    List<PersonPeriod> findByTeamName(TeamName byName);
}