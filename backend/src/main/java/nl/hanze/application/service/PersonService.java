package nl.hanze.application.service;


import nl.hanze.application.domain.PersonPeriod;

import java.util.List;

public interface PersonService {

    List<PersonPeriod> findPersonPeriodByTeamPeriodId(Integer id);
    List<PersonPeriod> findPersonP(int id);

}
