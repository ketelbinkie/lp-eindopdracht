package nl.hanze.application.service;


import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.Person;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.domain.TeamName;

import java.util.List;
import java.util.Set;

public interface PersonService {
    List<Person> findAll();

    List<Person> findAllTrainers();

    List<PersonPeriod> findPersonByTrainerPeriodAndTeam(Integer trainerId, int teamNameId);

    List<CombinedEnquete> findCombinedPersonEnqueteByPersonId(Integer personId);

    PersonEnquete findPersonEnqueteByPersonId(Integer personId);

    List<Person> findPersonByTeamName(String teamName);

    List<Person> findPersonOnAge(int age);

    Set<TeamName> findTeamsByTrainerPeriod(Integer trainerId);
}
