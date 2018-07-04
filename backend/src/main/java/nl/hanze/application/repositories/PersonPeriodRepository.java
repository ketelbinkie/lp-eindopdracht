package nl.hanze.application.repositories;

import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.domain.TeamPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonPeriodRepository extends JpaRepository<PersonPeriod, Integer> {

    List<PersonPeriod> findAllByTeamPeriodId(Integer id);

    PersonPeriod findByPersonId(int personId);

    List<PersonPeriod> findByTeamPeriod(TeamPeriod teamPeriod);


//    PersonPeriod findAllByTeamPeriodId(int id);

//    @Query("SELECT p FROM PersonPeriod p WHERE p.person = :person")
//    public List<PersonPeriod> find(Person person);
}