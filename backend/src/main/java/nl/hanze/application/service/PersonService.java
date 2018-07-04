package nl.hanze.application.service;


import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.Person;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    //    List<PersonPeriod> findPersonPeriodByTeamPeriodId(Integer id);
    List<PersonPeriod> findPersonByTrainerPeriod(Integer trainerId) throws Exception;

    List<CombinedEnquete> findCombinedPersonEnqueteByPersonId(Integer personId);

    PersonEnquete findPersonEnqueteByPersonId(Integer personId);

    List<Person> findPersonByTeamName(String teamName);

    List<Person> findPersonOnAge(int age);
//    List<TeamPeriod> findPersonOnteam(String team);
}
