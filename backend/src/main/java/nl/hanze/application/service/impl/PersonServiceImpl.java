package nl.hanze.application.service.impl;

import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.repositories.PersonPeriodRepository;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonPeriodRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonPeriodRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<PersonPeriod> findPersonPeriodByTeamPeriodId(Integer id) {
        return personRepository.findAllByTeamPeriodId(id);
    }

    @Override
    public List<PersonPeriod> findPersonP(int id) {
        return null;
    }
}
